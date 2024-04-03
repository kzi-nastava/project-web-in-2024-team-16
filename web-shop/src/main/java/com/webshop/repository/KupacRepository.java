package com.webshop.repository;

import com.webshop.model.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KupacRepository extends JpaRepository<Kupac, String> {
}
