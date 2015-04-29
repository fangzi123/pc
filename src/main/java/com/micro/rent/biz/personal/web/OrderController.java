package com.micro.rent.biz.personal.web;

import com.micro.rent.biz.base.service.RegionService;
import com.micro.rent.biz.enum_.ERenovation;
import com.micro.rent.biz.enum_.EWeekDayType;
import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.biz.personal.service.OrderService;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.biz.weixin.service.AuthService;
import com.micro.rent.biz.weixin.service.GetAccessTokenService;
import com.micro.rent.biz.weixin.service.SendTemplateMessageService;
import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.support.DateUtil;
import com.micro.rent.common.support.EmailUtil;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.common.web.service.SmsService;
import com.micro.rent.common.web.service.impl.SmsServiceImpl;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.personal.Order;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dell
 * @Description:
 * @date 2014-9-9
 */
@Controller
@RequestMapping("/rental/order")
public class OrderController extends _BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private GetAccessTokenService getAccessTokenService;
    @Autowired
    private SendTemplateMessageService sendTemplateMessageService;

//	@RequestMapping("/queryList")
//	public ModelAndView queryOrderList(HttpServletRequest request,
//			@RequestParam(value = "code", required = false) String code) {
//		ModelAndView mv = createMAV("personal/order_list");
//
//		String weixinId = SessionUtil.getOpenId(request, authService);
//		log.debug("weixinId is " + weixinId);
//		weixinId = "wx111111"; // TEST ONLY
//		List<Order> resultList = orderService.queryOrderList(weixinId);
//		mv.addObject("resultList", resultList);
//		mv.addObject("weixinId", weixinId);
//		return mv;
//	}

    @RequestMapping("/myList")
    public ModelAndView queryOrderHouseList(HttpServletRequest request,
                                            @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("personal/personal");
        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});
        log.debug("weixinId is " + openid);

        List<HouseVo> resultList = orderService.queryOrderHouseList(openid);
