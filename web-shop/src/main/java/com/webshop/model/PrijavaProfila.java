package com.webshop.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


enum  Status{PODNETA, ODBIJENA, PRIHVACENA}

@Entity
@Table(name="prijava_profila")
public class PrijavaProfila implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String razlogPrijave;

    @Column
    private Date datumPodnosenjaPrijave;

    @Column
    @Enumerated(EnumType.STRING)
    private Status statusPrijave;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik podnosiocPrijave;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik prijavljeniKorisnik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public void setRazlogPrijave(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
    }

    public Date getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public void setDatumPodnosenjaPrijave(Date datumPodnosenjaPrijave) {
        this.datumPodnosenjaPrijave = datumPodnosenjaPrijave;
    }

    public Korisnik getPodnosiocPrijave() {
        return podnosiocPrijave;
    }

    public void setPodnosiocPrijave(Korisnik podnosiocPrijave) {
        this.podnosiocPrijave = podnosiocPrijave;
    }

    public Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }

    public void setPrijavljeniKorisnik(Korisnik prijavljeniKorisnik) {
        this.prijavljeniKorisnik = prijavljeniKorisnik;
    }

    public Status getStatusPrijave() {
        return statusPrijave;
    }

    public void setStatusPrijave(Status statusPrijave) {
        this.statusPrijave = statusPrijave;
    }

    @Override
    public String toString() {
        return "PrijavaProfila{" +
                "id=" + id +
                ", razlogPrijave='" + razlogPrijave + '\'' +
                ", datumPodnosenjaPrijave=" + datumPodnosenjaPrijave +
                ", podnosiocPrijave=" + podnosiocPrijave +
                ", prijavljeniKorisnik=" + prijavljeniKorisnik +
                ", statusPrijave=" + statusPrijave +
                '}';
    }
}
