package com.tkmce.pharmacyDelivery.controller;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.dto.MedicineDto;
import com.tkmce.pharmacyDelivery.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    private final MedicineService medicineService;
    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<MedicineDto>> getAllMedicines(){
        List<MedicineDto> medicineDtos=medicineService.getAllMedicines();
        return ResponseEntity.ok(medicineDtos);
    }

    @GetMapping("/details")
    public ResponseEntity<MedicineDto> getMedicineDetails(@RequestParam("medicineName") String medicineName) {
        MedicineDto medicineDetails = medicineService.getMedicineDetailsByName(medicineName);
        return ResponseEntity.ok(medicineDetails);
    }

}
