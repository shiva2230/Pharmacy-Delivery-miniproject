package com.tkmce.pharmacyDelivery.repository;

import com.tkmce.pharmacyDelivery.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
