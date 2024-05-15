package com.webshop.controller;

import com.webshop.DTO.ProizvodDTO;

import com.webshop.DTO.ProizvodPrekoKategorijeDTO;
import com.webshop.error.*;
import com.webshop.model.*;
import com.webshop.repository.KategorijaRepository;
import com.webshop.service.KategorijaService;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductCanNotBeeChanged;
import com.webshop.error.ProductNotFoundException;

import com.webshop.error.UserNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.Ponuda;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.service.ProizvodService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.service.ProizvodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/product")
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KategorijaRepository kategorijaRepository;

    @GetMapping("/products")
    public ResponseEntity<List<ProizvodDTO>> getAllProducts() {
        System.out.println("ulazi u kontroler");
        System.out.println("pre ispisa statusa");
        //return ResponseEntity.ok(proizvodi);
        List<ProizvodDTO> proizvodiDTO = proizvodService.findAll();
        return ResponseEntity.ok(proizvodiDTO);
    }

    @GetMapping("/{id}")
    public ProizvodDTO getEmployee(@PathVariable(name = "id") Long id, HttpSession session){
        Proizvod proizvod = (Proizvod) session.getAttribute("proizvod");
        session.invalidate();
        return proizvodService.findOne(id);
    }/*
  @GetMapping("/{Id}")
  public ResponseEntity<Proizvod> getProductById(@Valid  @PathVariable Long id, @PathVariable String Id) throws UserNotFoundException {
      Proizvod proizvod = ProizvodService.findById(id);
      return ResponseEntity.ok(proizvod);
  }*/

    @GetMapping("/search")
    public ResponseEntity<List<ProizvodDTO>> searchProducts(@RequestParam(required = false) String name, @RequestParam(required = false) String description) throws ProductNotFoundException, PasswordMismatchException {
        List<ProizvodDTO> proizvod = new ArrayList<>();
        if (name != null && description != null) {
            // Pretraga po nazivu i opisu
            proizvod = proizvodService.findByNazivAndOpis(name, description);
        } else if (name != null) {
            // Pretraga samo po nazivu
            proizvod = proizvodService.findByNaziv(name);
        } else if (description != null) {
            // Pretraga samo po opisu
            proizvod = proizvodService.findByOpis(description);
        } else {
            // Ako nisu prosleđeni ni naziv ni opis, možete vratiti sve proizvode
            proizvod = proizvodService.findAll();
        }
        return ResponseEntity.ok(proizvod);
    }
    @GetMapping("/filterByCategory")
    public ResponseEntity<List<ProizvodDTO>> filterProductsByCategory(@RequestParam(required = false) String category) throws ProductNotFoundException {
        List<ProizvodDTO> proizvod = new ArrayList<>();

        if (category != null ) {
            proizvod = proizvodService.findByKategorija(category);
        }
        else {
            // Ako nisu prosleđeni ni naziv ni opis, možete vratiti sve proizvode
            proizvod = proizvodService.findAll();
        }
        return ResponseEntity.ok(proizvod);
    }
    @GetMapping("/filterByType")
    public ResponseEntity<List<ProizvodDTO>> filterProductsByType(@RequestParam(required = false) TipProdaje type) throws ProductNotFoundException {
        List<ProizvodDTO> proizvod = new ArrayList<>();

        if (type != null ) {
            proizvod = proizvodService.findByTipProdaje(type);
        }
        else {
            proizvod = proizvodService.findAll();
        }
        return ResponseEntity.ok(proizvod);
    }
    @GetMapping("/filterByPrice")
    public ResponseEntity<List<ProizvodDTO>> filterProductsByPrice(@RequestParam(required = false) Double priceFrom,@RequestParam(required = false) Double priceTo) throws ProductNotFoundException {
        List<ProizvodDTO> proizvod = new ArrayList<>();

        if (priceFrom!= null && priceTo!=null ) {
            proizvod = proizvodService.findByCena(priceFrom, priceTo);
        }
        else {
            proizvod = proizvodService.findAll();
          //  throw new ProductNotFoundException("Morate uneti od do za cenu!");???????
        }
        return ResponseEntity.ok(proizvod);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Proizvod updatedProduct, HttpSession session) throws UserNotFoundException, ProductCanNotBeeChanged {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            throw new UserNotFoundException("Greska!");
        }

        Optional<Proizvod> existingProduct = proizvodService.findById(id);
        if (existingProduct.get().getProdat()) {//ako je prodat proizvod on ne moze da se menja
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            throw new ProductCanNotBeeChanged("Proizvod koji je prodat ne može da se menja!");
        }
        // Provera da li postoji proizvod sa datim ID-om
        if (existingProduct.isEmpty()) {
            // return ResponseEntity.notFound().build();
            throw new UserNotFoundException("Proizvod sa ID-jem " + id + " nije pronađen.");
        }
        if (existingProduct.get().getTip() == TipProdaje.AUKCIJA && !existingProduct.get().getPonude().isEmpty()) {
          /*  for(Ponuda ponuda: existingProduct.get().getPonude()){
               // System.out.println(ponuda.getCena());
            }*/

            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Proizvod se ne može izmeniti jer postoje aktivne ponude u aukciji.");
            throw new ProductCanNotBeeChanged("Proizvod se ne može izmeniti jer postoje aktivne ponude u aukciji!");
        }

        if (!existingProduct.get().getProdavac().getKorisnickoIme().equals(korisnik.getKorisnickoIme())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        proizvodService.updateProduct(existingProduct.get(), updatedProduct);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/addForSale/{id}")
    public ResponseEntity<String> SetProductForSell(@PathVariable Long id,@RequestBody ProizvodDTO proizvodDTO,HttpSession session) throws UserNotFoundException, NoSellerException, CategoryExistsException {
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");
        System.out.println("usao u metodu");
        if(korisnik==null){
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            throw new UserNotFoundException("Greska!");

        }
        if(!korisnik.getUloga().equals(Uloga.PRODAVAC)) {

            throw new NoSellerException("MORA BITI PRODAVAC");
        }
        // Provera da li je kategorija validna
       /* KategorijaService kategorijaService = new KategorijaService();
        if (!kategorijaService.proveriPostojanjeKategorije(proizvodDTO.getKategorije().getNazivKategorije())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kategorija ne postoji.");
        }*/
        Set<Kategorija> kategorijeSet = new HashSet<>();
        for (Kategorija kategorijaDTO : proizvodDTO.getKategorije()) {
            Kategorija kategorija = kategorijaRepository.findByNazivKategorije(kategorijaDTO.getNazivKategorije());
            System.out.println("usao u for");
            if (kategorija == null) {
                throw new CategoryExistsException("Kategorija koju zelite da unesete ne postoji, morate je dodati!");
            }
            kategorijeSet.add(kategorija);
        }

        // Dodavanje proizvoda u sistem
        proizvodService.dodajProizvod(proizvodDTO, korisnik);

        return ResponseEntity.ok().body("Proizvod uspešno postavljen na prodaju.");
    }

    @GetMapping("/products/category/{kategorijaId}")
    public ResponseEntity<List<ProizvodPrekoKategorijeDTO>> getProductsByCategory(@PathVariable Long kategorijaId) throws ProductNotFoundException {
        List<ProizvodPrekoKategorijeDTO> proizvodi = proizvodService.findByKategorijaId(kategorijaId);
        if (proizvodi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proizvodi);
    }



}
