package com.isystem.ilibraryapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private Integer phone;

    private String address;

    private LocalDate membershipDate;

}
