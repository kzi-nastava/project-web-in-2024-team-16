<template>

  <div class="sveRecenzije">
    <div v-for="review in allReviews" :review="review">
      <div class="recenzije">
        <p class="korisnik">Korinsik: {{review.ocena}}</p>
      </div>
    </div>
    <p>aa</p>
  </div>

</template>

<script>

import HelloWorld from "@/components/HelloWorld.vue";
import axios from "axios";

export default {
  name: 'RecenzijeView',
  components: {
    HelloWorld
  },
  data(){
    return {
      allReviews: []
    }
  },
  computed: {
    prikazRecenzija(){
      return this.allReviews;
    }
  },
  fetchReviews(){
    axios
        .get('http://localhost:8080/api/user/reviewedSellers/received', {
        })
        .then(response => {
          console.log(response.data);
          this.allReviews = response.data;
        })
        .catch(error => {
          console.error("There was an error fetching the products:", error);
        });
  }
}

</script>

<style scoped>

</style>