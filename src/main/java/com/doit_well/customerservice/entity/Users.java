package com.doit_well.customerservice.entity;

import com.doit_well.customerservice.helper.CustomAuthorityDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Users implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty()
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;
    @Column(nullable = true)
    private LocalDateTime dob;
//    @Column(nullable = true)
//    private String country = "Cameroun";
//    @Column(nullable = true)
//    private String city;
//    @Column(nullable = true)
//    private String profil_image;
//    @Column(nullable = true)
//    private String cni_number;
//    @Column(nullable = true)
//    private String cni_image;
//    @Column(nullable = true)
//    private LocalDateTime created_at;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }



    @Override
    public boolean isEnabled() {
        return true;
    }
}
