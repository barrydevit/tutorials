package com.app.demoprojection.repositories;

import com.app.demoprojection.entities.Addresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresse, Long> {
}
