package com.webshop.controller;

import com.webshop.DTO.KategorijaDTO;
import com.webshop.DTO.ProizvodDTO;
import com.webshop.error.CategoryExistsException;
import com.webshop.error.NoSellerException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Uloga;
import com.webshop.service.KategorijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class KategorijaController {
    @Autowired
    private KategorijaService kategorijaService;

    @PostMapping("/newCategory/{id}")
    public ResponseEntity<String> addNewCategory(@PathVariable Long id,@RequestBody String nazivKategorije, HttpSession session) throws CategoryExistsException, UserNotFoundException, NoSellerException {
            // Provera da li kategorija već postoji
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if(korisnik==null){
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            throw new UserNotFoundException("Greska!");

        }
        if(!korisnik.getUloga().equals(Uloga.PRODAVAC)) {

            throw new NoSellerException("MORA BITI PRODAVAC");
        }
        if (kategorijaService.proveriPostojanjeKategorije(nazivKategorije)) {
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prosleđena kategorija već postoji.");
            throw new CategoryExistsException("Prosleđena kategorija već postoji!");
        }
            // Dodavanje nove kategorije u sistem
            kategorijaService.dodajNovuKategoriju(nazivKategorije);

            return ResponseEntity.ok().body("Nova kategorija uspešno dodata!");

    }
  
    @GetMapping("/categories")
    public ResponseEntity<List<KategorijaDTO>> getAllCategories() {
        List<KategorijaDTO> kategorijaDTO = kategorijaService.findAll();
        return ResponseEntity.ok(kategorijaDTO);
    }

}
