<template>
  <div>
    <div class="user-profile">
      <h2>Vasi podaci</h2>
      <div v-if="loading">Učitavanje...</div>
      <div v-else>
        <img :src="currentUser.slika" alt="Slika korisnika" class="slika"/><br>
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
        <input v-model="currentUser.staraLozinka" type="password" id="staraLozinka" placeholder="Unesite staru lozinku" /><br>
        <label for="novaLozinka">Nova lozinka:</label>
        <input v-model="currentUser.novaLozinka" type="password" id="novaLozinka" placeholder="Unesite novu lozinku" /><br>
        <label for="datumRodjenja">Datum rodjenja:</label>
        <input v-model="currentUser.datumRodjenja" type="date" id="datumRodjenja" placeholder="Unesite novi datum rodjenja" /><br>
        <label for="slika">Slika:</label>
        <input v-model="currentUser.slika" alt="Slika korisnika" type="text" id="slika" placeholder="Unesite novu sliku" /><br>
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

.user-profile {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.loading {
  text-align: center;
  font-size: 18px;
  color: #333;
}

.registration-form {
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  flex-wrap: wrap;
  width: 100%;
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.column {
  flex: 1;
  margin-right: 20px;
}

.column:last-child {
  margin-right: 0;
}

.registration-form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.registration-form input[type="text"],
.registration-form input[type="password"],
.registration-form input[type="date"],
.registration-form select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  background-color: #d8d8f6;
}

.registration-form button {
  width: 100%;
  padding: 12px 20px;
  margin-top: 20px;
  background-color: #44449d;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.registration-form button:hover {
  background-color: rgba(68, 68, 157, 0.8);
}
.slika{
  width: 150px;
  height: 150px;
  border: 1px solid #ccc;
  border-radius: 50%;
}

</style>