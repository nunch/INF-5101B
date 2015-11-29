/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entity.Category;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author yo
 */
public class Funtions2 extends SimpleTagSupport {
    
    double price;
    String lang;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        String name="";
        if(lang.equals("fr")) {
            price = round(price,2);
            name = price+" &euro;";
        }else if(lang.equals("en")){
            price = price*0.918906501;
            price = round(price,2);
            name = "$"+price;
        }
        JspWriter out = getJspContext().getOut();
        out.println(name);
    }
    
    private double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
