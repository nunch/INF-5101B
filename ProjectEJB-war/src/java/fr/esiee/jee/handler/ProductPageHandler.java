/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Category;
import Entity.Product;
import Entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoann
 */
public class ProductPageHandler extends Handler{

    String jsp = "/WEB-INF/product.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getRequestURI().replace(request.getContextPath(), "").replaceAll("%20", " ").replaceAll("%22", "\"");
        while(name.contains("/")){
            name = name.substring(name.indexOf('/')+1);
            System.out.println(name);
        }
        Product p = productFacade.find(Integer.parseInt(name));
        Category cat = p.getCategoryId();
        User com = p.getCompagnyId();
        request.setAttribute("compagny", com);
        request.setAttribute("category",cat);
        request.setAttribute("bean", p);
        return jsp;
    }
    
}
