/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Card;
import Entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
public class ChooseCardHandler extends Handler {
    
    String jsp="/WEB-INF/chooseCard.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<Card> l = cardFacade.findCustomerId(u.getId());
        request.setAttribute("adressId", request.getParameter("adressId"));
        request.setAttribute("cards", l);
        return jsp;
    }
}
