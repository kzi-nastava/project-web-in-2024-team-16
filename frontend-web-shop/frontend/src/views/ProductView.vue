<template>
  <div>
    <div class="brand">
      <span class="letter">J</span><span class="letter">O</span><span class="letter">M</span><span class="letter">I</span>
    </div>
  <button class="btn-close" v-on:click="closeProductDetails"></button>
  <div class="product-details">
    <div class="product-image">
    <img :src="product.slikaProizvoda" alt="Product Image">
    </div>
    <div class="product-info">
      <hr color="#44449d">
      <h1>{{ product.naziv }}</h1>
      <hr color="#44449d">
      <br>
      <br>
      <p>Opis proizvoda: {{ product.opis }}</p>
      <p>Tip prodaje: {{ product.tipProdaje}}</p>
     <!-- <p>Kategorije: {{ product.kategorije}}</p>-->
    <p>Cena: {{ product.cena }} RSD</p>
      <div class="buy-button-container">
        <button class="my-custom-button">Kupi proizvod</button>
      </div>
    <!-- Dodajte ostale detalje proizvoda koje želite prikazati -->
    </div>
  </div>
  </div>
</template>
<script>
import axios from 'axios';


export default {
  name: 'ProductDetails',
  data() {
    return {
      product: {} // Inicijalizacija praznog objekta za prikaz proizvoda
    };
  },
  mounted:function() {
    // Učitavanje detalja proizvoda na osnovu ID-a iz URL parametra
    const productId = this.$route.params.id;
    axios.get("http://localhost:8080/api/product/" + productId)
        .then(response => {
          this.product = response.data;
        })
        .catch(error => {
          console.error('Greška pri dobijanju detalja proizvoda:', error);
        });
  },
  methods: {
    closeProductDetails() {
      // Redirekcija korisnika na početnu stranicu
      this.$router.push('/');
    }
  }
};
</script>
<style scoped>
.btn-close {
  position: absolute;
  top: 10px;
  left: 10px;
  color: #44449d;
  cursor: pointer;
  background: transparent;
  border: none;
  font-size: 24px;
  font-weight: bold;
  outline: none;
  z-index: 1000;  /*Osigurava da je dugme iznad drugih elemenata */
}

.btn-close:hover {
  color: red; /* Boja dugmeta pri prelasku mišem */
}
.product-details {
  font-family: Arial;
  display: flex;
  margin-top: 50px; /* Razmak na vrhu */
  margin-right: 50px;
}

.product-image {
  margin-left: 50px;
  flex: 0 0 auto; /* Skroz levo */
  margin-right: 20px; /* Razmak između slike i informacija */
  max-width: 500px; /* Maksimalna širina slike */
}

.product-info {
  flex: 1; /* Ostatak prostora zauzima informacije */
  font-size: 30px; /* Primer uvećanja fonta na 18px */
}

.product-image img {
  max-width: 100%; /* Slika se skalira na maksimalnu širinu */
  height: auto; /* Visina se automatski prilagođava */
}

.product-info h1 {
  font-size: 24px;
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  color: #000000;

}

.product-info p {
  font-size: 18px;
  line-height: 1.6;
  margin-bottom: 10px;
  justify-content: center; /* Centriranje po vertikali */
}

.buy-button-container {
  display: flex;
  justify-content: flex-end; /* Poravnanje na kraj */
  align-items: flex-end; /* Poravnanje na dno */
  margin-top: 150px; /* Pomera kontejner na dno, ako je potrebno */
  margin-right: 300px; /* Margina za razmak od desnog kraja */
  margin-bottom: 400px; /* Margina za razmak od donjeg kraja */
}
.my-custom-button {

  width: 300px; /* Širina dugmeta */
  cursor: pointer;
}
.brand {
  font-family: Arial;
  margin-top: 20px;
  margin-left:1300px ;
}

</style>