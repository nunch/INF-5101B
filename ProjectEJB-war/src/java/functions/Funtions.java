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

/**
 *
 * @author yo
 */
public class Funtions extends SimpleTagSupport {
    
    Category cat;
    String lang;

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public void setLang(String language) {
        this.lang = language;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        String name;
        if(lang.equals("fr")) name = cat.getNameFr();
        else name = cat.getNameEng();
        JspWriter out = getJspContext().getOut();
        out.println(name);
      }
}
