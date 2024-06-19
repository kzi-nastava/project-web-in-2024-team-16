<template>

  <div>
    <HelloWorld />
    <div class="registration-form">
      <input v-model="korisnik.ime" type="text" placeholder="Ime" /><br />
      <input v-model="korisnik.prezime" type="text" placeholder="Prezime" /><br />
      <input v-model="korisnik.korisnickoIme" type="text" placeholder="Korisnicko ime" /><br />
      <input v-model="korisnik.mejl" type="text" placeholder="Mejl" /><br />
      <input v-model="korisnik.telefon" type="text" placeholder="Telefon" /><br />
      <input v-model="korisnik.lozinka" type="password" placeholder="Lozinka" /><br />
      <input v-model="korisnik.ponovljenaLozinka" type="password" placeholder="Ponovljena lozinka" /><br />
      <input v-model="korisnik.datumRodjenja" type="date" placeholder="Datum rodjenja" /><br />
      <label for="uloga">Izaberite ulogu:</label><br />
      <select v-model="korisnik.uloga" id="uloga" name="uloge" required>
        <option value="PRODAVAC">Prodavac</option>
        <option value="KUPAC">Kupac</option>
      </select><br>
      <button v-on:click="registration">Registruj se</button>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import HelloWorld from "@/components/HelloWorld.vue";
export default {
  name: "RegistrationView",
  components: {HelloWorld},
  data: function () {
    return {
      korisnik: {},//ovo dobijam prilikom prijave, posto to posle prosledjujem pomocu url mora da bude isto kao polja u
      //login dto klasi
    };
  },
  methods: {
    registration: function () {
      axios
          .post("http://localhost:8080/api/user/registration", this.korisnik, {
            withCredentials: true,//zbog sesije
          })
          .then((res) => {
            console.log(res);//javlja da je dobro i stavlja me na home stranicu
            this.$router.push("/home");
          })
          .catch((err) => {
            console.log(err);
            alert("Došlo je do greške");
          });

    },
  },
};
</script>

<style>

.registration-form {
  display: flex;
  flex-direction: column;
  width: 300px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f0f0f0; /* Light gray background */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  align-items: center;
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
  width: 90%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  margin-bottom: 10px;
  background-color: #ffffff; /* White background for inputs */
  box-sizing: border-box; /* Ensure padding and border are included in width */
}

.registration-form input[type="text"]:focus,
.registration-form input[type="password"]:focus,
.registration-form input[type="date"]:focus,
.registration-form select:focus {
  outline: none;
  border-color: #44449d; /* Darker border color on focus */
}

.registration-form button {
  background-color: #44449d; /* Dark blue button color */
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.registration-form button:hover {
  background-color: rgba(68, 68, 157, 0.8); /* Darker blue on hover */
}

</style>