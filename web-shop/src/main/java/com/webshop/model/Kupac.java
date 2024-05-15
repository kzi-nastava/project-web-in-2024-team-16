package com.webshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Kupac extends Korisnik{
    @Column
    private Double prosecnaOcena;

    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Proizvod> kupljeniProizvodi = new ArrayList<>();

    @OneToMany(mappedBy = "kupacKojiDajePonudu",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponuda = new HashSet<>();

    public List<Proizvod> getKupljeniProizvodi() {
        return kupljeniProizvodi;
    }

    public void setKupljeniProizvodi(ArrayList<Proizvod> kupljeniProizvodi) {
        this.kupljeniProizvodi = kupljeniProizvodi;
    }

    public Set<Ponuda> getPonuda() {
        return ponuda;
    }

    public void setPonuda(Set<Ponuda> ponuda) {
        this.ponuda = ponuda;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "kupljeniProizvodi=" + kupljeniProizvodi +
                ", ponuda=" + ponuda +
                ", prosecnaOcena=" + prosecnaOcena +
                '}';
    }
}
