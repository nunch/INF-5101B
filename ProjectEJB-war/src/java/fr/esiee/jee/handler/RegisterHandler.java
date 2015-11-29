/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public class RegisterHandler extends Handler {
    String jspConnexion = "/WEB-INF/register.jsp";
    String jspIndex = "/index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        if(username == null || username.equals("")) return jspConnexion;
        String password = request.getParameter("password");
        String password2 = request.getParameter("confirm");
        if(!password.equals(password2)){
            request.setAttribute("problem2", "Passwords are different");
            return jspConnexion;
        }
        String mail = request.getParameter("email");
        String type = "user";
        
        User user = userFacade.findUsername(username);
        if(user!=null){
            request.setAttribute("problem1", "Username is already used");
            return jspConnexion;
        }
        user = userFacade.findMail(mail);
        if(user!=null){
            request.setAttribute("problem1", "Email is already used");
            return jspConnexion;
        }
        
        User u = (User) request.getSession().getAttribute("user");
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setMail(mail);
        u.setPassword(password);
        u.setUsername(username);
        u.setType(type);
        userFacade.create(u);
        
        
        return jspIndex;
    }
    
}
