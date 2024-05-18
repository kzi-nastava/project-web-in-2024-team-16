package com.webshop.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaKorisnikDTO {

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String mejl;
}
