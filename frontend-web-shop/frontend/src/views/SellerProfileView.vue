<template>
  <div class="seller-profile">
    <div class="brand">
      <span class="letter">J</span><span class="letter">O</span><span class="letter">M</span><span class="letter">I</span>
    </div>
    <h1 style="text-align: center;">Profil prodavca</h1>

    <!-- Slika prodavca -->
    <div class="seller-image">
      <img v-if="prodavac.slika" :src="prodavac.slika" alt="Slika profila prodavca">
      <p v-else>Profilna slika nije dostupna.</p>
    </div>

    <!-- Osnovne informacije o prodavcu -->
    <div class="basic-info">
      <p class="username">{{ prodavac.korisnickoIme }}</p>
      <p><strong>Ime:</strong> {{ prodavac.ime }} {{ prodavac.prezime }}</p>
      <p><strong>Telefon:</strong> {{ prodavac.telefon }}</p>
      <p><strong>Opis:</strong> {{ prodavac.opisKorisnika }}</p>
      <!-- Dodajte ostale osnovne informacije o prodavcu koje želite prikazati -->
    </div>

    <!-- Proizvodi koje prodavac prodaje -->
    <div class="products">
      <h2>Proizvodi koje prodavac prodaje</h2>
      <ul>
        <li v-for="proizvod in prodavac.proizvodiNaProdaju" :key="proizvod.id">
          {{ proizvod.naziv }}
        </li>
      </ul>
    </div>

    <!-- Recenzije sa prosečnom ocenom -->
    <div class="reviews">
      <h2>Recenzije prodavca (prosečna ocena: {{ prodavac.prosecnaOcena.toFixed(1) }})</h2>
      <ul v-if="prodavac.dobijeneRecenzije.length > 0">
        <li v-for="recenzija in prodavac.dobijeneRecenzije" :key="recenzija.id">
          <p><strong>Ocena:</strong> {{ recenzija.ocena }}</p>
          <p><strong>Komentar:</strong> {{ recenzija.komentar }}</p>
          <!-- Dodajte ostale informacije iz recenzija koje želite prikazati -->
        </li>
      </ul>
      <p v-else>Nema recenzija za ovog prodavca.</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'SellerProfileView',
  data() {
    return {
      prodavac: {
        ime: '',
        prezime: '',
        korisnickoIme: '',
        telefon: '',
        opisKorisnika: '',
        slika: '', // Dodato polje za sliku
        proizvodiNaProdaju: [],
        dobijeneRecenzije: [],
        prosecnaOcena: 0
      }
    };
  },
  mounted() {
    const prodavacId = this.$route.params.id;
    axios.get(`http://localhost:8080/api/user/profileView/${prodavacId}`, { withCredentials: true })
        .then(response => {
          this.prodavac = response.data;
        })
        .catch(error => {
          console.error('Greška pri dobijanju profila prodavca:', error);
        });
  }
};
</script>

<style scoped>
.seller-profile {
  font-family: Arial, sans-serif;
  margin: 20px auto; /* Centralizacija i prilagođavanje margina */
  padding: 20px;
  max-width: 600px; /* Maksimalna širina diva */
  border: 2px solid #488871;
  border-radius: 5px;
  background-color: #ffffff; /* Default pozadina */
}

.brand {
  font-size: 2em;
  margin-bottom: 10px;
  text-align: center; /* Centralizacija branda */
}

.seller-image {
  text-align: center; /* Centralizacija slike */
}

.seller-image img {
  width: 150px; /* Prilagodite širinu slike po potrebi */
  height: 150px; /* Prilagodite visinu slike po potrebi */
  border-radius: 10px; /* Zaobljeni rubovi slike */
  object-fit: cover; /* Da bi se slika proporcionalno prikazala */
  margin-bottom: 10px;
}

.basic-info {
  margin-bottom: 20px;
}

.products {
  margin-bottom: 20px;
}

.reviews {
  margin-bottom: 20px;
  background-color: #ffffff; /* Pozadina recenzija */
}

.reviews li {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: rgba(187, 224, 208, 0.84);
}

.username {
  text-align: center; /* Centralizacija korisničkog imena */
  font-weight: bold;
  font-size: 1.2em;
}
</style>