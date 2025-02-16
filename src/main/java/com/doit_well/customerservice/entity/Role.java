package com.doit_well.customerservice.entity;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    private Role(String value){
        this.value = value;
    }
}
