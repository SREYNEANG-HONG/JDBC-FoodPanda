package model.dao;

import model.entity.Customer;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> queryAllProducts() {
        String sql = """
                SELECT * FROM "product"
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
            List<Product> products = new ArrayList<>();
            while(resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_code"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("imported_at"),
                        resultSet.getDate("expired_at"),
                        resultSet.getString("product_description")

                ));

            }
            return products;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteProductById(Integer id) {
        String sql = """
                DELETE FROM "product" WHERE id = ?
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
    public int addNewProduct(Product product) {
        String  sql = """
                INSERT INTO "product" (product_name, product_code, is_deleted , imported_at , expired_at , product_description)
                VALUES (?, ?, ?, ?, ?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "Neang@@@619"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setBoolean(3, product.getIsDeleted());
            preparedStatement.setDate(4, product.getImportDate());
            preparedStatement.setDate(5, product.getExpiredDate());
            preparedStatement.setString(6,product.getProductDescription());
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
    public int updateProductById(Integer id) {
        String sql = """
                UPDATE "product"
                SET product_name = ? ,product_code = ?
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

            Product product = searchProductById(id);
            if(product == null){
                System.out.println("Product Not found");
            }else {
                System.out.print("Insert Product Name :");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("Insert Product Code :");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3, id);

                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0){
                    System.out.println("Update Successfully");
                }else {
                    System.out.println("Can't update " );
                }
            }
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProductById(Integer id) {
        String sql = """
                SELECT * FROM "product"
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
            Product product = null;
            while(resultSet.next()) {
                product = Product.builder().
                        id(resultSet.getInt("id"))
                        .productName(resultSet.getString("product_name"))
                        .productCode(resultSet.getString("product_code"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .importDate(resultSet.getDate("imported_at"))
                        .expiredDate(resultSet.getDate("expired_at"))
                        .productDescription(resultSet.getString("product_description"))
                        .build();
            }

            return product;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
