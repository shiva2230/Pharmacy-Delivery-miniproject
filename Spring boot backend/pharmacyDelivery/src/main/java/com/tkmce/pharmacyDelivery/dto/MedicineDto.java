package com.tkmce.pharmacyDelivery.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {

    private Long medicineId;

    private String medicineName;

    private String prescription;

    private String typeOfSell;

    private String manufacturer;

    private String salt;

    private String mrp;

    private String uses;

    private String alternateMedicines;

    private String sideEffects;

    private String howToUse;

    private String chemicalClass;

    private String habitForming;

    private String therapClass;

    private String actionClass;

    private String howItWorks;
}
