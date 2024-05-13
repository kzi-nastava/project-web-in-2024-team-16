package com.webshop.controller;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/products")
    public ResponseEntity<List<ProizvodDTO>> getAllProducts() {
        System.out.println("ulazi u kontroler");
        //List<Proizvod> proizvodi = proizvodService.findAll();
        System.out.println("pre ispisa statusa");
        //return ResponseEntity.ok(proizvodi);
        List<ProizvodDTO> proizvodiDTO = proizvodService.findAll();
        return ResponseEntity.ok(proizvodiDTO);
    }

    @GetMapping("/{id}")
    public ProizvodDTO getProizvod(@PathVariable(name = "id") Long id, HttpSession session) {
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
            throw new ProductNotFoundException("Morate uneti od do za cenu!");
        }
        return ResponseEntity.ok(proizvod);
    }



}
