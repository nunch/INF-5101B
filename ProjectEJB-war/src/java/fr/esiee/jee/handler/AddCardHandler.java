/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Card;
import Entity.User;
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
public class AddCardHandler extends Handler {
    String jsp = "/ProjectEJB-war/web/listCards";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        User u = (User) request.getSession().getAttribute("user");
        Card c = new Card();
        c.setUserId(u);
        c.setName(name);
        cardFacade.create(c);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", new Boolean(true));
            json.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(AddBasketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            response.sendRedirect(jsp);
        } catch (IOException ex) {
            Logger.getLogger(AddCardHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
