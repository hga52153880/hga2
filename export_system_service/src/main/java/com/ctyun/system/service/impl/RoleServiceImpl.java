package com.ctyun.system.service.impl;

import com.ctyun.dao.RoleDao;
import com.ctyun.domain.Role;
import com.ctyun.system.service.ModuleService;
import com.ctyun.system.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ModuleService moduleService;
    @Override
    public PageInfo findAll(String companyId, String page, String pageSize) {
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(pageSize));
        List<Role> all = roleDao.findAll(companyId);
        return new PageInfo(all);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public int delete(String roleId) {
        return roleDao.delete(roleId);
    }

    @Override
    public int save(Role role) {
        role.setId(UUID.randomUUID().toString().replace("-",""));
        return roleDao.save(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public List<Map> findRoleModule(String roleid) {

        return moduleService.findRoleModule(roleid);
    }

    @Override
    public void updateRoleModule(String roleid, String[] moduleIds) {
        //删除掉以前的角色权限
        moduleService.delOldRoleModule(roleid);
        //添加最新的
        for (String moduleId : moduleIds) {

            moduleService.addNewRoleModule(roleid, moduleId);
        }
    }
}