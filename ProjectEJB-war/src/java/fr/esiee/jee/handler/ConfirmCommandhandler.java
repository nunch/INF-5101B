/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Adress;
import Entity.Card;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public class ConfirmCommandhandler extends Handler{

    String jsp = "/WEB-INF/confirmCommand.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String adressId = request.getParameter("adressId");
        String cardId = request.getParameter("cardId");
        Adress a = adressFacade.find(Integer.parseInt(adressId));
        Card c = cardFacade.find(Integer.parseInt(cardId));
        request.setAttribute("adress", a);
        request.setAttribute("card", c);
        
        return jsp;
    }
    
}
