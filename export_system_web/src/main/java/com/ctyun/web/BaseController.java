package com.ctyun.web;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {
    /**
     * spring框架为我们实现了绑定线程的操作，它是从当前线程上获取的request对象
     */
    @Autowired
    protected HttpServletRequest request = null;

    @Autowired
    protected HttpSession session = null;

//    @Autowired
//    protected HttpServletResponse response = null;


    //protected String companyId;
    //protected String companyName;
    //protected User user;

    /**
     * 返回的是当前认证成功的用户对应的企业id
     * @return
     */
    public String getCurrentUserCompanyId(){
        return "1";
    }

    /**
     * 返回的是当前认证成功的用户对应的企业名称
     * @return
     */
    public String getCurrentUserCompanyName(){
        return "传智播客";
    }

    /**
     * @ModelAttribute
     *     它是可以在控制器任何方法执行之前，先执行
     * 此处有线程安全问题
     * 分析：
     *   1、spring框架中bean对象都是单例的（默认情况下）
     *   2、单例对象中类成员，只会随着对象创建时，初始化一次。
     *   3、ModelAttribute注解修饰的方法会在每次控制器方法执行之前先执行一次
     *   4、Http协议：它是客户浏览器和服务器之间的一种一问一答的规则。
     *
     * @param request
     * @param response
     * @param session

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
        //模拟数据
	    //user = (User)session.getAttribute("loginUser");
	    //if(user != null) {
		//    this.companyId = user.getCompanyId();
		//    this.companyName=user.getCompanyName();
	    //}
    }*/
}
