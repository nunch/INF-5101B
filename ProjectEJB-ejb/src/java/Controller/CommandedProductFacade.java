/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.CommandedProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class CommandedProductFacade extends AbstractFacade<CommandedProduct> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandedProductFacade() {
        super(CommandedProduct.class);
    }
    
    public List<CommandedProduct> findCommandId(int commandId){
        return em.createNamedQuery("CommandedProduct.findByCommandId")
                .setParameter("commandId", commandId)
                .getResultList();
    }
    
}
