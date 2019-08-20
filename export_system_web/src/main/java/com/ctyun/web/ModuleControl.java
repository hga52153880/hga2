package com.ctyun.web;

import com.ctyun.domain.Dept;
import com.ctyun.domain.Module;
import com.ctyun.system.service.DeptService;
import com.ctyun.system.service.ModuleService;
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
@RequestMapping("/system/module")
public class ModuleControl extends BaseController {
    @Autowired
    private ModuleService moduleService;

    @Autowired
    private DeptService deptService;
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "2") String pageSize) {
        PageInfo all = moduleService.findAll( page, pageSize);
        ModelAndView m = new ModelAndView();
        System.out.println(all);
        m.addObject("page", all);
        m.setViewName("system/module/module-list");
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
        ModelAndView m = new ModelAndView();
        List<Module> module = moduleService.findAll();
        m.setViewName("/system/module/module-add");
        m.addObject("menus", module);

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
    public ModelAndView edit(Module module) {
        System.out.println(module);
        String id = module.getId();
        if (id != null && !"".equals(id)) {
            moduleService.update(module);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/module/list.do");
            return m;
        } else {
         /*   module.setCompanyId(super.getCurrentModuleCompanyId());
            module.setCompanyName(super.getCurrentModuleCompanyName());*/
            System.out.println(module);
            moduleService.save(module);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/system/module/list.do");
            return m;
        }
    }

    @RequestMapping("delete")
    public ModelAndView delete(String id) throws CustomerException {

        try {
            moduleService.delete(id);
        } catch (Exception e) {
            throw new CustomerException("不能删除");
        }
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/system/module/list.do");
        return m;
    }

    /**
     * 前往更新模块页面
     *
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(String id) {
        Module byId = moduleService.findById(id);
        List<Dept> all = deptService.findAll(super.getCurrentUserCompanyId());
        ModelAndView m = new ModelAndView();
        m.addObject("module", byId);
        m.addObject("deptList", all);
        m.setViewName("/system/module/module-update");
        return m;
    }
}