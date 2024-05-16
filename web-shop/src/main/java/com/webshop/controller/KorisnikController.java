package com.webshop.controller;

import com.webshop.DTO.*;
import com.webshop.error.*;
import com.webshop.model.*;
import com.webshop.service.KorisnikService;
import com.webshop.service.PrijavaProfilaService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PrijavaProfilaService prijavaProfilaService;
    @Autowired
    private ProizvodService proizvodService;

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
    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<?> updateSeller(@PathVariable Long id, @RequestBody KupacDTO updatedSeller, HttpSession session) throws PasswordMismatchException, EmailAlreadyExistsException, UserAlreadyExistsException, UserNotFoundException, NoSellerException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            throw new UserNotFoundException("Samo ulogovani korisnici mogu da menjaju podatke!");
        }

        Optional<Korisnik> existingUser = korisnikService.findById(id);

        if (existingUser.isEmpty()) {
            // return ResponseEntity.notFound().build();
            throw new UserNotFoundException("Korisnik sa ID-jem " + id + " nije pronađen.");
        }

        if (!existingUser.get().getKorisnickoIme().equals(korisnik.getKorisnickoIme())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if(existingUser.get().getUloga() != Uloga.KUPAC){
            throw new NoSellerException("Samo KUPAC moze da menja podatke!");
        }

        korisnikService.updateSeller(existingUser.get(), updatedSeller);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/profileView/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long id, HttpSession session) throws UserNotFoundException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            throw new UserNotFoundException("Niste prijavljeni!");
        }

        Optional<Korisnik> korisnikOptional = korisnikService.findById(id);
        if (korisnikOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik sa datim ID-om nije pronađen.");
        }

        // Ako gledam profil kupca
        if (korisnikOptional.get().getUloga().equals(Uloga.KUPAC)) {
            // Dobavljanje informacija o kupcu
            KupacProfilDTO kupac = korisnikService.getKupacProfile(id);
            return ResponseEntity.ok(kupac);
        }
        // Ako gledam profil prodavca
        else if (korisnikOptional.get().getUloga().equals(Uloga.PRODAVAC)) {
            // Dobavljanje informacija o prodavcu
            ProdavacProfilDTO prodavac = korisnikService.getProdavacProfile(id);
            return ResponseEntity.ok(prodavac);
        }
        // Ako korisnik nije ni kupac ni prodavac
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nemate pristup ovom profilu.");
        }
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody ProdavacDTO updatedCustomer, HttpSession session) throws PasswordMismatchException, EmailAlreadyExistsException, UserAlreadyExistsException, UserNotFoundException, NoSellerException, NoCustomerException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            throw new UserNotFoundException("Samo ulogovani korisnici mogu da menjaju podatke!");
        }

        Optional<Korisnik> existingUser = korisnikService.findById(id);

        if (existingUser.isEmpty()) {
            // return ResponseEntity.notFound().build();
            throw new UserNotFoundException("Korisnik sa ID-jem " + id + " nije pronađen.");
        }

        if (!existingUser.get().getKorisnickoIme().equals(korisnik.getKorisnickoIme())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if(existingUser.get().getUloga() != Uloga.PRODAVAC){
            throw new NoCustomerException("Samo PRODAVAC moze da menja podatke!");
        }

        korisnikService.updateCustomer(existingUser.get(), updatedCustomer);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/rate/{prodavacId}")
    public Prodavac oceniProdavca(@PathVariable Long prodavacId, @RequestParam int ocena, @RequestParam String komentar, HttpSession session) throws UserNotFoundException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            throw new UserNotFoundException("Samo ulogovani korisnici mogu da menjaju podatke!");
        }

        if(!korisnikService.jeKupacKupioOdProdavca(korisnik.getId(), prodavacId)) {
            throw new IllegalArgumentException("Kupac može da oceni prodavca samo ako je kupio proizvod od tog prodavca!");
        }

        return korisnikService.oceniProdavca(korisnik.getId(), prodavacId, ocena, komentar);
    }

    @GetMapping("/averageRating/{prodavacId}")
    public double prosecnaOcena(@PathVariable Long prodavacId) {
        return korisnikService.izracunajProsecnuOcenu(prodavacId);
    }
    @GetMapping("/admin/reports")
    public ResponseEntity<List<PrijavaProfila>> pregledPrijava(HttpSession session) throws UserNotFoundException, NoAdministratorException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            throw new UserNotFoundException("Morate se ulogovati!");
        }
        if(!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)){

            throw new NoAdministratorException("Samo administrator može da vidi prijave.");
        }

        List<PrijavaProfila> prijave = prijavaProfilaService.pregledPrijava();
        return ResponseEntity.ok(prijave);
    }
    @PostMapping("/shopNowFixedPrice/{id}")
    public ProizvodiNaProdajuDTO kupovinaProizvodaFiksnaCena(@PathVariable Long id, HttpSession session) throws UserNotFoundException, NoCustomerException, ProductNotFoundException, ProductSoldException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null){
            throw new UserNotFoundException("Samo ulogovani korisnici mogu da kupuju proizvode!");
        }
        if(!korisnik.getUloga().equals(Uloga.KUPAC)){
            throw new NoCustomerException("Samo kupac može da kupuje!");
        }

        Optional<Proizvod> proizvod =proizvodService.findById(id);
        if (proizvod.isEmpty()) {
            throw new ProductNotFoundException("Proizvod ne postoji!");
        }
        if(!proizvod.get().getTip().equals(TipProdaje.FIKSNA)) {
            throw new ProductNotFoundException("Cena nije fiksna.");

        }
        ProizvodiNaProdajuDTO kupljeniProizvodFiksna=proizvodService.kupiProizvodFiksnaCena(proizvod.get(), korisnik);
        return kupljeniProizvodFiksna;


    }
  /*  @PostMapping("/shopNowAutction/{id}")
    public ResponseEntity<?> kupovinaProizvodaAukcija(@PathVariable Long proizvodId, HttpSession session) throws UserNotFoundException, NoCustomerException, ProductNotFoundException {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null){
            throw new UserNotFoundException("Samo ulogovani korisnici mogu da kupuju proizvode!");
        }
        if(!korisnik.getUloga().equals(Uloga.KUPAC)){
            throw new NoCustomerException("Samo kupac može da kupuje!");
        }

        Optional<Proizvod> proizvod =proizvodService.findById(proizvodId);
        if (proizvod.isEmpty()) {
            throw new ProductNotFoundException("Proizvod ne postoji!");
        }
        if(proizvod.get().getTip().equals(TipProdaje.AUKCIJA)) {
            Proizvod kupljeniProizvodAukcija=proizvodService.kupiProizvodAukcija(proizvodId, korisnik);
            //VRATI PROIZVOD
        }
        throw new ProductNotFoundException("Došlo je do greške");

    }*/

}
