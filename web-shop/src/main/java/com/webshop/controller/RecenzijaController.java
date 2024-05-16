package com.webshop.controller;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.DTO.RecenzijaDTO;
import com.webshop.error.NoSellerException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.model.Korisnik;
import com.webshop.model.TipProdaje;
import com.webshop.model.Uloga;
import com.webshop.repository.RecenzijaRepository;
import com.webshop.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class RecenzijaController {

    @Autowired
    private RecenzijaService recenzijaService;

//    @GetMapping("/sellerReviews/{id}")
//    public ResponseEntity<List<RecenzijaDTO>> getRecenzijeProdavca(@PathVariable Long kupacId, HttpSession session) throws NoSellerException {
//        // Provera autentikacije
//        System.out.println("ulazi u metodu");
//        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
//        System.out.println("ulazi u metodu");
//        if (korisnik == null) {
//            // Korisnik nije prijavljen
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//
//        // Provera da li je korisnik kupac
//        if (!korisnik.getUloga().equals(Uloga.KUPAC)) {
//            // Korisnik nije kupac
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//
//
//        // Dohvatanje recenzija prodavca sa datim ID-om
////        List<RecenzijaDTO> recenzije = recenzijaService.findRecenzijeKupca(kupacId);
////
////        // Provera da li je kupac dao recenziju tom prodavcu
////      /*  Long kupacId = korisnik.getId();
////        boolean kupacDaoRecenziju = recenzijaService.kupacDaoRecenziju(prodavacId, kupacId);
////        if (!kupacDaoRecenziju) {
////            // Ako kupac nije dao recenziju tom prodavcu, vraćamo zabranu pristupa
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
////        }
//
//*/
//        if (recenzije.isEmpty()) {
//            // Ako nema recenzija za ovog prodavca ili ako korisnik nije dao recenziju ovom prodavcu
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }
//        return ResponseEntity.ok(recenzije);
//    }


}
