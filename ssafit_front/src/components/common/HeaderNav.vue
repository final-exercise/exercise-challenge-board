<template>
  <div>
    <header>
      <div class="tmp">
      <!-- 로고 -->
      <div class="container-header-logo">
        <router-link to="/"><img src="@/assets/logo.png" /></router-link>
        <router-link to="/">
          <h2>SSAFIT</h2>
        </router-link>
      </div>
      <!-- navigation -->
      <nav class="container-header-nav">
        <div class="dropdown">
          <button class="dropbtn" @click="dropdown">
            <a class="a-menu dropbtn_content">운동 영상</a>
            <span class="dropbtn_click" >&nbsp; ▼</span>
          </button>
          <div class="dropdown-content">
            <div class="supertype">
              <span @click="dropdownSubmenu">스트레칭</span>
              <div class="dropdown-sub-content">
                <div class="subtype" @click="getSortedVideo">전신</div>
                <div class="subtype" @click="getSortedVideo">상체</div>
                <div class="subtype" @click="getSortedVideo">하체</div>
              </div>
            </div>
            <div class="supertype">
              <span @click="dropdownSubmenu">근력강화</span>
            </div>
            <div class="supertype">
              <span @click="dropdownSubmenu">체중감량</span>
            </div>
            <div class="supertype">
              <span @click="dropdownSubmenu">교정</span>
            </div>
            <div class="supertype">
              <span @click="dropdownSubmenuSport">스포츠</span>
              <div class="dropdown-sub-content-sport">
                <div class="subtype" @click="getSortedVideo">필라테스</div>
                <div class="subtype" @click="getSortedVideo">골프</div>
              </div>
            </div>
          </div>
        </div>
        <router-link class="a-menu" to="/challenge">챌린지</router-link>
      </nav>
    </div>
      <!-- login/signup 버튼 -->
      <login-nav v-if="(auth!='c')&(auth!='u')"></login-nav>
      <after-login-nav v-if="auth=='u'" :userNickname="nickname"></after-login-nav>
      <after-coach-login-nav v-if="auth=='c'" :coachNickname="nickname"></after-coach-login-nav>
    </header>
  </div>
</template>

<script>
import AfterLoginNav from "./AfterLoginNav.vue"
import AfterCoachLoginNav from "./AfterCoachLoginNav.vue"
import LoginNav from "./LoginNav.vue"

export default {
  components:{
    LoginNav,
    AfterLoginNav,
    AfterCoachLoginNav
  },
  data(){
    return{
      super:"",
      sub:"",
      nickname:"",
      isLogin:"",
      auth:""
    }
  }, 
  computed: {
  },
  created(){
    this.auth = sessionStorage.getItem("authority");
    this.nickname = sessionStorage.getItem("nickname");
  },
  methods: {
    dropdown(){
      var v = document.querySelector('.dropdown-content');
      v.classList.toggle('show');
    },
    dropdownSubmenu(event){
      this.super = event.target.innerHTML;
      var v = document.querySelector('.dropdown-sub-content');
      var vAnother = document.querySelector('.dropdown-sub-content-sport');
      if(vAnother.classList.contains('show')){
        vAnother.classList.remove('show');
      }
      v.classList.toggle('show');
    },
    dropdownSubmenuSport(event){
      this.super = event.target.innerHTML;
      var v = document.querySelector('.dropdown-sub-content-sport');
      var vAnother = document.querySelector('.dropdown-sub-content');
      if(vAnother.classList.contains('show')){
        vAnother.classList.remove('show');
      }
      v.classList.toggle('show');
    },
    getSortedVideo(event){
      this.sub = event.target.innerHTML;
      location.href=`/video?super=${this.super}&sub=${this.sub}`;
    }
  }
}
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px;
  padding: 0px 20px;
  height: 70px;
}

.container-header-logo {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container-header-logo img {
  width: 50px;
}

.tmp{
  display: flex;
}

.container-header-logo h2 {
  color: #393E46;
  margin-left: 5px;
}

.container-header-nav {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container-header-nav .a-menu {
  border-left: 2px solid #EEEEEE;
  color: #393E46;
  padding-left: 10px;
  /* margin-right: 10px; */
}

.container-header-nav>.a-menu:last-child {
  padding-right: 10px;
  border-right: 2px solid #EEEEEE;
}


.dropbtn {
  background-color: white;
  font-weight: 700;
  color: #393E46;

  
}

.dropbtn_click{
  color : #3b3b3bcf; 
  float:right;
  font-size: 0.8rem;
  cursor: pointer;
}

.dropdown-content {
  padding: 0px 0px;
  cursor: pointer;
  display: none;
  position: absolute;
  z-index: 2;
  font-weight: 400;
  background-color: #fcfcfc;
  min-width: 130px;
  border-radius: 8px;
  height: 160px;
  /* overflow: scroll; */
  border: 1px solid rgba(0, 0, 0, 0.045);
  box-shadow: 2px 2px 7px -5px rgba(0,0,0,0.36);
}

.dropdown-content .supertype,
.dropdown-content .subtype {
  display: block;
  text-decoration: none;
  color: rgb(37, 37, 37);
  font-size: 0.95rem;
  padding: 5px 20px;
}

.dropdown-content.show {
  justify-content: center;
  align-items: flex-start;
  flex-direction: column;
  display: flex;
} 

.dropdown-sub-content{
  background-color: black;
  position: absolute;
  left: 100%;
  top: 0;
  z-index: 0;
  background-color: #fcfcfc;
  min-width: 100px;
  border-radius: 8px;
  min-height: 100px;
  border: 1px solid rgba(0, 0, 0, 0.045);
  box-shadow: 2px 2px 7px -5px rgba(0,0,0,0.36);
  display:none;
}

.dropdown-sub-content.show{
  justify-content: center;
  align-items: flex-start;
  flex-direction: column;
  display: flex;
}

.dropdown-sub-content-sport{
  background-color: black;
  position: absolute;
  left: 100%;
  top: 0;
  z-index: 0;
  background-color: #fcfcfc;
  min-width: 100px;
  border-radius: 8px;
  min-height: 100px;
  border: 1px solid rgba(0, 0, 0, 0.045);
  box-shadow: 2px 2px 7px -5px rgba(0,0,0,0.36);
  display:none;
}

.dropdown-sub-content-sport.show{
  padding-top: 10px;
  align-items: flex-start;
  flex-direction: column;
  display: flex;
}




</style>