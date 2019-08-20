package com.ctyun.system.service.impl;

import com.ctyun.dao.DeptDao;
import com.ctyun.domain.Dept;
import com.ctyun.system.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author hga
 * @date 2019-07-25 00:22
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public PageInfo findAll(String companyId, String page, String pageSize) {
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(pageSize));
        List<Dept> all = deptDao.findAll(companyId);
        return new PageInfo(all);
    }

    @Override
    public Dept findById(String deptId) {
        return deptDao.findById(deptId);
    }

    @Override
    public int delete(String deptId) {
        return deptDao.delete(deptId);
    }

    @Override
    public int save(Dept dept) {
        dept.setId(UUID.randomUUID().toString().replace("-",""));
        return deptDao.save(dept);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }
}