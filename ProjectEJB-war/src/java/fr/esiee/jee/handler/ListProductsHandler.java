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

/**
 *
 * @author yo
 */
public class ListProductsHandler extends Handler{
    String jsp="/WEB-INF/listProducts.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");
        List<Product> res = productFacade.findCompagnyId(u.getId());
        request.setAttribute("products", res);
        List<Category> l = categoryFacade.findAll();
        List<Category> cat = new LinkedList<>();
        HashMap<Product,String> map2 = new HashMap<>();
        HashMap<Integer,Category> map = new HashMap<>();
        for(Category c:l){
            cat.add(c);
            map.put(c.getId(), c);
            for(Product p:res){
                if(p.getCategoryId().getId() == c.getId()) map2.put(p, c.getNameFr());
            }
        }
        request.setAttribute("product_category", map2);
        for(Category c : cat){
            if(c.getSuperId()!=null && map.keySet().contains(c.getSuperId().getId())) {
                cat.remove(map.get(c.getSuperId().getId()));
                map.remove(c.getSuperId().getId());
            }
        }
        request.setAttribute("categoriess", cat);
        return jsp;
    }
    
}
