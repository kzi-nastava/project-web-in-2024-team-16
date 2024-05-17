package com.webshop.repository;

import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, String> {

    Optional<Kupac> findById(Long id);
}
