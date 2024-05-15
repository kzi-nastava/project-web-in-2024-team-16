package com.webshop.DTO;


import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KupacProfilDTO {
    private String ime;

    private String prezime;

    private String korisnickoIme;

    private Date datumRodjenja;

    private String slika;

    private String opisKorisnika;
    private Double prosecnaOcena;

    private Set<ProizvodiNaProdajuDTO> kupljeniProizvodi = new HashSet<>();

    private Set<Recenzija> dobijeneRecenzije = new HashSet<>();

}
