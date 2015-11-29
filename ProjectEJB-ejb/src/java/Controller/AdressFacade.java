/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Adress;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class AdressFacade extends AbstractFacade<Adress> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdressFacade() {
        super(Adress.class);
    }
    
    public List<Adress> findUserId(int userId){
        return em.createNamedQuery("Adress.findByUserId")
                .setParameter("userId", userId)
                .getResultList();
    }
    
}
