<template>
  <div class="container-auth">
    <nav class="container-auth-nav">
        <button class="button-redir" @click="redirectHome"><a>회원</a></button>
        <button ><a @click="modalStatusChange">코치</a></button>
    </nav>
    <div class="modal-coach-login" :class="{modalVisib:isModal}">
      <div class="form-coach-login">
        <h1>코치 로그인</h1>
        <input class="input-id" v-model="coach.coachId" type="text" placeholder="아이디 입력"/> <br>
        <input class="input-password" v-model="coach.coachPassword" type="password" placeholder="비밀번호 입력" />
        <button class="button-modal" @click="coachLogin">로그인</button>
        <button class="button-close" @click="modalStatusChange" >X</button>
        </div>
    </div> 
  </div>
</template>

<script>
export default {
  name:"AuthNav",
  data(){
    return{
      isModal: true,
      coach:{
        coachId:"",
        coachPassword:""
      }
    }
  },
  methods: {
    modalStatusChange(){
      this.isModal = !this.isModal;
    },
    coachLogin(){
      console.log(this.coach);

      if(sessionStorage.getItem("authority")=="u"){
        if(window.confirm("로그아웃 후 다시 진행하시겠습니까?")){
          this.$store.dispatch('logout');
          this.$store.dispatch('coachLogin', this.coach);
        } else{
          return;
        }
      }else if(sessionStorage.getItem("authority")=="c"){
        return;
      }
      
      this.$store.dispatch('coachLogin', this.coach);
    },
    redirectHome(){
      console.log(sessionStorage.getItem("authority"))
      if(sessionStorage.getItem("authority")=="c")
        this.$store.dispatch('logout');
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
}

a, button{
  cursor: pointer;
}

.container-auth-nav button:last-child{
  border-right:  2px solid #eeeeee00;
}

.button-redir a{
  border-right:  2px solid #EEEEEE;
  padding-right: 20px;
  
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

.button-close{
  margin-top: 20px;
  background-color: rgb(242, 242, 242);
  border: 2px solid rgb(219, 219, 219);
  border-radius:30px;
  width:37px;
}

</style>