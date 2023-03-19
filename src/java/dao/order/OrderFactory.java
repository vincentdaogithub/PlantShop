package dao.order;

import dao.plant.PlantDAO;
import java.time.LocalDate;
import obj.order.Order;
import obj.order.OrderDetail;
import obj.order.OrderStatuses;

public class OrderFactory {

    private static final int SHIPPING_DURATION = 2;

    public static final Order createOrder(Integer accountID, OrderStatuses status) {

        LocalDate currentTime = LocalDate.now();
        LocalDate shippingTime = currentTime.plusDays(SHIPPING_DURATION);

        return new Order(
                0,
                currentTime,
                shippingTime,
                status,
                accountID
        );
    }

    public static final OrderDetail createOrderDetail(Integer plantID, Integer quantity) {

        return new OrderDetail(0, 0, PlantDAO.getPlant(plantID), quantity);
    }
}
