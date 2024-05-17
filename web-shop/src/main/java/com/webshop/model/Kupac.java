package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kupac extends Korisnik{
    @Column
    private Double prosecnaOcena;

    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Proizvod> kupljeniProizvodi = new ArrayList<>();


    @OneToMany(mappedBy = "kupacKojiDajePonudu",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ponuda> ponuda = new HashSet<>();

//    public List<Proizvod> getKupljeniProizvodi() {
//
//        return kupljeniProizvodi;
//    }

//    public void setKupljeniProizvodi(Set<Proizvod> kupljeniProizvodi) {
//        this.kupljeniProizvodi = kupljeniProizvodi;


    @ElementCollection
    private Map<String, Integer> ocene = new HashMap<>(); // Kupac ID, Ocena

    @ElementCollection
    private Map<String, String> komentari = new HashMap<>(); // Kupac ID, Komentar

//    }

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
