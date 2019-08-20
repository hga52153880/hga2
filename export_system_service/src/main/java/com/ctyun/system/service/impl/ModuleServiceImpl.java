package com.ctyun.system.service.impl;

import com.ctyun.dao.ModuleDao;
import com.ctyun.domain.Module;
import com.ctyun.system.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author hga
 * @date 2019-07-25 00:22
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Override
    public PageInfo findAll( String page, String pageSize) {
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(pageSize));
        List<Module> all = moduleDao.findAll();
        return new PageInfo(all);
    }

    @Override
    public Module findById(String moduleId) {
        return moduleDao.findById(moduleId);
    }

    @Override
    public int delete(String moduleId) {
        return moduleDao.delete(moduleId);
    }

    @Override
    public int save(Module module) {
        module.setId(UUID.randomUUID().toString().replace("-",""));
        return moduleDao.save(module);
    }

    @Override
    public int update(Module module) {
        return moduleDao.update(module);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public List<Map> findRoleModule(String roleid) {
        return moduleDao.findRoleModule(roleid);
    }

    @Override
    public void delOldRoleModule(String roleid) {
        moduleDao.delOldRoleModule(roleid);
    }

    @Override
    public void addNewRoleModule(String roleid, String moduleId) {
        moduleDao.addNewRoleModule(roleid, moduleId);
    }
}