package com.webshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Prodavac extends Korisnik{

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    @Column
    private Double prosecnaOcena;

    public Set<Proizvod> getProizvodiNaProdaju() {
        return proizvodiNaProdaju;
    }

    public void setProizvodiNaProdaju(Set<Proizvod> proizvodiNaProdaju) {
        this.proizvodiNaProdaju = proizvodiNaProdaju;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    @Override
    public String toString() {
        return "Prodavac{" +
                "proizvodiNaProdaju=" + proizvodiNaProdaju +
                ", prosecnaOcena=" + prosecnaOcena +
                '}';
    }
}
