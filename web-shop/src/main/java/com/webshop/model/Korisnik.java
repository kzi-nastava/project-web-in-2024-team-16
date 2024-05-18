package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayway.jsonpath.internal.function.text.Length;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


 //enum Uloga{KUPAC, PRODAVAC, ADMINISTRATOR};

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Korisnik")
@Entity
@Getter
@Setter
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(nullable = false)
    private String korisnickoIme;

    @Column(nullable = false)
    //,unique = true
    //@Email(message = "Invalid mail")
    private String mejl;

    @Column
   // @Length(max=40, message = "Neispravan broj telefona!")
    private String telefon;

    @Column(nullable = false)
    //@Pattern(regexp = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{8,}$")
    private String lozinka;

    @Column
    private Date datumRodjenja;

    @Column
    private String slika;

    @Column
    private String opisKorisnika;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonIgnore
    private Uloga uloga;

    @Column
    private Boolean blokiran;

    @OneToMany(mappedBy = "korisnikKojiJeDaoRecenziju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Recenzija> recenzije = new HashSet<>();

    @OneToMany(mappedBy = "korisnikKojiJeDobioRecenziju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Recenzija> dobijeneRecenzije = new HashSet<>();

    @OneToMany(mappedBy = "podnosiocPrijave", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PrijavaProfila> prijava = new HashSet<>();

    @OneToMany(mappedBy = "prijavljeniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PrijavaProfila> prijavljen = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getOpisKorisnika() {
        return opisKorisnika;
    }

    public void setOpisKorisnika(String opisKorisnika) {
        this.opisKorisnika = opisKorisnika;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Boolean getBlokiran() {
        return blokiran;
    }

    public void setBlokiran(Boolean blokiran) {
        this.blokiran = blokiran;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Set<PrijavaProfila> getPrijava() {
        return prijava;
    }

    public void setPrijava(Set<PrijavaProfila> prijava) {
        this.prijava = prijava;
    }

    public Set<PrijavaProfila> getPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(Set<PrijavaProfila> prijavljen) {
        this.prijavljen = prijavljen;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnicko_ime='" + korisnickoIme + '\'' +
                ", mejl='" + mejl + '\'' +
                ", telefon=" + telefon +
                ", lozinka='" + lozinka + '\'' +
                ", datum_rodjenja=" + datumRodjenja +
                ", slika='" + slika + '\'' +
                ", opis='" + opisKorisnika + '\'' +
                ", uloga=" + uloga +
                ", blokiran=" + blokiran +
                ", recenzije=" + recenzije +
                ", prijava=" + prijava +
                ", prijavljen=" + prijavljen +
                '}';
    }
}
