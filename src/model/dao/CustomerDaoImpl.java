package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            List<Customer> customers = new ArrayList<>();
            while(resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("created_date")
                ));

            }
            return customers;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = """
                DELETE FROM "customer" WHERE id = ?
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
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected > 0 ? "Successfully deleted" : "Failed to delete customer with id " + id;
            System.out.println(message);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String  sql = """
                INSERT INTO "customer" (name, email, password , is_deleted , created_date)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setBoolean(4, customer.getIsDeleted());
            preparedStatement.setDate(5,customer.getCreatedDate());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Insert Successfully");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id) {
        String sql = """
                UPDATE "customer"
                SET name = ? ,email = ?
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            Customer customer = searchCustomerById(id);
            if(customer == null){
                System.out.println("Customer Not found");
            }else {
                System.out.print("Insert Name :");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("Insert Email :");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3, id);

                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0){
                    System.out.println("Update Successfully");
                }else {
                    System.out.println("Can't update customer" );
                }
            }
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Customer searchCustomerById(Integer id) {
        String sql = """
                SELECT * FROM "customer"
                WHERE id = ?
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
            Customer customer = new Customer();
            while(resultSet.next()) {
                customer = Customer.builder().
                        id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .createdDate(resultSet.getDate("created_date"))
                .build();
            }

            return customer;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
