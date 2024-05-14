package com.webshop.repository;

import com.webshop.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, String> {
    Kategorija findByNazivKategorije(String nazivKategorije);
}
