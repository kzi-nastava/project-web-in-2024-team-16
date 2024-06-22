<template>
  <div>
    <div class="user-profile">
      <h2>Vaši podaci</h2>
      <div v-if="loading">Učitavanje...</div>
      <div v-else>
        <img :src="currentUser.slika" alt="Slika korisnika" class="slika"/><br>
        <div class="form-container">
          <div class="column">
            <label for="ime">Ime:</label>
            <input v-model="currentUser.ime" type="text" id="ime" placeholder="Unesite novo ime" /><br>
            <label for="prezime">Prezime:</label>
            <input v-model="currentUser.prezime" type="text" id="prezime" placeholder="Unesite novo prezime" /><br>
            <label for="korisnickoIme">Korisničko ime:</label>
            <input v-model="currentUser.korisnickoIme" type="text" id="korisnickoIme" placeholder="Unesite novo korisničko ime" /><br>
          </div>
          <div class="column">
            <label for="mejl">Mejl:</label>
            <input v-model="currentUser.mejl" type="text" id="mejl" placeholder="Unesite novi mejl" /><br>
            <label for="telefon">Telefon:</label>
            <input v-model="currentUser.telefon" type="text" id="telefon" placeholder="Unesite novi telefon" /><br>
            <label for="datumRodjenja">Datum rođenja:</label>
            <input v-model="currentUser.datumRodjenja" type="date" id="datumRodjenja" placeholder="Unesite novi datum rođenja" /><br>
          </div>
          <div class="column">
            <label for="opisKorisnika">Opis:</label>
            <input v-model="currentUser.opisKorisnika" type="text" id="opisKorisnika" placeholder="Unesite nov opis" /><br>
            <label for="slika">Slika:</label>
            <input v-model="currentUser.slika" alt="Slika korisnika" type="text" id="slika" placeholder="Unesite novu sliku" /><br>
          </div>
          <div class="column password-role-container">
            <label for="staraLozinka">Stara lozinka:</label>
            <input v-model="currentUser.staraLozinka" type="password" id="staraLozinka" placeholder="Unesite staru lozinku" required /><br>
            <div class="novaLozinka">
              <label for="novaLozinka">Nova lozinka:</label>
              <input v-model="currentUser.novaLozinka" type="password" id="novaLozinka" placeholder="Unesite novu lozinku" /><br>
            </div>
            <label for="uloga">Uloga:</label>
            <select v-model="currentUser.uloga" id="uloga" name="uloge" required>
              <option value="PRODAVAC">Prodavac</option>
              <option value="KUPAC">Kupac</option>
            </select>
          </div>
        </div>
        <button v-on:click="update">Update</button>
        <button @click="recenzije">Recenzije</button>
        <div v-if="showReviews">
          <div v-for="review in reviews" :key="review.id" class="review">
            <h3>Prodavac kojem sam dao recenziju:</h3>
            <p>Ime: {{ review.prodavacKojemSamDaoRecenziju.ime }}</p>
            <p>Prezime: {{ review.prodavacKojemSamDaoRecenziju.prezime }}</p>
            <p>Korisničko ime: {{ review.prodavacKojemSamDaoRecenziju.korisnickoIme }}</p>
            <p>Datum podnošenja recenzije: {{ formatDate(review.datumPodnosenjaRecenzije) }}</p>
            <p>Ocena: {{ review.ocena }}</p>
            <p>Komentar: {{ review.komentar }}</p>
          </div>
        </div>
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
      loading: true, // Prikazivanje loadera dok se podaci učitavaju
      showReviews: false,
      reviews: []
    };
  },
  mounted() {
    // Pozivamo metodu za dobavljanje trenutnog korisnika kada se komponenta montira
    this.fetchCurrentUser();
    //this.fetchReviews();
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
      if(this.currentUser.staraLozinka != this.currentUser.novaLozinka){
        alert('Lozinke se ne poklapaju');
      }
      if(this.currentUser.staraLozinka == null){
        alert('Morate uneti trenutnu lozinku');
      }
      if(this.currentUser.uloga == null){
        alert('Morate izabrati ulogu');
      }
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
    },
    // recenzije() {
    //   this.$router.push("http://localhost:8080/api/user/reviewedSellers/received");
    // },
    recenzije(){
      this.showReviews = !this.showReviews;
      console.log('Toggle Reviews:', this.showReviews); // Dodajte konzolni izlaz za praćenje stanja
      if (this.showReviews && this.reviews.length === 0) {
        this.fetchReviews();
      }
      // axios
      //     .get('http://localhost:8080/api/user/reviewedSellers/received',  {
      //       withCredentials: true,
      //       headers: {
      //         'Content-Type': 'application/json'
      //       }
      //     })
      //     .then(response => {
      //       this.currentUser = response.data;
      //       console.log(this.reviews);
      //     })
      //     .catch(error => {
      //       console.error('Greška pri dobavljanju podataka o recenzijama:', error);
      //     });
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    fetchReviews(){
      axios
          .get('http://localhost:8080/api/user/reviewedSellers/received', {
            withCredentials: true,
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then(response => {
            console.log('Fetched Reviews:', response.data);
            this.reviews = response.data;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka o recenzijama:', error);
          });
    }
  }
};
</script>

<style scoped>

.user-profile {
  max-width: 1200px; /* Povećana maksimalna širina da bi se smestile tri kolone */
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: relative; /* Potrebno za relativno pozicioniranje slike */
}

.user-profile h2 {
  text-align: center;
}

.slika {
  position: absolute; /* Apsolutno pozicioniranje slike */
  top: 20px; /* Prilagodite ovu vrednost za željeni vertikalni pomak */
  left: 20px; /* Prilagodite ovu vrednost za željeni horizontalni pomak */
  width: 150px; /* Prilagodite veličinu slike po želji */
  height: auto;
  border-radius: 50%;
  //margin-top: 70px;
}

.novaLozinka {
  margin-top: -18px;
}

.form-container {
  display: flex;
  justify-content: space-between;
  margin-left: 200px; /* Pomera formu udesno da ne prekriva sliku */
}

.column {
  flex: 1; /* Ravnomerno raspoređuje prostor između kolona */
  margin: 0 10px; /* Razmak između kolona */
}

.password-role-container {
  margin-top: 0px; /* Dodatni razmak između forme i polja stara lozinka, nova lozinka, uloga */
  display: flex;
  flex-direction: column;
}

.user-profile label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.user-profile input[type="text"],
.user-profile input[type="password"],
.user-profile input[type="date"],
.user-profile select {
  width: 100%; /* Popunjava celu širinu sa dodatnim prostorom */
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
}

.user-profile button {
  width: calc(100% - 20px); /* Popunjava celu širinu sa dodatnim prostorom */
  padding: 12px 20px;
  margin-top: 20px;
  background-color: rgba(47, 128, 102, 0.76);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.user-profile button:hover {
  background-color: rgb(72, 136, 113);
}

</style>