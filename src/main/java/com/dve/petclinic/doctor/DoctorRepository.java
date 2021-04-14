package com.dve.petclinic.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<CommonDoctor, Long> {

}
