package com.webshop.repository;

import com.webshop.model.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {
}
