/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.OrderedProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderedProduct> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderedProductFacade() {
        super(OrderedProduct.class);
    }
    
    public List<OrderedProduct> findCustomerId(int customerId){
        return em.createNamedQuery("OrderedProduct.findByCustomerId").setParameter("customerId", customerId).getResultList();
    }
    
    public OrderedProduct findIds(int customerId, int productId){
        List<OrderedProduct> l = em.createNamedQuery("OrderedProduct.findByIds")
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public void removeCustomerId(int customerId){
        em.createNamedQuery("OrderedProduct.removeByCustomerId")
                .setParameter("customerId", customerId)
                .executeUpdate();
    }
    
}
