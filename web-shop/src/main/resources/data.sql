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
    VALUES (1,'Aleksandar', 'Aleksić', 'aleksa92', 'aleksandar@gmail.com', '060123456', 'lozinka123', '1992-05-20 12:56:12', 'slika1.jpg', 'ljubazan', 'KUPAC', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (2,'Mila', 'Milic', 'mila12', 'mila@gmail.com', '0664444', 'mi', '2003-05-20 21:01:48', 'slika2.jpg', 'iskusan', 'PRODAVAC', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (3,'Petar', 'Markovic', 'peki001', 'petar@gmail.com', '062335514', 'password', '2000-08-15 10:17:29', 'slika3.jpg', 'neiskusan', 'PRODAVAC', true);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (4,'Stefan', 'Nikolic', 'Stefa3', 'stefan@gmail.com', '063588966', 'Lozinka1', '2003-11-29 19:39:56', 'slika4.jpg', 'profesionalan', 'ADMINISTRATOR', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (5,'Sinisa', 'Vucic', 'snisa', 'sinisa@gmail.com', '065822326', 'snile', '1990-05-17 16:23:47', 'slika5.jpg', 'pouzdan', 'KUPAC', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (6,'Darko', 'Lazic', 'dare99', 'darko@gmail.com', '066698235', 'bivsa121', '1991-10-19 08:59:00', 'slika6.jpg', 'iskusan', 'PRODAVAC', false);

INSERT INTO Korisnik (id,ime, prezime, korisnicko_ime, mejl, telefon, lozinka, datum_rodjenja, slika, opis_korisnika, uloga, blokiran)
    VALUES (7,'Sasa', 'Vuksanovic', 'cale', 'sasa@gmail.com', '062359487', 'cale123', '1986-03-28 02:21:22', 'slika7.jpg', 'proveren', 'KUPAC', false);

INSERT INTO Recenzija (ocena, komentar, datum_recenzije, korisnik_koji_je_dao_recenziju_id)
    VALUES (4, 'zadovoljan', '2021-02-10 19:54:03', 1);

INSERT INTO Recenzija (ocena, komentar, datum_recenzije, korisnik_koji_je_dao_recenziju_id)
    VALUES (5, 'sve preporuke', '2022-03-21 21:39:47', 5);

INSERT INTO Recenzija (ocena, komentar, datum_recenzije, korisnik_koji_je_dao_recenziju_id)
    VALUES (1, 'proizvod stigao u losem stanju', '2022-04-03 17:20:06', 1);

INSERT INTO Recenzija (ocena, komentar, datum_recenzije, korisnik_koji_je_dao_recenziju_id)
    VALUES (4, '10/10', '2022-03-22 13:35:24', 7);

INSERT INTO prijava_profila(razlog_prijave, datum_podnosenja_prijave, status_prijave, podnosioc_prijave_id, prijavljeni_korisnik_id)
    VALUES ('ne postuje dogovor', '2023-02-10 18:25:48', 'ODBIJENA', 1, 3);

INSERT INTO prijava_profila(razlog_prijave, datum_podnosenja_prijave, status_prijave, podnosioc_prijave_id, prijavljeni_korisnik_id)
    VALUES ('prodao mi je pokvaren proizvod', '2023-11-13 20:56:41', 'PODNETA', 5, 2);

INSERT INTO prijava_profila(razlog_prijave, datum_podnosenja_prijave, status_prijave, podnosioc_prijave_id, prijavljeni_korisnik_id)
    VALUES ('prevarant', '2023-08-14 10:56:31', 'PRIHVACENA', 1, 3);

INSERT INTO Kupac(id, prosecna_ocena) VALUES (1, 4.5);

INSERT INTO Kupac(id, prosecna_ocena) VALUES (5, 4.7);

INSERT INTO Kupac(id, prosecna_ocena) VALUES (7, 4.1);

INSERT INTO Prodavac(id, prosecna_ocena) VALUES(2, 3.2);

INSERT INTO Prodavac(id, prosecna_ocena) VALUES(3, 2.1);

INSERT INTO Prodavac(id, prosecna_ocena) VALUES(6, 4.4);

-- INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
--      prodat, kupac_id, prodavac_id)VALUES (1,'telefon', 'samsung', 'samsung.jpg', 'telefoni', 30000, 'FIKSNA', '2019-02-10',
--                                                             true, false, true, 1, 2);
--
-- INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
--     prodat, kupac_id, prodavac_id)VALUES (2,'laptop', 'lenovo', 'laptop.jpg', 'laptopovi', 45000, 'FIKSNA', '2020-03-11',
--                                                             false, true, false, 1, 2);
--
-- INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
--     prodat, kupac_id, prodavac_id)VALUES (3,'akva busilica', 'bosch', 'busilica.jpg', 'alat', 4000, 'FIKSNA', '2019-12-21',
--                                                             true, false, true, 5, 3);
--
-- INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
--     prodat, kupac_id, prodavac_id)VALUES (4,'automobil', 'ford', 'ford.jpg', 'automobili', 100000, 'AUKCIJA', '2021-08-19',
--                                                             false, true, false, 1, 3);
--
-- INSERT INTO Proizvod (id, naziv, opis, slika_proizvoda, kategorija, cena, tip, datum_objavljivanja, recenzija_kupac, recenzija_prodavac,
--     prodat, kupac_id, prodavac_id)VALUES (5,'tiganj', 'metalac', 'tiganj.jpg', 'kuhinja', 2200, 'FIKSNA', '2020-06-30',
--                                                             true, false, true, 5, 2);

