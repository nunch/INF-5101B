/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esiee.jee.servlet;

import fr.esiee.jee.handler.AddBasketHandler;
import fr.esiee.jee.handler.AddCardHandler;
import fr.esiee.jee.handler.CardHandler;
import fr.esiee.jee.handler.CategoryProductHandler;
import fr.esiee.jee.handler.ConnexionHandler;
import fr.esiee.jee.handler.GetBasketHandler;
import fr.esiee.jee.handler.Handler;
import fr.esiee.jee.handler.IndexHandler;
import fr.esiee.jee.handler.ListCardsHandler;
import fr.esiee.jee.handler.ListProductsHandler;
import fr.esiee.jee.handler.LogOffHandler;
import fr.esiee.jee.handler.ProductPageHandler;
import fr.esiee.jee.handler.RegisterHandler;
import fr.esiee.jee.handler.RemoveBasketHandler;
import fr.esiee.jee.handler.RemoveCardHandler;
import fr.esiee.jee.handler.RemoveAdressHandler;
import fr.esiee.jee.handler.RemoveProductHandler;
import fr.esiee.jee.handler.ChooseAdressHandler;
import fr.esiee.jee.handler.ChooseCardHandler;
import fr.esiee.jee.handler.ConfirmCommandhandler;
import fr.esiee.jee.handler.ConfirmedCommandHandler;
import fr.esiee.jee.handler.SearchHandler;
import fr.esiee.jee.handler.UpdateBasketHandler;
import fr.esiee.jee.handler.ViewCommandHandler;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
@WebServlet(name = "ControlServlet", urlPatterns = {"/web/*"})
public class ControlServlet extends HttpServlet {

    private static HashMap<String,Handler> map = null;
    private static AddBasketHandler h1 = new AddBasketHandler();
    private static UpdateBasketHandler h2 = new UpdateBasketHandler();
    private static RemoveBasketHandler h3 = new RemoveBasketHandler();
    private static GetBasketHandler h4 = new GetBasketHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(map == null) initHashMap();
        // Permet de récupérer uniquement l'extension de l'adresse
        
        String url = request.getRequestURI().replace(request.getContextPath(), "");
        HttpSession session = request.getSession();
        // Lors du lancement de l'application l'adresse peut etre suivie de ;jssession...
        // On test ainsi si cette extension est présente et si oui
        // On la localise avec le caractère ';' et on la retire de la String
        if(url.contains(";")){
            url = url.substring(0, url.indexOf(';'));
        }
        if(url.contains("?")){
            url = url.substring(0, url.indexOf('?'));
        }
        // On récupère le Handler qui va permettre de gérer la page
        Handler handler = map.get(url);
        // Si la page tapée n'est pas gérée on redirige vers une page d'erreur
        System.err.println(url);
        if(url.contains("category")){
            handler = map.get("/web/category");
        }else if(url.contains("product")){
            handler = map.get("/web/product");
        }
        if(handler == null) {
            response.sendRedirect(request.getContextPath()+"/404.jsp");
            System.out.println("echec url="+url);
        } else {
            // La page tapée étant gérée, on gère la page avec le Handler
            // qui nous retourne l'adresse sur laquelle la servlet doit forwarder
            // Cette adresse peut etre aussi bien une page jsp
            // qu'une adresse gérée par la servlet
            String jsp = handler.execute(request, response);
            if(!jsp.equals("")) getServletContext().getRequestDispatcher(jsp).forward(request, response);
        }
    }

    private void initHashMap() {
        // On initialise la HashMap qui nous permettera de 
        // rediriger toutes les adresses gérées par la servlet
        // vers le Handler correspondant
        map = new HashMap<>();
        map.put("/web/register", new RegisterHandler());
        map.put("/web/login", new ConnexionHandler());
        map.put("/web/index", new IndexHandler());
        map.put("/web/addBasket", h1);
        map.put("/web/updateBasket", h2);
        map.put("/web/removeBasket", h3);
        map.put("/web/getBasket", h4);
        map.put("/web/cart", new CardHandler());
        map.put("/web/category", new CategoryProductHandler());
        map.put("/web/product", new ProductPageHandler());
        map.put("/web/listCards", new ListCardsHandler());
        map.put("/web/listProducts", new ListProductsHandler());
        map.put("/web/addCard", new AddCardHandler());
        map.put("/web/removeCard", new RemoveCardHandler());
        map.put("/web/removeProduct", new RemoveProductHandler());
        map.put("/web/logOff", new LogOffHandler());
        map.put("/web/removeAdress", new RemoveAdressHandler());
        map.put("/web/search", new SearchHandler());
        map.put("/web/chooseAdress", new ChooseAdressHandler());
        map.put("/web/chooseCard", new ChooseCardHandler());
        map.put("/web/confirmCommand", new ConfirmCommandhandler());
        map.put("/web/confirmedCommand", new ConfirmedCommandHandler());
        map.put("/web/viewCommand", new ViewCommandHandler());
    }
}
