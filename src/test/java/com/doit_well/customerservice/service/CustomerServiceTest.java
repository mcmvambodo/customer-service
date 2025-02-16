package com.doit_well.customerservice.service;

import com.doit_well.customerservice.db.CustomerTestDatabase;
import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.mapper.CustomerMapper;
import com.doit_well.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    void givenCreateCustomerRequest_whenCreateCustomer_thenReturnCustomerDto(){
        //given
        CreateCustomerRequest customerRequest = CustomerTestDatabase.createCustomerRequestWithNoAccount;
        Customer customer = CustomerTestDatabase.customerWithNoAccount;
        CustomerDto customerDto = CustomerTestDatabase.customerDtoWithNoAccount;

        //Mock calls
        Mockito.when(customerMapper.toCustomer(customerRequest)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerMapper.toDto(customer)).thenReturn(customerDto);

        //when
        CustomerDto response = customerService.createCustomer(customerRequest);

        //then
        assertNotNull(response);
        assertEquals(response.firstname(),customerRequest.firstname());
        assertEquals(response.id(),1);

        // verify
        Mockito.verify(customerMapper, Mockito.times(1)).toCustomer(customerRequest);
        Mockito.verify(customerMapper, Mockito.times(1)).toDto(customer);
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);

    }
}
