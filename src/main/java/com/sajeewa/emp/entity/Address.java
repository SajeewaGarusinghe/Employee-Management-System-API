package com.sajeewa.emp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
