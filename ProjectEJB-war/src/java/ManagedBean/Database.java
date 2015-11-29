/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Controller.AdressFacade;
import Controller.CardFacade;
import Controller.CategoryFacade;
import Controller.CommandFacade;
import Controller.OrderedProductFacade;
import Controller.ProductFacade;
import Controller.UserFacade;
import Entity.Adress;
import Entity.Category;
import Entity.Command;
import Entity.Product;
import Entity.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
public class Database {
    @EJB
    private CommandFacade commandFacade;
    @EJB
    private AdressFacade adressFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private OrderedProductFacade orderedProductFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private CardFacade cardFacade;
    
    private List<Entity.Product> products;
    private List<Category> categories;
    private HashMap<Entity.Product,Category> productCategory;

    public List<Entity.Product> getProducts() {
        if(products!=null){
            return products;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        products = productFacade.findCompagnyId(u.getId());
        return products;
    }

    public void setProducts(List<Entity.Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        if(categories!=null){
            return categories;
        }
        this.getProductCategory();
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public HashMap<Entity.Product, Category> getProductCategory() {
        if(productCategory!=null){
            return productCategory;
        }
        productCategory = new HashMap<>();
        List<Category> l = categoryFacade.findAll();
        List<Category> cat = new LinkedList<>();
        HashMap<Integer,Category> map = new HashMap<>();
        List<Entity.Product> res = this.getProducts();
        for(Category c:l){
            cat.add(c);
            map.put(c.getId(), c);
            for(Entity.Product p:res){
                if(p.getCategoryId().getId() == c.getId()) productCategory.put(p, c);
            }
        }
        for(Category c : cat){
            if(c.getSuperId()!=null && map.keySet().contains(c.getSuperId().getId())) {
                cat.remove(map.get(c.getSuperId().getId()));
                map.remove(c.getSuperId().getId());
            }
        }
        this.categories = cat;
        return productCategory;
    }

    public void setProductCategory(HashMap<Entity.Product, Category> productCategory) {
        this.productCategory = productCategory;
    }
    
    public List<Entity.Card> getCards(User u){
        return cardFacade.findCustomerId(u.getId());
    }
    
    public List<Adress> getAdresses(User u){
        return adressFacade.findUserId(u.getId());
    }
    
    public List<Command> getCommands(User u){
        return commandFacade.findUserId(u.getId());
    }
    
    public List<Product> getProducts(User u){
        return productFacade.findCompagnyId(u.getId());
    }
    
    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public OrderedProductFacade getOrderedProductFacade() {
        return orderedProductFacade;
    }

    public void setOrderedProductFacade(OrderedProductFacade orderedProductFacade) {
        this.orderedProductFacade = orderedProductFacade;
    }

    public CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }

    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public CardFacade getCardFacade() {
        return cardFacade;
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    /**
     * Creates a new instance of Database
     */
    public Database() {
    }
    
}
