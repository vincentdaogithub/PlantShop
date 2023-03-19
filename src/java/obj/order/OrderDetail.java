package obj.order;

import java.io.Serializable;
import obj.plant.Plant;

public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private int detailID;
    private int orderID;
    private Plant plant;
    private int quantity;

    public OrderDetail() {
        this.detailID = 0;
        this.orderID = 0;
        this.plant = new Plant();
        this.quantity = 0;
    }

    public OrderDetail(int detailID, int orderID, Plant plant, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.plant = plant;
        this.quantity = quantity;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the detailID
     */
    public int getDetailID() {
        return detailID;
    }

    /**
     * @param detailID the detailID to set
     */
    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the PID
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * @param plant the PID to set
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
