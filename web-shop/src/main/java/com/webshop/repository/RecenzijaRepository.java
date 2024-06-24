package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
    List<Recenzija> findByKorisnikKojiJeDaoRecenzijuId(Long korisnikId);

    List<Recenzija> findAllBykorisnikKojiJeDaoRecenziju(Korisnik korisnikKojiJeDaoRecenziju);

    Optional<Recenzija> findById(Long id);


    //List<Recenzija> findAllBykorisnikKojiJeDobioRecenziju(Korisnik KorisnikKojiJeDobioRecenziju);

    @Query("SELECT AVG(r.ocena) FROM Recenzija r WHERE r.korisnikKojiJeDobioRecenziju.id = :korisnikId")
    Double findAverageRatingByKorisnikId(Long korisnikId);
}
