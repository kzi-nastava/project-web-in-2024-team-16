package com.webshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Kategorija implements Serializable {

    @Id
    private String nazivKategorije;

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
