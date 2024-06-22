package com.webshop.DTO;

import com.webshop.model.Kategorija;
import com.webshop.model.Prodavac;
import com.webshop.model.TipProdaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SviProizvodiDTO {
    private Long id;

    private String naziv;

    private String opis;

    private String slikaProizvoda;

    private Set<KategorijaDTO> kategorije = new HashSet<>();

    private Double cena;

    private TipProdaje tipProdaje;

    private ProdavacProfilDTO prodavac;
}
