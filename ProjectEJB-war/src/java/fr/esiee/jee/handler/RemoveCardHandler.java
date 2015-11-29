/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Card;
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
public class RemoveCardHandler extends Handler{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Card c = cardFacade.find(id);
        cardFacade.remove(c);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", true);
            System.out.println(json);
            json.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(AddBasketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
