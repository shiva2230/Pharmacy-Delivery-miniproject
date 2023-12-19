package com.tkmce.pharmacyDelivery.repository;

import com.tkmce.pharmacyDelivery.entity.PrescriptionImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionImageRepository extends JpaRepository<PrescriptionImage, Long> {

    PrescriptionImage findByImageRef(String imageRef);
    PrescriptionImage findByLogNo(String logNo);

    }