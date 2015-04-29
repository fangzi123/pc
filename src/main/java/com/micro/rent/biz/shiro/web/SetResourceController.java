package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.SetResourceService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.comm.shiro.service.ShiroService;
import com.micro.rent.common.support.BusinessUtil;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.SetResource;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SetResourceController extends _BaseController {

    //注入shiroService
    private ShiroService shiroService;
    //注入setResourceService
    private SetResourceService setResourceService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示资源管理页面
     * @author
     */
    public ModelAndView resourceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String _search_url = "_search_url";

        String urlPattern = this.findStringParameterValue(request, _search_url);

        String _search_key = null, _search_value = null;
        if (_search_url != null) {
            _search_key = _search_url;
            _search_value = urlPattern;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("urlPattern", urlPattern);


        Page<SetResource> page = this.findPageFinal(request);
        page.setParams(params);


        setResourceService.queryPaged(page);

        return createMAV("/mt/resource/resource_read")
                .addObject("_search_key", _search_key)
                .addObject("_search_value", _search_value)
                .addObject("pageList", page);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示创建资源页面
     * @author
     */
    public ModelAndView resourceCreateDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return createMAV("/mt/resource/resource_create");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 创建资源页面提交
     * @author
     */
    public ModelAndView resourceCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SetResource resource = this.bindModel(request, SetResource.class);
        if (resource == null) {
            return resourceCreateDisplay(request, response).addObject(Constants._message, "创建的资源信息为空");
        }

        //String seq = setResourceService.querySeq();
        String seq = Identities.create32LenUUID();

        if (StringUtils.isBlank(seq)) {
            return resourceCreateDisplay(request, response).addObject(Constants._message, "主键生成错误");
        }

        //resource.setResourceId(NumberUtils.createBigDecimal(seq));
        resource.setResourceId(seq);

        resource.setStatus(Constants.RESOURCE_EFFECT);

        setResourceService.create(resource);

        String urlPattern = resource.getUrlPattern();
        String[] roles = new String[]{String.valueOf(resource.getResourceId())};
        shiroService.addSecurityFilter(urlPattern, roles);

        return resourceCreateDisplay(request, response).addObject(Constants._message, "创建成功");
    }

    public ModelAndView resourceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String _delData = this.findStringParameterValue(request, Constants._delData);
        if (StringUtils.isBlank(_delData)) {
            return resourceDisplay(request, response).addObject(Constants._message, "主键为空");
        }

        String resourceId = _delData;

        SetResource resource = setResourceService.queryById(resourceId);
        setResourceService.deleteMenu(resourceId);

        String urlPattern = resource.getUrlPattern();
        shiroService.deleteSecurityFilter(urlPattern);

        return resourceDisplay(request, response).addObject(Constants._message, "删除成功");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 更新建资源显示
     * @author
     */
    public ModelAndView resourcePersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String _storage = this.findStringParameterValue(request, Constants._storage);
        LinkedHashMap<String, String> selectArr = BusinessUtil.getStorage(_storage);

        if (selectArr == null || selectArr.isEmpty()) {
            return resourceDisplay(request, response);
        }
        //选中记录的主键
        String menuId = selectArr.get(Constants._col1);
        //根据主键查询对应的资源信息
        SetResource resource = setResourceService.queryById(menuId);

        if (resource != null) {
            return createMAV("/mt/resource/resource_update").addObject("setResource", resource);
        }

        return resourceDisplay(request, response);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 编辑资源页面提交
     * @author
     */
    public ModelAndView resourceUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SetResource resource = this.bindModel(request, SetResource.class);

        String resourceId = resource.getResourceId();

        SetResource lastResource = setResourceService.queryById(resourceId);

        setResourceService.update(resource);
        String[] roles = new String[]{String.valueOf(resourceId)};
        shiroService.deleteSecurityFilter(lastResource.getUrlPattern());
        shiroService.addSecurityFilter(resource.getUrlPattern(), roles);
        return resourcePersistenceDisplay(request, response).addObject(Constants._message, "编辑成功");
    }

    public void setSetResourceService(SetResourceService setResourceService) {
        this.setResourceService = setResourceService;
    }


    public void setShiroService(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

}
