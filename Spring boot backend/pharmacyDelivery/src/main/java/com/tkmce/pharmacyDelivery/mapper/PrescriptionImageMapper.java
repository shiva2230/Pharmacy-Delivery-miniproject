package com.tkmce.pharmacyDelivery.mapper;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.dto.PrescriptionImageDto;
import com.tkmce.pharmacyDelivery.entity.Login;
import com.tkmce.pharmacyDelivery.entity.PrescriptionImage;

public class PrescriptionImageMapper {
    public static PrescriptionImageDto mapToPrescriptionImageDto(PrescriptionImage prescriptionImage){
        return new PrescriptionImageDto(
                prescriptionImage.getId(),
                prescriptionImage.getLogNo(),
                prescriptionImage.getName(),
                prescriptionImage.getEmail(),
                prescriptionImage.getImageRef()
        );
    }
    public static PrescriptionImage mapToPrescriptionImage(PrescriptionImageDto prescriptionImageDto){
        return new PrescriptionImage(
                prescriptionImageDto.getId(),
                prescriptionImageDto.getLogNo(),
                prescriptionImageDto.getName(),
                prescriptionImageDto.getEmail(),
                prescriptionImageDto.getImageRef()
        );
    }
}

