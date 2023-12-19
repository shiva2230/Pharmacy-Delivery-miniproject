package com.tkmce.pharmacyDelivery.service.impl;


import com.tkmce.pharmacyDelivery.dto.MedicineDto;
import com.tkmce.pharmacyDelivery.entity.Medicine;
import com.tkmce.pharmacyDelivery.mapper.MedicineMapper;
import com.tkmce.pharmacyDelivery.repository.MedicineRepository;
import com.tkmce.pharmacyDelivery.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream().map(MedicineMapper::mapToMedicineDto).collect(Collectors.toList());
    }

    @Override
    public MedicineDto getMedicineDetailsByName(String medicineName) {
        Optional<Medicine> medicineOptional = medicineRepository.findByMedicineName(medicineName);
        return medicineOptional.map(MedicineMapper::mapToMedicineDto).orElse(null);
    }
}

