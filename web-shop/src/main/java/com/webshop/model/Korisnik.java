package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

enum Tip{KUPAC, PRODAVAC, ADMINISTRATOR};
@Entity
public class Korisnik implements Serializable {
    @Column
    private String ime;
    @Column
    private String prezime;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private String korisnicko_ime;
    @Column(unique = true)
    private String mejl;
    @Column
    private Long telefon;
    @Column
    private String lozinka;
    @Column
    private Date datum_rodjenja;
    @Column
    private String slika;
    @Column
    private String opis;
    @Column
    private Tip uloga;
    @Column
    private Boolean blokiran;

    @OneToMany(mappedBy = "korisnikKojiJeDaoRecenziju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    @OneToMany(mappedBy = "podnosiocPrijave", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<PrijavaProfila> prijava = new ArrayList<>();

    @OneToMany(mappedBy = "prijavljeniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<PrijavaProfila> prijavljen = new ArrayList<>();

}
