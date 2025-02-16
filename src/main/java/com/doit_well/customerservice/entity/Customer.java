package com.doit_well.customerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Table(name = "customer")
@Entity
@Data
@AllArgsConstructor
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(nullable = false)
    private String tel;
    private String country = "Cameroun";
    private String city;
    private String address;
    private String profileImage;
    private String cniNumber;
    private String cniImage;
    private LocalDateTime createdAt;
    private Boolean hasAccount;

    public Customer(){
        createdAt = LocalDateTime.now(ZoneId.of("Africa/Douala"));
    }
}
