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
@Table(name = "customer_medicine")
public class CustomerMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Login customer;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private String medicines;

    private String instructions;

    private int quantity;

    private double mrp;

}
