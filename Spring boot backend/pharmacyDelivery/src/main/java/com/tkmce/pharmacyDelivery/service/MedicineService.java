package com.tkmce.pharmacyDelivery.service;

import com.tkmce.pharmacyDelivery.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    List<MedicineDto> getAllMedicines();

    MedicineDto getMedicineDetailsByName(String medicineName);
}
