/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author yo
 */
public class RemoveProductHandler extends Handler{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product p = productFacade.find(id);
        productFacade.remove(p);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(RemoveProductHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
