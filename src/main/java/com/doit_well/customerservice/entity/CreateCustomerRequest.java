package com.doit_well.customerservice.entity;

import lombok.Builder;

@Builder
public record CreateCustomerRequest(
         String firstname,
         String lastname,
         String email,
         String password,
         String tel,
         String address,
         String country ,
         String city,
         String profileImage,
         String cniNumber,
         String cniImage,
         Boolean createAccount
) {
}
