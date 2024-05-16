package com.webshop.service;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.DTO.ProizvodPrekoKategorijeDTO;
import com.webshop.DTO.ProizvodiNaProdajuDTO;
import com.webshop.error.*;
import com.webshop.model.*;
import com.webshop.repository.*;
import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;

@Service
public class ProizvodService {

    @Autowired
    private  ProizvodRepository proizvodRepository;
    @Autowired
    private KategorijaRepository kategorijaRepository;
    @Autowired
    private ProdavacRepository prodavacRepository;
    @Autowired
    private KupacRepository kupacRepository;


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

    public void updateProduct(Proizvod proizvod, Proizvod updatedProduct) {


        proizvod.setCena(updatedProduct.getCena());
       // proizvod.setId(updatedProduct.getId());
        proizvod.setNaziv(updatedProduct.getNaziv());
        proizvod.setDatumObjavljivanja(updatedProduct.getDatumObjavljivanja());
        proizvod.setKategorija(updatedProduct.getKategorija());
       // proizvod.setKupac(updatedProduct.getKupac());
        proizvod.setPonude(updatedProduct.getPonude());
     //   proizvod.setProdat(updatedProduct.getProdat());
        proizvod.setOpis(updatedProduct.getOpis());
       // proizvod.setProdavac(updatedProduct.getProdavac());
     //   proizvod.setRecenzijaKupac(updatedProduct.getRecenzijaKupac());
        proizvod.setSlikaProizvoda(updatedProduct.getSlikaProizvoda());
        proizvod.setTip(updatedProduct.getTip());
        proizvodRepository.save(proizvod);

    }

    public Optional<Proizvod> findById(Long id) throws UserNotFoundException {
        Optional<Proizvod> proizvod = proizvodRepository.findById(id);
        if (proizvod.isPresent()) {
            return proizvod;
        } else {
            throw new UserNotFoundException("Proizvod sa ID-jem " + id + " nije pronađen.");
        }
    }

    public void dodajProizvod(ProizvodDTO proizvodDTO, Korisnik korisnik) throws CategoryExistsException {

        Proizvod proizvod=new Proizvod();

        proizvod.setKupac(null);
        proizvod.setNaziv(proizvodDTO.getNaziv());
        proizvod.setTip(proizvodDTO.getTipProdaje());
        proizvod.setOpis(proizvodDTO.getOpis());
        proizvod.setSlikaProizvoda(proizvodDTO.getSlikaProizvoda());
        proizvod.setCena(proizvodDTO.getCena());

        Optional<Prodavac> prodavac=prodavacRepository.findById(korisnik.getId());
        proizvod.setProdavac(prodavac.get());///moram get kad je optional

        proizvod.setKategorija(proizvodDTO.getKategorije());
        proizvod.setDatumObjavljivanja(new Date());


        proizvod=proizvodRepository.save(proizvod);

    }

    public ProizvodiNaProdajuDTO kupiProizvodFiksnaCena(Proizvod proizvod, Korisnik korisnik) throws ProductSoldException {

        if(proizvod.getProdat()){
            throw  new ProductSoldException("Ovaj proizvod je već prodat.");
        }

        Optional<Kupac> kupac = kupacRepository.findById(korisnik.getId());
        kupac.get().getKupljeniProizvodi().add(proizvod);

        proizvod.setProdat(true);
        proizvod.setKupac(kupac.get());

        Optional<Prodavac> prodavac = prodavacRepository.findById(proizvod.getProdavac().getId());

        prodavac.get().getProizvodiNaProdaju().remove(proizvod);

        proizvod.setProdavac(prodavac.get());
        proizvodRepository.save(proizvod);
        ProizvodiNaProdajuDTO proizvodiNaProdajuDTO=new ProizvodiNaProdajuDTO();
        proizvodiNaProdajuDTO.setOpis(proizvod.getOpis());
        proizvodiNaProdajuDTO.setCena(proizvod.getCena());
        proizvodiNaProdajuDTO.setNaziv(proizvod.getNaziv());
        proizvodiNaProdajuDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());




        return proizvodiNaProdajuDTO;
    }


  /*  public Proizvod kupiProizvodAukcija(Long id, Korisnik korisnik) {
        Optional<Proizvod> proizvod = proizvodRepository.findById(id);

        //kastovanje korisnika u kupca
        Kupac kupac = (Kupac) korisnik;
        kupac.getKupljeniProizvodi().add(proizvod.get());



        return proizvod.get();
    }*/
}
