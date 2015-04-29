package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.RoleMenuService;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.dao.RoleMenuDao;
import com.micro.rent.dbaccess.dao.SetFroleFmenuDao;
import com.micro.rent.dbaccess.dao.SetFuncRoleDao;
import com.micro.rent.dbaccess.entity.RoleMenu;
import com.micro.rent.dbaccess.entity.SetFroleFmenu;
import com.micro.rent.dbaccess.entity.SetFuncRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private SetFroleFmenuDao setFroleFmenuDao;
    @Autowired
    private SetFuncRoleDao setFuncRoleDao;

    @Override
    public void queryPaged(Page<RoleMenu> page) {
        // 分页结果集
        List<RoleMenu> list = roleMenuDao.queryPaged(page);

        page.setResults(list);
    }

    @Override
    public void create(SetFuncRole role, List<SetFroleFmenu> list) {
        setFuncRoleDao.insert(role);
        for (SetFroleFmenu roleMenu : list) {
            setFroleFmenuDao.insert(roleMenu);
        }
    }

    @Override
    public void update(SetFuncRole role, List<SetFroleFmenu> list) {
        setFuncRoleDao.updateByPrimaryKeySelective(role);
        setFroleFmenuDao.deleteByRoleId(list.get(0).getFuncRoleId());
        for (SetFroleFmenu roleMenu : list) {
            setFroleFmenuDao.insert(roleMenu);
        }
    }

    @Override
    public void delete(String roleId) {
        setFuncRoleDao.deleteByPrimaryKey(roleId);
        setFroleFmenuDao.deleteByRoleId(roleId);
    }

    @Override
    public String queryRoleSeq() {
        return setFuncRoleDao.getSequences();
    }

}
