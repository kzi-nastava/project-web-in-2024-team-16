package com.webshop.DTO;

import com.webshop.model.Uloga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaKorisnikaDTO {

    private String korisnickoIme;

    private String lozinka;

    private Uloga uloga;

}
