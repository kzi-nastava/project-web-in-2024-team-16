package com.webshop.service;

import com.webshop.DTO.KupacDTO;
import com.webshop.DTO.RegistracijaKorisnikaDTO;
import com.webshop.error.EmailAlreadyExistsException;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.UserAlreadyExistsException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.Recenzija;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaProfilaRepository;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PrijavaProfilaRepository prijavaProfilaRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;


    public boolean emailExsist(String mejl) {
        return korisnikRepository.existsByMejl(mejl);
    }
    public boolean userExsist(String korisnickoIme) {
        return korisnikRepository.existsByKorisnickoIme(korisnickoIme);
    }

    public Korisnik registracijaKorisnika(RegistracijaKorisnikaDTO korisnikDTO) throws UserAlreadyExistsException, EmailAlreadyExistsException, PasswordMismatchException {


        Korisnik korisnik=new Korisnik();
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
        korisnik.setLozinka(korisnikDTO.getLozinka());
        korisnik.setTelefon(korisnikDTO.getTelefon());
        korisnik.setUloga(korisnikDTO.getUloga());
        korisnik.setMejl(korisnikDTO.getMejl());
        korisnik.setDatumRodjenja(korisnikDTO.getDatumRodjenja());

        korisnik.setBlokiran(false);
        korisnik.setOpisKorisnika("");

        Set<PrijavaProfila> prijava = new HashSet<>();
        korisnik.setPrijava(prijava);
        Set<PrijavaProfila> prijavljen = new HashSet<>();
        korisnik.setPrijavljen(prijavljen);
        Set<Recenzija> recenzije = new HashSet<>();
        korisnik.setRecenzije(recenzije);

        if(this.emailExsist(korisnikDTO.getMejl()))//poziva metodu, ako vrati gresku, ispisace...
        {//proveri u bazi uz pomoc repozitorijuma, ako postoji vrati mi gresku

            throw new EmailAlreadyExistsException("Korisnik sa ovim email-om vec postoji!");
        }
        if(this.userExsist(korisnikDTO.getKorisnickoIme()))
        {

            throw new UserAlreadyExistsException("Korisnik sa ovim korisnickim imenom vec postoji!");
        }
        if (!korisnikDTO.getLozinka().equals(korisnikDTO.getPonovljenaLozinka())) {
            throw new PasswordMismatchException("Lozinke se ne poklapaju!");
        }


        korisnik=korisnikRepository.save(korisnik);//saljem sve ali ja nisam morala pri pravljenju da unosim sve


        return korisnik;
    }

    public Korisnik prijava(String korisnickoIme, String lozinka) {

        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
            return null;
        return  korisnik;

    }
    public Optional<Korisnik> findById(Long id) throws UserNotFoundException {
        Optional<Korisnik> korisnik = korisnikRepository.findById(id);
        if (korisnik.isPresent()) {
            return korisnik;
        } else {
            throw new UserNotFoundException("Korisnik sa ID-jem " + id + " nije pronađen.");
        }
    }

    public void updateSeller(Korisnik korisnik, KupacDTO updatedSeller) throws PasswordMismatchException {

            korisnik.setIme(updatedSeller.getIme());
            korisnik.setPrezime(updatedSeller.getPrezime());
            korisnik.setTelefon(updatedSeller.getTelefon());
            korisnik.setUloga(updatedSeller.getUloga());
            korisnik.setDatumRodjenja(updatedSeller.getDatumRodjenja());
            korisnik.setSlika(updatedSeller.getSlika());
            korisnik.setOpisKorisnika(updatedSeller.getOpisKorisnika());

        if(korisnik.getLozinka().equals(updatedSeller.getStaraLozinka())){
           korisnik.setMejl(updatedSeller.getMejl());
           korisnik.setKorisnickoIme(updatedSeller.getKorisnickoIme());
           korisnik.setLozinka(updatedSeller.getNovaLozinka());
        }
        else{
            throw new PasswordMismatchException("Morate uneti ispravnu lozniku!");
        }

        korisnikRepository.save(korisnik);
    }
}
