package com.webshop.service;

import com.webshop.DTO.ProizvodDTO;

import com.webshop.DTO.ProizvodPrekoKategorijeDTO;
import com.webshop.error.*;
import com.webshop.model.*;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProdavacRepository;

import com.webshop.error.PasswordMismatchException;
import com.webshop.error.ProductNotFoundException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;

import com.webshop.repository.ProizvodRepository;
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

        //Kategorija kategorija = proizvodDTO.getKategorija();
      // String nazivKategorije = proizvodDTO.getKategorija(); // Pretpostavljamo da imate ovu metodu u DTO-u
       // Kategorija kategorija = kategorijaRepository.findByNazivKategorije(nazivKategorije);
      /*  if (kategorija == null) {
            // Kategorija ne postoji, tretirajte ovo u skladu sa vašim zahtevima
        } else {
            proizvod.setKategorija(kategorija);
        }*/
        Proizvod proizvod=new Proizvod();
        proizvod.setKupac(null);
        proizvod.setNaziv(proizvodDTO.getNaziv());
        proizvod.setTip(proizvodDTO.getTipProdaje());
        proizvod.setOpis(proizvodDTO.getOpis());
        proizvod.setSlikaProizvoda(proizvodDTO.getSlikaProizvoda());
        proizvod.setCena(proizvodDTO.getCena());
       /* Set<Kategorija> kategorijeSet = new HashSet<>();
        for (Kategorija kategorijaDTO : proizvodDTO.getKategorije()) {
            System.out.println("usao u for");
            Kategorija kategorija = kategorijaRepository.findByNazivKategorije(kategorijaDTO.getNazivKategorije());
            System.out.println(kategorija);
            if (kategorija == null) {
               throw new CategoryExistsException("Kategorija koju zelite da unesete ne postoji, morate je dodati!");

            }
            kategorijeSet.add(kategorija);
        }*/

      Optional<Prodavac> prodavac=prodavacRepository.findById(korisnik.getId());
        proizvod.setProdavac(prodavac.get());///moram get kad je optionalt
        proizvod.setKategorija(proizvodDTO.getKategorije());
      //  proizvod.setKategorija(kategorija);
        proizvod.setDatumObjavljivanja(new Date());


        proizvod=proizvodRepository.save(proizvod);//saljem sve ali ja nisam morala pri pravljenju da unosim sve

    }

    public List<ProizvodPrekoKategorijeDTO> findByKategorijaId(Long kategorijaId) throws ProductNotFoundException {
        List<Proizvod> proizvodi=proizvodRepository.findByKategorijaId(kategorijaId);

        List<ProizvodPrekoKategorijeDTO> proizvodiDTO = new ArrayList<>();


        for(Proizvod proizvod: proizvodi){
            ProizvodPrekoKategorijeDTO proizvodDTO = new ProizvodPrekoKategorijeDTO();
            proizvodDTO.setNaziv(proizvod.getNaziv());
            proizvodDTO.setOpis(proizvod.getOpis());
            proizvodDTO.setSlikaProizvoda(proizvod.getSlikaProizvoda());
            proizvodiDTO.add(proizvodDTO);
        }
        if (proizvodiDTO.isEmpty()) {
            throw new ProductNotFoundException("U kategoriji ne postoji ni jedan proizvod!");
        }
        return proizvodiDTO;
    }
}
