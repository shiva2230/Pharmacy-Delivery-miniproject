package com.tkmce.pharmacyDelivery.mapper;

import com.tkmce.pharmacyDelivery.dto.MedicineDto;
import com.tkmce.pharmacyDelivery.dto.PrescriptionDto;
import com.tkmce.pharmacyDelivery.entity.Medicine;
import com.tkmce.pharmacyDelivery.entity.Prescription;

public class MedicineMapper {

    public static MedicineDto mapToMedicineDto(Medicine medicine){
        return new MedicineDto(
                medicine.getMedicineId(),
                medicine.getMedicineName(),
                medicine.getPrescription(),
                medicine.getTypeOfSell(),
                medicine.getManufacturer(),
                medicine.getSalt(),
                medicine.getMrp(),
                medicine.getUses(),
                medicine.getAlternateMedicines(),
                medicine.getSideEffects(),
                medicine.getHowToUse(),
                medicine.getChemicalClass(),
                medicine.getHabitForming(),
                medicine.getTherapClass(),
                medicine.getActionClass(),
                medicine.getHowItWorks()
        );
    }

    public static Medicine mapToMedicine(MedicineDto medicineDto){
        return new Medicine(
                medicineDto.getMedicineId(),
                medicineDto.getMedicineName(),
                medicineDto.getPrescription(),
                medicineDto.getTypeOfSell(),
                medicineDto.getManufacturer(),
                medicineDto.getSalt(),
                medicineDto.getMrp(),
                medicineDto.getUses(),
                medicineDto.getAlternateMedicines(),
                medicineDto.getSideEffects(),
                medicineDto.getHowToUse(),
                medicineDto.getChemicalClass(),
                medicineDto.getHabitForming(),
                medicineDto.getTherapClass(),
                medicineDto.getActionClass(),
                medicineDto.getHowItWorks()
        );
    }
}
