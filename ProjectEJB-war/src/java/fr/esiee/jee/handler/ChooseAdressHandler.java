/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Adress;
import Entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
public class ChooseAdressHandler extends Handler {

    String jsp = "/WEB-INF/chooseAdress.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<Adress> l = adressFacade.findUserId(u.getId());
        request.setAttribute("adresses", l);
        return jsp;
    }
    
}
