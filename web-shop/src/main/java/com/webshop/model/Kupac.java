package com.webshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Kupac extends Korisnik{

    private List<Proizvod> kupljeni_proizvodi = new ArrayList<>();
    //private Recenzija recenzije;

    //Tip ce biti tip klase proizvod
    //private List<Proizvod> kupljeni_proizvodi = new ArrayList<>();
    //Tip ce biti tip klase recenzija
    //private Recenzija recenzije;

    private Double prosecna_ocena;

}
