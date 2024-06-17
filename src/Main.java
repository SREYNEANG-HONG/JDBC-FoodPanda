import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.entity.Customer;
import model.entity.Product;
import model.service.CustomerService;
import model.service.OrderService;
import model.service.ProductService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import static view.View.*;

public class Main {
    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();

















        //add new customer
//        new CustomerDaoImpl()
//                .addNewCustomer(new Customer(
//                        1,
//                        "JOJO",
//                        "jojo@gmail.com",
//                        "456yhjiiuhg",
//                        false,
//                        Date.valueOf(LocalDate.now())
//                ));


        //add new customer with builder
//        new CustomerDaoImpl()
//                .addNewCustomer(
//                        Customer.builder()
//                                .name("Hanko")
//                                .email("hanko@gmail.com")
//                                .password("ha2345#$$")
//                                .isDeleted(false)
//                                .created_Date(Date.valueOf(LocalDate.now()))
//                                .build()
//                );




        //deleted customer
//        new CustomerDaoImpl().deleteCustomerById(2);


        //show all customer
//        new CustomerDaoImpl().queryAllCustomers()
//                .forEach(System.out::println);



        //update customer
//        new CustomerDaoImpl().updateCustomerById(1
//        );

//        -------------------------------------------------------------------------------

        //add new product
//        new ProductDaoImpl()
//                .addNewProduct(new Product(
//                        1,
//                        "Coco",
//                        "#3456",
//                        false,
//                        Date.valueOf(LocalDate.now()),
//                        Date.valueOf(LocalDate.of(2020, 1, 1)),
//                        "Popular Drink"
//
//                ));

        //delete product
//        new ProductDaoImpl().deleteProductById(2);

        //show all product
//        new ProductDaoImpl().queryAllProducts()
//                .forEach(System.out::println);
//


        //update product
//        new ProductDaoImpl().updateProductById(3
//        );







    }
}