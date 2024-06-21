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
        <input v-model="currentUser.ime" type="text" id="ime" placeholder="Unesite novo ime" /><br>
        <label for="prezime">Prezime:</label>
        <input v-model="currentUser.prezime" type="text" id="prezime" placeholder="Unesite novo prezime" /><br>
        <label for="korisnickoIme">Korisnicko ime:</label>
        <input v-model="currentUser.korisnickoIme" type="text" id="korisnickoIme" placeholder="Unesite novo korisnicko ime" /><br>
        <label for="mejl">Mejl:</label>
        <input v-model="currentUser.mejl" type="text" id="mejl" placeholder="Unesite novi mejl" /><br>
        <label for="telefon">Telefon:</label>
        <input v-model="currentUser.telefon" type="text" id="telefon" placeholder="Unesite novi telefon" /><br>
        <label for="staraLozinka">Stara lozinka:</label>
        <input v-model="currentUser.staraLozinka" type="password" id="staraLozinka" placeholder="Unesite staru lozniku" /><br>
        <label for="novaLozinka">Nova lozinka:</label>
        <input v-model="currentUser.novaLozinka" type="password" id="novaLozinka" placeholder="Unesite novu lozinku" /><br>
        <label for="datumRodjenja">Datum rodjenja:</label>
        <input v-model="currentUser.datumRodjenja" type="date" id="datumRodjenja" placeholder="Unesite novi datum rodjenja" /><br>
        <label for="slika">Slika:</label>
        <input v-model="currentUser.slika" type="text" id="slika" placeholder="Unesite novu sliku" /><br>
        <label for="opisKorisnika">Opis:</label>
        <input v-model="currentUser.opisKorisnika" type="text" id="opisKorisnika" placeholder="Unesite nov opis" /><br>
        <label for="uloga">Uloga:</label>
        <select v-model="currentUser.uloga" id="uloga" name="uloge" required>
          <option value="PRODAVAC">Prodavac</option>
          <option value="KUPAC">Kupac</option>
        </select>
        <button v-on:click="update">Update</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      currentUser: {
        ime: '',
        prezime: '',
        korisnickoIme: '',
        mejl: '',
        telefon: '',
        staraLozinka: '',
        novaLozinka: '',
        datumRodjenja: '',
        uloga: '',
        slika: '',
        opisKorisnika: ''
      }, // Objekat za čuvanje podataka o korisniku
      loading: true // Prikazivanje loadera dok se podaci učitavaju
    };
  },
  mounted() {
    // Pozivamo metodu za dobavljanje trenutnog korisnika kada se komponenta montira
    this.fetchCurrentUser();
  },
  methods: {
    fetchCurrentUser() {
      axios
          .get('http://localhost:8080/api/user/currentUser', {withCredentials: true})
          .then(response => {
            this.currentUser = response.data;
            this.loading = false;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka korisnika:', error);
            this.loading = false;
          });
    },
    update(){
      console.log('Trenutni podaci o korisniku pre slanja:', this.currentUser);
      axios
          .put(`http://localhost:8080/api/user/updateSeller/${this.currentUser.id}`, this.currentUser, {
            withCredentials: true,
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then(response => {
            this.currentUser = response.data;
            this.loading = false;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka korisnika:', error);
            this.loading = false;
          });
    }
  }
};
</script>

<style scoped>

</style>