package com.ctyun.web;

import com.ctyun.domain.Dept;
import com.ctyun.system.service.DeptService;
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
@RequestMapping("/system/dept")
public class DeptControl extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("list")
    public ModelAndView list(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "2") String pageSize) {
        PageInfo all = deptService.findAll("1", page, pageSize);
        ModelAndView m = new ModelAndView();
        System.out.println(all);
        m.addObject("page", all);
        m.setViewName("system/dept/dept-list");
        return m;

    }

    /**
     * 访问添加页码,跳转到添加页面
     * @param
     * @param
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {

        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.setViewName("/system/dept/dept-add");
        m.addObject("deptList", all);
        return m;
    }
    /**
     * 访问添加页码,跳转到添加页面
     * @param
     * @param
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView edit(Dept dept) {
        System.out.println(dept);
        String id = dept.getId();
        if (id!=null&&!"".equals(id)){
            deptService.update(dept);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/dept/list.do");
            return m;
        }else {
            dept.setCompanyId(super.getCurrentUserCompanyId());
          dept.setCompanyName(super.getCurrentUserCompanyName());
        deptService.save(dept);
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/system/dept/list.do");
        return m;
        }
    }
    @RequestMapping("delete")
    public ModelAndView delete(String id) throws CustomerException {

        try {
            deptService.delete(id);
        } catch (Exception e) {
            throw new  CustomerException("不能删除");
        }
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/system/dept/list.do");
        return m;
    }

    /**
     * 前往更新部门页面
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(String id) {
        Dept byId = deptService.findById(id);
        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.addObject("dept", byId);
        m.addObject("deptList", all);
        m.setViewName("/system/dept/dept-update");
        return m;
    }
}