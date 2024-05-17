package com.webshop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ponuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cena;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kupac kupacKojiDajePonudu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "proizvod_id", insertable = false, updatable = false)
    private Proizvod proizvod;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kupac getKupacKojiDajePonudu() {
        return kupacKojiDajePonudu;
    }

    public void setKupacKojiDajePonudu(Kupac kupacKojiDajePonudu) {
        this.kupacKojiDajePonudu = kupacKojiDajePonudu;
    }

    @Override
    public String toString() {
        return "Ponuda{cena=" + cena +
                ", kupacKojiDajePonudu=" + kupacKojiDajePonudu +
                '}';
    }
}
