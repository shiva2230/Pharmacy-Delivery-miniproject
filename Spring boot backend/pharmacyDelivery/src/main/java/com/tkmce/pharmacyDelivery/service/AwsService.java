package com.tkmce.pharmacyDelivery.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface AwsService {
//    String uploadImage(MultipartFile file);

    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);
}
