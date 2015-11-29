package JSF;

import Entity.CommandedProduct;
import JSF.util.JsfUtil;
import JSF.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("commandedProductController")
@SessionScoped
public class CommandedProductController implements Serializable {

    private CommandedProduct current;
    private DataModel items = null;
    @EJB
    private JSF.CommandedProductFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CommandedProductController() {
    }

    public CommandedProduct getSelected() {
        if (current == null) {
            current = new CommandedProduct();
            current.setCommandedProductPK(new Entity.CommandedProductPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private CommandedProductFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (CommandedProduct) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CommandedProduct();
        current.setCommandedProductPK(new Entity.CommandedProductPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getCommandedProductPK().setProductId(current.getProduct().getId());
            current.getCommandedProductPK().setCommandId(current.getCommand().getId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/messages").getString("CommandedProductCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CommandedProduct) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getCommandedProductPK().setProductId(current.getProduct().getId());
            current.getCommandedProductPK().setCommandId(current.getCommand().getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/messages").getString("CommandedProductUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CommandedProduct) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/messages").getString("CommandedProductDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/messages").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public CommandedProduct getCommandedProduct(Entity.CommandedProductPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = CommandedProduct.class)
    public static class CommandedProductControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CommandedProductController controller = (CommandedProductController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "commandedProductController");
            return controller.getCommandedProduct(getKey(value));
        }

        Entity.CommandedProductPK getKey(String value) {
            Entity.CommandedProductPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entity.CommandedProductPK();
            key.setCommandId(Integer.parseInt(values[0]));
            key.setProductId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entity.CommandedProductPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCommandId());
            sb.append(SEPARATOR);
            sb.append(value.getProductId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CommandedProduct) {
                CommandedProduct o = (CommandedProduct) object;
                return getStringKey(o.getCommandedProductPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CommandedProduct.class.getName());
            }
        }

    }

}
