package com.webshop.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Ponuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatsko generisanje ključa
    private Long id;

    private double cena;

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
