package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

enum TipProdaje{FIKSNA, AUKCIJA};
@Entity
public class Proizvod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String naziv;
    private String opis;
    private String slika;
    private String kategorija;
    private Double cena;
    private TipProdaje tip;
    private Date datum_objavljivanja;

    //private Ponuda ponude;

    //private Prodavac prodavac;
    private Boolean recenzija_kupac;
    private Boolean recenzija_prodavac;
    private Boolean prodat;

}
