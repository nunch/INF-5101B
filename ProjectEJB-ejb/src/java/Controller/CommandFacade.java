/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Command;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class CommandFacade extends AbstractFacade<Command> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandFacade() {
        super(Command.class);
    }
    
    public Command findLastCommand(int userId){
        List<Command> l = em.createNamedQuery("Command.findByUserId")
                .setParameter("userId", userId)
                .getResultList();
        Command c = l.get(0);
        for(Command cc:l){
            if(cc.getId()>c.getId()) c = cc;
        }
        return c;
    }
    
    public List<Command> findUserId(int userId){
        return em.createNamedQuery("Command.findByUserId")
                .setParameter("userId", userId)
                .getResultList();
    }
    
}
