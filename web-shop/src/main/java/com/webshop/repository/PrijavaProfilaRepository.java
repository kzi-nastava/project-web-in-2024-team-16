package com.webshop.repository;

import com.webshop.model.PrijavaProfila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrijavaProfilaRepository extends JpaRepository<PrijavaProfila, Long> {
}
