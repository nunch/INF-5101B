/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Controller.AdressFacade;
import Entity.Adress;
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
public class Adresses {
    @EJB
    private AdressFacade adressFacade;

    String adress;
    String cityName;
    String country;

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AdressFacade getAdressFacade() {
        return adressFacade;
    }

    public String getAdress() {
        return adress;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }
        
    public Adresses() {
    }
    
    public void addAdress(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        
        Adress a = new Adress();
        a.setAdress(adress);
        a.setCityName(cityName);
        a.setCountry(country);
        a.setUserId(u);
        
        adressFacade.create(a);
    }
}
