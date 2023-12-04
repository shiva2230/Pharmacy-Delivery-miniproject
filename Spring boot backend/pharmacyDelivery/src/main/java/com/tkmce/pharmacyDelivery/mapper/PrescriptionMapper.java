package com.tkmce.pharmacyDelivery.mapper;

import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;
import com.tkmce.pharmacyDelivery.entity.Prescription;

public class PrescriptionMapper {
    public static PrescriptionDto mapToPrescriptionDto(Prescription prescription){
        return new PrescriptionDto(
                prescription.getId(),
                prescription.getCustomerId(),
                prescription.getImageReference()
        );
    }
    public static Prescription mapToPrescription(PrescriptionDto prescriptionDto){
        return new Prescription(
                prescriptionDto.getId(),
                prescriptionDto.getCustomerId(),
                prescriptionDto.getImageReference()
        );
    }
}
