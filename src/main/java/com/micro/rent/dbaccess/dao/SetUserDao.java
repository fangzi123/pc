package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetUser;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @Description 用户数据访问接口
 * @date 2013-03-01
 */

public interface SetUserDao {

    /**
     * @param
     * @Description 查询Sequences
     * @author
     */
    String getSequences();

    /**
     * @param user
     * @Description 插入新记录
     * @author
     */
    void insert(SetUser user);

    /**
     * @param userId
     * @return
     * @Description 根据主键删除记录
     * @author
     */
    void deleteByPrimaryKey(String userId);

    /**
     * @param user
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetUser user);

    /**
     * @param userId
     * @Description 根据主键查询记录
     * @author
     */
    SetUser selectByPrimaryKey(String userId);

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
    SetUser queryUserByUserName(String userName);

    /**
     * 修改密码
     *
     * @param map
     */
    void updateUserPasswd(Map<String, Object> mpParams);
}
