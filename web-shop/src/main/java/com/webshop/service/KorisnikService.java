package com.webshop.service;

import com.webshop.DTO.*;
import com.webshop.error.EmailAlreadyExistsException;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.UserAlreadyExistsException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.*;
import com.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
    @Autowired
    private ProdavacRepository prodavacRepository;
    @Autowired
    private ProizvodRepository proizvodRepository;


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

    public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme) throws UserNotFoundException {
        Optional<Korisnik> korisnik = Optional.ofNullable(korisnikRepository.getByKorisnickoIme(korisnickoIme));
        if (korisnik.isPresent()) {
            return korisnik;
        } else {
            throw new UserNotFoundException("Korisnik sa korisnickim imenom: " + korisnickoIme + " nije pronađen.");
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
            throw new PasswordMismatchException("Morate uneti ispravnu lozinku!");
        }

        korisnikRepository.save(korisnik);
    }

    public ProdavacProfilDTO proveraProdavac(String korisnickoIme) throws UserNotFoundException {
        Optional<Prodavac> korisnik = prodavacRepository.findByKorisnickoIme(korisnickoIme);
        if (korisnik.isPresent()) {
            ProdavacProfilDTO pp = new ProdavacProfilDTO();
            pp.setIme(korisnik.get().getIme());
            pp.setTelefon(korisnik.get().getTelefon());
            pp.setKorisnickoIme(korisnik.get().getKorisnickoIme());
            pp.setSlika(korisnik.get().getSlika());
            pp.setOpisKorisnika(korisnik.get().getOpisKorisnika());
            pp.setUloga(korisnik.get().getUloga());
            pp.setBlokiran(korisnik.get().getBlokiran());
          //  pp.setProizvodiNaProdaju(korisnik.get().getProizvodiNaProdaju());
            pp.setDobijeneRecenzije(korisnik.get().getDobijeneRecenzije());
            pp.setPrezime(korisnik.get().getPrezime());
            pp.setProsecnaOcena(korisnik.get().getProsecnaOcena());
            return pp;
        }else{
            throw new UserNotFoundException("Korisnik sa korisnickim imenom: " + korisnickoIme + " ne postoji!");
        }
    }

    public void updateCustomer(Korisnik korisnik, ProdavacDTO updatedCustomer) throws PasswordMismatchException {

        korisnik.setIme(updatedCustomer.getIme());
        korisnik.setPrezime(updatedCustomer.getPrezime());
        korisnik.setTelefon(updatedCustomer.getTelefon());
        korisnik.setUloga(updatedCustomer.getUloga());
        korisnik.setDatumRodjenja(updatedCustomer.getDatumRodjenja());
        korisnik.setSlika(updatedCustomer.getSlika());
        korisnik.setOpisKorisnika(updatedCustomer.getOpisKorisnika());

        if(korisnik.getLozinka().equals(updatedCustomer.getStaraLozinka())){
            korisnik.setMejl(updatedCustomer.getMejl());
            korisnik.setKorisnickoIme(updatedCustomer.getKorisnickoIme());
            korisnik.setLozinka(updatedCustomer.getNovaLozinka());
        }
        else{
            throw new PasswordMismatchException("Morate uneti ispravnu lozinku!");
        }

        korisnikRepository.save(korisnik);
    }

    public KupacProfilDTO getKupacProfile(Long id) throws UserNotFoundException {
        Optional<Korisnik> korisnikOptional = korisnikRepository.findById(id);

        if (korisnikOptional.isPresent()) {
            Korisnik korisnik = korisnikOptional.get();

            // Provera da li je korisnik zaista tipa Kupac
            if (korisnik.getUloga().equals(Uloga.KUPAC)) {
                Kupac kupac = (Kupac) korisnik; // Kastovanje korisnika u kupca

                    Set<ProizvodiNaProdajuDTO> proizvodiNaProdajuDTO=new HashSet<>();
                    for(Proizvod p: kupac.getKupljeniProizvodi()){
                        ProizvodiNaProdajuDTO proizvodNaProdajuDTO=new ProizvodiNaProdajuDTO();
                        proizvodNaProdajuDTO.setCena(p.getCena());
                        proizvodNaProdajuDTO.setNaziv(p.getNaziv());
                        proizvodNaProdajuDTO.setSlikaProizvoda(p.getSlikaProizvoda());
                        proizvodNaProdajuDTO.setOpis(p.getOpis());
                        proizvodiNaProdajuDTO.add(proizvodNaProdajuDTO);
                    }
                // Kreiranje DTO objekta za kupca
                KupacProfilDTO kupacProfilDTO = new KupacProfilDTO();
                kupacProfilDTO.setIme(korisnik.getIme());
                kupacProfilDTO.setPrezime(korisnik.getPrezime());
                kupacProfilDTO.setKorisnickoIme(korisnik.getKorisnickoIme());
                kupacProfilDTO.setSlika(korisnik.getSlika());
                kupacProfilDTO.setOpisKorisnika(korisnik.getOpisKorisnika());
                kupacProfilDTO.setDatumRodjenja(korisnik.getDatumRodjenja());
                // Dodajte ostale osnovne informacije o korisniku koje želite da prikažete

                // Računanje prosečne ocene
                Double prosecnaOcena = kupac.getProsecnaOcena();
                kupacProfilDTO.setProsecnaOcena(prosecnaOcena);

                kupacProfilDTO.setKupljeniProizvodi(proizvodiNaProdajuDTO);

                // Dohvatanje recenzija koje su ostavili prodavci
              //  Set<Recenzija> recenzijeProdavaca = korisnik.getDobijeneRecenzije();
                // Dodajte logiku za obračun prosečne ocene na osnovu recenzija prodavaca
                kupacProfilDTO.setDobijeneRecenzije(korisnikOptional.get().getDobijeneRecenzije());

                return kupacProfilDTO;
            } else {
                throw new UserNotFoundException("Korisnik sa datim ID-om nije kupac: " + id);
            }
        } else {
            throw new UserNotFoundException("Korisnik sa datim ID-om nije pronađen: " + id);
        }
}

    public ProdavacProfilDTO getProdavacProfile(Long id) throws UserNotFoundException {
        Optional<Korisnik> korisnikOptional = korisnikRepository.findById(id);

        if (korisnikOptional.isPresent()) {
            Korisnik korisnik = korisnikOptional.get();

            // Provera da li je korisnik zaista tipa Kupac
            if (korisnik.getUloga().equals(Uloga.PRODAVAC)) {
                Prodavac prodavac = (Prodavac) korisnik; // Kastovanje korisnika u prodavca
                Set<ProizvodiNaProdajuDTO> proizvodiNaProdajuDTO=new HashSet<>();
                for(Proizvod p: prodavac.getProizvodiNaProdaju()){
                    ProizvodiNaProdajuDTO proizvodNaProdajuDTO=new ProizvodiNaProdajuDTO();
                    proizvodNaProdajuDTO.setCena(p.getCena());
                    proizvodNaProdajuDTO.setNaziv(p.getNaziv());
                    proizvodNaProdajuDTO.setSlikaProizvoda(p.getSlikaProizvoda());
                    proizvodNaProdajuDTO.setOpis(p.getOpis());
                    proizvodiNaProdajuDTO.add(proizvodNaProdajuDTO);
                }
                // Kreiranje DTO objekta za prodavca
                ProdavacProfilDTO prodavacProfilDTO = new ProdavacProfilDTO();
                prodavacProfilDTO.setIme(korisnik.getIme());
                prodavacProfilDTO.setPrezime(korisnik.getPrezime());
                prodavacProfilDTO.setKorisnickoIme(korisnik.getKorisnickoIme());
                prodavacProfilDTO.setTelefon(korisnik.getTelefon());
                prodavacProfilDTO.setSlika(korisnik.getSlika());
                prodavacProfilDTO.setOpisKorisnika(korisnik.getOpisKorisnika());
                prodavacProfilDTO.setUloga(korisnik.getUloga());
                prodavacProfilDTO.setBlokiran(korisnik.getBlokiran());
                prodavacProfilDTO.setProizvodiNaProdaju(proizvodiNaProdajuDTO);
               // System.out.println(korisnik.getDobijeneRecenzije());
                prodavacProfilDTO.setDobijeneRecenzije(korisnikOptional.get().getDobijeneRecenzije());
                prodavacProfilDTO.setProsecnaOcena(prodavac.getProsecnaOcena());


                return  prodavacProfilDTO;
            } else {
                throw new UserNotFoundException("Korisnik sa datim ID-om nije prodavac " + id);
            }
        } else {
            throw new UserNotFoundException("Korisnik sa datim ID-om nije pronađen: " + id);
        }
    }

    public boolean jeKupacKupioOdProdavca(Long kupacId, Long prodavacId) {
        List<Proizvod> proizvodi = proizvodRepository.findAllByKupacIdAndProdavacId(kupacId, prodavacId);
        return !proizvodi.isEmpty();
    }

    public Prodavac oceniProdavca(Long kupacId, Long prodavacId, int ocena, String komentar) {
        // provera da li je kupac kupio proizvod od prodavca
        // ako jeste, ažuriraj ocenu i komentar
        Prodavac prodavac = prodavacRepository.findById(prodavacId).get();
        prodavac.getOcene().put(kupacId, ocena);
        prodavac.getKomentari().put(kupacId, komentar);
        double prosecnaOcena = prodavac.getOcene().values().stream().mapToInt(Integer::intValue).average().orElse(prodavac.getProsecnaOcena());
        prodavac.setProsecnaOcena(prosecnaOcena);

        return prodavacRepository.save(prodavac);
    }

    public double izracunajProsecnuOcenu(Long prodavacId) {
        // izračunaj prosečnu ocenu za prodavca
        Prodavac prodavac = prodavacRepository.findById(prodavacId).get();
        return prodavac.getOcene().values().stream().mapToInt(Integer::intValue).average().orElse(prodavac.getProsecnaOcena());
    }
}
