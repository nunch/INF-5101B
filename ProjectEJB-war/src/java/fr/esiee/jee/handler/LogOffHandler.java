/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Product;
import Entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
public class LogOffHandler extends Handler{
    String jsp="/WEB-INF/index.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");
        User user = userFacade.findUsername("session");
        u.setFirstname(user.getFirstname());
        u.setId(user.getId());
        u.setLastname(user.getLastname());
        u.setMail(user.getMail());
        u.setPassword(user.getPassword());
        u.setType(user.getType());
        u.setUsername(user.getUsername());
        
        List<Product> product = productFacade.findAll();
        
        
        request.setAttribute("product", product);
        return jsp;
    }
    
}
