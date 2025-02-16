package com.doit_well.customerservice.db;

import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.entity.UpdateCustomerRequest;

import java.time.LocalDateTime;

public class CustomerTestDatabase {
    public static final CreateCustomerRequest createCustomerRequestWithNoAccount =
            new CreateCustomerRequest(
                    "Francky",
                    "Abolo",
                    "abolo@email.com",
                    "abcd1234",
                    "00237546354653",
                    "Central 2",
                    "Cameroun",
                    "Ayos",
                    "/",
                    "20173567865537",
                    "/",
                    false
                    );

    public static final Customer customerWithNoAccount =
            new Customer(
                    1,
                    "Francky",
                    "Abolo",
                    "abolo@email.com",
                    "abcd1234",
                    "00237546354653",
                    "Cameroun",
                    "Ayos",
                    "Central 2",
                    "/",
                    "20173567865537",
                    "/",
                    LocalDateTime.of(2024,12,22,14,50),
                    false
            );
    public static final CustomerDto customerDtoWithNoAccount =
            new CustomerDto(
                    1,
                    "Francky",
                    "Abolo",
                    "abolo@email.com",
                    "00237546354653",
                    "Central 2",
                    "Cameroun",
                    "Ayos",
                    "/",
                    false
            );
    public static final CreateCustomerRequest createCustomerRequestWithAccount =
            new CreateCustomerRequest(
                    "Baby",
                    "Rimy",
                    "rimy@email.com",
                    "abcd1234",
                    "00237693718139",
                    "Biyem-Assi",
                    "Cameroun",
                    "Yaounde",
                    "/",
                    "20173567865537",
                    "/",
                    true
                    );
    public static final CustomerDto customerDtoWithAccount =
            new CustomerDto(
                    2,
                    "Baby",
                    "Rimy",
                    "rimy@email.com",
                    "00237693718139",
                    "Biyem-Assi",
                    "Cameroun",
                    "Yaounde",
                    "/",
                    true
                    );

    public static final UpdateCustomerRequest updateRequest =
            new UpdateCustomerRequest(
                    1,
                    "Francky",
                    "Abolo Abolo",
                    "00237546354653",
                    "Cameroun",
                    "Biyem-Assi",
                    "Ayos",
                    "/",
                    "20173567865537",
                    "/"
            );
}
