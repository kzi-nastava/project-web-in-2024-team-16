<template>
  <nav class="navbar">
    <div class="search-container">
      <input type="text" placeholder="Pretraga...">
      <button type="submit" class="search-button">
        <img src="@/assets/pretraga.webp" alt="Pretraga">
      </button>
    </div>
    <div class="brand">
      <span class="letter">J</span><span class="letter">O</span><span class="letter">M</span><span class="letter">I</span>
    </div>
  </nav>
</template>
<script>
export default {
  name: 'HelloWorld',

}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 50px; /*udaljenost searcha i jomija u odnosu na velicinu navigacije*/
  background-color: white;
  color: black;
  width: 70%; /* da ne ide preko celog ekrana*/
  margin: 0 auto;
}
input[type="text"] {
  padding: 6px;
  border-radius: 30px; /*uokviruje pretragu*/
  border: none; /* Uklanja okvir */
  width: 500px; /* Postavlja širinu input polja */
  background-color: #f0f0f0; /* Svetlo siva pozadina */
}
button {
  padding: 6px 12px;
  border-radius: 200px;
  background-color: #f0f0f0;
  color: white;
  border: none;
  cursor: pointer;
}
button img {
  width: 15px; /* Postavite željenu širinu slike */
  height: 14px; /* Postavite željenu visinu slike */
}
.brand {
  font-size: 50px; /* Postavite veličinu fonta */
  display: flex; /* Postavite fleksibilni prikaz */
  align-items: center; /* Centriraj elemente */
  font-family: "Arial Narrow";
}

.letter:nth-child(1) {
  color: #773577 /* Boja slova J */
}
.letter:nth-child(2) {
  color: #4141da; /* Boja slova O */
}
.letter:nth-child(3) {
  color: #ffb4f4; /* Boja slova M */
}
.letter:nth-child(4) {
  color: #488871; /* Boja slova I */
}
</style>
