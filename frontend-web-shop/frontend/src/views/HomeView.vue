<template>
  <div class="home">
    <HelloWorld @search="executeSearch" />
    <img id="headerimg" src="/kutija.JPG">
    <div id="headercont">
      <h1>Sve za Vas na jednom mestu!</h1>
     <!-- <button v-on:click="registration">Napravi nalog</button>
      <button v-on:click="login">Prijavite se</button>-->
      <button v-if="!isLoggedIn" @click="registration">Napravite nalog</button>
      <button v-if="!isLoggedIn" @click="login">Prijavite se</button>
      <button v-if="isLoggedIn" @click="goToProfile">Moj profil</button>
      <button v-if="isLoggedIn" @click="logout">Odjavite se</button>
    </div>
    <div id="post-new-product">
      <button v-if="isLoggedIn && userRole === 'PRODAVAC'" @click="postNewProduct">Postavite oglas</button>
    </div>

    <div class="filters-container">

    <div class="filter-type-container">
      <h2>Tip proizvoda</h2>
      <label>
        <input type="radio" name="productType" @click="fetchProducts"> Svi proizvodi
      </label>
      <br>
      <label>
        <input type="radio" name="productType" @click="filterByType('FIKSNA')"> Fiksna cena
      </label>
      <br>
      <label>
        <input type="radio" name="productType" @click="filterByType('AUKCIJA')"> Aukcija
      </label>
    </div>

      <div class="filter-price-container">
        <h2>Filter po ceni</h2>
        <label for="priceFrom">Od:</label>
        <input type="number" id="priceFrom" v-model="priceFrom" placeholder="Minimalna cena">
        <label for="priceTo">Do:</label>
        <input type="number" id="priceTo" v-model="priceTo" placeholder="Maksimalna cena">
        <button @click="filterByPrice">Filtriraj</button>
      </div>
    </div>

      <div class="category-product-container">
      <!-- Kategorije -->
    <div class="categories">
      <h2>Kategorije</h2>
      <br>
      <ul class="category-list">
        <li  class="category-link" @click="fetchProducts">Svi proizvodi</li>
        <br>
        <li v-for="category in categories" :key="category.id">
         <!-- <a href="#" class="category-link"  @click="filterByCategory(category.id)>{{ category.nazivKategorije }}</a>-->
          <span class="category-link" @click="filterByCategory(category.nazivKategorije)">{{ category.nazivKategorije }}</span>
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
              <!--  <a href="#" class="btn btn-primary">Vidi više...</a>-->
              <router-link :to="'/product/' + product.id" class="btn btn-primary">Vidi više...</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 0">Prethodna stranica</button>
      <button @click="nextPage">Sledeća stranica</button>
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
        allProducts: [],//novo, search
        products: [],//paginacija proizvodi
        categories: [],//sve kategorije
        currentPage: 0,
        pageSize: 6,
        isLoggedIn: false,
        priceTo: 0,
        priceFrom: 0,
        userRole: "",
      };
    },
    computed: {//ako nista nije napisano ispisi sve proizvode
      displayedProducts() {
        return this.products.length > 0 ? this.products : this.allProducts;
      }
    },
    methods: {
      login: function () {
        this.$router.push("/login");
      },
      logout() {
        // Logika za odjavu (brisanje localStorage i preusmeravanje)
        localStorage.removeItem('user');
        this.isLoggedIn = false;
      },
      goToProfile() {
        this.$router.push("/profile");
      },
      registration: function (){
        this.$router.push("/registration");
      },
      fetchProducts() {
        axios.get("http://localhost:8080/api/product/pages", {
          params: {
            page: this.currentPage,
            size: this.pageSize
          }
        })
            .then(response => {
              console.log(response.data);
              this.products = response.data;
            })
            .catch(error => {
              console.error("There was an error fetching the products:", error);
            });
      },
      previousPage() {
        if (this.currentPage > 0) {
          this.currentPage--;
          this.fetchProducts();
        }
      },
      nextPage() {
        this.currentPage++;
        this.fetchProducts();
      },
      fetchCategories() {
        axios.get("http://localhost:8080/api/category/categories")
            .then(response => {
              console.log(response.data);
              this.categories = response.data;
            })
            .catch(error => {
              console.error("Greska pri dobijanju svih kategorija", error);
            });
      },
      filterByPrice() {
        if (this.priceFrom !== null && this.priceTo !== null) {
          axios
              .get("http://localhost:8080/api/product/filterByPrice", {
                params: {
                  priceFrom: this.priceFrom,
                  priceTo: this.priceTo
                }
              })
              .then(response => {
                this.products = response.data;
              })
              .catch(error => {
                console.error("Greska pri filtriranju proizvoda po ceni:", error);
              });
        } else {
          this.fetchProducts(); // ili neki drugi fallback ako korisnik nije uneo oba polja
        }
      },
      filterByType(type) {
        axios.get("http://localhost:8080/api/product/filterByType", {
          params: {type: type}
        })
            .then(response => {
              this.products = response.data;
            })
            .catch(error => {
              console.error("Greška pri filtriranju proizvoda po tipu:", error);
            });
      },
      executeSearch(searchCriteria) {
        const { name, description } = searchCriteria;
        if(description==''){
          axios.get("http://localhost:8080/api/product/search?name=" + name)
              .then(response => {
                this.products = response.data;
              })
              .catch(error => {
                this.fetchProducts();
                console.error("Greska pri koriscenju search-a", error);
              });
        }
        else if(name==''){
          axios.get("http://localhost:8080/api/product/search?description=" + description)
              .then(response => {
                this.products = response.data;
              })
              .catch(error => {
                this.fetchProducts();
                console.error("Greska pri koriscenju search-a", error);
              });
        }else {
          const params = {
            name,
            description
          };

          axios.get("http://localhost:8080/api/product/search", {params})
              .then(response => {
                this.products = response.data;
              })
              .catch(error => {
                this.fetchProducts();
                console.error("Greska pri koriscenju search-a", error);
              });
        }
      },
      filterByCategory(categoryName) {
        axios.get("http://localhost:8080/api/product/filterByCategory", {
          params: {category: categoryName}
        })
            .then(response => {
              this.products = response.data;
            })
            .catch(error => {
              console.error("Greska pri filtriranju proizvoda po kategoriji:", error);
            });
      },
      postNewProduct() {
        // Method logic
      },
      },
      mounted() {
        this.fetchProducts();
        this.fetchCategories();
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
          this.isLoggedIn = true;
          const userRole = localStorage.getItem("userRole");
          if (userRole) {
            this.userRole = userRole;
          }
        }

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
    font-family: Arial;
    position: absolute;
    top: 40%;
    left: 30%;/*65*/
    transform: translate(-50%, -50%);
    text-align: center;
    z-index: 1;
    color: rgba(47, 128, 102, 0.76);
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
    background-color: rgba(47, 128, 102, 0.76);
    padding: 15px 25px;/*da bude oko teksta toliko praznog*/
    border-radius: 5px;/*zaobli ivice*/
    border: 1px solid gray;
    cursor: pointer;
  }
  button:hover {
    background-color: rgb(72, 136, 113); /* menja boju pri stavljanju misa na prijavu */
  }
  #post-new-product {
    display: flex; /* Koristi fleksibilni prikaz */
    justify-content: center; /* Centriraj sadržaj po horizontalnoj osi */
    align-items: center; /* Centriraj sadržaj po vertikalnoj osi */
  }
  #post-new-product button {
    display: block; /* Podešava dugme da bude blok element */
    width: 200px; /* Širina dugmeta */
    padding: 10px; /* Unutrašnji padding */
    border: none; /* Uklanja ivice */
    border-radius: 50px; /* Zaobljeni rubovi */
    background-color: rgba(47, 128, 102, 0.76); /* Boja pozadine */
    color: white; /* Boja teksta */
    font-size: 16px; /* Veličina fonta */
    cursor: pointer; /* Promena kursora na pokazivač */
    margin-top: 10px; /* Margin na vrhu */
  }
  #post-new-product button:hover {
    background-color: #488871; /* Promena boje pozadine prilikom prelaska mišem */
  }
  .filter-type-container {
    float: left; /* Postavlja kontejner na levu stranu */
   /* background-color: rgba(93, 187, 155, 0.88);   Zelena pozadina */
    padding: 10px;/*  Razmak unutar kontejnera */
    color: white; /* Boja teksta */
    font-family: Arial, sans-serif; /* Font */
    margin-left: 60px;
    width: 500px;
    height: 120px;
    color: #966396;
    text-align: center;
  }
  .filter-type-container h2 {
    margin-top: 0; /* tip proizvoda */
    font-size: 20px;
  }
  .filter-price-container {
    font-family: Arial, sans-serif; /* Font */
    color: #966396; /* Zelena boja teksta */
  }
  .filter-price-container input {
    margin-right: 10px; /* Dodavanje desne margine između input polja */
  }
  .filter-price-container button {
    width: 200px;
    border-radius: 30px;
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
    align-items: center;
    /*width: 20%;  Širina kategorija na levoj strani */
    margin-top: 20px;
    margin-left: 60px;
    padding: 40px; /* Dodajemo padding za uokvirivanje */
    /*border: 1px solid #2f8066;  Dodajemo ivicu
    border-radius: 8px;  Zaobljujemo ivicu */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Dodajemo senku */
    background-color: rgba(47, 128, 102, 0.76); /* Boja pozadine */
    /*width: fit-content; rilagođava širinu sadržaju unutar */
    width: 200px;
    height: fit-content;
    color: white;
    font-family: Arial;
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
    background-color: rgb(72, 162, 131);
    cursor: pointer;
  }
  .product-container {
    font-family: Arial;
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
  .pagination {
    margin-bottom: 20px; /* Prilagodite vrednost margine po želji */
  }
  .pagination button {
    margin-right: 5px; /* Prilagodite ovu vrednost prema potrebi */
    width: 200px;
    border-radius: 30px;
    height: 10px;
    font-size: 15px;
    text-align: center;

  }
  </style>