package com.tkmce.pharmacyDelivery.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.tkmce.pharmacyDelivery.service.AwsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class AwsServiceImpl implements AwsService {

    private final AmazonS3 s3Client;
    String bucketName = "prescriptionstoragebucket";

    @Autowired
    public AwsServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

//    public String uploadImage(MultipartFile file) {
//        String fileName = UUID.randomUUID().toString();
//        String key = fileName;
//
//        try {
//            long contentLength = file.getSize();
//
//            System.out.println(bucketName+" "+key+" "+file.getInputStream());
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(contentLength);
//            s3Client.putObject(bucketName, key, file.getInputStream(), metadata);
//            return key;
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to upload image to S3", e);
//        }
//    }

    @Override
    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }


    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }


}
