package com.micro.rent.pc.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.postgresql.geometric.PGpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.vo.BranchVo;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.BranchService;
import com.micro.rent.pc.service.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController extends _BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BranchService branchService;
	
 @RequestMapping(value = "/recomended", method = { RequestMethod.GET })
 @ResponseBody
 public ModelMap recommendBrands(HttpServletRequest request)
         throws Exception {
     ModelMap mm = new ModelMap();
     String code = this.findStringParameterValue(request, "region");
     if (code == null) {
         code = "110000";// beijing
     }
     List<BrandVo> brandList = brandService.getRecommendBrandList(code);
     mm.addObject(brandList);
     return mm;
 }
 
 //waibao
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index() {
		logger.debug("brand entering ...");
		ModelAndView result = this.createMAV("");
		return result;
	}
	
	/**
	 * http://localhost:8080/rent/brand/brandDetail?brandId=3685
	 * @param request
	 * @param response
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/brandDetail", method=RequestMethod.GET)
	//@ResponseBody
	public String BrandDetail(HttpServletRequest request, HttpServletResponse response, Model result) {
		try {
//			Map<String, Object> result = new HashMap<String, Object>();
			
			Long brandId = this.findLongParameterValue(request, "brandId");
			
			Brand brand = brandService.findBrandById(brandId);
			result.addAttribute("brand", brand);
			
			List<BranchVo> branchList = branchService.findDetailBrandId(brandId);
			List<MapBranch> mapBranchList = new ArrayList<MapBranch>();
			
			for(BranchVo bv : branchList) {
				mapBranchList.add(new MapBranch(bv.getName(), bv.getAddress().getAddress(), bv.getContact().getPhone(),
						bv.getMonthlyPrice(), bv.getPicPath(), ((PGpoint)bv.getAddress().getPoint()).x, ((PGpoint)bv.getAddress().getPoint()).y,bv.getId()));
			}
			String jsonStr = new ObjectMapper().writeValueAsString(mapBranchList);
			result.addAttribute("branchList", jsonStr);
			
			//return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "brand/brandDetail";
	}
	
	class MapBranch {
		private String projectName;
		private String address;
		private String phoneNum;
		private java.math.BigDecimal              price;
		private String img;
		private Double lng;
		private Double lat;
		private Long id;
		
		public MapBranch() {
			
		}
		
		public MapBranch(String projectName, String address, String phoneNum,
				java.math.BigDecimal price, String img, Double lng, Double lat,Long id) {
			super();
			this.projectName = projectName;
			this.address = address;
			this.phoneNum = phoneNum;
			this.price = price;
			this.img = img;
			this.lng = lng;
			this.lat = lat;
			this.id = id;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}
		public java.math.BigDecimal getPrice() {
			return price;
		}
		public void setPrice(java.math.BigDecimal price) {
			this.price = price;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public Double getLng() {
			return lng;
		}
		public void setLng(Double lng) {
			this.lng = lng;
		}
		public Double getLat() {
			return lat;
		}
		public void setLat(Double lat) {
			this.lat = lat;
		}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
		
		
	}
}
