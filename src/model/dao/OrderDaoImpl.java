package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl  implements OrderDao {
    @Override
    public List<Order> queryAllOrders() {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN customer c ON "order".cus_id = c.id;
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(Order.builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .orderDate(resultSet.getDate("ordered_at"))
                        .customer(Customer.builder()
                                .id(resultSet.getInt("cus_id"))
                                .name(resultSet.getString("name"))
                                .email(resultSet.getString("email"))
                                .isDeleted(resultSet.getBoolean("is_deleted"))
                                .createdDate(resultSet.getDate("created_date"))
                                .build())
                        .build());
            }
            return orderList;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addNewOrder(Order order) {
        String sql = """
            INSERT INTO "order" (order_name, order_description, cus_id, ordered_at)
        VALUES (?, ?, ?, ?)
        """;
        String sql2 = """
            INSERT INTO "product_order" (pro_id, order_id)
        VALUES (?, ?)
        """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        ){
            preparedStatement.setString(1, order.getOrderName());
            preparedStatement.setString(2, order.getOrderDescription());
            preparedStatement.setInt(3, order.getCustomer().getId());
            preparedStatement.setDate(4, order.getOrderDate());

            int rowsAffected = preparedStatement.executeUpdate();
            String message = rowsAffected > 0 ? "Order added successfully" : "Order add failed";
            System.out.println(message);

            // Product_Order
            for (Product product : order.getProductsList()){
                preparedStatement2.setInt(1, product.getId());
                preparedStatement2.setInt(2, order.getId());

            }
            int rowsAffected2 = preparedStatement2.executeUpdate();
            String message2 = rowsAffected2 > 0 ? "Product_Order added successfully" : "Product_Order add failed";
            System.out.println(message2);
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        String sql = """
                UPDATE "order"
                    SET order_name = ? , order_description = ?
                        WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            Order order = searchOrderById(id);
            if(order != null){
                System.out.print("input order_name: ");
                String order_name = new Scanner(System.in).nextLine();
                System.out.print("input order_description: ");
                String order_description  = new Scanner(System.in).nextLine();

                preparedStatement.setString(1, order_name);
                preparedStatement.setString(2, order_description);
                preparedStatement.setInt(3, id);

                int rowsAffected = preparedStatement.executeUpdate();
                String message = rowsAffected > 0 ? "update is successfully" : "update failed";
                System.out.println(message);
            }else {
                System.out.println("order with id = " + id + " not found");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        String sql = """
                DELETE FROM "order" where id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            Order order = searchOrderById(id);
            if(order != null){
                preparedStatement.setInt(1, order.getId());
                int rowsAffected = preparedStatement.executeUpdate();
                String message = rowsAffected > 0 ? "Order deleted successfully" : "Order deleted failed";
                System.out.println(message);
                return rowsAffected;
            }else {
                System.out.println("Order does not exist");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                SELECT * FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                order = Order.builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .customer(Customer.builder()
                                .id(resultSet.getInt("cus_id"))
                                .build())
                        .orderDate(resultSet.getDate("ordered_at"))
                        .build();
            }
            return order;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
