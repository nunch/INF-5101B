/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
    
    public Category findNameFr(String nameFr){
        List<Category> l =  em.createNamedQuery("Category.findByNameFr").setParameter("nameFr", nameFr).getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public Category findNameEng(String nameEng){
        List<Category> l =  em.createNamedQuery("Category.findByNameEng").setParameter("nameEng", nameEng).getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
}
