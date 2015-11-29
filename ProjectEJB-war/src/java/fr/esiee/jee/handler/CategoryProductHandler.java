/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Category;
import Entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoann
 */
public class CategoryProductHandler extends Handler{
    String jsp="/WEB-INF/category.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getRequestURI().replace(request.getContextPath(), "").replaceAll("%20", " ");
        while(name.contains("/")){
            name = name.substring(name.indexOf('/')+1);
            System.out.println(name);
        }
        Category cat = categoryFacade.findNameFr(name);
        List<Product> l = productFacade.findCategoryId(cat.getId());
        request.setAttribute("product", l);
        return jsp;
    }
    
}
