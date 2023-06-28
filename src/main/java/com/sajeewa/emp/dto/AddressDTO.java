package com.sajeewa.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
}
