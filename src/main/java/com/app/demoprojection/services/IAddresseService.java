package com.app.demoprojection.services;


import com.app.demoprojection.entities.Addresse;

import java.util.Optional;

public interface IAddresseService {
    Optional<Addresse> getAddressById(Long id);
}
