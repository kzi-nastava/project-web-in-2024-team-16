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
            <label for="uloga">Uloga:</label>
            <select v-model="currentUser.uloga" id="uloga" name="uloge" required>
              <option value="PRODAVAC">Prodavac</option>
              <option value="KUPAC">Kupac</option>
            </select>
          </div>
          <div class="column password-role-container">
            <label for="staraLozinka">Stara lozinka:</label>
            <input v-model="currentUser.staraLozinka" type="password" id="staraLozinka" placeholder="Unesite staru lozinku" required /><br>
            <div class="novaLozinka">
              <label for="novaLozinka">Nova lozinka:</label>
              <input v-model="currentUser.novaLozinka" type="password" id="novaLozinka" placeholder="Unesite novu lozinku" /><br>
            </div>
            <div class="updateDugme">
            <button v-on:click="update">Update</button>
            </div>
          </div>
        </div>
        <button @click="recenzije">Recenzije</button>
        <div v-if="showReviews">
          <div v-for="review in reviews" :key="review.id" class="review">
            <h3>{{ currentUser.uloga === 'PRODAVAC' ? 'Kupac kojem sam dao recenziju:' : 'Prodavac kojem sam dao recenziju:' }}</h3>
            <p>Ime: {{ currentUser.uloga === 'PRODAVAC' ? review.kupacKojemSamDaoRecenziju.ime : review.prodavacKojemSamDaoRecenziju.ime }}</p>
            <p>Prezime: {{ currentUser.uloga === 'PRODAVAC' ? review.kupacKojemSamDaoRecenziju.prezime : review.prodavacKojemSamDaoRecenziju.prezime }}</p>
            <p>Korisničko ime: {{ currentUser.uloga === 'PRODAVAC' ? review.kupacKojemSamDaoRecenziju.korisnickoIme : review.prodavacKojemSamDaoRecenziju.korisnickoIme }}</p>
            <p>Datum podnošenja recenzije: {{ formatDate(review.datumPodnosenjaRecenzije) }}</p>
            <p>Ocena: {{ review.ocena }}</p>
            <p>Komentar: {{ review.komentar }}</p>
          </div>
        </div>
      </div>
    </div>
    <div v-if="this.currentUser.uloga === 'KUPAC'" class="product-section" @click="prikaziDetaljeProizvoda(proizvod.id)">
      <h2>Kupljeni proizvodi</h2>
      <ul>
        <li v-for="proizvod in kupljeniProizvodi" :key="proizvod.id">
          <div>
            <p>Naziv: {{ proizvod.naziv }}</p>
            <p>ID: {{ proizvod.id }}</p>
            <!-- Dodajte ostale informacije o proizvodu kako je potrebno -->
          </div>
        </li>
      </ul>
    </div>

    <div v-else-if="currentUser.uloga === 'PRODAVAC'" class="product-section">
      <h2>Proizvodi na prodaju</h2>
      <ul>
        <li v-for="proizvod in proizvodiNaProdaju" :key="proizvod.id" @click="prikaziDetaljeProizvoda(proizvod.id)">
          <div>
            <p>Naziv: {{ proizvod.naziv }}</p>
            <p>ID: {{ proizvod.id }}</p>
            <!-- Dodajte ostale informacije o proizvodu kako je potrebno -->
          </div>
        </li>
      </ul>
    </div>
    <!-- Dodajte ovo za prikaz detalja proizvoda -->
    <div v-if="selectedProduct">
      <h3>Detalji proizvoda:</h3>
      <p>Naziv: {{ selectedProduct.naziv }}</p>
      <p>Cena: {{ selectedProduct.cena }}</p>
      <p>Opis: {{ selectedProduct.opis }}</p>
      <!-- Dodajte ostale informacije koje želite prikazati -->
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
      reviews: [],
      proizvodiNaProdaju: [],
      kupljeniProizvodi:[],
      user: null,
      selectedProduct: null
    };
  },
  mounted() {
    // Pozivamo metodu za dobavljanje trenutnog korisnika kada se komponenta montira
    this.fetchCurrentUser();
    this.fetchProducts();
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
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    fetchReviews(){
      let endpoint;
      console.log(this.currentUser.uloga);
      if(this.currentUser.uloga === 'KUPAC'){
        endpoint = 'http://localhost:8080/api/user/reviewedSellers/received';
      } else if(this.currentUser.uloga === 'PRODAVAC'){
        endpoint = 'http://localhost:8080/api/user/reviewedBuyer/received';
      }else{
        console.error('Nepoznata uloga korisnika', this.currentUser);
        return;
      }

      axios
          .get(endpoint, {
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
    },
    fetchProducts() {
      /*const userId = localStorage.getItem("userId");
      const userRole = localStorage.getItem("userRole");
      console.log(userId);
      console.log("ULOGA",userRole);*/
      console.log("NJANJA");
      const user = JSON.parse(localStorage.getItem('user'));
      console.log(user);
      const userRole = localStorage.getItem("userRole");
      console.log("ULOGA",userRole);
      let endpoint;
      if (userRole === 'KUPAC') {
        endpoint = `http://localhost:8080/api/user/products/${user}`;
      } else if (userRole === 'PRODAVAC') {
        endpoint = `http://localhost:8080/api/user/products/${user}`;
      } else {
        console.error('Nepoznata uloga korisnika:',userRole   );
        return;
      }

      axios.get(endpoint, { withCredentials: true })
          .then(response => {
            console.log(response);
            if (userRole   === 'KUPAC') {
              this.kupljeniProizvodi = response.data;
            } else if (userRole === 'PRODAVAC') {
              this.proizvodiNaProdaju = response.data; // Update proizvodiNaProdaju here
            }
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka o proizvodima:', error);
          });
    },
    prikaziDetaljeProizvoda(id) {
      console.log("OVO JE PROIZVOD",id);
      axios.get(`http://localhost:8080/api/product/${id}`, { withCredentials: true })
          .then(response => {
            // Ovde možete upravljati prikazom detalja proizvoda, na primer, čuvanjem u data objektu ili prikazom u modalu
            console.log('Detalji proizvoda:', response.data);
            // Primer kako biste mogli da prikažete detalje u modalu ili nekom drugom delu komponente
            this.selectedProduct = response.data;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju detalja proizvoda:', error);
          });
    }
  }
};
</script>

<style scoped>

.user-profile {
  max-width: 1200px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: relative;
}

.user-profile h2 {
  text-align: center;
}

.slika {
  position: absolute;
  top: 20px;
  left: 20px;
  width: 150px;
  height: auto;
  border-radius: 50%;
}

.novaLozinka {
  margin-top: -18px;
}

.form-container {
  display: flex;
  justify-content: space-between;
  margin-left: 200px;
}

.column {
  flex: 1;
  margin: 0 10px;
}

.password-role-container {
  margin-top: 0px;
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
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
}

.user-profile button {
  width: calc(100% - 20px);
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

/* Dodati stilovi za sekciju recenzija */
.review {
  background-color: #ffffff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  margin-top: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.review h3 {
  margin-top: 0;
}

.review p {
  margin: 5px 0;
}

.updateDugme {
  margin-left: 20px;
}
.product-section {
  margin-top: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 2px 5px rgba(47, 128, 102, 0.76);
  color: rgba(47, 128, 102, 0.76);
}

.product-section h2 {
  text-align: center;
  font-size: 22px;
  margin-bottom: 10px;
}

.product-list {
  list-style-type: none;
  padding: 0;
}

.product-list li {

  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.product-list li:last-child {
  border-bottom: none;
}

.product-list li:hover {
  background-color: #f0f0f0;
  cursor: pointer;
}

</style>