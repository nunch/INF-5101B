/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author yo
 */
@Entity
@Table(name = "commanded_product")
@NamedQueries({
    @NamedQuery(name = "CommandedProduct.findAll", query = "SELECT c FROM CommandedProduct c"),
    @NamedQuery(name = "CommandedProduct.findByCommandId", query = "SELECT c FROM CommandedProduct c WHERE c.commandedProductPK.commandId = :commandId"),
    @NamedQuery(name = "CommandedProduct.findByProductId", query = "SELECT c FROM CommandedProduct c WHERE c.commandedProductPK.productId = :productId"),
    @NamedQuery(name = "CommandedProduct.findByQuantity", query = "SELECT c FROM CommandedProduct c WHERE c.quantity = :quantity")})
public class CommandedProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommandedProductPK commandedProductPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "command_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Command command;

    public CommandedProduct() {
    }

    public CommandedProduct(CommandedProductPK commandedProductPK) {
        this.commandedProductPK = commandedProductPK;
    }

    public CommandedProduct(CommandedProductPK commandedProductPK, int quantity) {
        this.commandedProductPK = commandedProductPK;
        this.quantity = quantity;
    }

    public CommandedProduct(int commandId, int productId) {
        this.commandedProductPK = new CommandedProductPK(commandId, productId);
    }

    public CommandedProductPK getCommandedProductPK() {
        return commandedProductPK;
    }

    public void setCommandedProductPK(CommandedProductPK commandedProductPK) {
        this.commandedProductPK = commandedProductPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if(commandedProductPK == null) commandedProductPK = new CommandedProductPK();
        commandedProductPK.setProductId(product.getId());
        this.product = product;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        if(commandedProductPK == null) commandedProductPK = new CommandedProductPK();
        commandedProductPK.setCommandId(command.getId());
        this.command = command;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandedProductPK != null ? commandedProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandedProduct)) {
            return false;
        }
        CommandedProduct other = (CommandedProduct) object;
        if ((this.commandedProductPK == null && other.commandedProductPK != null) || (this.commandedProductPK != null && !this.commandedProductPK.equals(other.commandedProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CommandedProduct[ commandedProductPK=" + commandedProductPK + " ]";
    }
    
}
