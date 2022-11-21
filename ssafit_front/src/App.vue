<template>
  <div id="app">
    <auth-nav></auth-nav>
    <header-nav></header-nav>
    <router-view class="main"/>
    <footer-nav></footer-nav>
  </div>
</template>

<script>
import FooterNav from './components/common/FooterNav.vue';
import HeaderNav from './components/common/HeaderNav.vue';
import AuthNav from './components/common/AuthNav.vue';

export default {
  name:"App",
  components:{
    AuthNav,
    HeaderNav,
    FooterNav,
  }
}
</script>

<style>
.main{
  min-height:calc(100vh - 410px);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
