package com.doit_well.customerservice.controller;

import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.UpdateCustomerRequest;
import com.doit_well.customerservice.helper.Common;
import com.doit_well.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(Common.BASE_PATH + "/customer")
@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest request){
        return new ResponseEntity<CustomerDto>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody UpdateCustomerRequest request){
        return new ResponseEntity<>(customerService.updateCustomer(request), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> findCustomer(@PathVariable Integer id){
        return new ResponseEntity<CustomerDto>(customerService.getCustomer(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public Boolean deleteCustomer(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }
}
