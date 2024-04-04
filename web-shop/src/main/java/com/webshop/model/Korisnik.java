package com.webshop.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

enum Uloga{KUPAC, PRODAVAC, ADMINISTRATOR};

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Korisnik")
@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(unique = true)
    private String korisnickoIme;

    @Column(unique = true)
    private String mejl;

    @Column
    private String telefon;

    @Column
    private String lozinka;

    @Column
    private Date datumRodjenja;

    @Column
    private String slika;

    @Column
    private String opisKorisnika;

    @Enumerated(EnumType.STRING)
    @Column
    private Uloga uloga;

    @Column
    private Boolean blokiran;

    @OneToMany(mappedBy = "korisnikKojiJeDaoRecenziju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    @OneToMany(mappedBy = "podnosiocPrijave", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<PrijavaProfila> prijava = new ArrayList<>();

    @OneToMany(mappedBy = "prijavljeniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<PrijavaProfila> prijavljen = new ArrayList<>();

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

    public void setOpisKorisnika(String opis) {
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

    public ArrayList<PrijavaProfila> getPrijava() {
        return prijava;
    }

    public void setPrijava(ArrayList<PrijavaProfila> prijava) {
        this.prijava = prijava;
    }

    public ArrayList<PrijavaProfila> getPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(ArrayList<PrijavaProfila> prijavljen) {
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
