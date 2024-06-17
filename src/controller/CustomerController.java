package controller;

import mapper.Mapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CreateCustomerDto;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public class CustomerController {
    private final CustomerDao customerDao = new CustomerDaoImpl();

    public List<Customer> getAllCustomers() {
        return customerDao.queryAllCustomers();
    }

    public void addNewCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = Customer.builder()
                .name(createCustomerDto.name())
                .email(createCustomerDto.email())
                .password(createCustomerDto.password())
                .build();
        customerDao.addNewCustomer(customer);
    }

    public void updateCustomer(Integer id) {
        customerDao.updateCustomerById(id);
    }

    public void deleteCustomer(Integer id) {
        customerDao.deleteCustomerById(id);
    }

    public CustomerDto getCustomerDto(Integer id) {
        Customer customer = customerDao.searchCustomerById(id);
        return Mapper.fromCustomerToCustomerDto(customer);
    }
}