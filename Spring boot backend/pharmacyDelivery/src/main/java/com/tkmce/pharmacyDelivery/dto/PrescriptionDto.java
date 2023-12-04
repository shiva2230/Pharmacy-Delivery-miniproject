package com.tkmce.pharmacyDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto {
    private Long id;
    private Long customerId;
    private String imageReference;
}
