package com.webshop.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Prodavac extends Korisnik{

    //Tip ce biti tip klase proizvod
 //   private List<Proizvod> proizvodi_na_prodaju = new ArrayList<>();
    //Tip ce biti tip klase recenzija
    //private Recenzija recenzije;
    private Double prosecna_ocena;

}
