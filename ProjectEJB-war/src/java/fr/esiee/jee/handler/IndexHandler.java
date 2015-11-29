/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Category;
import Entity.Product;
import Entity.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
public class IndexHandler extends Handler {
    
    String jsp = "/WEB-INF/index.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        //request.setAttribute("language","fr");
        
        User u = userFacade.findUsername("session");
//        User u = userFacade.findUsername("nunch2");
        List<Product> product = productFacade.findAll();
        
        HttpSession session = request.getSession();
        
        request.setAttribute("product", product);
        User us = (User) session.getAttribute("user");
        if(us == null || us.getId()==null){
            session.setAttribute("user", u);
        }
        
        String language = request.getParameter("language");
        if(language!=null){
            request.setAttribute("language", language);
            session.setAttribute("lang", language);
        }
        
        if(session.getAttribute("categories")==null){
            request.setAttribute("language", "fr");
            session.setAttribute("lang", "fr");
            List<Category> l = categoryFacade.findAll();
            HashMap<Category,Integer> map = new HashMap<>();
            List<Category> cat = new LinkedList<>();
            for (Category c : l) {
                if(c.getSuperId()==null){
                    cat.add(c);
                }
                else {
                    map.put(c,c.getSuperId().getId());
                }
            }
            session.setAttribute("categories", cat);
            session.setAttribute("map_categories", map);
        }
        
        
        return jsp;
    }
}
