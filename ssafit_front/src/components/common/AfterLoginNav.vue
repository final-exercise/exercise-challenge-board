<template>
  <div class="container-afterlogin-button">
    <div class="div-nickname">
      <h4>💖 {{userNickname}}님 환영합니다!</h4>
    </div>
    <button class="button-login">
      <router-link to="/mypage">마이페이지</router-link>
    </button>
    <button class="button-login">
      <router-link to="/login">나의 기록</router-link>
    </button>
    <button class="button-logout">
      <a>로그아웃</a>
    </button>
  </div>
</template>

<script>
export default {
  props:['userNickname']
}
</script>

<style scoped>

.container-afterlogin-button {
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-user-signup{
  height: 400px;
  width: 400px;
  background-color: white;
  border-radius: 20px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  top: 10%;
  position: absolute;
}

.div-nickname h4 {
  color: rgb(60, 60, 60);
}

.div-nickname{
  margin-right: 10px;
}
.button-logout {
  background-color: #87A2FB;
  border-color: #87A2FB;
}
.button-logout a{
  color: white;
}

.button-login{
  margin-right: 10px;
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

.modal-button {
  margin-top: 20px;
  width: 250px;
  color: white;
  font-weight: 500;
  border: 1px solid rgba(255, 255, 255, 0);
}

.button-kakao{
  background-color: #FEE500;
}

.button-naver{
  background-color: #1EC800;
}

.button-email{
  background-color:#ff8b95
}

.modalVisib{
  display: flex;
  justify-content: center;
  position: absolute;
}

.modalNotVisib{
  display:none;
}

.modal-user-signup{
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}




.input-id{
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
}

.input-password{
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
}


.button-modal{
  margin-top: 20px;
  width: 250px;
  background-color: #ff8b95;
  color: white;
  font-weight: 500;
}

a:active,
a:visited,
a:hover,
a:link{
  color: white;
}
</style>