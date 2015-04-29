package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.SetFuncMenuService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.dao.SetFuncMenuDao;
import com.micro.rent.dbaccess.entity.SetFuncMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("setFuncMenuService")
public class SetFuncMenuServiceImpl implements SetFuncMenuService {


    @Autowired
    private SetFuncMenuDao menuDao;

    public void queryPaged(Page<SetFuncMenu> page) {
        List<SetFuncMenu> list = menuDao.queryPaged(page);

        page.setResults(list);

    }

    @Override
    public void create(SetFuncMenu menu) {
        //if(menu.getPmenuId().compareTo(new BigDecimal(-1)) != 0){
        if (!menu.getPmenuId().equals("-1")) {
            SetFuncMenu pmenu = new SetFuncMenu();
            pmenu.setMenuId(menu.getPmenuId());
            pmenu.setIsLeaf(Constants.IS_NOT_LEAF);
            menuDao.updateByPrimaryKeySelective(pmenu);
        }
        menuDao.insert(menu);
    }

    @Override
    public void update(SetFuncMenu menu) {
        menuDao.updateByPrimaryKeySelective(menu);
    }

    @Override
    public String querySeq() {
        return menuDao.getSequences();
    }

    @Override
    public List<SetFuncMenu> queryAll() {
        return menuDao.queryAll();
    }

    @Override
    public SetFuncMenu queryById(String menuId) {
        return menuDao.selectByPrimaryKey(menuId);
    }

    @Override
    public void deleteMenu(Map<String, String> params) {
        menuDao.deleteByMenuCode(params);
    }

    @Override
    public List<SetFuncMenu> queryCandidateResource(String roleId) {
        return menuDao.queryCandidateResource(roleId);
    }

    @Override
    public List<SetFuncMenu> querySelectedResource(String roleId) {
        return menuDao.querySelectedResource(roleId);
    }
}
