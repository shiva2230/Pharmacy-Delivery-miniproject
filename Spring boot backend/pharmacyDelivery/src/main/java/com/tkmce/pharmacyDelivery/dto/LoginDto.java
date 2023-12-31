package com.tkmce.pharmacyDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private Long id;
    private String logNo;
    private String name;
    private String pass;
    private String email;
}
