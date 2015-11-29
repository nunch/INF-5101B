/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findUsername(String name){
        List<User> l = em.createNamedQuery("User.findByUsername").setParameter("username", name).getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public User connectUser(String username, String password){
        List<User> l = em.createNamedQuery("User.findByUserConnection")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public User connectMail(String mail, String password){
        List<User> l = em.createNamedQuery("User.findByMailConnection")
                .setParameter("mail", mail)
                .setParameter("password", password)
                .getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public User findMail(String mail){
        List<User> l = em.createNamedQuery("User.findByMail")
                .setParameter("mail", mail)
                .getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
}
