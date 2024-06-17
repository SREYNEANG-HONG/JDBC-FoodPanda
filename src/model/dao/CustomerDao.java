package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(Integer id);
    int addNewCustomer(Customer customer);
    int updateCustomerById(Integer id);
    Customer searchCustomerById(Integer id);
}
