/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author yo
 */
@Stateful
public class Hello implements HelloInterface {
    int number = 0;
    
    @Override
    public void init(int nb){
        number = nb;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void add(int nb) {
        number+=nb;
    }

    @Override
    public void sub(int nb) {
        number-=nb;
    }

    @Override
    public void mul(int nb) {
        number*=nb;
    }

    @Override
    public void div(int nb) {
        number/=nb;
    }
    
    @Override
    public int getNumber(){
        return number;
    }
}
