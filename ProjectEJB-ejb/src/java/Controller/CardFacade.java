/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Card;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class CardFacade extends AbstractFacade<Card> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CardFacade() {
        super(Card.class);
    }
    
    public Card findName(String name){
        List<Card> l = em.createNamedQuery("Card.findByName")
                .setParameter("name", name)
                .getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public List<Card> findCustomerId(int customerId){
        return em.createNamedQuery("Card.findByCustomerId")
                .setParameter("customerId", customerId)
                .getResultList();
    }
    
}
