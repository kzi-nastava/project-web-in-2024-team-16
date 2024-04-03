package com.webshop.model;

import java.io.Serializable;
import java.util.Date;


enum  Status{Podneta, Odbijena, Prihvacena}
public class PrijavaProfila implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatsko generisanje ključa
    private Long id;
    private String razlogPrijave;

    private Date datumPodnosenjaPrijave;

    private  Korisnik podnosiocPrijave;

    private Korisnik prijavljeniKorisnik;

    private Status statusPrijave;

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
