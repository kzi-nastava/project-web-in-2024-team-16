package com.webshop.DTO;

import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecenzijaPrikazDTO {

    private ProdavacPrikazRecenzijeDTO prodavacKojiJeDobioRecenziju;

    private String komentar;

    private Date datumPodnosenjaRecenzije;

    private Double ocena;

}
