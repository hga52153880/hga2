package com.ctyun.system.service;

import com.ctyun.domain.Role;
import com.ctyun.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author hga
 * @date 2019-07-25 00:21
 */

public interface UserService {

    //根据企业id分页查询全部
    PageInfo findAll(String companyId, String page, String pageSize);

    //根据id查询
    User findById(String userId);

    //根据id删除
    int delete(String userId);

    //保存
    int save(User user);

    //更新
    int update(User user);

    /**
     * 查询全部部门信息
     * @param companyId
     * @param
     * @param
     * @return
     */
    List<User> findAll(String companyId);

    List<String> findUserRoleByUserId(String id);

    List<Role> findAllRole(String currentUserCompanyId);
}