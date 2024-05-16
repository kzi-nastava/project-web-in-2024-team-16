package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
public class Kategorija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nazivKategorije;

   @ManyToMany(mappedBy = "kategorija", fetch = FetchType.LAZY)
  // @JsonIgnore
    private Set<Proizvod> proizvodi = new HashSet<>();
  
   //@OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   //private Set<Proizvod> proizvodi = new HashSet<>();
   //   @ManyToMany(mappedBy = "kategorija", fetch = FetchType.LAZY)
    //private ArrayList<Proizvod> proizvodi = new ArrayList<>();

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
