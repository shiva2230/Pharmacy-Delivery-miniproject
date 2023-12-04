package com.tkmce.pharmacyDelivery.controller;

import com.amazonaws.util.IOUtils;
import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;
import com.tkmce.pharmacyDelivery.service.AwsService;
import com.tkmce.pharmacyDelivery.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/pharmacy/images")
@CrossOrigin(origins = "*")
@Slf4j
public class PrescriptionController {
    private final AwsService awsService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(AwsService awsService, PrescriptionService prescriptionService) {
        this.awsService = awsService;
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String s3Key = awsService.uploadFile(file);

        PrescriptionDto prescriptionDto= new PrescriptionDto();
        prescriptionDto.setImageReference(s3Key);
        prescriptionService.createPrescription(prescriptionDto);

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