//		if (resultList == null) {
//			// throw new BizException("请检查定位设置是否打开");
//			// 返回提示页面，提示用户打开位置定位
//			mv = createMAV("myrent/note");
//			return mv;
//		}
        mv.addObject("resultList", resultList);
        mv.addObject("weixinId", openid);
        mv.addObject("type", "o");
        return mv;
    }

    /**
     * 预约
     *
     * @param request
     * @return
     */
    @RequestMapping("preOrder")
    public ModelAndView preOrder(HttpServletRequest request) {
        ModelAndView mv = createMAV("personal/pre_order");

        String houseId = this.findStringParameterValue(request, "houseId");
        String projectId = this.findStringParameterValue(request, "projectId");
        String weixinId = this.findStringParameterValue(request, "weixinId");
        String orderDate = this.findStringParameterValue(request, "orderDate");

        mv.addObject("houseId", houseId);
        mv.addObject("projectId", projectId);
        mv.addObject("weixinId", weixinId);
        mv.addObject("orderDate", orderDate);

        return mv;
    }

    /**
     * 获取预约验证码
     *
     * @param request
     */
    @RequestMapping("get_verifyOrder")
    public void getVerifyCode(HttpServletRequest request, ModelMap modelMap) {
        // 取得请求中的预约电话号码
        String orderTel = this.findStringParameterValue(request, "orderTel");
        if (orderTel == null || orderTel == ""
                || !NumberUtils.isDigits(orderTel)) {
            modelMap.put("success", false);
            modelMap.put("message", "请输入正确的手机号！");
        } else {
            // 生成短信验证码
            try {
                WordGenerator wordGenerator = new RandomWordGenerator(
                        ConfigUtil.getConfig("verifycode.acceptchars"));
                String verifyCode = wordGenerator.getWord(4,
                        Locale.getDefault());
                // 将生成的短信验证码保存进Session
                request.getSession().setAttribute(orderTel, verifyCode);

                // 调用短信平台接口发送验证码
                if (sendVerifyCodeMessage(orderTel, verifyCode)) {
                    modelMap.put("success", true);
                    modelMap.put("verifyCode", verifyCode);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("message", "验证码取得失败，请重新获取！");
                }
            } catch (Exception e) {
                logger.error("验证码备选字符读取失败！", e);
                modelMap.put("success", false);
                modelMap.put("message", "验证码取得失败，请重新获取！");
            }
        }
    }

    /**
     * 保存预约信息
     *
     * @param request
     * @param order
     * @param modelMap
     */
    @RequestMapping("add_preOrder")
    public void addPreOrder(HttpServletRequest request, Order order,
                            ModelMap modelMap) {
        // 预约者电话
        String telephone = this.findStringParameterValue(request, "orderTel");
        // 验证码
        String verifyCode = this.findStringParameterValue(request, "verifyCode");

        if (StringUtils.equals(verifyCode, (String) request.getSession().getAttribute(telephone))) {
            // 房屋ID
            String houseId = this.findStringParameterValue(request, "houseId");
            // 预约者微信ID
            order.setWeixinId(StringUtils.defaultIfEmpty(order.getWeixinId(), "weixinId"));
            // 公寓房源编号
            order.setHouseId(houseId);
            // 预约日期
            String orderDate = this.findStringParameterValue(request, "orderDate");
            order.setOrderDate(orderDate);
            // 联系电话
            order.setTelephone(telephone);

            // 检查该微信用户是否已预约了该房源
            int count = orderService.queryOrderCountByHouseIdAndWeixinIdAndOrderDate(order.getWeixinId(), houseId, orderDate);
            if (count == 0) {
                // 保存预约信息
                orderService.addPreOrder(order);
                // 清空Session中保存的该手机号的验证码
                request.getSession().removeAttribute(telephone);

                // 取得预约房源的信息
                HouseWrapVo houseInfo = houseService
                        .findHouseInfoByHouseId(houseId);
                // 房屋描述信息
                StringBuilder houseDesc = new StringBuilder();
                // 小区名称+楼号+房间数
                houseDesc.append(houseInfo.getCommunityName()).append("  ");
                if (StringUtils.isNotBlank(houseInfo.getBuildingNo())) {
                    houseDesc.append(houseInfo.getBuildingNo()).append("号楼");
                }
                if (StringUtils.isNotBlank(houseInfo.getLayout())) {
                    if (ERenovation.BAY.getCode().equals(houseInfo.getLayout())) {
                        houseDesc.append("开间");
                    } else {
                        houseDesc.append(houseInfo.getLayout()).append("室");
                    }
                }
                if (StringUtils.isNotBlank(houseInfo.getUseArea())) {
                    houseDesc.append("  ");
                    houseDesc.append(houseInfo.getUseArea().replaceAll("\\.\\d*$", "")).append("平米");
                }
                if (houseInfo.getLongPrice() != null) {
                    houseDesc.append("  ");
                    houseDesc.append(houseInfo.getLongPrice().toPlainString().replaceAll("\\.\\d*$", "")).append("元/月");
                }

                // 编辑地址
                String houseAddress = houseInfo.getStreet();

                DateFormat dateFormat = new SimpleDateFormat(
                        Constants.DATE_FORMAT_NO_SEPARATOR);
                StringBuilder orderTimeDisplayBul = new StringBuilder();
                try {
                    Date date = dateFormat.parse(orderDate);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int weedDay = calendar.get(Calendar.DAY_OF_WEEK);
                    orderTimeDisplayBul.append(DateUtil.getFmtDate(orderDate,
                            Constants.DATE_FORMAT_NO_SEPARATOR, "yyyy年MM月dd日"));
                    orderTimeDisplayBul.append("  ").append(
                            EWeekDayType.getNameByCode(weedDay));
                } catch (ParseException e) {
                    logger.error("预约日期格式解析错误", e);
                }

                String orderDateDisplay = orderTimeDisplayBul.toString();
                modelMap.put("success", true);
                modelMap.put("description", houseDesc.toString());
                modelMap.put("houseTel", houseInfo.getTelephone());
                modelMap.put("houseAdd", houseAddress);
                modelMap.put("orderDate", orderDateDisplay);

                // TODO 向用户微信发送预约消息
//				ServletContext sevletContext = request.getSession()
//						.getServletContext();
//				// 获取微信access_token
//				String accessToken = (String) sevletContext
//						.getAttribute(Constants.WEIXIN_ACCESS_TOKEN);
//				// 尝试从application context中取得access_token信息
//				Integer validSecond = (Integer) sevletContext
//						.getAttribute(Constants.WEIXIN_ACCESS_TOKEN_VALID_TIME);
//				Long accessTokenCallTime = (Long) sevletContext
//						.getAttribute(Constants.ACCESS_TOKEN_CALL_TIME);
//				// 获取access_token失败标志
//				boolean getAccessTokenFailFlag = false;
//				// 判定application context范围内的微信access_token信息是否存在且是否有效
//				if (StringUtils.isEmpty(accessToken)
//						|| (System.currentTimeMillis() - accessTokenCallTime) <= validSecond) {
//					// 不存在或者已失效
//					String accessTokenInfo = getAccessTokenService.getAccessToken();
//					if (StringUtils.isEmpty(accessTokenInfo)) {
//						log.error("微信access_token获取失败！");
//						getAccessTokenFailFlag = true;
//					} else {
//						int underScoreIndex = accessTokenInfo
//								.indexOf(GetAccessTokenService.ACCESS_TOKEN_INFO_SEPERATOR);
//						validSecond = Integer.valueOf(accessTokenInfo.substring(0,
//								underScoreIndex));
//						accessToken = accessTokenInfo
//								.substring(underScoreIndex + 1);
//						accessTokenCallTime = System.currentTimeMillis();
//
//						// 将取得的access_token信息保存至application context范围内
//						sevletContext.setAttribute(Constants.WEIXIN_ACCESS_TOKEN,
//								accessToken);
//						sevletContext.setAttribute(
//								Constants.WEIXIN_ACCESS_TOKEN_VALID_TIME,
//								validSecond);
//						sevletContext.setAttribute(
//								Constants.ACCESS_TOKEN_CALL_TIME,
//								accessTokenCallTime);
//					}
//				}
//
//				if (!getAccessTokenFailFlag
//						&& sendOrderMessageToWeixinUser(accessToken,
//								order.getWeixinId(), orderDateDisplay,
//								houseDesc.toString(), houseInfo.getTelephone())) {
//					log.error("预约微信发送失败！[clientTel:" + telephone + ", orderTime:"
//							+ orderDateDisplay + ", houseAddress:" + houseAddress
//							+ ", houseTel:" + houseInfo.getTelephone() + "]");
//				}

                if (!sendOrderMessageToCusetomerService(telephone,
                        orderDateDisplay, houseDesc.toString(),
                        houseInfo.getTelephone(), houseInfo.getBrandName())) {
                    log.error("预约邮件发送失败！[clientTel:" + telephone + ", orderTime:"
                            + orderDateDisplay + ", houseAddress:" + houseAddress
                            + ", houseTel:" + houseInfo.getTelephone() + "]");
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "已预约过在您指定的日期看该房源！");
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("message", "输入的验证码有误，请重新输入！");
        }
    }

    @RequestMapping("move_to_confirm_order")
    public ModelAndView moveToConfirmOrder(HttpServletRequest request) {
        ModelAndView mv = createMAV("personal/confirm_order");
        // 预约信息
        mv.addObject("projectDesc",
                this.findStringParameterValue(request, "description"));
        // 联系方式
        mv.addObject("telephone",
                this.findStringParameterValue(request, "houseTel"));
        // 地址
        mv.addObject("communityName",
                this.findStringParameterValue(request, "houseAdd"));
        // 看房时间
        String orderDate = this.findStringParameterValue(request, "orderDate");
        try {
            if (orderDate != null)
                mv.addObject("orderDate", DateUtil.getFmtDate(orderDate,
                        Constants.DATE_FORMAT_NO_SEPARATOR, "yyyy年MM月dd日"));
        } catch (ParseException e) {
            logger.error("预约日期格式解析错误", e);
            mv.addObject("orderDate", orderDate);
        }
        return mv;
    }

    @RequestMapping("reserve")
    public ModelAndView reserve(HttpServletRequest request) {
        ModelAndView mv = createMAV("personal/reserve");

        String houseId = this.findStringParameterValue(request, "houseId");

        HouseInfo houseInfo = houseService.findBaseHouseInfoByHouseId(houseId);
        return mv.addObject("house", houseInfo);
    }

    @RequestMapping("payment")
    public ModelAndView payment(HttpServletRequest request) {
        ModelAndView mv = createMAV("personal/payment");

        String houseId = this.findStringParameterValue(request, "houseId");

        HouseInfo houseInfo = houseService.findBaseHouseInfoByHouseId(houseId);
        return mv.addObject("house", houseInfo);
    }

    /**
     * 向房源提供者发送预约短信
     *
     * @param tenentTel  预约者电话
     * @param verifyCode 短信验证码
     */
    private boolean sendVerifyCodeMessage(String tenentTel, String verifyCode) {
        boolean flag = false;
        try {
            String templateId = ConfigUtil
                    .getConfig("cloopen.orderVerifyCodeTemplate");
            Map<String, Object> result = smsService.sendTemplateSMS(templateId,
                    tenentTel, new String[]{verifyCode, "1"});

            if (SmsServiceImpl.SUCCESS_CODE.equals(result.get("statusCode"))) {
                flag = true;
            } else {
                // 异常返回输出错误码和错误信息
                log.error("短信获取失败：[code:" + result.get("statusCode")
                        + ", message:" + result.get("statusMsg") + "]");
            }

        } catch (Exception e) {
            log.error("验证码短信模板ID取得失败！", e);
        }

        return flag;
    }

    /**
     * 向房源提供者发送预约短信
     *
     * @param tenentTel     预约者电话
     * @param orderDateTime 预约时间
     * @param houseOwnerTel 发送目标
     * @param houseAddress  预约的房屋地址
     */
    private void sendOrderMessageToProjectOwner(String tenentTel,
                                                String orderDateTime, String houseOwnerTel, String houseAddress) {
        try {
            String templateId = ConfigUtil
                    .getConfig("cloopen.toHouseOwnerTemplate");
            Map<String, Object> result = smsService.sendTemplateSMS(templateId,
                    houseOwnerTel, new String[]{tenentTel, orderDateTime,
                            houseAddress});
            if (!SmsServiceImpl.SUCCESS_CODE.equals(result.get("statusCode"))) {
                // 异常返回输出错误码和错误信息
                log.error("短信获取失败：[code:" + result.get("statusCode")
                        + ", message:" + result.get("statusMsg") + "]");
                log.error("给房源提供者发送预约成功短信失败![orderTel:" + tenentTel
                        + ", orderDateTime:" + orderDateTime
                        + ", houseAddress:" + houseAddress + "]");
            }
        } catch (Exception e) {
            log.error("验证码短信模板ID取得失败！", e);
        }
    }

    /**
     * 向预约者发送预约短信
     *
     * @param tenentTel     预约者电话
     * @param orderDateTime 预约时间
     * @param houseOwnerTel 发送目标
     * @param houseAddress  预约的房屋地址
     */
    private void sendOrderMessageToTenent(String tenentTel,
                                          String orderDateTime, String houseOwnerTel, String houseAddress) {
        try {
            String templateId = ConfigUtil
                    .getConfig("cloopen.toHouseOrderTemplate");
            Map<String, Object> result = smsService.sendTemplateSMS(templateId,
                    tenentTel, new String[]{houseOwnerTel, orderDateTime,
                            houseAddress});
            if (!SmsServiceImpl.SUCCESS_CODE.equals(result.get("statusCode"))) {
                // 异常返回输出错误码和错误信息
                log.error("短信获取失败：[code:" + result.get("statusCode")
                        + ", message:" + result.get("statusMsg") + "]");
                log.error("给房源预订者发送预约成功短信失败![orderTel:" + tenentTel
                        + ", orderDateTime:" + orderDateTime
                        + ", houseAddress:" + houseAddress + "]");
            }
        } catch (Exception e) {
            log.error("验证码短信模板ID取得失败！", e);
        }
    }

    /**
     * 向预约者发送微信信息
     *
     * @param accessToken
     * @param openId       微信ID
     * @param orderTime    预约时间
     * @param houseAddress 公寓地址
     * @param houseTel     公寓公司电话
     */
    private boolean sendOrderMessageToWeixinUser(String accessToken,
                                                 String openId, String orderTime, String houseAddress,
                                                 String houseTel) {
        String weixinMessageTemplate;
        try {
            weixinMessageTemplate = ConfigUtil
                    .getConfig("weixin.template.order.message");
        } catch (Exception e) {
            log.error("获取预约微信模板信息失败！");
            return false;
        }

        sendTemplateMessageService.sendTemplateMessage(accessToken, openId,
                MessageFormat.format(weixinMessageTemplate, orderTime,
                        houseAddress, houseTel));
        return true;
    }

    /**
     * 向客服发送信息
     *
     * @param clientTel    预约客户电话
     * @param orderTime    预约时间
     * @param houseAddress 公寓地址
     * @param houseTel     公寓公司电话
     * @param brandName    公寓公司
     * @return 邮件发送是否成功
     */
    private boolean sendOrderMessageToCusetomerService(String clientTel,
                                                       String orderTime, String houseAddress, String houseTel, String brandName) {
        String subject;
        String message;
        try {
            subject = ConfigUtil.getConfig("mail.template.order.subject");
            message = ConfigUtil.getConfig("mail.template.order.message");
        } catch (Exception e) {
            log.error("获取预约邮件主题、信息模板信息失败！");
            return false;
        }

        try {
            EmailUtil.sendHtmlEmail(MessageFormat.format(subject, clientTel,
                    houseAddress, orderTime, brandName), MessageFormat.format(
                    message, clientTel, houseAddress, orderTime, brandName, houseTel));
        } catch (EmailException e) {
            log.error("发送预约邮件失败！");
            return false;
        }

        return true;
    }
}
