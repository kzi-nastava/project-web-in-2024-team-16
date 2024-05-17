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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            proizvod = proizvodService.findByNazivAndOpis(name, description);

        } else if (name != null) {
            proizvod = proizvodService.findByNaziv(name);

        } else if (description != null) {
            proizvod = proizvodService.findByOpis(description);

        } else {
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
            throw new ProductNotFoundException("Morate uneti od-do za cenu.");


        }
        return ResponseEntity.ok(proizvod);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Proizvod updatedProduct, HttpSession session) throws UserNotFoundException, ProductCanNotBeeChanged {

        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if(korisnik==null){
            throw new UserNotFoundException("Morate biti prijavljeni.");

        }

        Optional<Proizvod> existingProduct = proizvodService.findById(id);

        if(existingProduct.get().getProdat()){
            throw new ProductCanNotBeeChanged("Proizvod je prodat.");

        }
       if (existingProduct.isEmpty()) {
           throw new UserNotFoundException("Proizvod sa ID-jem " + id + " nije pronađen.");

        }
        if (existingProduct.get().getTip() == TipProdaje.AUKCIJA && !existingProduct.get().getPonude().isEmpty()) {

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
        if(korisnik==null){

            throw new UserNotFoundException("Morate biti prijavljeni");
        }
        if(!korisnik.getUloga().equals(Uloga.PRODAVAC)) {

            throw new NoSellerException("Samo prodavac može da postavi proizvod na prodaju.");
        }
        Set<Kategorija> kategorijeSet = new HashSet<>();
        for (Kategorija kategorijaDTO : proizvodDTO.getKategorije()) {
            Kategorija kategorija = kategorijaRepository.findByNazivKategorije(kategorijaDTO.getNazivKategorije());
            if (kategorija == null) {
                throw new CategoryExistsException("Kategorija koju želite da unesete ne postoji, morate je dodati.");
            }
            kategorijeSet.add(kategorija);
        }

        proizvodService.dodajProizvod(proizvodDTO, korisnik);

        return ResponseEntity.ok().body("Proizvod uspešno postavljen na prodaju.");
    }



}
