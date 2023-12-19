package com.tkmce.pharmacyDelivery.repository;

import com.tkmce.pharmacyDelivery.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByMedicineName(String medicineName);
}
