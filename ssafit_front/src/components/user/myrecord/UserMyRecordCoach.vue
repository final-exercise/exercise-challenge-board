<template>
  <div>
    <div class="div-myrecord-coach-title">
      <h1>코치 정보 보기</h1>
      <div class="div-decorate" style="margin-left:0px; margin-bottom: 10px; width: 20%"></div>
    </div>
    
      <div class="div-mypage-info">
        <div class="div-img-crop">
          <div class="div div-img">
            <img class="img-profile" :src="require(`@/assets/profile/`+this.coach.coachProfileImgUrl+`.jpg`)"/>
          </div>
        </div>
      <div class="div div-id">
        <label>이름</label>
        <input class="input disabled" type="text" v-model="coach.coachName" disabled/>
      </div>
      <div class="div div-nickname">
        <label>닉네임</label>
        <input class="input disabled" v-model="coach.coachNickname" type="text" disabled />
      </div>
      <div class="div div-name">
        <label>연락처</label>
        <input class="input disabled" v-model="coach.coachEmail" type="text" disabled />
      </div>
      <button class="button-modify">채팅하기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  components:{

  },
  data(){
    return{
      coach:{}
    }
  },
  computed:{
    coachProfile(){
      return `@/assets/profile/`+this.coach.coachProfileImgUrl+`.jpg`;
    }
  }, 
  created(){
    const API_URL = `http://localhost:331/user/coach`
  
      axios ({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res)=>{
        this.coach=res.data.res;
      }).then((res)=>{
        this.$create;
      }).catch((err)=>{
        console.log(err);
      })
  }
}
</script>

<style scoped>
.div-myrecord-coach-title{
  min-height: 15%;
  margin-top: 20px;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
}

.div-myrecord-coach-info{
  width: 100%;
  background-color:rgba(187, 187, 187, 0.131);
  padding: 20px;
  border: 1px solid rgba(0, 0, 0, 0.214);
}

.container-mypage-info{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.div-img-crop {
  height: 300px;
  width: 250px;
  overflow: hidden;
  margin-bottom: 20px;
}

.video-cropped-img img {
  width: 80%;
  height:auto;
  max-width: initial;
  margin-top: -17px;
}

.container-mypage-info{
  margin: 20px 20px 40px 20px;
}

.input.disabled{
  background-color:rgba(231, 231, 231, 0.359);
}

.div-mypage-info{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
  background-color: rgba(224, 224, 224, 0.295);
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
}

.div{
  display: flex;
  align-items:center;
  justify-content: center;
  margin-bottom: 15px;
}

select{
  appearance:auto;
  background-color: #ffffff;
  height: 40px;
  width: 300px;
  display: flex;
  justify-content: flex-end;
  border: 2px solid rgba(200, 200, 200, 0.334);
  border-radius: 10px;
  padding-left: 8px;
  color: grey;
}

.div label{
  width: 80px;
  text-align: left;
  font-weight: 600;
  font-size: 1.1rem;
}

.input{
  background-color: #ffffff;
  width: 300px;
  /* box-shadow: rgba(0, 0, 0, 0.15) 1px 1px 2px; */
  border: 2px solid rgba(200, 200, 200, 0.334);
  border-radius:10px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
}

.button-modify{
  margin:10px 0px;
  width: 100%;
  border-radius: 8px;
  font-weight: 500;
  font-size: 1.1rem;
  background-color:rgb(255, 162, 162);
}

.button-delete{
  margin-bottom:10px;
  width: 100%;
  border-radius: 8px;
  font-weight: 500;
  font-size: 1.1rem;
  color: white;
  background-color:rgba(212, 212, 212, 0.915);
}

.img-profile{

}

</style>