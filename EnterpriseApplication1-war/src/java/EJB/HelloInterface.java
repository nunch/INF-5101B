/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Remote;

/**
 *
 * @author yo
 */
@Remote
public interface HelloInterface {
    public void add(int nb);
    public void sub(int nb);
    public void mul(int nb);
    public void div(int nb);
    public void init(int nb);
    public int getNumber();
    
}
