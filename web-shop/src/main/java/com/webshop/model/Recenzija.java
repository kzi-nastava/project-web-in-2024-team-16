package com.webshop.model;

import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double ocena;

    @Column
    private String komentar;

    @Column
    private Date datumRecenzije;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik  korisnikKojiJeDaoRecenziju;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        if (ocena >= 1 && ocena <= 5) {
            this.ocena = ocena;
        } else {
            // Ako ocena nije u validnom opsegu, možete baciti izuzetak,
            // ili postaviti neku podrazumevanu vrednost, kao što je 1 ili 5.
            throw new IllegalArgumentException("Ocena mora biti u opsegu od 1 do 5.");
        }
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Date getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(Date datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

    public Korisnik getKorisnikKojiJeDaoRecenziju() {
        return korisnikKojiJeDaoRecenziju;
    }

    public void setKorisnikKojiJeDaoRecenziju(Korisnik korisnikKojiJeDaoRecenziju) {
        this.korisnikKojiJeDaoRecenziju = korisnikKojiJeDaoRecenziju;
    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "id=" + id +
                ", ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                ", datumRecenzije=" + datumRecenzije +
                ", korisnikKojiJeDaoRecenziju=" + korisnikKojiJeDaoRecenziju +
                '}';
    }
}
