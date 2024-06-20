<template>
  <div>
    <div class="user-profile">
      <h2>Profil korisnika</h2>
      <div v-if="loading">Učitavanje...</div>
      <div v-else>
        <p>Ime: {{ currentUser.ime }}</p>
        <p>Prezime: {{ currentUser.prezime }}</p>
        <p>Email: {{ currentUser.email }}</p>
        <!-- Dodajte ostale informacije koje želite prikazati -->
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
      axios.get('http://localhost:8080/api/currentUser', {withCredentials: true})
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