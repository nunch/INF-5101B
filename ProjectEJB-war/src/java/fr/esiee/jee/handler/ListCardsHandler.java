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

/**
 *
 * @author yo
 */
public class ListCardsHandler extends Handler{
    String jsp = "/WEB-INF/listCards.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");
        List<Card> res = cardFacade.findCustomerId(u.getId());
        request.setAttribute("cards", res);
        return jsp;
    }
    
}
