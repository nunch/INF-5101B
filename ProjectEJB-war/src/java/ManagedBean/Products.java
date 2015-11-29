/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Controller.CategoryFacade;
import Controller.ProductFacade;
import Entity.Category;
import Entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author yo
 */
@ManagedBean
@RequestScoped
public class Products {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CategoryFacade categoryFacade;

    private Part image;
    private String name;
    private String description;
    private double price;
    private int categoryId;

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
    
    /**
     * Creates a new instance of Product
     */
    public Products() {
    }
    
    public String addProduct() throws InterruptedException{
        Entity.Product p = new Entity.Product();
        Category c = categoryFacade.find(categoryId);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        User u = (User) session.getAttribute("user");
        String path = facesContext.getExternalContext().getRealPath("");
        path = path.substring(0, path.indexOf("ProjectEJB")-1);
        String folder="/ProjectEJB/ProjectEJB-war/web/images/";
        System.out.println(path+folder);
        String namee = ""+System.currentTimeMillis();
        String truc = image.getSubmittedFileName();
        int i = image.getSubmittedFileName().lastIndexOf('.');
        String imageName = namee+image.getSubmittedFileName().substring(i);
        String imagePath = path+folder+imageName;
        System.out.println(imagePath);
        
        String imageBDDName = "/ProjectEJB-war/images/"+imageName;
        p.setCategoryId(c);
        p.setCompagnyId(u);
        p.setDescription(description);
        p.setName(name);
        p.setPrice(new BigDecimal(price));
        p.setImage(imageBDDName);
        
        try {
            InputStream input = image.getInputStream();
            Files.copy(input,new File(path+folder, imageName).toPath());
        } catch (IOException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        productFacade.create(p);
        return "Ok";
    }
    
}
