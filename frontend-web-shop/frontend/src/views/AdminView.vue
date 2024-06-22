<template>

  <div>
    <div class="naslov"><h1>Pregled svih recenzija</h1></div>
    <div class="review-container">
      <div v-for="review in paginatedReviews()" :key="review.id" class="review-card">
        <p>Recenziju podneo: {{review.recenzijuPodneo.ime}} {{review.recenzijuPodneo.prezime}} "{{review.recenzijuPodneo.korisnickoIme}}"</p>
        <p>Recenziju primio: {{review.recenzijuPrimio.ime}} {{review.recenzijuPrimio.prezime}} "{{review.recenzijuPrimio.korisnickoIme}}"</p>
        <p>Ocena: {{ review.ocena }}</p>
        <p>Komentar: {{ review.komentar }}</p>
        <p>Datum podnošenja recenzije: {{ formatDate(review.datumPodnosenjaRecenzije) }}</p>
        <div class="button-container">
          <button class="review-button update" @click="updateReview(review.id)">Ažuriraj</button>
          <button class="review-button delete" @click="deleteReview(review.id)">Obriši</button>
        </div>
      </div>
    </div>
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">Prethodna</button>
      <span>Stranica {{ currentPage }}</span>
      <button @click="nextPage" :disabled="currentPage * itemsPerPage >= reviews.length">Sledeća</button>
    </div>
  </div>

</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      reviews: [],
      currentPage: 1, // Trenutna stranica
      itemsPerPage: 4 // Broj kartica po stranici
    };
  },
  mounted() {
    this.fetchReviews();
  },
  methods: {
    fetchReviews() {
      axios
          .get('http://localhost:8080/api/user/reviews/admin', {withCredentials: true})
          .then(response => {
            this.reviews = response.data;
          })
          .catch(error => {
            console.error('Greška pri dobavljanju podataka o recenzijama:', error);
          });
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
    deleteReview(reviewId) {
      console.log('ID recenzije za brisanje:', reviewId);
      axios
          .delete(`http://localhost:8080/api/user/deleteReview/${reviewId}`, { withCredentials: true })
          .then(response => {
            console.log('Recenzija obrisana:', response);
            // Uklonite obrisanu recenziju iz lokalne liste
            this.reviews = this.reviews.filter(review => review.id !== reviewId);
          })
          .catch(error => {
            console.error('Greška pri brisanju recenzije:', error.response.data);
            alert('Greška pri brisanju recenzije: ' + error.response.data.message);
          });
    },
    // Vraća recenzije za trenutnu stranicu
    paginatedReviews() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.reviews.slice(start, end);
    },
    // Prelazak na sledeću stranicu
    nextPage() {
      if (this.currentPage * this.itemsPerPage < this.reviews.length) {
        this.currentPage++;
      }
    },
    // Prelazak na prethodnu stranicu
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
}

</script>

<style>

.naslov {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px; /* Promenjeno na margin-bottom */
}

.review-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  padding: 30px;
}

.review-card {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

.button-container {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.review-button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.review-button.update {
  background-color: #4caf50; /* Zelena boja za Ažuriraj */
  color: white; /* Bela boja teksta */
}

.review-button.delete {
  background-color: #f44336; /* Crvena boja za Obriši */
  color: white; /* Bela boja teksta */
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.pagination button {
  padding: 10px 20px;
  margin: 0 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  background-color: #488871;
}

.pagination span {
  font-size: 18px;
}

</style>