package com.ctyun.system.service;

import com.ctyun.domain.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author hga
 * @date 2019-07-25 00:21
 */

public interface DeptService {

    //根据企业id分页查询全部
    PageInfo findAll(String companyId, String page, String pageSize);

    //根据id查询
    Dept findById(String deptId);

    //根据id删除
    int delete(String deptId);

    //保存
    int save(Dept dept);

    //更新
    int update(Dept dept);

    /**
     * 查询全部部门信息
     * @param companyId
     * @param
     * @param
     * @return
     */
    List<Dept> findAll(String companyId);
}