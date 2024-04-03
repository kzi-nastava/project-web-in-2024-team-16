package com.webshop.repository;

import com.webshop.model.Ponuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda, Long> {

}
