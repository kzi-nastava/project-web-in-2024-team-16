package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

enum TipProdaje{FIKSNA, AUKCIJA};
@Entity
public class Proizvod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String opis;
    private String slika;
    private String kategorija;
    private Double cena;
    private TipProdaje tip;
    private Date datumObjavljivanja;

    //private Ponuda ponude;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Prodavac prodavac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kupac kupac;
    private Boolean recenzijaKupac;
    private Boolean recenzijaProdavac;
    private Boolean prodat;

}
