package com.ctyun.system.service.impl;

import com.ctyun.dao.RoleDao;
import com.ctyun.dao.UserDao;
import com.ctyun.domain.Role;
import com.ctyun.domain.User;
import com.ctyun.system.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public PageInfo findAll(String companyId, String page, String pageSize) {
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(pageSize));
        List<User> all = userDao.findAll(companyId);
        return new PageInfo(all);
    }

    @Override
    public User findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public int delete(String userId) {
        return userDao.delete(userId);
    }

    @Override
    public int save(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        return userDao.save(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public List<String> findUserRoleByUserId(String id) {
        return roleDao.findUserRoleByUserId(id) ;
    }

    @Override
    public List<Role> findAllRole(String currentUserCompanyId) {
        return roleDao.findAllRole(currentUserCompanyId);
    }
}