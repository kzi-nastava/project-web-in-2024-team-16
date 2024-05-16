package com.webshop.repository;

import com.webshop.DTO.ProdavacDTO;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdavacRepository extends JpaRepository<Prodavac, String> {
    Optional<Prodavac> findById(Long id);

    Optional<Prodavac> findByKorisnickoIme(String korisnickoIme);

    Prodavac findProdavacByKorisnickoIme(String prodavacKorisnickoIme);
}
