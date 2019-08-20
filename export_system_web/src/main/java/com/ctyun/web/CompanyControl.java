package com.ctyun.web;

import com.ctyun.domain.Company;
import com.ctyun.system.service.CompanyService;
import com.ctyun.utils.UtilFuns;
import com.ctyun.web.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * @author hga
 * @date 2019-07-21 22:17
 */
@Controller
@RequestMapping("/company")
public class CompanyControl {
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/list")
    public ModelAndView findAll(){
        List<Company> allCompany = companyService.findAllCompany();
        ModelAndView m=new ModelAndView();
        System.out.println(allCompany);
        m.addObject("company", allCompany);
        m.setViewName("/company/company-list");
        return m;
    }
    @RequestMapping("/toAdd")
    public String add(){
        return "/company/company-add";
    }

    @RequestMapping("edit")
    public ModelAndView edit(Company company) throws CustomerException {
        /*判断有没有id  有id就是修改,没有就是插入*/
        System.out.println(company);
        if (UtilFuns.isEmpty(company.getName())){
            throw new CustomerException("公司名不能为空");

        }
        String id = company.getId();
        if (UtilFuns.isEmpty(id)){
            //这是添加方法
            String replace = UUID.randomUUID().toString().replace("-", "");
            company.setId(replace);
            System.out.println(company);
            companyService.addCompany(company);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/company/list.do");
            return m;
        }else{
            companyService.update(company);
            ModelAndView m = new ModelAndView();
            m.setViewName("redirect:/company/list.do");
            return m;
            //这是修改方法
        }
    }

    @RequestMapping("toUpdate")
    public  ModelAndView update(String id){
        //根据id查询公司信息放入域对象在请求转发到add页面
        Company companyById = companyService.findCompanyById(id);
        ModelAndView m = new ModelAndView();
        m.addObject("company", companyById);
        m.setViewName("/company/company-add");
        return m;
    }
    @RequestMapping("delete")
    public ModelAndView delete(String id){
        System.out.println(id);
        /*删除数据*/
        companyService.delete(id);
        //重定向到查询所有信息的页面
        ModelAndView m = new ModelAndView();
        m.setViewName("redirect:/company/list.do");
        return m;

    }
}