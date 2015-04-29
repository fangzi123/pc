package com.micro.rent.common.web.controller;

import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.exceptions.SysException;
import com.micro.rent.common.support.CookieUtil;
import com.micro.rent.common.web.controller.pagination.CustomPaginatedList;
import com.micro.rent.common.web.controller.pagination.Pageable;
import com.micro.rent.common.web.controller.pagination.PaginatedList;
import com.micro.rent.common.web.controller.pagination.impl.PageRequest;
import com.micro.rent.pc.util.JedisUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author
 * @version 1.0
 * @Description 控制器基类(SBS权限管理部分迁移使用)
 * @date 2012-11-15
 */
public abstract class _BaseController extends MultiActionController {

    /**
     * 分页请求起始参数
     */
    protected final String PAGE_PARAM_START = "page";
    /**
     * 每页显示数量
     */
    protected final String PAGE_PARAM_LIMIT = "pageSize";
    protected transient Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 候选日期模式
     */
    private String[] candidateDatePatterns;

    /**
     * @return
     * @Description 创建ModelAndView实例
     * @author
     */
    protected ModelAndView createMAV() {
        return new ModelAndView();
    }

    /**
     * @param viewName
     * @return
     * @Description 创建带有试图名称的ModelAndView实例
     * @author
     */
    protected ModelAndView createMAV(String viewName) {
        return new ModelAndView(viewName);
    }

