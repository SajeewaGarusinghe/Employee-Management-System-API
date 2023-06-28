package com.sajeewa.emp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Column(nullable = false)
    private String empName;
    @Column(nullable = false)
    private String empAddress;
    @Column(nullable = false, unique = true)
    private int empNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole employeeRole;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany()
    @JoinTable(
            name = "employee_mission",
            joinColumns = {
                    @JoinColumn(name = "employee_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "mission_id")
            }
    )
    private List<Mission> missions;

}
