package com.doit_well.customerservice.mapper;

import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.entity.Users;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);
    //@Mapping(target = "hasAccount", source = "dto.createAccount")
    Customer toCustomer(CreateCustomerRequest dto);
    Customer toCustomer(CustomerDto dto);
    Users toUser(Customer customer);
}
