package com.webshop.service;

import com.webshop.DTO.RecenzijaDTO;
import com.webshop.error.NoSellerException;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;
/*
    public List<RecenzijaDTO> findRecenzijeProdavca(Long prodavacId) throws NoSellerException {
        // Proveri da li postoje recenzije za traženog prodavca koje je dao dati kupac
        // Pronađi recenzije za datog prodavca
        List<Recenzija> recenzije = recenzijaRepository.findByKorisnikKojiJeDaoRecenzijuId(prodavacId);
        boolean pronadjenProdavac = false;
        // Mapiranje entiteta Recenzija na DTO objekte
        List<RecenzijaDTO> recenzijeDTO = new ArrayList<>();
        for (Recenzija recenzija : recenzije) {
                // Ako je korisnik koji je dao recenziju prodavac, dodajemo recenziju u rezultat
                RecenzijaDTO recenzijaDTO = new RecenzijaDTO();
                recenzijaDTO.setOcena(recenzija.getOcena());
                recenzijaDTO.setKomentar(recenzija.getKomentar());
                recenzijaDTO.setDatumRecenzije(recenzija.getDatumRecenzije());
                // Dodajte recenziju u listu DTO objekata
                recenzijeDTO.add(recenzijaDTO);
                pronadjenProdavac = true;

        }
        if (!pronadjenProdavac) {
            // Ako nije pronađen prodavac sa recenzijama, baci izuzetak
            throw new NoSellerException("Nije pronadjen ni jedan Prodavac kome je ostavljena recenzija");
        }

        return recenzijeDTO;
    }*/

    public List<RecenzijaDTO> findRecenzijeKupca(Long kupacId) {
        // Pronađi sve recenzije za datog kupca
        List<Recenzija> recenzije = recenzijaRepository.findByKorisnikKojiJeDaoRecenzijuId(kupacId);

        // Mapiranje entiteta Recenzija na DTO objekte
        List<RecenzijaDTO> recenzijeDTO = new ArrayList<>();
        for (Recenzija recenzija : recenzije) {
            RecenzijaDTO recenzijaDTO = new RecenzijaDTO();
            recenzijaDTO.setOcena(recenzija.getOcena());
            recenzijaDTO.setKomentar(recenzija.getKomentar());
            recenzijaDTO.setDatumRecenzije(recenzija.getDatumRecenzije());
            // Dodaj recenziju u listu DTO objekata
            recenzijeDTO.add(recenzijaDTO);
        }

        return recenzijeDTO;
    }




/*
    public boolean kupacDaoRecenziju(Long prodavacId, Long kupacId) {

    }*/
}
