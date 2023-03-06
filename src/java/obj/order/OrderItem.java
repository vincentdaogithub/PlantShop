package obj.order;

import java.io.Serializable;

public class OrderItem implements Serializable, Comparable<OrderItem> {
    
    private static long serialVersionUID = 1L;

    private int PID;
    private int quantity;

    public OrderItem() {
        this.PID = 0;
        this.quantity = 0;
    }

    public OrderItem(int PID, int quantity) {
        this.PID = PID;
        this.quantity = quantity;
    }

    @Override
    public final int compareTo(OrderItem orderItemToCompare) {
        return Integer.compare(PID, orderItemToCompare.getPID());
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
