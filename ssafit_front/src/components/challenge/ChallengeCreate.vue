<template>
  <div class="container-challenge-create">
    <div class="div-challenge-make">
      <div class="div-challenge-make-title">
        <h2>나만의 챌린지</h2>
      </div>
      <div class="div-challenge-make-info">
        <div class="div-title"><div>제목 </div><input type="text" v-model="challenge.challengeTitle"/></div>
        <hr>
        <div class="div-content"><div>설명 </div>
          <textarea id="comment-content" class="textarea-comment" v-model="challenge.challengeDescription" placeholder="어떤 챌린지인가요?"></textarea>
        </div>
        <hr>
        <div class="div-duration"><div>기간(일) </div><input type="number" v-model="challenge.duration" max="30" min="7" value="7"/></div>
        <hr>
        <div class="div-isPublic">
          <div class="div-radio"><input type="radio" v-model="challenge.isPublic" value="true"/> 같이하기</div>
          <div class="div-radio"><input type="radio" v-model="challenge.isPublic" value="false"/> 혼자하기</div>
        </div>
      </div>
      <div class="div-challenge-video-list" v-for="(item, index) in challengeVideo" :key="index">
        <div class="div-challenge-video" @click="videoDelete(index)">
          <h4>{{item.videoTitle|titleLength}}</h4>
          <span style="display:none">{{index}}</span>
        </div>
      </div>

      <div class="div-challenge-make-button">
        <button class="button-challenge-make" @click="createChallenge(challenge)">만들기! 👉</button>
      </div>
    </div>
    <div class="div-wish-video-list">
      <!-- <h1>내가 찜한 영상</h1> -->
      <!-- <div class="div-decorate"></div> -->
      <!-- <video-list></video-list> -->
      <video-wish-list @videoSelect="videoSelect"></video-wish-list>
    </div>
  </div>
</template>

<script>
// import VideoList from "@/components/video/VideoList.vue"
import VideoWishList from "@/components/video/VideoWishList.vue";
import {mapState} from 'vuex';

export default {
  components:{
    VideoWishList
  },
  data(){
    return{
      challenge:{
        challengeTitle:"",
        challengeDescription:"",
        isPublic: true,
        duration: 7
      }
    }
  },
  methods:{
    videoSelect(payload){
      this.$store.commit("VIDEO_SELECT",payload);
    },
    videoDelete(payload){
      console.log(payload);
      this.$store.commit("VIDEO_DELETE",payload)
    },
    createChallenge(data){
      if(!window.confirm("챌린지를 생성하시겠습니까?")){
        return;
      }
      this.$store.dispatch("createChallenge",data);
      window.location.replace("/challenge");
    }
  },
  computed:{
    ...mapState(['challengeVideo'])
  },
  filters:{
    titleLength(value){
      if(value.length>12){
       return value.substring(0,12)+"...";
      } else{
        return value;
      }
    }
  }
}
</script>

<style scoped>
.container-challenge-create{
  display: flex;
}

.div-challenge-make-info{
  background-color: rgb(234, 234, 234);
  padding: 3px 20px;
}
.div-challenge-make{
  background-color: rgb(219, 219, 219);
  /* flex-shrink:; */
  min-width: 30%;
}


.div-duration,
.div-title,
.div-content{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.div-challenge-make-info div{
  margin: 10px 0px;
}

.div-isPublic{
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 15px;
}

.div-radio{
  display: flex;
  align-items: center;
  justify-content: center;
}

.div-radio input{
  margin-right: 10px;
}

[type="radio"] {
  vertical-align: middle;
  border: max(2px, 0.1em) solid rgb(108, 108, 108);
  border-radius: 50%;
  width: 1.25em;
  height: 1.25em;
  accent-color: rgb(173, 223, 152);
}

.div-duration input{
  width: 70%;
  margin-left: 10px;
  text-align: right;
}

.div-challenge-make h2{
  padding: 20px;
  
}

.div-challenge-video-list{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
}

.div-challenge-make-info input,textarea{
  width: 75%;
}

textarea{
  background-color: white;
  padding: 10px;
  height: 5em;
  border: none;
  resize: none;
}

.div-wish-video-list{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.div-wish-video-list h1{
  margin: 10px;
}

.div-challenge-video{
  background-color: white;
  border-radius: 10px;
  height: 40px;
  display: flex;
  justify-content:center;
  align-items:center;
  width: 80%;
  margin-top: 15px;
}

.button-challenge-make{
  background-color: rgb(255, 147, 147);
  width: 80%;
  height: 40px;
  border-radius:10px;
  margin-top: 15px;
  color: white;
  font-weight: 600;
}
</style>