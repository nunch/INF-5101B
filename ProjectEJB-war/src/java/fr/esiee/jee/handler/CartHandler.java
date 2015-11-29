/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.OrderedProduct;
import Entity.Product;
import Entity.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yoann
 */
public class CartHandler extends Handler{
    String jsp = "/WEB-INF/cart.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");
        List<OrderedProduct> l = orderedProductFacade.findCustomerId(u.getId());
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        List<Product> pro = new LinkedList<>();
        for(OrderedProduct b : l){
            Product p = productFacade.find(b.getOrderedProductPK().getProductId());
            map.put(p.getName(), b.getQuantity());
            pro.add(p);
        }
        request.setAttribute("product", pro);
        request.setAttribute("map", map);
        return jsp;
    }
    
}
