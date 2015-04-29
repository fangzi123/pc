package com.micro.rent.biz.base.web;

import com.micro.rent.biz.base.service.RegionService;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.base.RegionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rental/region")
public class RegionController extends _BaseController {
    @Autowired
    private RegionService regionService;

    @RequestMapping("/findRegionsByLevel")
    @ResponseBody
    public List<RegionInfo> findRegionsByparentIdAndLevel(ModelMap modelMap,
                                                          @RequestParam(value = "parentId") String parentId) {
        List<RegionInfo> regionInfo = null;
        if (parentId == "2" || parentId == "3" || parentId == "10" || parentId == "23") {
            regionInfo = regionService.findDirectCityByGovnmentByParentId(parentId);
        } else {
            regionInfo = regionService.findReginsByParentId(parentId);
        }
        return regionInfo;
    }
}
