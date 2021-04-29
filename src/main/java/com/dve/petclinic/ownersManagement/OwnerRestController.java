package com.dve.petclinic.ownersManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {

    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResponseModel> getOwner(@PathVariable Long ownerId) {
        return new ResponseEntity<>(ownerService.getOwnerById(ownerId), HttpStatus.OK);
    }
}
