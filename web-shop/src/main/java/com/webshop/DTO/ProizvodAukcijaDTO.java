package com.webshop.DTO;

import com.webshop.model.TipProdaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProizvodAukcijaDTO {

    private String naziv;

    private String opis;

    private String slikaProizvoda;

    private TipProdaje tipProdaje;
}
