package com.br.kchallenge.crud.model;

import jakarta.persistence.*; // Importing JPA annotations for entity mapping
import lombok.Data; // Importing Lombok for automatic getter/setter generation
import lombok.NoArgsConstructor; // Importing Lombok for no-args constructor generation
import lombok.AllArgsConstructor; // Importing Lombok for all-args constructor generation

@Entity // Indicates that this class is a JPA entity
@Table(name = "users") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
      // toString methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the primary key is auto-generated
    private Long id; // Unique identifier for the user

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;
}
