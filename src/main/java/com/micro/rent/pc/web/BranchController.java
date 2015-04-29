package com.micro.rent.pc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.PriceGroup;
import com.micro.rent.pc.entity.vo.AvailableHouseInfoVo;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.AddressService;
import com.micro.rent.pc.service.AmenityService;
import com.micro.rent.pc.service.AvailableHouseInfoService;
import com.micro.rent.pc.service.Branch2picService;
import com.micro.rent.pc.service.BranchService;
import com.micro.rent.pc.service.ContactService;
import com.micro.rent.pc.service.Houselayout2picService;
import com.micro.rent.pc.service.NearHouseService;
import com.micro.rent.pc.service.PriceGroupService;
import com.micro.rent.pc.util.MapBuilder;

@Controller
@RequestMapping("/branch")
public class BranchController  extends _BaseController {
	private static Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PriceGroupService priceGroupService;
	
	@Autowired
	private AvailableHouseInfoService availableHouseInfoService;
	
	@Autowired
	private AmenityService amenityService;
	
	@Autowired
	private Branch2picService branch2picService;
	
	@Autowired
	private Houselayout2picService houselayout2picService;
	
	@Autowired
	private NearHouseService nearHouseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		logger.info("BranchController entering ...");
		return null;
	}
	
	/**
	 * http://localhost:8080/rent/branch/branchDetail?branchId=5270
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/branchDetail", method=RequestMethod.GET)
//	@ResponseBody
	public String branchDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Long branchId = this.findLongParameterValue(request, "branchId"); 
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			//branch  里面有物业类型、门店描述
			Branch branch = branchService.findBranchById(branchId);
			result.put("branch", branch);
			//contact  里面有电话信息
			result.put("contact", contactService.findByBranchId(branchId));
			//address  地址信息、坐标信息
			Address address = addressService.findAddressByBranchId(branchId);
			result.put("address", address);
			//priceGroup 最低价格信息
			List<PriceGroup> priceGroupList = priceGroupService.findByBranchId(branchId);
			result.put("priceGroup", priceGroupList.get(0));
			//可用房源信息
			Page<AvailableHouseInfoVo> page = new Page<AvailableHouseInfoVo>();
			page.setPageLimit(6);
			page.setPageStart(1);
			page.setParams(MapBuilder.with("branchId", branchId).build());
			Page<AvailableHouseInfoVo> availableHouseInfoPage = availableHouseInfoService.findPagedByBranchId(page);
			result.put("availableHouseInfoPage", availableHouseInfoPage);
			result.put("availableHouseInfoList", availableHouseInfoPage.getResults());
			//房间数量
			//result.put("houseNum", availableHouseInfoList.size());
			//生活设施
			result.put("amenityList", amenityService.findAmenitysByBranchId(branchId));
			//门店的图片
			result.put("branch2picList", branch2picService.findBranch2picByBranchId(branchId));
			//项目图片
			//result.put("houselayout2picList", houselayout2picService.findProjectHouselayout2picByBranchId(branchId));
			//
			//result.put("Houselayout2picList", houselayout2picService.findHouselayout2picByBranchId(branchId));
			
			//查找附近房源
			if (address != null && branch != null) {
				List<BrandVo> brandVoList = nearHouseService.findNearBrandByAddressBrand(address.getX(), address.getY(), branch.getBrandId());
				
				model.addAttribute("nearBrandVoList", brandVoList);
			}
			
			model.addAllAttributes(result);
		} catch (Exception e) {
			e.printStackTrace();
			//result.put("success", false);
			//result.put("message", "系统异常，请稍后重试");
		}
		return "community/comniDetail";
	}
	
	@RequestMapping(value="/findPageidAvailableHouseInfo", method=RequestMethod.POST)
	@ResponseBody
	public List<AvailableHouseInfoVo> findPageidAvailableHouseInfo(HttpServletRequest request) throws Exception {
		Long branchId = this.findLongParameterValue(request, "branchId"); 
		Integer start = this.findIntegerParameterValue(request, "start");
		Page<AvailableHouseInfoVo> page = new Page<AvailableHouseInfoVo>();
		page.setPageLimit(6);
		page.setPageStart(start);
		page.setParams(MapBuilder.with("branchId", branchId).build());
		Page<AvailableHouseInfoVo> availableHouseInfoPage = availableHouseInfoService.findPagedByBranchId(page);
		return availableHouseInfoPage.getResults();
	}
}
