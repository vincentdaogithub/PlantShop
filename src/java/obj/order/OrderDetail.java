package obj.order;

import java.io.Serializable;

public class OrderDetail implements Serializable, Comparable<OrderDetail> {
    
    private static long serialVersionUID = 1L;

    private int orderID;
    private int PID;
    private int quantity;

    public OrderDetail() {
        this.orderID = 0;
        this.PID = 0;
        this.quantity = 0;
    }

    public OrderDetail(int orderID, int PID, int quantity) {
        this.orderID = orderID;
        this.PID = PID;
        this.quantity = quantity;
    }

    @Override
    public final int compareTo(OrderDetail object) {
        return Integer.compare(orderID, object.getPID());
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the PID
     */
    public int getPID() {
        return PID;
    }

    /**
     * @param PID the PID to set
     */
    public void setPID(int PID) {
        this.PID = PID;
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
