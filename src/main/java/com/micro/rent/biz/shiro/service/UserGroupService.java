package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.SetRoleGroup;
import com.micro.rent.dbaccess.entity.SetUser;
import com.micro.rent.dbaccess.entity.SetUserRgroup;
import com.micro.rent.dbaccess.entity.UserGroup;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 用户组管理Service层接口
 * @date 2013-03-01
 */
public interface UserGroupService {

    /**
     * @param page 分页对象
     * @return
     * @Description 按条件分页查询结算菜单
     * @author
     */
    public void queryPaged(Page<UserGroup> page);

    /**
     * @param roleGroup 组对象
     * @param list      角色组关系集合
     * @return
     * @Description 新增用户以及用户组关系
     * @author
     */
    public void create(SetUser user, List<SetUserRgroup> list);

    /**
     * @param roleGroup 组对象
     * @param list      角色组关系集合
     * @return
     * @Description 编辑用户以及用户组关系
     * @author
     */
    public void update(SetUser user, List<SetUserRgroup> list);

    /**
     * @param userId 用户主键
     * @return
     * @Description 删除用户以及用户组关系
     * @author
     */
    public void delete(String userId);

    /**
     * @return
     * @Description 得到用户主键
     * @author
     */
    public String queryUserSeq();

    /**
     * @return
     * @Description 查询所有组
     * @author
     */
    List<SetRoleGroup> queryAll();

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色ID查询候选角色组
     * @author
     */
    public List<SetRoleGroup> queryCandidateResource(String userId);

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色ID查询已选角色组
     * @author
     */
    public List<SetRoleGroup> querySelectedResource(String userId);

    /**
     * @param userId
     * @return
     * @Description 根据主键查询用户
     * @author
     */
    public SetUser queryById(String userId);

    /**
     * 查询用户名是否存在
     *
     * @param userName
     * @return
     */
    Integer queryUserExist(String userName);

    /**
     * 根据用户名返回用户信息
     *
     * @param userName
     * @return
     */
    public SetUser queryUserByUserName(String userName);

    /**
     * 修改用户密码
     *
     * @param userName
     * @param passwd
     */
    public void updateUserPasswd(String userName, String passwd);
}
