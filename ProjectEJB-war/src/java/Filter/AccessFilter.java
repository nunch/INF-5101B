/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
@WebFilter(filterName = "AccessFilter", servletNames = {"Faces Servlet"})
public class AccessFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig config;
    
    public AccessFilter() {
    }    
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if((requestURI.contains("/admin/")) && (u==null || (u.getType()!=null && !u.getType().equals("admin")))) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/web/index");
        } else {
          chain.doFilter(request, response);
        }
        
    }


    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.config = filterConfig;
    }

    
    
    
}
