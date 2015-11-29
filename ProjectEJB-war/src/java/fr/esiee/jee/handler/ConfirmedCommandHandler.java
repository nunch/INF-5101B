/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Entity.Adress;
import Entity.Card;
import Entity.Command;
import Entity.CommandedProduct;
import Entity.OrderedProduct;
import Entity.User;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
public class ConfirmedCommandHandler extends Handler{

    String jsp = "/web/index";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String adressId = request.getParameter("adressId");
        String cardId = request.getParameter("cardId");
        Adress a = adressFacade.find(Integer.parseInt(adressId));
        Card c = cardFacade.find(Integer.parseInt(cardId));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<OrderedProduct> l = orderedProductFacade.findCustomerId(u.getId());
        Command command = new Command();
        command.setUserId(u);
        
        double total = 0;
        for(OrderedProduct o : l){
            total+=o.getProduct().getPrice().doubleValue()*o.getQuantity();
        }
        
        command.setAmount(new BigDecimal(total));
        command.setCardId(c);
        command.setUserId(u);
        command.setAdressId(a);
        commandFacade.create(command);
        command = commandFacade.findLastCommand(u.getId());
        
        for(OrderedProduct o : l){
            CommandedProduct cp = new CommandedProduct();
            cp.setCommand(command);
            cp.setProduct(o.getProduct());
            cp.setQuantity(o.getQuantity());
            commandedProductFacade.create(cp);
        }
        
        orderedProductFacade.removeCustomerId(u.getId());
        
        return jsp;
    }
}
    