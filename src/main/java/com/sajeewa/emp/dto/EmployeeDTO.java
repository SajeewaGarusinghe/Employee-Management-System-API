package com.sajeewa.emp.dto;

import com.sajeewa.emp.entity.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private int empId;
    private String empName;
    private String empAddress;
    private int empNumber;
    private EmployeeRole employeeRole;
}
