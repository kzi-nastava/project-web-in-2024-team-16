package com.webshop.repository;

import com.webshop.DTO.ProizvodDTO;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

    List<Proizvod> findByNazivAndOpis(String name, String description);

    List<Proizvod> findByNaziv(String name);

    List<Proizvod> findByOpis(String description);

  //  List<Proizvod> findByNazivKategorije(String category);
  List<Proizvod> findByKategorijaNazivKategorije(String category);

   // List<Proizvod> findByTip(String type);
   List<Proizvod> findByTipProdaje(TipProdaje tipProdaje);

    List<Proizvod> findByCenaGreaterThanEqualAndCenaLessThanEqual(Double priceFrom, Double priceTo);




    List<Proizvod> findAllByKupacIdAndProdavacId(Long kupacId, Long prodavacId);

    List<Proizvod> findAllByProdavacIdAndKupacId(Long prodavacId, Long kupacId);

    Page<Proizvod> findAll(Pageable pageable);



    //List<Proizvod> findByKategorijaId(Long kategorijaId);

    //List<Proizvod> findByKategorijaId(Long kategorijaId);


}
