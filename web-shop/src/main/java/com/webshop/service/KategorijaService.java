package com.webshop.service;

import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class KategorijaService {
    @Autowired
    private KategorijaRepository kategorijaRepository;

    public boolean proveriPostojanjeKategorije(String nazivKategorije) {
        Kategorija kategorija = kategorijaRepository.findByNazivKategorije(nazivKategorije);
        return kategorija!=null;
    }

    public void dodajNovuKategoriju(String nazivKategorije) {
        Kategorija novaKategorija = new Kategorija();
        novaKategorija.setNazivKategorije(nazivKategorije);

        // Čuvanje nove kategorije u repozitorijumu
        kategorijaRepository.save(novaKategorija);
    }
}
