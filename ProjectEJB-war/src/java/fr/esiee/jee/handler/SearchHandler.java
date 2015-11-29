/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public class SearchHandler extends Handler{

    String jsp = "/WEB-INF/search.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        String[] list = name.split(" ");
        List<Product> l = productFacade.findLike(list);
        request.setAttribute("product", l);
        return jsp;
    }
    
}
