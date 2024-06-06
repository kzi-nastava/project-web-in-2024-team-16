<template>
  <div class="home">
    <HelloWorld />
    <img id="headerimg" src="@/assets/glavna.png">
    <div id="headercont">
      <h1>Sve za Vas na jednom mestu!</h1>
      <button>Napravite nalog</button>
      <button v-on:click="login">Prijavite se</button>
    </div>
   <!-- <h1>Dobrodošli na Početnu Stranicu CAO CAO</h1>
    <p>Ovo je primer jednostavne početne stranice.</p>-->

    <!-- Prikaz proizvoda -->
    <div class="product-container">
      <div class="row">
        <div class="col-6" v-for="product in products" :key="product.id" :product="product">
          <div class="card">
            <img :src="product.slikaProizvoda" class="card-img-top" alt="Product Image">
            <div class="card-body">
              <h5 class="card-title">{{ product.naziv }}</h5>
              <!--<h5 class="card-title">ID: {{ product.id }}</h5>-->
              <!-- <p class="card-text">{{ product.opis }}</p>-->
               <p class="card-text">Cena: {{ product.cena }} RSD</p>
               <!--  <p class="card-text">Tip prodaje: {{ product.tipProdaje }}</p>-->
              <!--  <p class="card-text">Kategorije: {{ product.kategorije.join(', ') }}</p>-->
                <a href="#" class="btn btn-primary">Vidi više...</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

  <script>
  // Uvoz HelloWorld komponente
  import HelloWorld from "@/components/HelloWorld.vue";
  import axios from "axios";

  export default {
    name: "HomeView",
    // Registrovanje komponente
    components: {
      HelloWorld
    },
    data() {
      return {
        products: []
      };
    },
    methods: {
      login: function () {
        this.$router.push("/login");
      },
      fetchProducts() {
        axios.get("http://localhost:8080/api/product/pages")
            .then(response => {
              console.log(response.data)
              this.products = response.data;
            })
            .catch(error => {
              console.error("There was an error fetching the products:", error);
            });
      }
    },
      mounted() {
        this.fetchProducts();

    },

  };
  </script>
  <style scoped>
  .home {
    text-align: center;
    font-family: "Bodoni MT";
  }
  #headerimg{
    width: 60%;  /* Fills 100% of the container width */
    height: 60vh;
    /* z-index: 1;*/
  }
  #headercont {
    position: absolute;
    top: 40%;
    left: 65%;
    transform: translate(-50%, -50%);
    text-align: center;
    z-index: 1;
    color: #44449d;
  }
  #headercont button { /*podesavanje da dugad budu jedno ispod drugog*/
    display: flex; /* Uključite fleksbox za dugmeta */
    flex-direction: column; /* Postavite smer fleksboxa vertikalno */
    align-items: center; /* Centrirajte dugmeta horizontalno */
    margin: 10px 0; /* Dodajte razmak između dugmeta */
    width: 100%;
    border-radius: 30px;
  }
  button{
    color: white;
    background-color: rgba(68, 68, 157, 0.9);
    padding: 15px 25px;/*da bude oko teksta toliko praznog*/
    border-radius: 5px;/*zaobli ivice*/
    border: 1px solid gray;
    cursor: pointer;
  }
  button:hover {
    background-color: rgba(68, 68, 157, 0.76); /* menja boju pri stavljanju misa na prijavu */
  }
  .product-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin-top: 20px;
    width: 100%; /* Postavljanje širine na 100% */

  }
  .row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    width: 60%;/*udaljenost slika*/
  }
  .col-6 {
   /* flex: 0 0 48%;*/
   /*flex: 0 0 calc(50% - 20px);  Smanjivanje širine kartice i postavljanje lufta od 20 piksela između kartica
    margin: 3px;
    margin-bottom: 20px;*/
    flex: 0 0 calc(30% - 10px); /* Smanjenje širine kartice i postavljanje lufta od 10 piksela između kartica */
    /*margin: 10px;  Smanjenje margine na 5 piksela */
    margin: 0 5px;
    margin-bottom: 10px;



  }
  .card {
   /* width: 100%;*/
    width: 200px; /* Primer: postavljanje širine na 200px */
    height: 300px; /* Primer: postavljanje visine na 300px */
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    background-color: rgba(246, 246, 246, 0.84);
    padding: 10px;/*da ne bi odma pocela slika kad i kartica*/

  }
  .card-img-top {
    width: 80%;
   /* height: 200px;*/
    height: 70%; /* Promenjeno na auto */
    object-fit: cover;


  }
  .card-body {
    padding: 10px;
    font-size: 12px; /* Primer: postavljanje veličine fonta na 14 piksela */
    overflow-wrap: break-word; /* Tekst će se prelomiti ako prelazi dimenzije kartice */
  }
  .card-body a.btn-primary {
    color: #44449d; /* Promenite boju teksta na belu */
    text-decoration: none;/*da ne bude podvuceno*/
  }

  .card-body a.btn-primary:hover {
    background-color: lavender; /* Promenite boju pozadine na svetliju ljubičastu kad je link u hover stanju */
  }
  </style>