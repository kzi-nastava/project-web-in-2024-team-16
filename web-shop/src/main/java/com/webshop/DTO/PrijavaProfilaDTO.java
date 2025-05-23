package com.webshop.DTO;

import com.webshop.model.Korisnik;
import com.webshop.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaProfilaDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razlogPrijave;

    private Date datumPodnosenjaPrijave;

    private Status statusPrijave;

    private PrijavaKorisnikDTO podnosiocPrijave;

    private PrijavaKorisnikDTO prijavljeniKorisnik;

}
