<template>

  <div>
    <div v-for="review in reviews" :key="review.id" class="reviews">
      <p>Recenziju podneo: {{review.recenzijuPodneo.ime}} {{review.recenzijuPodneo.prezime}} "{{review.recenzijuPodneo.korisnickoIme}}"</p>
      <p>Recenziju primio: {{review.recenzijuPrimio.ime}} {{review.recenzijuPrimio.prezime}} "{{review.recenzijuPrimio.korisnickoIme}}"</p>
      <p>Ocena: {{ review.ocena }}</p>
      <p>Komentar: {{ review.komentar }}</p>
      <p>Datum podnošenja recenzije: {{ formatDate(review.datumPodnosenjaRecenzije) }}</p>
      <p>---------------------------------------------------------</p>
    </div>
  </div>

</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      reviews: []
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
  }
}

</script>

<style>

</style>