/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSF;

import Entity.CommandedProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class CommandedProductFacade extends AbstractFacade<CommandedProduct> {

    @PersistenceContext(unitName = "ProjectEJB-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandedProductFacade() {
        super(CommandedProduct.class);
    }
    
}