    /**
     * @param viewName
     * @param page
     * @return
     */
    protected <T> ModelAndView createMAV(String viewName, Page<T> page) {
        return createMAV(viewName).addObject("pageList", page);
    }

    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);

        //扩展日期绑定
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public String getAsText() {
                return String.valueOf(getValue());
            }

            public void setAsText(String value) {
                try {
                    setValue(DateUtils.parseDate(value, candidateDatePatterns));
                } catch (ParseException e) {
                    log.error(e.getMessage());
                    setValue(null);
                }
            }

        });
    }

    /**
     * @param request
     * @param clazz   绑定类
     * @return
     * @throws Exception
     * @Description 通过请求参数绑定Model
     * @author
     */
    protected <T> T bindModel(HttpServletRequest request, Class<T> clazz)
            throws Exception {
        T object = (T) newCommandObject(clazz);
        bind(request, object);
        return object;
    }

    /**
     * @param request
     * @return
     * @Description 获取分页信息
     * @author
     */
    protected Pageable findPage(HttpServletRequest request) {
        return findPage(request, PAGE_PARAM_START, PAGE_PARAM_LIMIT);
    }

    protected Pageable findPageByPageSize(HttpServletRequest request) {
        Pageable pageable = null;
        if (request.getParameter("pageSize") == null
                || request.getParameter("pageSize").equals("")) {
            Integer start = findIntegerParameterValue(request, Constants._page);
            pageable = new PageRequest(start == null ? 1 : start, Constants.DEFAULT_LIMIT);
        } else {
            pageable = this.findPage(request);
        }

        return pageable;
    }

    protected <T> Page<T> findPageFinal(HttpServletRequest request) {
        Pageable pageable = findPageByPageSize(request);

        return findPageFinal(pageable);
    }

    protected <T> Page<T> findPageFinal(Pageable requestPage) {
        Page<T> page = new Page<T>();

        page.setPageLimit(requestPage.getPageLimit());
        page.setPageStart(requestPage.getPageStart());

        return page;
    }

    /**
     * @param request
     * @param pageFieldName     起始页字段名称
     * @param pageSizeFieldName 单页总量字段名称
     * @return
     * @Description 获取分页信息
     * @author
     */
    protected Pageable findPage(HttpServletRequest request, String pageFieldName, String pageSizeFieldName) {
        Validate.notBlank(pageFieldName, "page field name required");
        Validate.notBlank(pageSizeFieldName, "pageSize field name required");
        Integer start = findIntegerParameterValue(request, pageFieldName);
        Integer limit = findIntegerParameterValue(request, pageSizeFieldName);
        if (limit == null) {
            throw new SysException("pageSize is required");
        }
        //限制pageSize <= 100
        if (limit > 100) {
            log.warn("pageSize must be less than 100");
            limit = 100;
        }
        ;
        return new PageRequest(
                start == null ? 1 : start,
                limit
        );
    }

    /**
     * @param pageable 分页结果
     * @param start
     * @param limit
     * @return
     * @Description 执行分页处理
     * @author
     */
    protected PaginatedList doPaging(Pageable pageable, int start, int limit) {
        // 分页获取记录
        int pageNumber = start;

        CustomPaginatedList pageList = new CustomPaginatedList();

        // 设置当前页数
        pageList.setPageNumber(pageNumber);
        // 设置当前页列表
        pageList.setList(pageable.getResult(List.class));
        // 设置page size
        pageList.setObjectsPerPage(limit);
        // 设置总数
        pageList.setFullListSize(pageable.getCount());

        return pageList;
    }

    /**
     * @param request
     * @param pageable 分页结果
     * @return
     * @Description 执行分页处理
     * @author
     */
    protected PaginatedList doPaging(HttpServletRequest request, Pageable pageable) {
        // 分页获取记录
        int pageNumber;
        if (StringUtils.isNotBlank(request.getParameter(PAGE_PARAM_START))) {
            pageNumber = findIntegerParameterValue(request, PAGE_PARAM_START);
        } else {
            pageNumber = 1;
        }

        CustomPaginatedList pageList = new CustomPaginatedList();

        // 设置当前页数
        pageList.setPageNumber(pageNumber);
        // 设置当前页列表
        pageList.setList(pageable.getResult(List.class));
        // 设置page size
        pageList.setObjectsPerPage(this.findIntegerParameterValue(request, PAGE_PARAM_LIMIT));
        // 设置总页数
        pageList.setFullListSize(pageable.getCount());

        return pageList;
    }

    /**
     * @param request
     * @param name    参数名称
     * @return
     * @Description 从请求中获取Integer类型参数
     * @author
     */
    protected Integer findIntegerParameterValue(HttpServletRequest request,
                                                String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Integer.parseInt(pv);
    }

    /**
     * @param request
     * @param name    参数名称
     * @return
     * @Description 从请求中获取Long类型参数
     * @author
     */
    protected Long findLongParameterValue(HttpServletRequest request,
                                          String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Long.parseLong(pv);
    }

    /**
     * @param request
     * @param name    参数名称
     * @return
     * @Description 从请求中获取BigDecimal类型参数
     * @author
     */
    protected BigDecimal findBigDecimalParameterValue(HttpServletRequest request,
                                                      String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : new BigDecimal(pv);
    }

    /**
     * @param request
     * @param name    参数名称
     * @return
     * @Description 从请求中获取String类型参数
     * @author
     */
    protected String findStringParameterValue(HttpServletRequest request,
                                              String name) {
        return WebUtils.findParameterValue(request, name);
    }

    /**
     * @param request
     * @param name    参数名称
     * @return
     * @Description 从请求中获取Boolean类型参数
     * @author
     */
    protected Boolean findBooleanParameterValue(HttpServletRequest request,
                                                String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Boolean.parseBoolean(pv);
    }

    /**
     * @param request
     * @param name        参数名称
     * @param datePattern 日期模式
     * @return
     * @throws ParseException
     * @Description 从请求中获取Date类型参数
     * @author
     */
    protected Date findDateParameterValue(HttpServletRequest request,
                                          String name, String datePattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : dateFormat.parse(WebUtils.findParameterValue(request, name));
    }

    protected Locale getLocal(HttpServletRequest request) {
        return RequestContextUtils.getLocale(request);
    }

    public void setCandidateDatePatterns(String[] candidateDatePatterns) {
        this.candidateDatePatterns = candidateDatePatterns;
    }
    /**
     * @Title: getVerifyCode
     * @Description: 从redis中获取验证码
     * @param request
     * @param telephone
     * @return
     * @return: String
     */
    public String getVerifyCode(HttpServletRequest request, String telephone) {
        return (String) JedisUtil.get(telephone);
    }

    /**
     * @Title: removeVerifyCode
     * @Description: 清除redis中的验证码
     * @param request
     * @param telephone
     * @return: void
     */
    public void removeVerifyCode(HttpServletRequest request, String telephone) {
        JedisUtil.del(telephone);
    }
    /**
     * @Title: removeVerifyCode
     * @Description: TODO
     * @param request
     * @param telephone
    	* @author: wff
     * @return: void
     */
    public boolean isTheSameVerifyCode(HttpServletRequest request, String telephone,String verifyCode) {
       boolean  same=  StringUtils.equals(verifyCode, getVerifyCode(request, telephone))?true:false;
       if(same){
           this.removeVerifyCode(request,telephone);
       }
       return same;
    }
    /**
     * @Description: cookie是否包含info 存在返回true,反之返回false
     * @param cookie
     * @param houseId
     * @return
    	* @author: wff
     * @return: boolean
     */
    public boolean containInfo(Cookie cookie, String info) {
        if(cookie==null) return false;
        boolean rs = false;
        String[] ids = cookie.getValue().split(",");
        for (String pid : ids) {
            if (pid.equals(info)) {
                rs = true;
                break;
            }
        }
        return rs;
    }
}
