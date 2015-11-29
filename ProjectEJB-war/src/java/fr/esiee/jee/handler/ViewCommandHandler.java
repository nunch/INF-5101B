/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Command;
import Entity.CommandedProduct;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public class ViewCommandHandler extends Handler {

    String jsp="/WEB-INF/viewCommand.jsp";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String commandId = request.getParameter("commandId");
        Command c = commandFacade.find(Integer.parseInt(commandId));
        
        request.setAttribute("command", c);
        
        List<CommandedProduct> l = commandedProductFacade.findCommandId(c.getId());
        
        request.setAttribute("products", l);
        
        request.setAttribute("total", c.getAmount().doubleValue());
        return jsp;
        
    }
    
}
