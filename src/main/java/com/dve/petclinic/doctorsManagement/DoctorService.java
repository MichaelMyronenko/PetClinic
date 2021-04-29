package com.dve.petclinic.doctorsManagement;

import com.dve.petclinic.doctorsManagement.reading.DoctorReadingService;
import com.dve.petclinic.doctorsManagement.reading.DoctorResponseModel;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorReadingService doctorReadingService;


    public DoctorService(DoctorReadingService doctorReadingService) {
        this.doctorReadingService = doctorReadingService;
    }

    public DoctorResponseModel getDoctorByID(Long doctorId) {
        return doctorReadingService.getDoctorById(doctorId);
    }
}
