package com.daocao.userweb.execption;


import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
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
