/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Controller.CardFacade;
import Entity.User;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yo
 */
@ManagedBean
@RequestScoped
public class Cards {
    @EJB
    private CardFacade cardFacade;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Cards() {
    }
    
    public String addCard(){
        Entity.Card c = new Entity.Card();
        c.setName(name);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        c.setUserId(u);
        cardFacade.create(c);
        return "";
    }
    
}
