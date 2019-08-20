package com.ctyun.dao;

import com.ctyun.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleDao {

    //根据id角色
    Role findById(String id);

    //查询全部用户
    List<Role> findAll(String companyId);

	//根据id角色
    int delete(String id);

	//添加
    int save(Role role);

	//更新
    int update(Role role);

    /**
     * 建立角色和模块的关联关系，就是往角色模块中间表插入数据
     * @param id
     * @param moduleId
     */
    void saveRoleModule(@Param("id") String id, @Param("moduleId") String moduleId);

    /**
     * 根据角色id，删除角色模块中间表的数据
     * @param id
     */
    void deleteRoleModule(String id);

    List<String> findUserRoleByUserId(String userid);

    List<Role> findAllRole(String companyId);
}