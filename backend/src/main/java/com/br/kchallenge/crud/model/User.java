package com.br.kchallenge.crud.model;

import com.br.kchallenge.crud.enums.RolesEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolesEnum role = RolesEnum.USER; //

    @Column(nullable = false)
    private boolean isActive = true;

    // public User orElse(Object object) {
    //     if (object instanceof User user) {
    //         return user;
    //     }
    //     return null;
    // }
}
