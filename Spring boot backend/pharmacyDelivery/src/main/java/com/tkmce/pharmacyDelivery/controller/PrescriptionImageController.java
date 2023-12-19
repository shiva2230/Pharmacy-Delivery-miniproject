package com.tkmce.pharmacyDelivery.controller;

import com.tkmce.pharmacyDelivery.dto.PrescriptionImageDto;
import com.tkmce.pharmacyDelivery.service.PrescriptionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/prescription-images")
public class PrescriptionImageController {

    private final PrescriptionImageService prescriptionImageService;

    @Autowired
    public PrescriptionImageController(PrescriptionImageService prescriptionImageService) {
        this.prescriptionImageService = prescriptionImageService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionImageDto> createPrescriptionImage(
            @RequestBody PrescriptionImageDto prescriptionImageDto) {
        PrescriptionImageDto createdImage = prescriptionImageService.createPrescriptionImage(prescriptionImageDto);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @PutMapping("/{logNo}")
    public ResponseEntity<PrescriptionImageDto> updatePrescriptionImage(
            @PathVariable String logNo,
            @RequestBody PrescriptionImageDto prescriptionImageDto) {
        PrescriptionImageDto updatedImage = prescriptionImageService.updatePrescriptionImage(logNo, prescriptionImageDto.getImageRef());
        if (updatedImage != null) {
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{imageRef}")
    public ResponseEntity<PrescriptionImageDto> getPrescriptionImageByImageRef(@PathVariable String imageRef) {
        PrescriptionImageDto image = prescriptionImageService.getPrescriptionImageByImageRef(imageRef);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
