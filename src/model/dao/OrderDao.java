package model.dao;

import model.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> queryAllOrders();
    int addNewOrder(Order order);
    int updateOrderById(Integer id);
    int deleteOrderById(Integer id);
    Order searchOrderById(Integer id);
}
