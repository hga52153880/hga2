package com.ctyun.system.service;

import com.ctyun.domain.Module;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author hga
 * @date 2019-07-25 00:21
 */

public interface ModuleService {

    //根据企业id分页查询全部
    PageInfo findAll( String page, String pageSize);

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //保存
    int save(Module module);

    //更新
    int update(Module module);

    /**
     * 查询全部部门信息
     * @param
     * @param
     * @param
     * @return
     */
    List<Module> findAll();

    List<Map> findRoleModule(String roleid);

    void delOldRoleModule(String roleid);

    void addNewRoleModule(String roleid, String moduleId);
}