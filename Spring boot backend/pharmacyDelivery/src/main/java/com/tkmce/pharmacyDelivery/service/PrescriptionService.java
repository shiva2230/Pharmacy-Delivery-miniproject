package com.tkmce.pharmacyDelivery.service;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;

import java.util.List;

public interface PrescriptionService{
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto);
    List<PrescriptionDto> getAllPrescriptions();
}
