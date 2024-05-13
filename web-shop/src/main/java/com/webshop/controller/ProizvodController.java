package com.webshop.controller;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

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
}
