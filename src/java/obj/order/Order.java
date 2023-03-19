package obj.order;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int SHIPMENT_DURATION = 2;

    private int orderID;
    private LocalDate orderDate;
    private LocalDate shipDate;
    private OrderStatuses status;
    private int accountID;

    public Order() {
        this.orderID = 0;
        this.orderDate = LocalDate.now();
        this.shipDate = LocalDate.now();
        this.status = OrderStatuses.UNDEFINED;
        this.accountID = 0;
    }

    public Order(int orderID, LocalDate orderDate, LocalDate shipDate, OrderStatuses status, int accountID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accountID = accountID;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * @return the orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the shipDate
     */
    public LocalDate getShipDate() {
        return shipDate;
    }

    /**
     * @param shipDate the shipDate to set
     */
    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    /**
     * @return the status
     */
    public OrderStatuses getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatuses status) {
        this.status = status;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
