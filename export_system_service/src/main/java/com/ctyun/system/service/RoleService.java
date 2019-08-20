package com.ctyun.system.service;

import com.ctyun.domain.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author hga
 * @date 2019-07-25 00:21
 */

public interface RoleService {

    //根据企业id分页查询全部
    PageInfo findAll(String companyId, String page, String pageSize);

    //根据id查询
    Role findById(String roleId);

    //根据id删除
    int delete(String roleId);

    //保存
    int save(Role role);

    //更新
    int update(Role role);

    /**
     * 查询全部角色信息
     * @param companyId
     * @param
     * @param
     * @return
     */
    List<Role> findAll(String companyId);

    List<Map> findRoleModule(String roleid);

    void updateRoleModule(String roleid, String[] moduleIds);
}