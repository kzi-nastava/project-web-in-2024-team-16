package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
    List<Recenzija> findByKorisnikKojiJeDaoRecenzijuId(Long korisnikId);


    List<Recenzija> findAllBykorisnikKojiJeDaoRecenziju(Korisnik korisnikKojiJeDaoRecenziju);

}
