package com.webshop.repository;

import com.webshop.model.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdavacRepository extends JpaRepository<Prodavac, String> {

}
