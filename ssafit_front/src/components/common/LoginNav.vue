<template>
  <div class="container-header-button">
    <button class="button-login">
      <router-link to="login">로그인</router-link>
    </button>
    <button class="button-signup">
      <a @click="modalStatusChange">회원가입</a>
    </button>

    <div @click="modalStatusChange" class="modal-user-signup" :class="{modalNotVisib:isModal, modalVisib:!isModal}">
      <div @click="modalStatusChange" class="form-user-signup">
        <h1>회원가입</h1>
        <!-- <button class="modal-button button-naver" @click="modalStatusChange">네이버로 회원가입</button>
        <button class="modal-button button-kakao" @click="modalStatusChange">카카오로 회원가입</button> -->
        <button class="modal-button button-email" @click="modalStatusChange"><router-link to="/signup/email" >이메일로 회원가입</router-link></button>

      </div>
    </div> 
  </div>

      
</template>

<script>
export default {
  data(){
    return{
      isModal: true
    }
  },
  methods:{
    modalStatusChange(){
      this.isModal = !this.isModal;
    },
  },
}
</script>

<style scoped>

.container-header-button {
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
.button-signup {
  background-color: #87A2FB;
  border-color: #87A2FB;
}

.button-signup a{
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