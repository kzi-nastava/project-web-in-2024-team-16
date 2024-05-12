package com.webshop.repository;

import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Korisnik getByKorisnickoIme(String korisnickoIme);

    boolean existsByMejl(String mejl);
    boolean existsByKorisnickoIme(String korisnickoIme);

}
