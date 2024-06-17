package view;

import model.dao.OrderDaoImpl;
import model.dto.CreateCustomerDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.CustomerService;
import model.service.OrderService;
import model.service.ProductService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    public static void printMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1. Display all Customers");
        System.out.println("2. Add new Customer");
        System.out.println("3. Delete Customer by ID");
        System.out.println("4. Update Customer by ID");
        System.out.println("5. Display all Orders");
        System.out.println("6. Add new Order");
        System.out.println("7. Display all Products");
        System.out.println("8. Add new Product");
        System.out.println("9. Update Product by ID");
        System.out.println("10. Delete Product by ID");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }


    public void displayMessage(String message) {
            System.out.println(message);
        }

    public static void displayAllCustomers(CustomerService customerService) {
        View viewDashboard = new View();
        viewDashboard.displayMessage("All Customers:");
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(customer -> viewDashboard.displayMessage(customer.toString()));
    }

    public static void addNewCustomer(Scanner scanner, CustomerService customerService) {
        System.out.println("\nEnter new customer details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        CreateCustomerDto newCustomerDto = CreateCustomerDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .is_deleted(false)
                .created_at(Date.valueOf(LocalDate.now()))
                .build();

        customerService.addNewCustomer(newCustomerDto);
        System.out.println("\nAdded new customer: " + newCustomerDto.name());
    }

    public static void deleteCustomerById(Scanner scanner, CustomerService customerService) {
        System.out.print("\nEnter customer ID to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerService.deleteCustomer(customerIdToDelete);
        System.out.println("\nDeleted customer with ID: " + customerIdToDelete);
    }

    public static void updateCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("\nEnter customer ID to update: ");
        int customerIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerService.updateCustomer(customerIdToUpdate);
        System.out.println("\nUpdated customer with ID: " + customerIdToUpdate);
    }

    public static void displayAllOrders(OrderService orderService) {
        View viewDashboard = new View();
        viewDashboard.displayMessage("\nAll Orders:");
        List<Order> orders = orderService.getAllOrders();
        orders.forEach(order -> viewDashboard.displayMessage(order.toString()));
    }

    public static void addNewOrder(Scanner scanner, OrderService orderService) {

        new OrderDaoImpl().addNewOrder(Order.builder()
                .id(6)
                .orderName("Espresso")
                .orderDescription("strong coffee 2 sot")
                .orderDate(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(6)
                        .build())
                .productsList(new ArrayList<>(List.of(Product.builder()
                        .id(4)
                        .build())))
                .build());
    }

    public static void displayAllProducts(ProductService productService) {
        View viewDashboard = new View();
        viewDashboard.displayMessage("\nAll Products:");
        List<Product> products = productService.getAllProducts();
        products.forEach(product -> viewDashboard.displayMessage(product.toString()));
    }

    public static void addNewProduct(ProductService productService) {

        System.out.println("\nEnter new product details:");
        View viewDashboard = new View();
        Product newProduct = Product.builder()
                .productName("Ice Lemon")
                .productCode("Coffee-1002")
                .isDeleted(false)
                .importDate(Date.valueOf(LocalDate.now()))
                .expiredDate(Date.valueOf(LocalDate.now().plusMonths(6)))
                .productDescription("Sample product description")
                .build();
        productService.addNewProduct(newProduct);
        viewDashboard.displayMessage("\nAdded new product: " + newProduct.getProductName());
    }

    public static void updateProduct(Scanner scanner, ProductService productService) {
        System.out.print("\nEnter product ID to update: ");
        int productIdToUpdate = scanner.nextInt();
        scanner.nextLine();
        productService.updateProduct(productIdToUpdate);
        System.out.println("\nUpdated product with ID: " + productIdToUpdate);
    }

    public static void deleteProduct(Scanner scanner, ProductService productService) {
        System.out.print("\nEnter product ID to delete: ");
        int productIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        productService.deleteProduct(productIdToDelete);
        System.out.println("\nDeleted product with ID: " + productIdToDelete);
    }
}
