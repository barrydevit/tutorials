package com.app.demoprojection.services.impl;

import com.app.demoprojection.entities.Addresse;
import com.app.demoprojection.repositories.AddressRepository;
import com.app.demoprojection.services.IAddresseService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddresseService implements IAddresseService {
    private final AddressRepository addressRepository;

    public AddresseService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Addresse> getAddressById(Long id) {
        return addressRepository.findById(id);
    }
}
