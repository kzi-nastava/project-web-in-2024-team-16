package com.webshop.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cena;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kupac kupacKojiDajePonudu;

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
        return "Ponuda{" +
                "cena=" + cena +
                ", kupacKojiDajePonudu=" + kupacKojiDajePonudu +
                '}';
    }
}
