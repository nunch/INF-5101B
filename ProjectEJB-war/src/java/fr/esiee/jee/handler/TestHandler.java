/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Card;
import Entity.Category;
import Entity.OrderedProduct;
import Entity.Product;
import Entity.User;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public class TestHandler extends Handler {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User u = new User();
        u.setUsername("yo");
        u.setPassword("yo");
        u.setMail("mail_yo");
        u.setType("compagny");
        userFacade.create(u);
        User u2 = userFacade.findMail("mail_yo");
        User u3 = userFacade.find(u2.getId());
        User u4 = userFacade.findUsername("yo");
        User u5 = userFacade.connectUser("yo", "yo");
        
        Category c = new Category();
        c.setNameEng("cat");
        c.setNameFr("cat");
        categoryFacade.create(c);
        Category c2 = categoryFacade.findNameEng("cat");
        Category c3 = categoryFacade.find(c2.getId());
        
        Product p = new Product();
        p.setCategoryId(c3);
        p.setCompagnyId(u4);
        p.setImage("image");
        p.setName("name");
        p.setPrice(new BigDecimal(1.52));
        p.setDescription("des");
        
        productFacade.create(p);
        Product p2 = productFacade.findName("name");
        Product p3 = productFacade.find(p2.getId());
        List<Product> p4 = productFacade.findCategoryId(c3.getId());
        List<Product> p5 = productFacade.findCompagnyId(u3.getId());
        
        Card ca = new Card();
        ca.setName("name");
        ca.setUserId(u4);
        
        cardFacade.create(ca);
        Card ca2 = cardFacade.findName("name");
        Card ca3 = cardFacade.find(ca2.getId());
        
        OrderedProduct o = new OrderedProduct();
        //o.setOrderedProductPK(new OrderedProductPK(p2.getId(), u4.getId()));
        o.setProduct(p3);
        o.setQuantity((short) 1);
        o.setUser(u4);
        
        orderedProductFacade.create(o);
        OrderedProduct o2 = orderedProductFacade.findIds(u4.getId(), p2.getId());
        List<OrderedProduct> o3 = orderedProductFacade.findCustomerId(u4.getId());
        
        Product p6 = productFacade.find(1);
        Product p7 = productFacade.find(2);
        Product p8 = productFacade.find(3);
        Product p9 = productFacade.find(4);
        Product p10 = productFacade.find(5);
        Product p11 = productFacade.find(6);
        
        orderedProductFacade.remove(o2);
        
        cardFacade.remove(ca3);
        
        productFacade.remove(p3);
        
        categoryFacade.remove(c3);
        
        userFacade.remove(u4);
        return "";
    }
    
}
