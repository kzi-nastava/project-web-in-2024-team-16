package com.webshop.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.webshop.DTO.*;
import com.webshop.error.NoCustomerException;
import com.webshop.error.NoReportException;
import com.webshop.error.NoSellerException;
import com.webshop.error.UserNotFoundException;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaProfilaRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.repository.RecenzijaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrijavaProfilaService {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PrijavaProfilaRepository prijavaProfilaRepository;
    @Autowired
    private ProizvodRepository proizvodRepository;

    @Transactional
    public PrijavaProfilaDTO PodnesiPrijacuZaKupca(Korisnik korisnik, PrijavaRequestDTO prijavaRequestDTO, Long id) throws UserNotFoundException, NoCustomerException {

        Optional<Korisnik> prijavljeniKorisnikOptional = korisnikRepository.findById(id);
        Optional<Korisnik> podnosiocPrijave=korisnikRepository.findById(korisnik.getId());
        if (prijavljeniKorisnikOptional.isEmpty()) {

            throw new UserNotFoundException("Korisnik nije pronađen.");
        }

        Korisnik prijavljeniKorisnik = prijavljeniKorisnikOptional.get();

        if (!prijavljeniKorisnik.getUloga().equals(Uloga.KUPAC)) {

            throw new NoCustomerException("Možete prijaviti samo kupce vaših proizvoda.");
        }
        PrijavaProfila prijavaProfila = new PrijavaProfila();
        prijavaProfila.setRazlogPrijave(prijavaRequestDTO.getRazlogPrijave());
        prijavaProfila.setDatumPodnosenjaPrijave(new Date());
        prijavaProfila.setStatusPrijave(Status.PODNETA);
        prijavaProfila.setPodnosiocPrijave(podnosiocPrijave.get());
        prijavaProfila.setPrijavljeniKorisnik(prijavljeniKorisnik);

        prijavaProfila = prijavaProfilaRepository.save(prijavaProfila);
        PrijavaKorisnikDTO prijavljenKorisnikDTO=new PrijavaKorisnikDTO();
        PrijavaKorisnikDTO podnosiocPrijaveKorisnikDTO=new PrijavaKorisnikDTO();
        prijavljenKorisnikDTO.setIme(prijavljeniKorisnik.getIme());
        prijavljenKorisnikDTO.setPrezime(prijavljeniKorisnik.getPrezime());
        prijavljenKorisnikDTO.setMejl(prijavljeniKorisnik.getMejl());
        prijavljenKorisnikDTO.setKorisnickoIme(prijavljeniKorisnik.getKorisnickoIme());

        podnosiocPrijaveKorisnikDTO.setKorisnickoIme(podnosiocPrijave.get().getKorisnickoIme());
        podnosiocPrijaveKorisnikDTO.setIme(podnosiocPrijave.get().getIme());
        podnosiocPrijaveKorisnikDTO.setPrezime(podnosiocPrijave.get().getPrezime());
        podnosiocPrijaveKorisnikDTO.setMejl(podnosiocPrijave.get().getMejl());


        PrijavaProfilaDTO prijavaProfilaDTO=new PrijavaProfilaDTO();
        prijavaProfilaDTO.setDatumPodnosenjaPrijave(prijavaProfila.getDatumPodnosenjaPrijave());
        prijavaProfilaDTO.setPrijavljeniKorisnik(prijavljenKorisnikDTO);
        prijavaProfilaDTO.setPodnosiocPrijave(podnosiocPrijaveKorisnikDTO);
        prijavaProfilaDTO.setStatusPrijave(prijavaProfila.getStatusPrijave());
        prijavaProfilaDTO.setRazlogPrijave(prijavaProfila.getRazlogPrijave());


        return prijavaProfilaDTO;
    }

    public List<PrijavaProfila> pregledPrijava() {

        return prijavaProfilaRepository.findAllByStatusPrijave(Status.PODNETA);
    }

    public PrijavaProfilaDTO PodnesiPrijacuZaProdavca(Korisnik korisnik, PrijavaRequestDTO prijavaRequestDTO, Long id) throws UserNotFoundException, NoSellerException {

        Optional<Korisnik> prijavljeniKorisnikOptional = korisnikRepository.findById(id);
        Optional<Korisnik> podnosiocPrijave=korisnikRepository.findById(korisnik.getId());
        if (prijavljeniKorisnikOptional.isEmpty()) {

            throw new UserNotFoundException("Korisnik nije pronađen.");
        }

        Korisnik prijavljeniKorisnik = prijavljeniKorisnikOptional.get();

        if (!prijavljeniKorisnik.getUloga().equals(Uloga.PRODAVAC)) {

            throw new NoSellerException("Možete prijaviti samo prodavca od kog ste uzeli proizvod.");
        }

        PrijavaProfila prijavaProfila = new PrijavaProfila();
        prijavaProfila.setRazlogPrijave(prijavaRequestDTO.getRazlogPrijave());
        prijavaProfila.setDatumPodnosenjaPrijave(new Date());
        prijavaProfila.setStatusPrijave(Status.PODNETA); // ili neki drugi podrazumevani status
        prijavaProfila.setPodnosiocPrijave(podnosiocPrijave.get());
        prijavaProfila.setPrijavljeniKorisnik(prijavljeniKorisnik);


        prijavaProfila = prijavaProfilaRepository.save(prijavaProfila);
        PrijavaKorisnikDTO prijavljenKorisnikDTO=new PrijavaKorisnikDTO();
        PrijavaKorisnikDTO podnosiocPrijaveKorisnikDTO=new PrijavaKorisnikDTO();
        prijavljenKorisnikDTO.setIme(prijavljeniKorisnik.getIme());
        prijavljenKorisnikDTO.setPrezime(prijavljeniKorisnik.getPrezime());
        prijavljenKorisnikDTO.setMejl(prijavljeniKorisnik.getMejl());
        prijavljenKorisnikDTO.setKorisnickoIme(prijavljeniKorisnik.getKorisnickoIme());

        podnosiocPrijaveKorisnikDTO.setKorisnickoIme(podnosiocPrijave.get().getKorisnickoIme());
        podnosiocPrijaveKorisnikDTO.setIme(podnosiocPrijave.get().getIme());
        podnosiocPrijaveKorisnikDTO.setPrezime(podnosiocPrijave.get().getPrezime());
        podnosiocPrijaveKorisnikDTO.setMejl(podnosiocPrijave.get().getMejl());


        PrijavaProfilaDTO prijavaProfilaDTO=new PrijavaProfilaDTO();
        prijavaProfilaDTO.setDatumPodnosenjaPrijave(prijavaProfila.getDatumPodnosenjaPrijave());
        prijavaProfilaDTO.setPrijavljeniKorisnik(prijavljenKorisnikDTO);
        prijavaProfilaDTO.setPodnosiocPrijave(podnosiocPrijaveKorisnikDTO);
        prijavaProfilaDTO.setStatusPrijave(prijavaProfila.getStatusPrijave());
        prijavaProfilaDTO.setRazlogPrijave(prijavaProfila.getRazlogPrijave());

        return prijavaProfilaDTO;
    }
    private void sendReportRejected(Korisnik korisnik, String razlogOdbijanja) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Prijava odbijena.";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani " + korisnik.getIme() + "," +
                " Vaša prijava je odbijena. Razlog je sledeći:  "+ razlogOdbijanja +
                 ". Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    private void sendBlockEmail(Korisnik korisnik) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Blokirani ste na sajtu WebShop.";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Uprkos podnetim zahtevima za prijavu vašeg profila, vaš profil je sada blokiran.  "+
                "Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
    private void sendReportAccept(Korisnik korisnik, Korisnik prijavljeniKorisnik) throws IOException {

        Email from = new Email("webshopjm.in@gmail.com");
        String subject = "Vaša prijava je prihvaćena.";
        Email to = new Email(korisnik.getMejl());
        Content content = new Content("text/plain", "Poštovani/na " + korisnik.getIme() + "," +
                " Vaša prijava korisnika "+ prijavljeniKorisnik.getIme() +" "+ " je prihvaćena"+
                ". Srdačno,\n"
                + " Vaš Webshop.");
        Mail mail = new Mail(from, subject, to, content);
        String kljuc = System.getenv("SENDGRID_API_KEY");

        SendGrid sg = new SendGrid(kljuc);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void odbijPrijavu(Long prijavaId, String razlogOdbijanja) throws IOException {

        Optional<PrijavaProfila> prijava = prijavaProfilaRepository.findById(prijavaId);
        if(prijava.isEmpty()){

            throw new NoReportException("Tražena prijava ne postoji");
        }
      //  PrijavaProfila izmenjenaPrijava= new PrijavaProfila();
        prijava.get().setStatusPrijave(Status.ODBIJENA);
       prijava.get().setRazlogOdbijanja(razlogOdbijanja);
        prijavaProfilaRepository.save(prijava.get());
        Korisnik korisnik=prijava.get().getPodnosiocPrijave();
        sendReportRejected(korisnik, razlogOdbijanja);

    }

    public void prihvatiPrijavu(Long prijavaId, String razlogPrihvatanja) throws IOException {

        Optional<PrijavaProfila> prijava = prijavaProfilaRepository.findById(prijavaId);
        if(prijava.isEmpty()){

            throw new NoReportException("Tražena prijava ne postoji.");
        }

        prijava.get().setStatusPrijave(Status.PRIHVACENA);
        prijava.get().setRazlogOdbijanja(razlogPrihvatanja);
        prijavaProfilaRepository.save(prijava.get());
        Korisnik korisnik=prijava.get().getPodnosiocPrijave();
        Korisnik prijavljeniKorisnik=prijava.get().getPrijavljeniKorisnik();
        prijavljeniKorisnik.setBlokiran(true);

        sendBlockEmail(prijavljeniKorisnik);
        sendReportAccept(korisnik, prijavljeniKorisnik);
    }

    public List<PrijavaProfilaDTO> vratiPrijaveAdministrator(Long administratorId){
        Korisnik admin = korisnikRepository.findById(administratorId).get();
        List<PrijavaProfila> svePrijave = prijavaProfilaRepository.findAll();

        List<PrijavaProfilaDTO> prijave = new ArrayList<>();
        for(PrijavaProfila prijava : svePrijave){
            PrijavaProfilaDTO dto = new PrijavaProfilaDTO();

            dto.setRazlogPrijave(prijava.getRazlogPrijave());
            dto.setStatusPrijave(prijava.getStatusPrijave());
            dto.setDatumPodnosenjaPrijave(prijava.getDatumPodnosenjaPrijave());
            dto.setId(prijava.getId());

            Korisnik podnosilac = prijava.getPodnosiocPrijave();
            Korisnik prijavljen = prijava.getPrijavljeniKorisnik();

            PrijavaKorisnikDTO podnosilacDto = new PrijavaKorisnikDTO();
            podnosilacDto.setIme(podnosilac.getIme());
            podnosilacDto.setPrezime(podnosilac.getPrezime());
            podnosilacDto.setMejl(podnosilac.getMejl());
            podnosilacDto.setKorisnickoIme(podnosilac.getKorisnickoIme());

            PrijavaKorisnikDTO prijavljenDto = new PrijavaKorisnikDTO();
            prijavljenDto.setIme(prijavljen.getIme());
            prijavljenDto.setPrezime(prijavljen.getPrezime());
            prijavljenDto.setMejl(prijavljen.getMejl());
            prijavljenDto.setKorisnickoIme(prijavljen.getKorisnickoIme());

            dto.setPodnosiocPrijave(podnosilacDto);
            dto.setPrijavljeniKorisnik(prijavljenDto);

            prijave.add(dto);
        }
        return prijave;
    }
}
