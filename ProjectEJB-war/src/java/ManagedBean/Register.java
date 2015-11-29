/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Controller.UserFacade;
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
public class Register {

    @EJB
    private UserFacade userFacade;

    private String firstname;
    private String lastname;
    private String mail;
    private String username;
    private String password;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    private String phone;
    private String type;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * Creates a new instance of Register
     */
    public Register() {
    }
    
    public String register(){
        if(!password.equals(confirmPassword)) return "";
        User u = userFacade.findMail(mail);
        if(u!=null) return "";
        u = userFacade.findUsername(username);
        if(u!=null) return "";
        u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setMail(mail);
        u.setUsername(username);
        u.setType(type);
        u.setPassword(password);
        u.setPhone(phone);
        userFacade.create(u);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("user", u);
        return "Ok";
    }
    
}
