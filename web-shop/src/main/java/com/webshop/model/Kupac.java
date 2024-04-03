package com.webshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Kupac extends Korisnik{
    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<Proizvod> kupljeniProizvodi = new ArrayList<>();

    //@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Set<Recenzija> recenzijeKupci = new HashSet<>();

    @OneToMany(mappedBy = "kupacKojiDajePonudu",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponuda = new HashSet<>();
    private Double prosecnaOcena;

}
