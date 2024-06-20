<template>
  <div>
    <HelloWorld />
    <div class="login-form">
      <input v-model="korisnik.korisnickoIme" type="text" placeholder="Korisničko ime" /><br />
      <input v-model="korisnik.lozinka" type="password" placeholder="Lozinka" /><br />
      <button v-on:click="login">Prijavi se</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import HelloWorld from "@/components/HelloWorld.vue";
export default {
  name: "LoginView",
  components: {HelloWorld},
  data: function () {
    return {
      korisnik: {},//ovo dobijam prilikom prijave, posto to posle prosledjujem pomocu url mora da bude isto kao polja u
      //login dto klasi
    };
  },
  methods: {
    login: function () {
      axios
          .post("http://localhost:8080/api/user/login", this.korisnik, {
            withCredentials: true,//zbog sesije
          })
          .then((res) => {
            console.log(res);//javlja da je dobro i stavlja me na home stranicu
            console.log("CAO CAO CAO CAO");
            console.log("Prijavljen korisnik:", this.korisnik.korisnickoIme);
            console.log("ID korisnika:", res.data.id);
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
 .login-form {
  display: flex;
  flex-direction: column;
  width: 300px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ffffff;
  border-radius: 5px;
  background-color: #ffffff; /* Light gray background */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
   align-items: center;
}

.login-form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.login-form input[type="text"],
.login-form input[type="password"] {
  width: 90%;
  padding: 10px;
  border: 1px solid #ffffff;
  border-radius: 3px;
  margin-bottom: 10px;
  background-color: #d8d8f6;
}

.login-form input[type="text"]:focus,
.login-form input[type="password"]:focus {
  outline: none;
  border: 1px solid #44449d; /* menja boju pri pritisku za unos */
}

.login-form button {
  background-color: #44449d; /* Green button color */
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 50px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-form button:hover {
  background-color: rgba(68, 68, 157, 0.76); /* menja boju pri stavljanju misa na prijavu */
}
</style>



