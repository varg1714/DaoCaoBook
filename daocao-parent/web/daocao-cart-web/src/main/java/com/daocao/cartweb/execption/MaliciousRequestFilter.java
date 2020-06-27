package com.daocao.cartweb.execption;


import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author varg
 * @date 2020/4/11 23:40
 */
@WebFilter(urlPatterns = "/*",filterName = "maliciousRequestFilter")
public class MaliciousRequestFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("filter...");
        }catch (Exception exception){
//            HttpServletResponse response = (HttpServletResponse)servletResponse;
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
