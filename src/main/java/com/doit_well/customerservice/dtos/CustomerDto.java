package com.doit_well.customerservice.dtos;

public record CustomerDto(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String tel,
        String address,
        String country ,
        String city,
        String profileImage,
        Boolean hasAccount
) {

}
