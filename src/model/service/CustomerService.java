package model.service;

import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CreateCustomerDto;
import model.entity.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDao customerDao = new CustomerDaoImpl();

    public List<Customer> getAllCustomers() {
        return customerDao.queryAllCustomers();
    }

    public void addNewCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = Customer.builder()
                .name(createCustomerDto.name())
                .email(createCustomerDto.email())
                .password(createCustomerDto.password())
                .isDeleted(createCustomerDto.is_deleted())
                .createdDate(createCustomerDto.created_at())
                .build();
        customerDao.addNewCustomer(customer);
    }

    public void updateCustomer(Integer id) {
        customerDao.updateCustomerById(id);
    }

    public void deleteCustomer(Integer id) {
        customerDao.deleteCustomerById(id);
    }

    public Customer getCustomerById(Integer id) {
        return customerDao.searchCustomerById(id);
    }
}