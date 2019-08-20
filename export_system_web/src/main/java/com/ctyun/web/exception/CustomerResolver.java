package com.ctyun.web.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hga
 * @date 2019-07-23 21:52
 */
@Component
public class CustomerResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView m = new ModelAndView();
        m.setViewName("error");
        if (ex instanceof CustomerException){
            m.addObject("error", ex.getMessage());
        }else{
            m.addObject("error", "服务器忙,请联系管理员");
        }
        return m;
    }
}