package com.webshop.controller;

import com.webshop.DTO.KupacDTO;
import com.webshop.DTO.PrijavaKorisnikaDTO;
import com.webshop.DTO.RegistracijaKorisnikaDTO;
import com.webshop.error.EmailAlreadyExistsException;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.UserAlreadyExistsException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.Uloga;
import com.webshop.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> registracijaKorisnika(@Valid @RequestBody RegistracijaKorisnikaDTO korisnik) throws UserAlreadyExistsException, EmailAlreadyExistsException, PasswordMismatchException {//valid proverava da li su ispunjeni zahtevi unutar registracija korstnika dTO
        Korisnik registrovaniKorisnik = korisnikService.registracijaKorisnika(korisnik);
        return new ResponseEntity<>(registrovaniKorisnik, HttpStatus.CREATED);
    }
    //valid automatski proverava da li sve sto je proslednjeno je u skladu sa nasim DTO, ako ne valja generise greske vraca 400

    @PostMapping("/login")
    public ResponseEntity<String> prijava(@RequestBody PrijavaKorisnikaDTO prijavaDto, HttpSession session){
        // proverimo da li su podaci validni
        if(prijavaDto.getKorisnickoIme().isEmpty() || prijavaDto.getLozinka().isEmpty())
            return new ResponseEntity("Neispravni podaci, molim Vas pokušajte ponovo", HttpStatus.BAD_REQUEST);

        Korisnik loginovaniKorisnik = korisnikService.prijava(prijavaDto.getKorisnickoIme(), prijavaDto.getLozinka());
        if (loginovaniKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", loginovaniKorisnik);
        return ResponseEntity.ok("Prijava uspešna!");
    }
    @PostMapping("/logout")
    public ResponseEntity Odjava(HttpSession session){
       Korisnik loggedEmployee = (Korisnik) session.getAttribute("korisnik");

        if (loggedEmployee == null)
            return new ResponseEntity("Došlo je do greške prilikom odjave", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Odjava uspešna!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Korisnik> azuriranjeKupca(@Valid @RequestBody KupacDTO kupac, HttpSession session) throws PasswordMismatchException, EmailAlreadyExistsException, UserAlreadyExistsException, UserNotFoundException {
        Korisnik korisnik = korisnikService.azuriranje(kupac);

        if(korisnik.getUloga() != Uloga.KUPAC){
            return new ResponseEntity("Samo clanovi sa ulogom KUPAC mogu da menjaju profil!", HttpStatus.FORBIDDEN);
        }

        Korisnik loggedEmployee = (Korisnik) session.getAttribute("korisnik");

        if (loggedEmployee == null)
            return new ResponseEntity("Korisnik mora biti ulogovan!", HttpStatus.FORBIDDEN);

//        Korisnik loginovaniKorisnik = korisnikService.prijava(kupac.getKorisnickoIme(), kupac.getLozinka());
//        if (loginovaniKorisnik.getMejl() != null)
//            return new ResponseEntity("Korisnik sa istim mejlom vec postoji!", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

}
