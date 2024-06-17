package controller;

import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.entity.Order;

import java.util.List;

public class OrderController {
    private final OrderDao orderDao = new OrderDaoImpl();

    public List<Order> getAllOrders() {
        return orderDao.queryAllOrders();
    }

    public void addNewOrder(Order order) {
        orderDao.addNewOrder(order);
    }

    public void updateOrder(Integer id) {
        orderDao.updateOrderById(id);
    }

    public void deleteOrder(Integer id) {
        orderDao.deleteOrderById(id);
    }

    public Order getOrderById(Integer id) {
        return orderDao.searchOrderById(id);
    }
}
