/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

/**
 *
 * @author yo
 */
public class FunctionJSF {
    
    public static String catName(Entity.Category cat, String lang){
        if(lang.equals("fr")) return cat.getNameFr();
        return cat.getNameEng();
    }
    
}
