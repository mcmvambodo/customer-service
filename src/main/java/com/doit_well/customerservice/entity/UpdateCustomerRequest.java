package com.doit_well.customerservice.entity;

public record UpdateCustomerRequest(
        Integer id,
        String firstname,
        String lastname,
        String tel,
        String country,
        String address,
        String city,
        String profileImage,
        String cniNumber,
        String cniImage
) {
}
