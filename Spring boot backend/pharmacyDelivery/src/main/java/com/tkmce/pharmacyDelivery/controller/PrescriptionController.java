package com.tkmce.pharmacyDelivery.controller;

import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;
import com.tkmce.pharmacyDelivery.service.AwsService;
import com.tkmce.pharmacyDelivery.service.PrescriptionImageService;
import com.tkmce.pharmacyDelivery.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pharmacy/images")
@CrossOrigin(origins = "*")
@Slf4j
public class PrescriptionController {
    private final AwsService awsService;
    private final PrescriptionService prescriptionService;
    private final PrescriptionImageService prescriptionImageService;

    @Autowired
    public PrescriptionController(AwsService awsService, PrescriptionService prescriptionService, PrescriptionImageService prescriptionImageService) {
        this.awsService = awsService;
        this.prescriptionService = prescriptionService;

        this.prescriptionImageService = prescriptionImageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("logNo") String logNo) {

        String s3Key = awsService.uploadFile(file);
        s3Key = s3Key.substring(s3Key.lastIndexOf(":") + 1).trim();

        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setImageReference(s3Key);
        prescriptionService.createPrescription(prescriptionDto);

        // Assuming you have a method to update the logNo in the PrescriptionImage table
        prescriptionImageService.updatePrescriptionImage(logNo, s3Key);

        return ResponseEntity.ok("Image uploaded successfully. S3 key: " + s3Key);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = awsService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
