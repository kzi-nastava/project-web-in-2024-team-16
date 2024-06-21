<template>
  <div>
    <div class="user-profile">
      <h2>Profil korisnika</h2>
      <div v-if="loading">Učitavanje...</div>
      <div v-else>
        <p>Ime: {{ currentUser.ime }}</p>
        <p>Prezime: {{ currentUser.prezime }}</p>
        <p>Korisnicko ime: {{currentUser.korisnickoIme}}</p>
        <p>Lozinka: {{ currentUser.lozinka }}</p>
        <p>Telefon: {{currentUser.telefon}}</p>
        <p>Mejl: {{currentUser.mejl}}</p>
        <p>Datum rodjenja: {{currentUser.datumRodjenja}}</p>
        <p>Uloga: {{currentUser.uloga}}</p>
        <p>Nesto bzvz xx</p>
        <label for="ime">Ime:</label>
        <input v-model="currentUser.ime" type="text" id="ime" placeholder="Unesite novo ime" />
        <!-- Dodajte ostale informacije koje želite prikazati -->
        <button v-on:click="fetchCurrentUser">Registruj se</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      currentUser: {}, // Objekat za čuvanje podataka o korisniku
      loading: true // Prikazivanje loadera dok se podaci učitavaju
    };
  },
  mounted() {
    // Pozivamo metodu za dobavljanje trenutnog korisnika kada se komponenta montira
    this.fetchCurrentUser();
  },
  methods: {
    fetchCurrentUser() {
      axios.get('http://localhost:8080/api/user/currentUser', {withCredentials: true})
          .then(response => {
            this.currentUser = response.data;
            this.loading = false;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka korisnika:', error);
            this.loading = false;
          });
    },
    // update(){
    //   axios
    //       .put('http://localhost:8080/api/')
    // }
  }
};
</script>

<style scoped>

</style>