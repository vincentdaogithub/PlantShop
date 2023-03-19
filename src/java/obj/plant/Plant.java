package obj.plant;

import java.io.Serializable;

public class Plant implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String name;
    private int price;
    private String description;
    private PlantStatuses status;
    private PlantCategories category;

    public Plant() {

        this.ID = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.status = PlantStatuses.INACTIVE;
        this.category = PlantCategories.UNDEFINED;
    }

    public Plant(int ID, String name, int price, String description, PlantStatuses status, PlantCategories category) {

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.category = category;
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
    public PlantStatuses getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(PlantStatuses status) {
        this.status = status;
    }

    /**
     * @return the category
     */
    public PlantCategories getCategory() {
        return category;
    }

    /**
     * @param category the categoryID to set
     */
    public void setCategory(PlantCategories category) {
        this.category = category;
    }
}
