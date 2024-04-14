package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Kategorija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nazivKategorije;

    @ManyToMany(mappedBy = "kategorija", fetch = FetchType.LAZY)
    private ArrayList<Proizvod> proizvodi = new ArrayList<>();

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "naziv='" + nazivKategorije + '\'' +
                '}';
    }
}
