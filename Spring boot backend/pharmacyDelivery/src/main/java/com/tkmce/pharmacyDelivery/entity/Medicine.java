package com.tkmce.pharmacyDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "type_of_sell")
    private String typeOfSell;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "salt")
    private String salt;

    @Column(name = "mrp")
    private String mrp;

    @Column(name = "uses")
    private String uses;

    @Column(name = "alternate_medicines")
    private String alternateMedicines;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "how_to_use")
    private String howToUse;

    @Column(name = "chemical_class")
    private String chemicalClass;

    @Column(name = "habit_forming")
    private String habitForming;

    @Column(name = "therap_class")
    private String therapClass;

    @Column(name = "action_class")
    private String actionClass;

    @Column(name = "how_it_works")
    private String howItWorks;
}
