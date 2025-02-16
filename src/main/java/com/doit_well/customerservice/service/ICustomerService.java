package com.doit_well.customerservice.service;

import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.entity.UpdateCustomerRequest;
import com.doit_well.customerservice.entity.Users;

import java.util.List;

public interface ICustomerService {

    CustomerDto createCustomer(CreateCustomerRequest request);
    CustomerDto updateCustomer(UpdateCustomerRequest request);
    CustomerDto getCustomer(Integer id);
    List<CustomerDto> getAllCustomers();
    Boolean deleteCustomer(Integer customerId);
    Users createUserAccount(Customer customer);
}
