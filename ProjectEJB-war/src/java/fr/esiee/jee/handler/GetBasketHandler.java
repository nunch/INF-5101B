/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.OrderedProduct;
import Entity.Product;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author yoann
 */
public class GetBasketHandler extends Handler{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        User u = (User) request.getSession().getAttribute("user");
        List<OrderedProduct> l= orderedProductFacade.findCustomerId(u.getId());
        Integer num = 0;
        double total = 0;
        for(OrderedProduct bb:l){
            Product p = bb.getProduct();
            if(p==null){
                int i=0;
            }else if(p.getPrice()==null){
                int j=0;
            }
            double d = p.getPrice().doubleValue();
            int i = bb.getQuantity();
            total+=d*i;
            num+=i;
        }
        if(lang.equals("en")) total = round(total*0.918906501,2);
        else total=round(total,2);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", new Boolean(true));
            json.put("total", total);
            json.put("item", num);
            json.put("lang", lang);
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
