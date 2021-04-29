package com.dve.petclinic.doctorsManagement.reading;

import com.dve.petclinic.doctorsManagement.DoctorFetcher;
import org.springframework.stereotype.Service;

@Service
public class DoctorReadingService {

    private final DoctorResponseModelMapper modelMapper = new DoctorResponseModelMapper();
    private final DoctorFetcher doctorFetcher;

    public DoctorReadingService(DoctorFetcher doctorFetcher) {
        this.doctorFetcher = doctorFetcher;
    }

    public DoctorResponseModel getDoctorById(Long doctorId) {
        return modelMapper.mapToModel(doctorFetcher.fetchDoctorById(doctorId));
    }
}
