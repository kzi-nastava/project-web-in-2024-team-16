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
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class KategorijaController {

    @Autowired
    private KategorijaService kategorijaService;

    @PostMapping("/newCategory/{id}")
    public ResponseEntity<String> addNewCategory(@PathVariable Long id, @RequestBody Map<String, String> request, HttpSession session) throws CategoryExistsException, UserNotFoundException, NoSellerException {
        String nazivKategorije = request.get("naziv");

        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            throw new UserNotFoundException("Niste prijavljeni.");
        }
        if (!korisnik.getUloga().equals(Uloga.PRODAVAC)) {
            throw new NoSellerException("Da bi ste dodali kategoriju, morate biti prodavac.");
        }
        if (kategorijaService.proveriPostojanjeKategorije(nazivKategorije)) {
            throw new CategoryExistsException("Prosleđena kategorija već postoji.");
        }

        kategorijaService.dodajNovuKategoriju(nazivKategorije);

        return ResponseEntity.ok().body("Nova kategorija uspešno dodata.");
    }
  
    @GetMapping("/categories")
    public ResponseEntity<List<KategorijaDTO>> getAllCategories() {

        List<KategorijaDTO> kategorijaDTO = kategorijaService.findAll();
        return ResponseEntity.ok(kategorijaDTO);
    }

}
