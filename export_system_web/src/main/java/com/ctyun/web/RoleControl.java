package com.ctyun.web;

import com.ctyun.domain.Dept;
import com.ctyun.domain.Role;
import com.ctyun.system.service.DeptService;
import com.ctyun.system.service.RoleService;
import com.ctyun.web.exception.CustomerException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author hga
 * @date 2019-07-25 00:20
 */
@Controller
@RequestMapping("/system/role")
public class RoleControl extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "2") String pageSize) {
        PageInfo all = roleService.findAll("1", page, pageSize);
        ModelAndView m = new ModelAndView();
        System.out.println(all);
        m.addObject("page", all);
        m.setViewName("system/role/role-list");
        return m;

    }

    /**
     * 访问添加页码,跳转到添加页面
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd() {

        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.setViewName("/system/role/role-add");
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
    @RequestMapping("/edit")
    public ModelAndView edit(Role role) {

        System.out.println(role);
        String id = role.getId();
        if (id != null && !"".equals(id)) {
            roleService.update(role);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/role/list.do");
            return m;
        } else {
            role.setCompanyId(super.getCurrentUserCompanyId());
            role.setCompanyName(super.getCurrentUserCompanyName());
            System.out.println(role);
            roleService.save(role);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/role/list.do");
            return m;
        }
    }

    @RequestMapping("/delete")
    public ModelAndView delete(String id) throws CustomerException {

        try {
            roleService.delete(id);
        } catch (Exception e) {
            throw new CustomerException("不能删除");
        }
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/system/role/list.do");
        return m;
    }

    /**
     * 前往更新用户页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id) {
        Role byId = roleService.findById(id);
        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.addObject("role", byId);
        m.addObject("deptList", all);
        m.setViewName("/system/role/role-update");
        return m;
    }

    /**
     * 角色的权限控制
     * 前往权限页面
     * @param roleid 角色的id
     * @return
     */
    @RequestMapping("/roleModule")
    public ModelAndView roleModule(String roleid) {
        /*查询角色*/
        Role byId = roleService.findById(roleid);
        ModelAndView m = new ModelAndView();
        m.addObject("role", byId);

        m.setViewName("/system/role/role-module");
        return m;
    }

    /**
     * 获取ztree的json数据,必须是map类型
     * @param roleid
     * @return
     * 格式为  { id:菜单id, pId:父菜单id, name:"菜单名称",checked:是否选中},
     */
    @RequestMapping("/getZtreeNodes")
    @ResponseBody
    public List<Map> getZtreeNodes(String roleid) {
        List<Map> userRole = roleService.findRoleModule(roleid);

        return userRole;

    }
    @RequestMapping("/updateRoleModule")
    @ResponseBody
    public String updateRoleModule(String roleid,String[] moduleIds) {

        //更新角色权限
        roleService.updateRoleModule(roleid,moduleIds);

        return "redirect:/system/role/list.do";

    }
}