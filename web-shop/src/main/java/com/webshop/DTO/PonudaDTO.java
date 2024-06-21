package com.webshop.DTO;

import com.webshop.model.Kupac;
import com.webshop.model.Proizvod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PonudaDTO {

    private double cena;

    private PrijavaKorisnikDTO kupacKojiDajePonudu;

    private ProizvodAukcijaDTO proizvod;

}
