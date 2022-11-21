<template>
  <div class="container-header-button">
        <button class="button-login">
          <router-link to="/login">로그인</router-link>
        </button>
        <button class="button-signup">
          <router-link to="/signup">회원가입</router-link>
        </button>
      </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.container-header-button {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container-header-button button {
  margin-right: 10px;
}

.container-header-button button:last-child {
  margin-right: 0px;
}

.button-signup {
  background-color: #87A2FB;
  border-color: #87A2FB;
}

.button-signup a{
  color: white;
}


.button-login a:visited,
.button-login a:active,
.button-login a:link{
  color: grey;
  text-decoration: none;
}

button{
    border: 1px solid rgb(205, 205, 205);
}
</style>