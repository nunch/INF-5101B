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
public class RemoveBasketHandler extends Handler{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        String id = request.getParameter("id");
        int id_product = Integer.parseInt(id);
        User u =(User) session.getAttribute("user");
        int id_user = u.getId();
        OrderedProduct b = orderedProductFacade.findIds(id_user, id_product);
        orderedProductFacade.remove(b);
        List<OrderedProduct> l= orderedProductFacade.findCustomerId(u.getId());
        Integer num = 0;
        double total = 0;
        for(OrderedProduct bb:l){
            Product p = bb.getProduct();
            total+=p.getPrice().doubleValue()*bb.getQuantity();
            num+=bb.getQuantity();
        }
        
        Double sub_total=total*0.8;
        Double VAT=total*0.2;
        if(lang.equals("en")) {
            total = round(total*0.918906501,2);
            sub_total = round(sub_total*0.918906501,2);
            VAT = round(VAT*0.918906501,2);
        }
        else {
            total=round(total,2);
            sub_total = round(sub_total,2);
            VAT = round(VAT,2);
        }
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("lang",lang);
            json.put("total", total);
            json.put("item", num);
            json.put("lang", lang);
            json.put("sub-total", sub_total);
            json.put("VAT", VAT);
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
