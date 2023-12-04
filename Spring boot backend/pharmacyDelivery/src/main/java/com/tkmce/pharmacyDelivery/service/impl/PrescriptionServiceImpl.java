package com.tkmce.pharmacyDelivery.service.impl;

import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;
import com.tkmce.pharmacyDelivery.entity.Login;
import com.tkmce.pharmacyDelivery.entity.Prescription;
import com.tkmce.pharmacyDelivery.mapper.LoginMapper;
import com.tkmce.pharmacyDelivery.mapper.PrescriptionMapper;
import com.tkmce.pharmacyDelivery.repository.PrescriptionRepository;
import com.tkmce.pharmacyDelivery.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        Prescription prescription = PrescriptionMapper.mapToPrescription(prescriptionDto);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.mapToPrescriptionDto(savedPrescription);
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        List<Prescription> prescriptions=prescriptionRepository.findAll();
        return prescriptions.stream().map((allPrescription) -> PrescriptionMapper.mapToPrescriptionDto(allPrescription)).collect(Collectors.toList());

    }
}
