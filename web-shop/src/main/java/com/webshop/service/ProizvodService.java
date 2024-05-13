package com.webshop.service;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProizvodService {

    @Autowired
    private  ProizvodRepository proizvodRepository;

   /* public Proizvod findOne(Long id){
        Optional<Proizvod> foundProizvod = proizvodRepository.findById(id);
        if (foundProizvod.isPresent())
            return foundProizvod.get();

        return null;
    }*/
    public ProizvodDTO findOne(Long id){
        Optional<Proizvod> foundProizvod = proizvodRepository.findById(id);
      //  Proizvod prizvod=foundProizvod.get();//dobavljam nadjen proizvod
       // ProizvodDTO proizvodDTO=new ProizvodDTO();
        if (foundProizvod.isPresent()) {
            Proizvod proizvod=foundProizvod.get();
            ProizvodDTO proizvodDTO=new ProizvodDTO();
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setCena(proizvod.getCena());
            proizvodDTO.setTipProdaje(proizvod.getTip());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setId(proizvod.getId());
            return proizvodDTO;
        }
        return null;
    }


    /*   public List<Proizvod> findAll() {
           System.out.println("u servisu");
           return proizvodRepository.findAll();
   
       }*/
 public List<ProizvodDTO> findAll() {
     List<Proizvod> proizvodi = proizvodRepository.findAll();
     List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
     List<Proizvod> slanje = new ArrayList<>();



     for (Proizvod proizvod : proizvodi) {
         ProizvodDTO proizvodDTO = new ProizvodDTO();
         proizvodDTO.setId(proizvod.getId());
         proizvodDTO.setNaziv(proizvod.getNaziv());
         proizvodDTO.setOpis(proizvod.getOpis());
         proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
         // Postaviti ostale atribute po potrebi
         proizvodiDTO.add(proizvodDTO);
     }
     return proizvodiDTO;
 }



    public List<ProizvodDTO> findByNazivAndOpis(String name, String description) throws ProductNotFoundException {
        List<Proizvod> proizvodi = proizvodRepository.findByNazivAndOpis(name, description);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
          throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;


    }

    public List<ProizvodDTO> findByNaziv(String name) throws ProductNotFoundException, PasswordMismatchException {
        List<Proizvod> proizvodi = proizvodRepository.findByNaziv(name);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();
        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;

    }

    public List<ProizvodDTO> findByOpis(String description) throws ProductNotFoundException {
        List<Proizvod> proizvodi = proizvodRepository.findByOpis( description);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            //uvek novi upisati
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;

    }

    public List<ProizvodDTO> findByKategorija(String category) throws ProductNotFoundException {
        List<Proizvod> proizvodi = proizvodRepository.findByKategorijaNazivKategorije(category);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            //uvek novi upisati
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;
    }

    public List<ProizvodDTO> findByTipProdaje(TipProdaje type) throws ProductNotFoundException {
        List<Proizvod> proizvodi = proizvodRepository.findByTipProdaje(type);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();//ako ovo ne odradim vraacace mi npr dve serpe umesto tiganj i serpu, moram
            //uvek novi upisati
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;
    }

    public List<ProizvodDTO> findByCena(Double priceFrom, Double priceTo) throws ProductNotFoundException {
        List<Proizvod> proizvodi = proizvodRepository.findByCenaGreaterThanEqualAndCenaLessThanEqual(priceFrom, priceTo);
        List<ProizvodDTO> proizvodiDTO = new ArrayList<>();

        for(Proizvod proizvod: proizvodi){
            ProizvodDTO proizvodDTO = new ProizvodDTO();
            proizvodDTO.setId(proizvod.getId());
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("Trazeni proizvod ne postoji!");
        }
        return proizvodiDTO;
    }

/*
    public static Proizvod findById(Long id) throws UserNotFoundException {
        Optional<Proizvod> optionalProizvod = proizvodRepository.findById(id);
        if (optionalProizvod.isPresent()) {
            return optionalProizvod.get();
        } else {
            throw new UserNotFoundException("Proizvod sa ID-jem " + id + " nije pronađen.");
        }

    }*/
}
