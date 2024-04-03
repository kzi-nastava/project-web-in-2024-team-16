package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

enum Tip{KUPAC, PRODAVAC, ADMINISTRATOR};
@Entity
public class Korisnik implements Serializable {
    private String ime;
    private String prezime;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private String korisnicko_ime;
    @Column(unique = true)
    private String mejl;
    private Long telefon;
    private String lozinka;
    private Date datum_rodjenja;
    private String slika;
    private String opis;
    private Tip uloga;
    private Boolean blokiran;

}
