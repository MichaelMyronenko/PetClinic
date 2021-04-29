package com.dve.petclinic.doctorsManagement;

import com.dve.petclinic.doctorsManagement.reading.DoctorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final DoctorService doctorService;

    public DoctorsController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseModel> getDoctor(@PathVariable Long doctorId) {
        return new ResponseEntity<>(doctorService.getDoctorByID(doctorId), HttpStatus.OK);
    }
}
