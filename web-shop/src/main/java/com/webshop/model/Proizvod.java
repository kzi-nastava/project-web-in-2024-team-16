package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Proizvod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column
    private String opis;

    @Column
    private String slikaProizvoda;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "proizvod_kategorija",
            joinColumns = {
                    @JoinColumn(name = "proizvod_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "kategorija_id", referencedColumnName = "id")
            }
    )
    private Set<Kategorija> kategorija = new HashSet<>();

 /*   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kategorija  kategorija;*/



    //private ArrayList<Kategorija> kategorija = new ArrayList<>();
    @Column
    private Double cena;

    @Enumerated(EnumType.STRING)
    @Column
    private TipProdaje tip;

    @Column
    private Date datumObjavljivanja;

    @Column
    private Boolean recenzijaKupac;

    @Column
    private Boolean recenzijaProdavac;

    @Column
    private Boolean prodat;

    @OneToMany(mappedBy = "proizvod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponude =new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Prodavac prodavac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kupac kupac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlikaProizvoda() {
        return slikaProizvoda;
    }

    public void setSlikaProizvoda(String slikaProizvoda) {
        this.slikaProizvoda = slikaProizvoda;
    }

    public Set<Kategorija> getKategorija() {
        return kategorija;
    }

    public void setKategorija(Set<Kategorija> kategorija) {
        this.kategorija = kategorija;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public TipProdaje getTip() {
        return tip;
    }

    public void setTip(TipProdaje tip) {
        this.tip = tip;
    }

    public Date getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(Date datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public Boolean getRecenzijaKupac() {
        return recenzijaKupac;
    }

    public void setRecenzijaKupac(Boolean recenzijaKupac) {
        this.recenzijaKupac = recenzijaKupac;
    }

    public Boolean getRecenzijaProdavac() {
        return recenzijaProdavac;
    }

    public void setRecenzijaProdavac(Boolean recenzijaProdavac) {
        this.recenzijaProdavac = recenzijaProdavac;
    }

    public Boolean getProdat() {
        return prodat;
    }

    public void setProdat(Boolean prodat) {
        this.prodat = prodat;
    }

    public Set<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(Set<Ponuda> ponude) {
        this.ponude = ponude;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", slika='" + slikaProizvoda + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", cena=" + cena +
                ", tip=" + tip +
                ", datumObjavljivanja=" + datumObjavljivanja +
                ", prodavac=" + prodavac +
                ", kupac=" + kupac +
                ", recenzijaKupac=" + recenzijaKupac +
                ", recenzijaProdavac=" + recenzijaProdavac +
                ", prodat=" + prodat +
                '}';
    }
}
