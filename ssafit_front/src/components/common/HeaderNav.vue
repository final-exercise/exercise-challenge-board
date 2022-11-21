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
        <!--<div class="dropdown">
          <span class="dropbtn">운동 영상</span>
          <div class="dropdown-content">
            <a href="#">스트레칭</a>
            <a href="#">근력강화</a>
            <a href="#">체중감량</a>
            <a href="#">교정</a>
            <a href="#">스포츠</a>
          </div>
        </div>-->
        <div class="dropdown">
          <button class="dropbtn">
            <a class="a-menu dropbtn_content">운동 영상</a>
            <span class="dropbtn_click" @click="dropdown">&nbsp; ▼</span>
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
      <login-nav></login-nav>
    </header>
  </div>
</template>

<script>
// window.onload = () => {
//   document.querySelector('.dropbtn_click').onclick = () => {
//     dropdown();
//   }
//   document.getElementsByClassName('fastfood').onclick = () => {
//     showMenu(value);
//   };
//   dropdown = () => {
//     var v = document.querySelector('.dropdown-content');
//     var dropbtn = document.querySelector('.dropbtn')
//     v.classList.toggle('show');
//     dropbtn.style.borderColor = 'rgb(94, 94, 94)';
//   }

//   showMenu = (value) => {
//     var dropbtn_icon = document.querySelector('.dropbtn_icon');
//     var dropbtn_content = document.querySelector('.dropbtn_content');
//     var dropbtn_click = document.querySelector('.dropbtn_click');
//     var dropbtn = document.querySelector('.dropbtn');

//     dropbtn_icon.innerText = '';
//     dropbtn_content.innerText = value;
//     dropbtn_content.style.color = '#252525';
//     dropbtn.style.borderColor = '#3992a8';
//   }
// }
// window.onclick = (e) => {
//   if (!e.target.matches('.dropbtn_click')) {
//     var dropdowns = document.getElementsByClassName("dropdown-content");

//     var dropbtn_icon = document.querySelector('.dropbtn_icon');
//     var dropbtn_content = document.querySelector('.dropbtn_content');
//     var dropbtn_click = document.querySelector('.dropbtn_click');
//     var dropbtn = document.querySelector('.dropbtn');

//     var i;
//     for (i = 0; i < dropdowns.length; i++) {
//       var openDropdown = dropdowns[i];
//       if (openDropdown.classList.contains('show')) {
//         openDropdown.classList.remove('show');
//       }
//     }
//   }
// }
import LoginNav from "./LoginNav.vue"

export default {
  components:{
    LoginNav,
  },
  data(){
    return{
      super:"",
      sub:""
    }
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

/*
.dropdown-label {
  font-size: 12px;
  margin: 10px;
  font-weight: bold;
  color: rgb(50, 50, 50);
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropbtn_icon {
  font-family: 'Material Icons';
}
*/


.dropbtn {
  background-color: white;
  font-weight: 700;
  color: #393E46;
  /* display: block;
  border: 2px solid rgb(94, 94, 94);
  border-radius: 4px;
  background-color: #fcfcfc;
  font-weight: 400;
  color: rgb(124, 124, 124);
  padding: 12px;
  width: 240px;
  text-align: left;
  cursor: pointer;
  font-size: 12px;
  z-index: 1;
  position: relative; */

  
}

.dropbtn_click{
  color : #3b3b3bcf; 
  float:right;
  font-size: 0.8rem;
  cursor: pointer;
}

.dropdown-content {
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

/*
.dropdown-content::-webkit-scrollbar {
  width: 5px;
  height: 10px;
}

.dropdown-content::-webkit-scrollbar-thumb {
  border-radius: 2px;
  background-color: rgb(194, 194, 194)
}
*/
.dropdown-content .supertype,
.dropdown-content .subtype {
  display: block;
  text-decoration: none;
  color: rgb(37, 37, 37);
  font-size: 0.95rem;
  padding: 5px 20px;
}
/*
.dropdown-content div:hover {
  background-color: rgb(226, 226, 226);
}
*/
/* .dropdown-content { */
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