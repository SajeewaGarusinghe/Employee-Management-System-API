package com.sajeewa.emp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Mission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String duration;

    @ManyToMany(mappedBy = "missions")
    private List<Employee> employees;




}


