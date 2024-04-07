--INSERT INTO COMPANY (name, address) VALUES ('FTN', 'Trg Dositeja Obradovića 6');

--INSERT INTO DEPARTMENT (name, company_id) VALUES ('Menadžment', 1);
--INSERT INTO DEPARTMENT (name, company_id) VALUES ('Računarski centar', 1);

--INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Aleksandar', 'Aleksić', 'radnik', 1);
--INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Milica', 'Milić', 'menadžer', 1);
--INSERT INTO EMPLOYEE (first_name, last_name, position, department_id) VALUES ('Slavica', 'Slavić', 'radnik', 2);

--INSERT INTO PROJECT (name, deadline) VALUES ('Biću student FTN', '2019-02-10');
--INSERT INTO PROJECT (name, deadline) VALUES ('Podizanje sistema', '2019-05-1');

--INSERT INTO WORKING (project_id, employee_id) VALUES (1, 1);
--INSERT INTO WORKING (project_id, employee_id) VALUES (1, 2);
--INSERT INTO WORKING (project_id, employee_id) VALUES (2, 3);*/


--INSERT INTO KORISNIK (blokiran, prosecna_ocena, uloga, datum_rodjenja, telefon, ime, korisnicko_ime, lozinka,
 --                     mejl, opis, prezime, slika) VALUES (false, 3.9, KUPAC,'2020-02-20', '060000000', 'Mara', 'Mara123',
 --                   'mara123', 'mara@gmail.com', 'dekorater', 'maric', 'slika');

--INSERT INTO KORISNIK(ime, prezime, korisnickoIme, mejl, telefon, lozinka, datumRodjenja, slika, opis, uloga, blokiran)
--VALUES ('Aleksandar', 'Aleksić', 'aleksa92', 'aleksandar@example.com', '060123456', 'lozinka123', '1992-05-20', 'slika1.jpg', 'Radnik', 'KUPAC', false);


--INSERT INTO RECENZIJE(ocena, komentar, datumRecenzije, korisnik_id) VALUES (4, 'zadovoljan', '2019-02-10', 1);

--INSERT INTO PRIJAVAPROFILA(razlogPrijave, datumPodnosenjaPrijave, statusPrijave, korisnik_id) VALUES ('lose', '2019-02-10', 'ODBIJEN', 1);



INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (1,'Aleksandar', 'Aleksić', 'aleksa92', 'aleksandar@gmail.com', '060123456', 'lozinka123', '1992-05-20', 'slika1.jpg', 'ljubazan', 'KUPAC', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (2,'Mila', 'Milic', 'mila12', 'mila@gmail.com', '0664444', 'mi', '2003-05-20', 'slika2.jpg', 'iskusan', 'PRODAVAC', false);

INSERT INTO Recenzija (ocena, komentar, datum_recenzije, korisnik_koji_je_dao_recenziju_id)
    VALUES (4, 'zadovoljan', '2019-02-10', 1);

INSERT INTO prijava_profila(razlog_prijave, datum_podnosenja_prijave, status_prijave, podnosioc_prijave_id, prijavljeni_korisnik_id)
    VALUES ('lose', '2019-02-10', 'ODBIJENA', 1, 1);

INSERT INTO Kupac(id, prosecna_ocena) VALUES (1, 5.5);

INSERT INTO Prodavac(id, prosecna_ocena) VALUES(2, 4);

INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
     prodat, kupac_id, prodavac_id)VALUES (1,'telefon', 'samsung', 'samsung.jpg', 'telefoni', 30000, 'FIKSNA', '2019-02-10',
     true, false, true, 1, 2);

INSERT INTO Ponuda (cena, kupac_koji_daje_ponudu_id, proizvod_id)VALUES (30000, 1,1);
