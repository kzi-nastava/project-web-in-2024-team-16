package com.webshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProizvodiNaProdajuDTO {

    private Long id;

    private String naziv;

    private String opis;

    private String slikaProizvoda;

    private Double cena;
}
