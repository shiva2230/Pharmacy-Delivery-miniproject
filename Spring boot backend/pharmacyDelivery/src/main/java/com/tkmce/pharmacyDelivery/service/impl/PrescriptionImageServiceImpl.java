package com.tkmce.pharmacyDelivery.service.impl;

import com.tkmce.pharmacyDelivery.dto.PrescriptionImageDto;
import com.tkmce.pharmacyDelivery.entity.PrescriptionImage;
import com.tkmce.pharmacyDelivery.mapper.PrescriptionImageMapper;
import com.tkmce.pharmacyDelivery.repository.PrescriptionImageRepository;
import com.tkmce.pharmacyDelivery.service.PrescriptionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionImageServiceImpl implements PrescriptionImageService {

    private final PrescriptionImageRepository repository;

    @Autowired
    public PrescriptionImageServiceImpl(
            PrescriptionImageRepository repository
            ) {
        this.repository = repository;

    }

    @Override
    public PrescriptionImageDto createPrescriptionImage(PrescriptionImageDto prescriptionImageDto) {
        PrescriptionImage entity = PrescriptionImageMapper.mapToPrescriptionImage(prescriptionImageDto);
        PrescriptionImage savedEntity = repository.save(entity);
        return PrescriptionImageMapper.mapToPrescriptionImageDto(savedEntity);
    }

    @Override
    public PrescriptionImageDto updatePrescriptionImage(String logNo, String imageRef) {
        PrescriptionImage prescriptionImage = repository.findByLogNo(logNo);

        if (prescriptionImage != null) {
            prescriptionImage.setImageRef(imageRef);
            PrescriptionImage updatedEntity = repository.save(prescriptionImage);
            return PrescriptionImageMapper.mapToPrescriptionImageDto(updatedEntity);
        } else {
            // Handle the case where the prescription image with the given logNo is not found
            return null;
        }
    }


    @Override
    public PrescriptionImageDto getPrescriptionImageByImageRef(String imageRef) {
        PrescriptionImage entity = repository.findByImageRef(imageRef);
        return PrescriptionImageMapper.mapToPrescriptionImageDto(entity);
    }
}
