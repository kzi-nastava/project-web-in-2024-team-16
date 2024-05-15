package com.webshop.DTO;

import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdavacProfilDTO {

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String telefon;

    private String slika;

    private String opisKorisnika;

    private Uloga uloga;

    private Boolean blokiran;

    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    private Set<Recenzija> recenzije = new HashSet<>();

    private Double prosecnaOcena;

}
