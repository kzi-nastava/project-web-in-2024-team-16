<template>
  <div class="home">
    <HelloWorld />
    <img id="headerimg" src="@/assets/glavna.png">
    <div id="headercont">
      <h1>Sve za Vas na jednom mestu!</h1>
      <button>Napravite nalog.</button>
      <button v-on:click="login">Prijavite se.</button>
    </div>
   <!-- <h1>Dobrodošli na Početnu Stranicu CAO CAO</h1>
    <p>Ovo je primer jednostavne početne stranice.</p>-->
  </div>
</template>

<script>
// Uvoz HelloWorld komponente
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "HomeView",
  // Registrovanje komponente
  components: {
    HelloWorld
  },
  methods: {
    login: function () {
      this.$router.push("/login");
    }
  }

};
</script>
<style scoped>
.home {
  text-align: center;
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

</style>