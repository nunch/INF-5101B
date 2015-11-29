/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yo
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "ProjectEJB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public Product findName(String name){
        List<Product> l = em.createNamedQuery("Product.findByName").setParameter("name", name).getResultList();
        if(l.isEmpty()) return null;
        return l.get(0);
    }
    
    public List<Product> findCategoryId(int categoryId){
        return em.createNamedQuery("Product.findByCategoryId")
                .setParameter("categoryId", categoryId)
                .getResultList();
    }
    
    public List<Product> findCompagnyId(int compagnyId){
        return em.createNamedQuery("Product.findByCompagnyId")
                .setParameter("companyId", compagnyId)
                .getResultList();
    }
    
    public List<Product> findLike(String[] like){
        List<Product> res = null;
        int i = 0;
        for(String s : like){
            List<Product> l = em.createNamedQuery("Product.findLike")
                .setParameter("like", "%"+s+"%")
                .getResultList();
            if(i==0) res = l;
            else res = intersection(res, l);
            i++;
        }
        return res;
    }
    
    private <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
}
