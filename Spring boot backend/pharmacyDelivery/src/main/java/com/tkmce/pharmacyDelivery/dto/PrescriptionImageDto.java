package com.tkmce.pharmacyDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionImageDto{
    private Long id;
    private String logNo;
    private String name;
    private String email;
    private String imageRef;
}


