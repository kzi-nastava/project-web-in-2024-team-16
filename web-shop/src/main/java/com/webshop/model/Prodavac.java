package com.webshop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Prodavac extends Korisnik{

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<Proizvod> proizvodiNaProdaju = new ArrayList<>();

    //@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Set<Recenzija> recenzijeProdavci = new HashSet<>();
    private Double prosecnaOcena;

}
