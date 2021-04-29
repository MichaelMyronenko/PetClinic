package com.dve.petclinic.ownersManagement;

import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerResponseModelMapper modelMapper = new OwnerResponseModelMapper();
    private final OwnerFetcher ownerFetcher;

    public OwnerService(OwnerFetcher ownerFetcher) {
        this.ownerFetcher = ownerFetcher;
    }

    public OwnerResponseModel getOwnerById(Long ownerId) {
        return modelMapper.mapToModel(ownerFetcher.fetchOwnerById(ownerId));
    }
}
