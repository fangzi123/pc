package com.micro.rent.pc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.entity.Amenity;
import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.Community;
import com.micro.rent.pc.entity.Contact;
import com.micro.rent.pc.entity.House2pic;
import com.micro.rent.pc.entity.PriceGroup;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.entity.vo.FeeVo;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.service.AmenityService;
import com.micro.rent.pc.service.AvailableHouseInfoService;
import com.micro.rent.pc.service.BranchService;
import com.micro.rent.pc.service.BrandService;
import com.micro.rent.pc.service.CommunityService;
import com.micro.rent.pc.service.ContactService;
import com.micro.rent.pc.service.FeeService;
import com.micro.rent.pc.service.House2picService;
import com.micro.rent.pc.service.HouseService;
import com.micro.rent.pc.service.NearHouseService;
import com.micro.rent.pc.service.PriceGroupService;
import com.micro.rent.pc.util.MapBuilder;

@Controller
@RequestMapping("/house")
public class HouseDetailController extends _BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private AmenityService amenityService;
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private PriceGroupService priceGroupService;
	
	@Autowired
	private House2picService house2picService;
	
	@Autowired
	private NearHouseService nearHouseService;
	
	@RequestMapping(value="/findHouseDetail", method=RequestMethod.GET)
	//@ResponseBody
	public String findHouseDetail(HttpServletRequest request, HttpServletResponse response, Model result) {
		logger.debug("HouseDetailController.findHouseDetail");
		//Map<String, Object> result = new HashMap<String, Object>();
		try {
			Long houseId = this.findLongParameterValue(request, "houseId");
			result.addAttribute("houseId", houseId);
			Community community = communityService.findByHouseId(houseId);
			result.addAttribute("community", community);
			
			List<Amenity> amenityList = amenityService.findByHouseId(houseId);
			result.addAttribute("amenityList", amenityList);
			
			List<FeeVo> feeVoList = feeService.findByHouseId(houseId);
			result.addAttribute("feeVoList", feeVoList);
			
			PriceGroup priceGroup = priceGroupService.findByHouseId(houseId);
			result.addAttribute("priceGroup", priceGroup);
			
			List<House2pic> house2picList = house2picService.findByHouseId(houseId);
			result.addAttribute("house2picList", house2picList);
			
			Contact contact = contactService.findHouseDetailContact(MapBuilder.with("houseId", houseId).build());
			result.addAttribute("contact", contact);
			
			HouseVo houseVo = houseService.findWithAddressById(houseId);
			result.addAttribute("houseVo", houseVo);
			
			HouseVo vo = houseService.findWithBranchBrandById(houseId);
			result.addAttribute("vo", vo);
			
			
			//查找附近房源
			if (houseVo != null && houseVo.getAddress() != null && vo != null && vo.getBrand() != null) {
				Address address = houseVo.getAddress();
				Brand brand = vo.getBrand();
				List<BrandVo> brandVoList = nearHouseService.findNearBrandByAddressBrand(address.getX(), address.getY(), brand.getId());
				result.addAttribute("nearBrandVoList", brandVoList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return JsonHelper.objectMapper(result);
		return "house/houseDetail";
	}
}
