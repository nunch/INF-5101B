/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author yo
 */
@Embeddable
public class CommandedProductPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "command_id")
    private int commandId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id")
    private int productId;

    public CommandedProductPK() {
    }

    public CommandedProductPK(int commandId, int productId) {
        this.commandId = commandId;
        this.productId = productId;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commandId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandedProductPK)) {
            return false;
        }
        CommandedProductPK other = (CommandedProductPK) object;
        if (this.commandId != other.commandId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CommandedProductPK[ commandId=" + commandId + ", productId=" + productId + " ]";
    }
    
}
