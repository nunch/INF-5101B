/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.handler;

import Controller.AdressFacade;
import Controller.CardFacade;
import Controller.CategoryFacade;
import Controller.CommandFacade;
import Controller.CommandedProductFacade;
import Controller.OrderedProductFacade;
import Controller.ProductFacade;
import Controller.UserFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yo
 */
public abstract class Handler {

    UserFacade userFacade = lookupUserFacadeBean();
    ProductFacade productFacade = lookupProductFacadeBean();
    OrderedProductFacade orderedProductFacade = lookupOrderedProductFacadeBean();
    CommandedProductFacade commandedProductFacade = lookupCommandedProductFacadeBean();
    CommandFacade commandFacade = lookupCommandFacadeBean();
    CategoryFacade categoryFacade = lookupCategoryFacadeBean();
    CardFacade cardFacade = lookupCardFacadeBean();
    AdressFacade adressFacade = lookupAdressFacadeBean();

    private AdressFacade lookupAdressFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AdressFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/AdressFacade!Controller.AdressFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CardFacade lookupCardFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CardFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/CardFacade!Controller.CardFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CategoryFacade lookupCategoryFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CategoryFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/CategoryFacade!Controller.CategoryFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CommandFacade lookupCommandFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CommandFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/CommandFacade!Controller.CommandFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CommandedProductFacade lookupCommandedProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CommandedProductFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/CommandedProductFacade!Controller.CommandedProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OrderedProductFacade lookupOrderedProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (OrderedProductFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/OrderedProductFacade!Controller.OrderedProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProductFacade lookupProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProductFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/ProductFacade!Controller.ProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UserFacade lookupUserFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UserFacade) c.lookup("java:global/ProjectEJB/ProjectEJB-ejb/UserFacade!Controller.UserFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
