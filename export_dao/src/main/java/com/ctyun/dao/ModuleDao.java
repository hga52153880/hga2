package com.ctyun.dao;

import com.ctyun.domain.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ModuleDao {

	//根据企业id查询全部
	List<Module> findAll();

	//根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

	//保存
    int save(Module module);

	//更新
    int update(Module module);

    List<Map> findRoleModule(String roleid);

    void delOldRoleModule(String roleid);

    void addNewRoleModule(@Param("roleid") String roleid,@Param("moduleId") String moduleId);
}