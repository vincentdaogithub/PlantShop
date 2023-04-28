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
    private String categoryName;

    public Plant() {

        this.ID = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.status = 0;
        this.categoryID = 0;
        this.categoryName = "";
    }

    public Plant(int ID, String name, int price, String description, int status, int categoryID, String categoryName) {

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        final Plant other = (Plant) obj;
        return this.ID == other.getID();
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

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
