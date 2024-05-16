package com.webshop.controller;

import com.webshop.DTO.KupacProfilDTO;
import com.webshop.DTO.PrijavaProfilaDTO;
import com.webshop.DTO.PrijavaRequestDTO;
import com.webshop.DTO.ProdavacProfilDTO;
import com.webshop.error.NoSellerException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.Uloga;
import com.webshop.service.KorisnikService;
import com.webshop.service.PrijavaProfilaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/report")
public class PrijavaController {


    @Autowired
    private PrijavaProfilaService prijavaProfilaService;
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/sellerRequest/{id}")
    public ResponseEntity<?> podnesiPrijavu(@PathVariable Long id, @RequestBody PrijavaRequestDTO prijavaRequestDTO, HttpSession session) throws UserNotFoundException, NoSellerException {
        System.out.println("ulaazi u metodu");
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        System.out.println("ulaazi u metodu");
        if(korisnik == null){
            System.out.println("ulaazi u niste prijavljeni");
            throw new UserNotFoundException("Niste prijavljeni!");

        }
        if(!korisnik.getUloga().equals(Uloga.PRODAVAC)){
            System.out.println("ulazi u if uloge");
            throw new NoSellerException("Korisnik koji nije prodavac, ne može da prijavi kupca.");

        }

        Optional<Korisnik> korisnikOptional = korisnikService.findById(id);
        if (korisnikOptional.isEmpty()) {
            throw new UserNotFoundException("Korisnik nije pronađen.");
        }
        if (korisnikOptional.get().getUloga().equals(Uloga.KUPAC)) {
            System.out.println("ulazi u poziv servisa");
            PrijavaProfilaDTO prijavljen = prijavaProfilaService.PodnesiPrijacuZaKupca(korisnik,prijavaRequestDTO, id);
            System.out.println("zavrseno sa servisom");
            return ResponseEntity.ok(prijavljen);
        }
        else {
            throw new UserNotFoundException("Možete prijaviti samo kupce vaših proizvoda!");
        }

    }
}
