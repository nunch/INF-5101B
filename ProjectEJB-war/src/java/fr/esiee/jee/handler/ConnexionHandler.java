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
public class ConnexionHandler extends Handler {
    String jspConnexion = "/WEB-INF/login.jsp";
    String jspResponse = "/WEB-INF/response.jsp";
    String jspPrevious = "/WEB-INF/previous.jsp";
    String jspIndex = "/index.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username==null){
            return jspConnexion;
        }
        User user = userFacade.connectUser(username, password);
        if(user==null) {
            request.setAttribute("problem", "Usename or password isn't good");
            return jspConnexion;
        }
        User u = (User) request.getSession().getAttribute("user");
        u.setFirstname(user.getFirstname());
        u.setId(user.getId());
        u.setLastname(user.getLastname());
        u.setMail(user.getMail());
        u.setPassword(user.getPassword());
        u.setType(user.getType());
        u.setUsername(user.getUsername());
        if(request.getSession().getAttribute("request_truc")!=null) return jspResponse;
        try{
            if(((String)request.getSession().getAttribute("request_truc")).equals("previous")) return jspPrevious;
        }catch(NullPointerException ee){
            
        }
        return jspIndex;
    }
}
