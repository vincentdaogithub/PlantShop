package obj.plant;

import java.io.Serializable;

public class Plant implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String name;
    private int price;
    private String description;
    private int status;
    private int categoryID;

    public Plant() {
        this.ID = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.status = 0;
        this.categoryID = 0;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