INSERT INTO Proizvod (cena, prodat, recenzija_kupac, recenzija_prodavac, datum_objavljivanja, id, kupac_id, prodavac_id, naziv,
                      opis, slika_proizvoda, tip) VALUES (2200, true, true, false, '2020-06-12 22:03:48', 1, 5, 2, 'tiganj',
                                                          'metalac', 'metalac.jpg', 'FIKSNA');

INSERT INTO Proizvod (cena, prodat, recenzija_kupac, recenzija_prodavac, datum_objavljivanja, id, kupac_id, prodavac_id, naziv,
                      opis, slika_proizvoda, tip) VALUES (100000, false, false, true, '2019-08-21 19:20:36', 2, 1, 3, 'automobil',
                                                          'ford', 'ford.jpg', 'AUKCIJA');

INSERT INTO Proizvod (cena, prodat, recenzija_kupac, recenzija_prodavac, datum_objavljivanja, id, kupac_id, prodavac_id, naziv,
                      opis, slika_proizvoda, tip) VALUES (4000, false, false, true, '2020-11-28 09:56:24', 3, 5, 3, 'akva busilica',
                                                          'bosch', 'bosch.jpg', 'FIKSNA');

INSERT INTO Proizvod (cena, prodat, recenzija_kupac, recenzija_prodavac, datum_objavljivanja, id, kupac_id, prodavac_id, naziv,
                      opis, slika_proizvoda, tip) VALUES (40000, true, true, false, '2018-03-30 20:13:44', 4, 7, 2, 'laptop',
                                                          'lenovo', 'lenovo.jpg', 'FIKSNA');

INSERT INTO Proizvod (cena, prodat, recenzija_kupac, recenzija_prodavac, datum_objavljivanja, id, kupac_id, prodavac_id, naziv,
                      opis, slika_proizvoda, tip) VALUES (80000, false, false, true, '2019-01-14 16:11:06', 5, 1, 3, 'telefon',
                                                          'samsung', 'samsung.jpg', 'FIKSNA');

INSERT INTO Ponuda (cena, id, kupac_koji_daje_ponudu_id)VALUES (8000, 1, 1);

INSERT INTO Ponuda (cena, id, kupac_koji_daje_ponudu_id)VALUES (40000, 2, 7);

INSERT INTO Ponuda (cena, id, kupac_koji_daje_ponudu_id)VALUES (4000, 3, 5);

INSERT INTO Ponuda (cena, id, kupac_koji_daje_ponudu_id)VALUES (100000, 4, 1);

INSERT INTO Ponuda (cena, id, kupac_koji_daje_ponudu_id)VALUES (2200, 5, 5);

INSERT INTO Kategorija (id, naziv_kategorije) VALUES (1, 'telefoni');

INSERT INTO Kategorija (id, naziv_kategorije) VALUES (2, 'laptopovi');

INSERT INTO Kategorija (id, naziv_kategorije) VALUES (3, 'alat');

INSERT INTO Kategorija (id, naziv_kategorije) VALUES (4, 'automobili');

INSERT INTO Kategorija (id, naziv_kategorije) VALUES (5, 'kuhinja');

INSERT INTO Proizvod_kategorija (proizvod_id, kategorija_id) VALUES (5, 1);

INSERT INTO Proizvod_kategorija (proizvod_id, kategorija_id) VALUES (4, 2);

INSERT INTO Proizvod_kategorija (proizvod_id, kategorija_id) VALUES (3, 3);

INSERT INTO Proizvod_kategorija (proizvod_id, kategorija_id) VALUES (2, 4);

INSERT INTO Proizvod_kategorija (proizvod_id, kategorija_id) VALUES (1, 5);
