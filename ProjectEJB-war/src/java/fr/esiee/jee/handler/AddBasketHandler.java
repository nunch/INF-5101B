/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.OrderedProduct;
import Entity.OrderedProductPK;
import Entity.Product;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author yo
 */
public class AddBasketHandler extends Handler{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id_product = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        User u =(User) session.getAttribute("user");
        int id_user = u.getId();
        OrderedProduct b = orderedProductFacade.findIds(id_user, id_product);
        Product p = productFacade.find(id_product);
        if(b== null){
            b = new OrderedProduct();
            b.setProduct(p);
            b.setUser(u);
            b.setQuantity((short) 1);
            b.setUser(u);
            orderedProductFacade.create(b);
        }else{
            b.setQuantity((short) (b.getQuantity()+1));
            orderedProductFacade.edit(b);
        }
        List<OrderedProduct> l= orderedProductFacade.findCustomerId(u.getId());
        Integer num = 0;
        double total = 0;
        for(OrderedProduct bb:l){
            Product pp = productFacade.find(bb.getOrderedProductPK().getProductId());
            total+=pp.getPrice().doubleValue()*bb.getQuantity();
            num+=bb.getQuantity();
        }
        if(lang.equals("en")) total = round(total*0.918906501,2);
        total=round(total,2);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("total", total);
            json.put("item", num);
            json.put("lang",lang);
            System.out.println(json);
            json.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(AddBasketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    private double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
}
