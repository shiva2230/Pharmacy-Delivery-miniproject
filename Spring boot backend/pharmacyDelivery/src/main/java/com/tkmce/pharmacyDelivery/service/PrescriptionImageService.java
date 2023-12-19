package com.tkmce.pharmacyDelivery.service;

import com.tkmce.pharmacyDelivery.dto.PrescriptionImageDto;
import com.tkmce.pharmacyDelivery.entity.PrescriptionImage;

public interface PrescriptionImageService {
    PrescriptionImageDto createPrescriptionImage(PrescriptionImageDto prescriptionImageDTO);

    PrescriptionImageDto updatePrescriptionImage(String logNo,String ImageRef);

    PrescriptionImageDto getPrescriptionImageByImageRef(String imageRef);

}
