package com.ctyun.web;

import com.ctyun.domain.Dept;
import com.ctyun.domain.Role;
import com.ctyun.domain.User;
import com.ctyun.system.service.DeptService;
import com.ctyun.system.service.UserService;
import com.ctyun.web.exception.CustomerException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hga
 * @date 2019-07-25 00:20
 */
@Controller
@RequestMapping("/system/user")
public class UserControl extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "5") String pageSize) {
        PageInfo all = userService.findAll(super.getCurrentUserCompanyId(), page, pageSize);
        ModelAndView m = new ModelAndView();
        System.out.println(all);
        m.addObject("page", all);
        m.setViewName("system/user/user-list");
        return m;

    }

    /**
     * 访问添加页码,跳转到添加页面
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {

        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.setViewName("/system/user/user-add");
        m.addObject("deptList", all);
        return m;
    }

    /**
     * 更新或者添加方法
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView edit(User user) {
        System.out.println(1111);
        System.out.println(user);
        String id = user.getId();
        if (id != null && !"".equals(id)) {
            userService.update(user);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/user/list.do");
            return m;
        } else {
            user.setCompanyId(super.getCurrentUserCompanyId());
            user.setCompanyName(super.getCurrentUserCompanyName());
            System.out.println(user);
            userService.save(user);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/user/list.do");
            return m;
        }
    }

    @RequestMapping("delete")
    public ModelAndView delete(String id) throws CustomerException {

        try {
            userService.delete(id);
        } catch (Exception e) {
            throw new CustomerException("不能删除");
        }
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/system/user/list.do");
        return m;
    }

    /**
     * 前往更新用户页面
     *
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(String id) {
        User byId = userService.findById(id);
        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.addObject("user", byId);
        m.addObject("deptList", all);
        m.setViewName("/system/user/user-update");
        return m;
    }

    /**
     * 查看用户角色
     * @param id
     * @return
     */
    @RequestMapping("roleList")
    public ModelAndView roleList(String id) {
        //查询用户
        User byId = userService.findById(id);
        //查询用户角色id
        List<String> userRoleByUserId = userService.findUserRoleByUserId(id);
        //查询公司内部全部角色
        List<Role> allRole = userService.findAllRole(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.addObject("user", byId);
        m.addObject("userRoleStr", userRoleByUserId);
        m.addObject("roleList", allRole);
        m.setViewName("/system/user/user-role");
        return m;
    }
}