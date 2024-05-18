package com.webshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="prijava_profila")
public class PrijavaProfila implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razlogPrijave;

    @Column(nullable = true)
    private String razlogOdbijanja;

    @Column(nullable = true)
    private String razlogPrihvatanja;

    @Column(nullable = false)
    private Date datumPodnosenjaPrijave;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status statusPrijave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Korisnik podnosiocPrijave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
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
