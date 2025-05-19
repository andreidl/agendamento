<template>
  <div id="app">
    <header>
      <h1>Sistema de Agendamento de Transferências</h1>
      <nav>
        <router-link to="/">Início</router-link> |
        <router-link to="/extrato">Extrato</router-link>
      </nav>
    </header>

    <main>
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

header {
  background-color: #3f51b5;
  color: white;
  padding: 15px;
  text-align: center;
  margin-bottom: 20px;
}

nav {
  margin-top: 10px;
}

nav a {
  color: white;
  text-decoration: none;
  margin: 0 10px;
}

nav a.router-link-exact-active {
  font-weight: bold;
  text-decoration: underline;
}

main {
  padding: 20px;
  background: #f4f4f4;
  border-radius: 8px;
}
</style>
