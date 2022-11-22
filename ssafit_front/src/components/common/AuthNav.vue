<template>
  <div class="container-auth">
    <nav class="container-auth-nav">
        <router-link to="/">회원</router-link>
        <!-- <router-link to="/login/coach">코치</router-link> -->
        <button ><a @click="modalStatusChange">코치</a></button>
    </nav>

    <div @click="modalStatusChange" class="modal-coach-login" :class="{modalVisib:isModal}">
      <div class="form-coach-login">
        <h1>코치 로그인</h1>
       
        <input class="input-id" type="text" placeholder="아이디 입력"/> <br>
        <input class="input-password" type="password" placeholder="비밀번호 입력" />
        <button class="button-modal">로그인</button>

        </div>
    </div> 
  

  </div>
</template>

<script>
// import CoachLogin from "../coach/CoachLogin.vue"

export default {
  name:"AuthNav",
  components: {
    // CoachLogin
  },
  data(){
    return{
      isModal: true,
    }
  },
  methods: {
    modalStatusChange(){
      this.isModal = !this.isModal;
    }
  }
}
</script>

<style scoped>
.container-auth{
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: rgba(147, 148, 148, 0.2) 0px 1px 1px;
  padding: 0px 20px;
  height: 40px;
}

.container-auth-nav{
  padding: 0px;
  display:flex;
  align-items: center;
}

.container-auth-nav a{
  font-size:0.9rem;
  color: #909090;
  padding-right: 10px;
  border-right:  2px solid #EEEEEE;
}

a{
  cursor: pointer;
}

.container-auth-nav a:last-child{
  border-right:  2px solid #eeeeee00;
}

.container-auth-nav button{
  background-color: white;
  
}

.modal-coach-login{
  position: absolute;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  top: -30%;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.483);
}

.modalVisib{
  display: none;
}

.form-coach-login{
  height: calc(100vh/2);
  width: calc(100vh/2.2);
  background-color: white;
  border-radius: 20px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-coach-login h1{
  margin-bottom: 20px;
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

.form-user-login input{
  display: flex;
  justify-content: flex-end;
}

.button-modal{
  margin-top: 20px;
  width: 250px;
  background-color: #ff8b95;
  color: white;
  font-weight: 500;
}

</style>