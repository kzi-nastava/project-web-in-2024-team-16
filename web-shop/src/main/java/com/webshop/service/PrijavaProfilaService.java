package com.webshop.service;

import com.webshop.DTO.PrijavaKorisnikDTO;
import com.webshop.DTO.PrijavaProfilaDTO;
import com.webshop.DTO.PrijavaRequestDTO;
import com.webshop.error.NoCustomerException;
import com.webshop.error.NoSellerException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.Status;
import com.webshop.model.Uloga;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaProfilaRepository;
import com.webshop.repository.ProizvodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrijavaProfilaService {


    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PrijavaProfilaRepository prijavaProfilaRepository;
    @Autowired
    private ProizvodRepository proizvodRepository;

    @Transactional
    public PrijavaProfilaDTO PodnesiPrijacuZaKupca(Korisnik korisnik, PrijavaRequestDTO prijavaRequestDTO, Long id) throws UserNotFoundException, NoCustomerException {
        Optional<Korisnik> prijavljeniKorisnikOptional = korisnikRepository.findById(id);
        Optional<Korisnik> podnosiocPrijave=korisnikRepository.findById(korisnik.getId());
        if (prijavljeniKorisnikOptional.isEmpty()) {
            throw new UserNotFoundException("Korisnik nije pronađen.");
        }

        Korisnik prijavljeniKorisnik = prijavljeniKorisnikOptional.get();

        if (!prijavljeniKorisnik.getUloga().equals(Uloga.KUPAC)) {
            throw new NoCustomerException("Možete prijaviti samo kupce vaših proizvoda!");
        }
        System.out.println("prosao ifove u servisu");
        PrijavaProfila prijavaProfila = new PrijavaProfila();
        prijavaProfila.setRazlogPrijave(prijavaRequestDTO.getRazlogPrijave());
        prijavaProfila.setDatumPodnosenjaPrijave(new Date());
        prijavaProfila.setStatusPrijave(Status.PODNETA); // ili neki drugi podrazumevani status
        prijavaProfila.setPodnosiocPrijave(podnosiocPrijave.get());
        prijavaProfila.setPrijavljeniKorisnik(prijavljeniKorisnik);

        prijavaProfila = prijavaProfilaRepository.save(prijavaProfila);
        PrijavaKorisnikDTO prijavljenKorisnikDTO=new PrijavaKorisnikDTO();
        PrijavaKorisnikDTO podnosiocPrijaveKorisnikDTO=new PrijavaKorisnikDTO();
        prijavljenKorisnikDTO.setIme(prijavljeniKorisnik.getIme());
        prijavljenKorisnikDTO.setPrezime(prijavljeniKorisnik.getPrezime());
        prijavljenKorisnikDTO.setMejl(prijavljeniKorisnik.getMejl());
        prijavljenKorisnikDTO.setKorisnickoIme(prijavljeniKorisnik.getKorisnickoIme());

        podnosiocPrijaveKorisnikDTO.setKorisnickoIme(podnosiocPrijave.get().getKorisnickoIme());
        podnosiocPrijaveKorisnikDTO.setIme(podnosiocPrijave.get().getIme());
        podnosiocPrijaveKorisnikDTO.setPrezime(podnosiocPrijave.get().getPrezime());
        podnosiocPrijaveKorisnikDTO.setMejl(podnosiocPrijave.get().getMejl());


        PrijavaProfilaDTO prijavaProfilaDTO=new PrijavaProfilaDTO();
        prijavaProfilaDTO.setDatumPodnosenjaPrijave(prijavaProfila.getDatumPodnosenjaPrijave());
        prijavaProfilaDTO.setPrijavljeniKorisnik(prijavljenKorisnikDTO);
        prijavaProfilaDTO.setPodnosiocPrijave(podnosiocPrijaveKorisnikDTO);
        prijavaProfilaDTO.setStatusPrijave(prijavaProfila.getStatusPrijave());
        prijavaProfilaDTO.setRazlogPrijave(prijavaProfila.getRazlogPrijave());


        return prijavaProfilaDTO;
    }

    public List<PrijavaProfila> pregledPrijava() {
        return prijavaProfilaRepository.findAllByStatusPrijave(Status.PODNETA);
    }

    public PrijavaProfilaDTO PodnesiPrijacuZaProdavca(Korisnik korisnik, PrijavaRequestDTO prijavaRequestDTO, Long id) throws UserNotFoundException, NoSellerException {
        Optional<Korisnik> prijavljeniKorisnikOptional = korisnikRepository.findById(id);
        Optional<Korisnik> podnosiocPrijave=korisnikRepository.findById(korisnik.getId());
        if (prijavljeniKorisnikOptional.isEmpty()) {
            throw new UserNotFoundException("Korisnik nije pronađen.");
        }

        Korisnik prijavljeniKorisnik = prijavljeniKorisnikOptional.get();

        if (!prijavljeniKorisnik.getUloga().equals(Uloga.PRODAVAC)) {
            throw new NoSellerException("Možete prijaviti samo prodavca od kog ste uzeli proizvod!");
        }

        PrijavaProfila prijavaProfila = new PrijavaProfila();
        prijavaProfila.setRazlogPrijave(prijavaRequestDTO.getRazlogPrijave());
        prijavaProfila.setDatumPodnosenjaPrijave(new Date());
        prijavaProfila.setStatusPrijave(Status.PODNETA); // ili neki drugi podrazumevani status
        prijavaProfila.setPodnosiocPrijave(podnosiocPrijave.get());
        prijavaProfila.setPrijavljeniKorisnik(prijavljeniKorisnik);


        prijavaProfila = prijavaProfilaRepository.save(prijavaProfila);
        PrijavaKorisnikDTO prijavljenKorisnikDTO=new PrijavaKorisnikDTO();
        PrijavaKorisnikDTO podnosiocPrijaveKorisnikDTO=new PrijavaKorisnikDTO();
        prijavljenKorisnikDTO.setIme(prijavljeniKorisnik.getIme());
        prijavljenKorisnikDTO.setPrezime(prijavljeniKorisnik.getPrezime());
        prijavljenKorisnikDTO.setMejl(prijavljeniKorisnik.getMejl());
        prijavljenKorisnikDTO.setKorisnickoIme(prijavljeniKorisnik.getKorisnickoIme());

        podnosiocPrijaveKorisnikDTO.setKorisnickoIme(podnosiocPrijave.get().getKorisnickoIme());
        podnosiocPrijaveKorisnikDTO.setIme(podnosiocPrijave.get().getIme());
        podnosiocPrijaveKorisnikDTO.setPrezime(podnosiocPrijave.get().getPrezime());
        podnosiocPrijaveKorisnikDTO.setMejl(podnosiocPrijave.get().getMejl());


        PrijavaProfilaDTO prijavaProfilaDTO=new PrijavaProfilaDTO();
        prijavaProfilaDTO.setDatumPodnosenjaPrijave(prijavaProfila.getDatumPodnosenjaPrijave());
        prijavaProfilaDTO.setPrijavljeniKorisnik(prijavljenKorisnikDTO);
        prijavaProfilaDTO.setPodnosiocPrijave(podnosiocPrijaveKorisnikDTO);
        prijavaProfilaDTO.setStatusPrijave(prijavaProfila.getStatusPrijave());
        prijavaProfilaDTO.setRazlogPrijave(prijavaProfila.getRazlogPrijave());

        return prijavaProfilaDTO;
    }
}
