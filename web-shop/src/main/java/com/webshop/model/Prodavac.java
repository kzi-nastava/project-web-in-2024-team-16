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
public class Prodavac extends Korisnik{

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    @Column
    private Double prosecnaOcena;

    @ElementCollection
    private Map<String, Integer> ocene = new HashMap<>(); // Kupac ID, Ocena

    @ElementCollection
    private Map<String, String> komentari = new HashMap<>(); // Kupac ID, Komentar

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
