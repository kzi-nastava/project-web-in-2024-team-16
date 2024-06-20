<template>
  <div class="home">
    <HelloWorld />
    <img id="headerimg" src="@/assets/glavna.png">
    <div id="headercont">
      <h1>Sve za Vas na jednom mestu!</h1>
      <button v-on:click="registration">Napravi nalog</button>
      <button v-on:click="login">Prijavite se</button>
    </div>
   <!-- <h1>Dobrodošli na Početnu Stranicu CAO CAO</h1>
    <p>Ovo je primer jednostavne početne stranice.</p>-->


    <div class="category-product-container">
    <!-- Kategorije -->
    <div class="categories">
      <h2>Kategorije</h2>
      <ul class="category-list">
        <li v-for="category in categories" :key="category.id">
          <a href="#" class="category-link">{{ category.nazivKategorije }}</a>
        </li>
      </ul>
    </div>

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
        products: [],
        categories: []
      };
    },
    methods: {
      login: function () {
        this.$router.push("/login");
      },
      registration: function (){
        this.$router.push("/registration");
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
      },
      fetchCategories() {
        axios.get("http://localhost:8080/api/category/categories")
            .then(response => {
              console.log(response.data);
              this.categories = response.data;
            })
            .catch(error => {
              console.error("There was an error fetching the categories:", error);
            });
      }
      },
      mounted() {
        this.fetchProducts();
        this.fetchCategories();

    },
  };
  </script>
  <style scoped>
  body, html {
    margin: 0;
    padding: 0;
    width: 100%;
  }
  .home {
    text-align: center;
    font-family: "Bodoni MT";
    width: 100%; /* Make sure the home div is 100% wide */
    margin: 0; /* Remove any default margin */
    padding: 0; /* Remove any default padding */
  }
  #headerimg{
    width: 100%;  /* Fills 100% of the container width */
    height: 60vh;
    margin: 0;
    padding: 0;

    /* z-index: 1;*/
  }
  #headercont {
    position: absolute;
    top: 40%;
    left: 65%;/*65*/
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
  .category-product-container {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
  }
  .categories {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    /*width: 20%;  Širina kategorija na levoj strani */
    margin-top: 20px;
    margin-left: 60px;
    padding: 40px; /* Dodajemo padding za uokvirivanje */
    /*border: 1px solid #2f8066;  Dodajemo ivicu
    border-radius: 8px;  Zaobljujemo ivicu */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Dodajemo senku */
    background-color: rgb(47, 128, 102); /* Boja pozadine */
    width: fit-content; /* Prilagođava širinu sadržaju unutar */
    height: fit-content;
    color: white;
  }

  .category-list {
    list-style-type: none;
    padding: 0;
  }

  .category-link {
    display: block;
    padding: 8px 16px; /* Prilagodite padding po potrebi */
    color: #fff; /* Promenjena boja teksta na belu */
    text-decoration: none;
  }

  .category-link:hover {
    background-color: rgb(72, 136, 113);
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