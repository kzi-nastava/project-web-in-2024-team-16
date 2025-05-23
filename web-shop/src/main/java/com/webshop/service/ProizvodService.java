package com.webshop.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.webshop.DTO.*;

import com.webshop.error.*;
import com.webshop.model.*;
import com.webshop.repository.*;


import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProdavacRepository;

import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;

import com.webshop.repository.ProizvodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.webshop.config.Config;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProizvodService {

    @Autowired
    private  ProizvodRepository proizvodRepository;
    @Autowired
    private KategorijaRepository kategorijaRepository;
    @Autowired
    private ProdavacRepository prodavacRepository;
    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private PonudaRepository ponudaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;


    public ProizvodDTO findOne(Long id){

        Optional<Proizvod> foundProizvod = proizvodRepository.findById(id);

        if (foundProizvod.isPresent()) {

            Proizvod proizvod=foundProizvod.get();
            ProizvodDTO proizvodDTO=new ProizvodDTO();
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setId(proizvod.getId());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            return proizvodDTO;
        }
        return null;
    }
    public List<SviProizvodiDTO> findAllProducts() {

        List<Proizvod> proizvodi = proizvodRepository.findAll();
        List<SviProizvodiDTO> proizvodiDTO = new ArrayList<>();
        List<Proizvod> slanje = new ArrayList<>();

        for (Proizvod proizvod : proizvodi) {
            SviProizvodiDTO proizvodDTO = new SviProizvodiDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
           Set< Kategorija> kategorije=proizvod.getKategorija();
           Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
           for(Kategorija kategorija: kategorije){
               KategorijaDTO kategorijaDTO= new KategorijaDTO();
               kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
               kategorijaDTO.setId(kategorija.getId());
               kategorijeDTO.add(kategorijaDTO);
           }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        return proizvodiDTO;
    }

    public List<ProizvodDTO> findAll() {

        List<Proizvod> proizvodi = proizvodRepository.findAll();
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
        List<Proizvod> slanje = new ArrayList<>();
   
        for (Proizvod proizvod : proizvodi) {
             ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
     }
     return proizvodiDTO;
 }


    public List<ProizvodDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Proizvod> proizvodiPage = proizvodRepository.findAll(pageable);

        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
        for (Proizvod proizvod : proizvodiPage.getContent()) {
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            // Postaviti ostale atribute po potrebi
            proizvodiDTO.add(proizvodDTO);
        }
        return proizvodiDTO;
    }




    public List<ProizvodDTO> findByNazivAndOpis(String name, String description) throws ProductNotFoundException {

        List<Proizvod> proizvodi = proizvodRepository.findByNazivAndOpis(name, description);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

          throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;


    }

    public List<ProizvodDTO> findByNaziv(String name) throws ProductNotFoundException, PasswordMismatchException {

        List<Proizvod> proizvodi = proizvodRepository.findByNaziv(name);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

            throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;

    }

    public List<ProizvodDTO> findByOpis(String description) throws ProductNotFoundException {

        List<Proizvod> proizvodi = proizvodRepository.findByOpis( description);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

            throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;

    }

    public List<ProizvodDTO> findByKategorija(String category) throws ProductNotFoundException {

        List<Proizvod> proizvodi = proizvodRepository.findByKategorijaNazivKategorije(category);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

            throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;
    }

    public List<ProizvodDTO> findByTipProdaje(TipProdaje type) throws ProductNotFoundException {

        List<Proizvod> proizvodi = proizvodRepository.findByTipProdaje(type);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

            throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;
    }

    public List<ProizvodDTO> findByCena(Double priceFrom, Double priceTo) throws ProductNotFoundException {

        List<Proizvod> proizvodi = proizvodRepository.findByCenaGreaterThanEqualAndCenaLessThanEqual(priceFrom, priceTo);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {

            throw new ProductNotFoundException("Trazeni proizvod ne postoji.");
        }
        return proizvodiDTO;
    }
    public SviProizvodiDTO updateProduct(Proizvod proizvod, Proizvod updatedProduct) {


        proizvod.setCena(updatedProduct.getCena());
        proizvod.setNaziv(updatedProduct.getNaziv());
        proizvod.setDatumObjavljivanja(updatedProduct.getDatumObjavljivanja());
        proizvod.setKategorija(updatedProduct.getKategorija());
        proizvod.setPonude(updatedProduct.getPonude());
        proizvod.setOpis(updatedProduct.getOpis());
        proizvod.setSlikaProizvoda(updatedProduct.getSlikaProizvoda());
       proizvod.setTip(updatedProduct.getTip());
        proizvodRepository.save(proizvod);

        SviProizvodiDTO updateProduct= new SviProizvodiDTO();
        updateProduct.setNaziv(proizvod.getNaziv());
        updateProduct.setOpis(proizvod.getOpis());
        updateProduct.setId(proizvod.getId());
        updateProduct.setSlikaProizvoda(proizvod.getSlikaProizvoda());
        updateProduct.setTipProdaje(proizvod.getTip());
        updateProduct.setCena(proizvod.getCena());
        updateProduct.setTipProdaje(proizvod.getTip());
        Set< Kategorija> kategorije=proizvod.getKategorija();
        Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
        for(Kategorija kategorija: kategorije){
            KategorijaDTO kategorijaDTO= new KategorijaDTO();
            kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
            kategorijaDTO.setId(kategorija.getId());
            kategorijeDTO.add(kategorijaDTO);
        }

        updateProduct.setKategorije(kategorijeDTO);

        return updateProduct;

    }
  /*  public void updateProduct(Proizvod proizvod, Proizvod updatedProduct) {


        proizvod.setCena(updatedProduct.getCena());
        proizvod.setNaziv(updatedProduct.getNaziv());
        proizvod.setDatumObjavljivanja(updatedProduct.getDatumObjavljivanja());
        proizvod.setKategorija(updatedProduct.getKategorija());
        proizvod.setPonude(updatedProduct.getPonude());
        proizvod.setOpis(updatedProduct.getOpis());
        proizvod.setSlikaProizvoda(updatedProduct.getSlikaProizvoda());
        proizvod.setTip(updatedProduct.getTip());
        proizvodRepository.save(proizvod);

    }*/

    public Optional<Proizvod> findById(Long id) throws UserNotFoundException {

        Optional<Proizvod> proizvod = proizvodRepository.findById(id);
        if (proizvod.isPresent()) {

            return proizvod;
        } else {

            throw new UserNotFoundException("Proizvod sa ID-jem " + id + " nije pronađen.");
        }
    }

    public void dodajProizvod(ProizvodiZaProdajuDTO proizvodDTO, Korisnik korisnik) throws CategoryExistsException {

        Proizvod proizvod = new Proizvod();

        proizvod.setKupac(null);
        proizvod.setNaziv(proizvodDTO.getNaziv());
        proizvod.setTip(proizvodDTO.getTipProdaje());
        proizvod.setOpis(proizvodDTO.getOpis());
        proizvod.setSlikaProizvoda(proizvodDTO.getSlikaProizvoda());
        proizvod.setCena(proizvodDTO.getCena());
        proizvod.setProdat(false);

        Optional<Prodavac> prodavac = prodavacRepository.findById(korisnik.getId());
        if (prodavac.isPresent()) {
            proizvod.setProdavac(prodavac.get());
        }

        proizvod.setKategorija(new HashSet<>(proizvodDTO.getKategorije())); // Osigurajte da je set postavljen

        proizvod.setDatumObjavljivanja(new Date());

        proizvod = proizvodRepository.save(proizvod);
    }

    public ProizvodiNaProdajuDTO kupiProizvodFiksnaCena(Proizvod proizvod, Korisnik korisnik) throws IOException {

        if(proizvod.getProdat()){

            throw  new ProductSoldException("Ovaj proizvod je već prodat.");
        }

        Optional<Kupac> kupac = kupacRepository.findById(korisnik.getId());
        kupac.get().getKupljeniProizvodi().add(proizvod);

        proizvod.setProdat(true);
        proizvod.setKupac(kupac.get());

        Optional<Prodavac> prodavac = prodavacRepository.findById(proizvod.getProdavac().getId());

        prodavac.get().getProizvodiNaProdaju().remove(proizvod);

        proizvod.setProdavac(prodavac.get());
        proizvodRepository.save(proizvod);
        ProizvodiNaProdajuDTO proizvodiNaProdajuDTO=new ProizvodiNaProdajuDTO();
        proizvodiNaProdajuDTO.setOpis(proizvod.getOpis());
        proizvodiNaProdajuDTO.setCena(proizvod.getCena());
        proizvodiNaProdajuDTO.setNaziv(proizvod.getNaziv());
        proizvodiNaProdajuDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());

        sendVerificationEmailCustomer(kupac.get());
        sendVerificationEmailSeller(prodavac.get());
        return proizvodiNaProdajuDTO;
    }
    private void sendVerificationEmailCustomer(Korisnik korisnik) throws IOException {
        System.out.println("Usao u slanje mejla");
        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Kupljen proizvod";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Uspešno ste obavili kupovinu proizvoda. "
                + "Hvala Vam,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    private void sendVerificationEmailSeller(Korisnik korisnik) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Proizvod prodat";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Vaš proizvod je prodat."
                + " Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public PonudaDTO postavljanjeProizvodaNaAukciju(Proizvod proizvod, Korisnik korisnik, Double novaPonuda, Korisnik prodavac) throws IOException {

        List<Ponuda> postojecePonude = ponudaRepository.findByProizvodId(proizvod.getId());

        boolean najvecaPonuda = true;


        for (Ponuda ponuda : postojecePonude) {
            if (novaPonuda <= ponuda.getCena()) {
                throw new NotHighestOfferException("Ponuda koju ste poslali se nije uvažila jer je drugi korisnik poslao veću: " + ponuda.getCena() + ".");
            }
        }
        Optional<Kupac> kupac = kupacRepository.findById(korisnik.getId());
        Optional<Proizvod> pp2 = proizvodRepository.findById(proizvod.getId());


        Ponuda ponuda = new Ponuda();
        ponuda.setCena(novaPonuda);
        ponuda.setKupacKojiDajePonudu(kupac.get());

        ProizvodAukcijaDTO proizvodAukcijaDTO=new ProizvodAukcijaDTO();
        proizvodAukcijaDTO.setNaziv(proizvod.getNaziv());
        proizvodAukcijaDTO.setOpis(proizvod.getOpis());
        proizvodAukcijaDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
        proizvodAukcijaDTO.setTipProdaje(proizvod.getTip());
        ponuda.setProizvod(pp2.get());
        ponudaRepository.save(ponuda);

        PrijavaKorisnikDTO kupacDTO = new PrijavaKorisnikDTO();
        kupacDTO.setMejl(kupac.get().getMejl());
        kupacDTO.setIme(kupac.get().getIme());
        kupacDTO.setPrezime(kupac.get().getPrezime());
        kupacDTO.setKorisnickoIme(kupac.get().getKorisnickoIme());

        PonudaDTO ponudaDTO = new PonudaDTO();
        ponudaDTO.setCena(ponuda.getCena());
        ponudaDTO.setProizvod(proizvodAukcijaDTO);
        ponudaDTO.setKupacKojiDajePonudu(kupacDTO);
        sendEmailAboutAuction(prodavac, novaPonuda);
        return ponudaDTO;

    }
    private void sendEmailAboutAuction(Korisnik korisnik, Double novaPonuda) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Obaveštenje o aukciji";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Vaš proizvod ima novu ponudu: "+novaPonuda+ " ."
                + " Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    public ProizvodiNaProdajuDTO endAuction(Long proizvodId, Long prodavacId) throws Exception {
        Proizvod proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new Exception("Proizvod ne postoji."));

        Prodavac prodavac = prodavacRepository.findById(prodavacId)
                .orElseThrow(() -> new Exception("Prodavac ne postoji."));

        if (!prodavac.getProizvodiNaProdaju().contains(proizvod)) {
            throw new Exception("Prodavac nema traženi proizvod na prodaju.");
        }

        if (proizvod.getTip() != TipProdaje.AUKCIJA) {
            throw new Exception("Proizvod nije na aukciji.");
        }

        if (!proizvod.getProdat() && !proizvod.getPonude().isEmpty()) {
            Ponuda highestPonuda = proizvod.getPonude()
                    .stream()
                    .max(Comparator.comparing(Ponuda::getCena))
                    .orElseThrow(() -> new Exception("Ne postoje ponude."));

            Kupac kupac = highestPonuda.getKupacKojiDajePonudu();
            kupac.getKupljeniProizvodi().add(proizvod);

            prodavac.getProizvodiNaProdaju().remove(proizvod);

            proizvod.setProdat(true);
            proizvod.setKupac(kupac);
            // Dobavi sve korisnike koji su dali ponude
            Set<Korisnik> korisniciSaPonudama = proizvod.getPonude().stream()
                    .map(Ponuda::getKupacKojiDajePonudu)
                    .collect(Collectors.toSet());

            ProizvodiNaProdajuDTO p = new ProizvodiNaProdajuDTO();
            p.setOpis(proizvod.getOpis());
            p.setCena(proizvod.getCena());
            p.setNaziv(proizvod.getNaziv());
            p.setSlikaProizvoda(proizvod.getSlikaProizvoda());

            korisnikRepository.save(kupac);
            korisnikRepository.save(prodavac);
            proizvodRepository.save(proizvod);

            for (Korisnik korisnik : korisniciSaPonudama) {
                if (korisnik.equals(kupac)) {
                    sendEmailAuctionWin(korisnik,proizvod.getNaziv());
                } else {
                    // Pošalji email ostalim korisnicima
                    sendEmailEndOfAuction(korisnik, proizvod.getNaziv());
                }
            }

            // Pošalji email prodavcu
         sendEmailAuctionEndSeller(prodavac,proizvod.getNaziv());

            return p;
        } else {
            throw new Exception("Aukcija nije aktivna ili nema ponuda.");
        }
    }
    private void sendEmailEndOfAuction(Korisnik korisnik, String naziv) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Obaveštenje o aukciji";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Vaša ponuda na aukciji nije bila najveća. Nažalost izgubili ste na aukciji za proizvod "+naziv+ " ."
                + " Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    private void sendEmailAuctionWin(Korisnik korisnik, String naziv) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Pobeda na aukciji!";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na, čestitamo," +
                " pobdeili ste na aukciji za proizvod "+ naziv
                + " .Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    private void sendEmailAuctionEndSeller(Korisnik korisnik, String naziv) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Kraj aukcije!";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na čestitamo," +
                " prodali ste proivod "+ naziv+ " sa Vaše aukcije. "
                + " .Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public SviProizvodiDTO findProduct(Long id) {
        Optional<Proizvod> foundProizvod = proizvodRepository.findById(id);

        if (foundProizvod.isPresent()) {

            Proizvod proizvod=foundProizvod.get();
            SviProizvodiDTO proizvodDTO=new SviProizvodiDTO();
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setId(proizvod.getId());

            Prodavac p=proizvod.getProdavac();
            ProdavacProfilDTO prodavacProfilDTO=new ProdavacProfilDTO();
            prodavacProfilDTO.setId(p.getId());
            prodavacProfilDTO.setIme(p.getIme());
            prodavacProfilDTO.setPrezime(p.getPrezime());
            prodavacProfilDTO.setBlokiran(p.getBlokiran());
            prodavacProfilDTO.setKorisnickoIme(p.getKorisnickoIme());
            prodavacProfilDTO.setSlika(p.getSlika());
            prodavacProfilDTO.setTelefon(p.getTelefon());
            prodavacProfilDTO.setDobijeneRecenzije(p.getDobijeneRecenzije());
            prodavacProfilDTO.setOpisKorisnika(p.getOpisKorisnika());
            prodavacProfilDTO.setProsecnaOcena(p.getProsecnaOcena());
            prodavacProfilDTO.setUloga(p.getUloga());

            Set<ProizvodiNaProdajuDTO> proizvodiNaProdajuDTO = new HashSet<>();
            for (Proizvod prodNaProdaju : p.getProizvodiNaProdaju()) {
                ProizvodiNaProdajuDTO prodDTO = new ProizvodiNaProdajuDTO();
                prodDTO.setNaziv(prodNaProdaju.getNaziv());
                prodDTO.setCena(prodNaProdaju.getCena());
                // Dodajte ostala potrebna polja
                proizvodiNaProdajuDTO.add(prodDTO);
            }

            proizvodDTO.setProdavac(prodavacProfilDTO);


            Kupac k = proizvod.getKupac();
            if (k != null) {
                KupacProfilDTO kupacProfilDTO = new KupacProfilDTO();
                kupacProfilDTO.setId(k.getId());
                kupacProfilDTO.setIme(k.getIme());
                kupacProfilDTO.setPrezime(k.getPrezime());
                kupacProfilDTO.setKorisnickoIme(k.getKorisnickoIme());
                kupacProfilDTO.setDatumRodjenja(k.getDatumRodjenja());
                kupacProfilDTO.setSlika(k.getSlika());
                kupacProfilDTO.setOpisKorisnika(k.getOpisKorisnika());
                kupacProfilDTO.setProsecnaOcena(k.getProsecnaOcena());

                // Set kupljenih proizvoda
                Set<ProizvodiNaProdajuDTO> kupljeniProizvodiDTO = new HashSet<>();
                for (Proizvod kupljenProizvod : k.getKupljeniProizvodi()) {
                    ProizvodiNaProdajuDTO kproizvodDTO = new ProizvodiNaProdajuDTO();
                    kproizvodDTO.setNaziv(kupljenProizvod.getNaziv());
                    kproizvodDTO.setCena(kupljenProizvod.getCena());
                    // Dodajte ostala polja po potrebi
                    kupljeniProizvodiDTO.add(kproizvodDTO);
                }
                kupacProfilDTO.setKupljeniProizvodi(kupljeniProizvodiDTO);

                proizvodDTO.setKupac(kupacProfilDTO);
            }
            Set< Kategorija> kategorije=proizvod.getKategorija();
            Set<KategorijaDTO> kategorijeDTO=new HashSet<>();
            for(Kategorija kategorija: kategorije){
                KategorijaDTO kategorijaDTO= new KategorijaDTO();
                kategorijaDTO.setNazivKategorije(kategorija.getNazivKategorije());
                kategorijaDTO.setId(kategorija.getId());
                kategorijeDTO.add(kategorijaDTO);
            }

            proizvodDTO.setKategorije(kategorijeDTO);
            return proizvodDTO;
        }
        return null;
    }
}
