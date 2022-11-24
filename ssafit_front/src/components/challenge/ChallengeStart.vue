<template>
  <div class="container-challenge-start">
    <div class="div-challenge-start">
      <div class="div-challenge-start-title">
        <h2>{{curChallenge.challengeDto.challengeTitle}}</h2>
        <span>{{curChallenge.challengeDto.challengeDescription}}</span>
      </div>
      <div class="div-duration">
        <div class="div-duration-icons">
          <div class="div-left">
            {{rest}}일 남았어요!
          </div>
          <div class="div-right">
            <span style="margin-right:6px;">{{curChallenge.challengeDto.endDate}} 🏁</span>
          </div>
        </div>
        <v-progress-linear
          color="red darken-2"
          rounded
          :value=restP
        ></v-progress-linear>
      </div>
      <hr style="margin: 20px 20px 5px 20px; width:100%;">
      <div class="div-challenge-video-list" v-for="(video, index) in curChallenge.challengeVideos" :key=index @click="hideDefault">
          <div class="div-challenge-video">
            <router-link :to="{name:'challenge-item', params:{videoSeq:video.videoSeq}}">
            <h4>{{video.videoTitle|titleLength}}</h4></router-link>
            <span style="display:none">{{video.videoSeq}}</span>
        </div>
      </div>
      <hr style="margin: 20px; width:100%;">
      <div class="div-challenge-start-button">
        <button class="button-challenge-complete" @click="completeChallenge(challenge)">다했어요! 👏</button>
      </div>
    </div>
    <div class="div-video-detail-view" style="margin-bottom:20px;">
      <router-view/>
      <div class="div-default" v-if="isDefault">
          <img src="@/assets/logo.png" style="width:100px;"/>
      </div>
    </div>
  </div>
</template>

<script>
// import VideoList from "@/components/video/VideoList.vue"
import VideoWishList from "@/components/video/VideoWishList.vue";
import {mapState} from 'vuex';
import ChallengeStartItem from "@/components/challenge/ChallengeStartItem.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";


export default {
  components:{
    // VideoWishList,
    // VideoDetail,
    // ChallengeStartItem,
  },
  data(){
    return{
      // restP: this.rest/this.curChallenge.challengeDto.duration,
      challengeSeq:0,
      isDefault: true
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
    startVideo(payload){
      // this.$router.push({name:'video-detail',params:{}, props:true });
    },
    hideDefault(){
      this.isDefault = false;
    },
    completeChallenge(){
      window.alert("축하합니다! 200exp가 적립됩니다🎉");
      this.$store.dispatch("updateExp");
    }
  },
  computed:{
    ...mapState(['curChallenge']),
    rest(){
      const td = new Date;
      const ed = new Date(this.curChallenge.challengeDto.endDate);
      const diffDate = ed.getTime()-td.getTime()
      return Math.ceil(diffDate / (1000 * 60 * 60 * 24));
    },
    restP(){
      const comp = this.curChallenge.challengeDto.duration-this.rest;
      return (comp/this.curChallenge.challengeDto.duration)*100;
    }
  },
  filters:{
    titleLength(value){
      if(value.length>15){
       return value.substring(0,12)+"...";
      } else{
        return value;
      }
    }
  }, 
  created(){
    const PathName = new URL(document.location).pathname.split("/");
    this.challengeSeq = PathName[PathName.length - 1];

    this.$store.dispatch('getChallenge',this.challengeSeq);
  },
}
</script>

<style scoped>
.container-challenge-start{
  display: flex;
}


.div-challenge-start-info{
  background-color: rgb(234, 234, 234);
  padding: 3px 20px;
  width: 80%
}
.div-challenge-start{
  background-color: rgb(219, 219, 219);
  /* flex-shrink:; */
  min-width: 30%;
  max-width: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
}

.div-challenge-video{
  background-color: white;
  border-radius: 10px;
  height: 40px;
  display: flex;
  justify-content:center;
  align-items:center;
  margin-top: 15px;
  width: 100%;
  cursor: pointer;
}

.div-challenge-video-list{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
}

.div-duration{
  width: 100%;
  padding: 10px;
  height: 60px;
  flex-direction: column;
  background-color: rgba(255, 255, 255, 0.882);
  border-radius: 15px;
  border: 2px solid rgb(204, 204, 204);
}

.div-duration-icons{
  display: flex;
  width: 100%;
  align-items: flex-end;
  justify-content: space-between;
}

.div-duration-icons div{
  margin: 0px;
  padding: 0px;
}

.div-duration,
.div-title,
.div-content{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.div-challenge-start-info div{
  margin: 10px 0px;
}

.div-challenge-start-button{
  width: 100%;
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


.div-challenge-video-list{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
}

.div-challenge-start-info input,textarea{
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

.div-challenge-start-title{
  display: flex;
  flex-direction: column;
  align-items:flex-start;
  width: 100%;
  padding: 5px;
  margin: 5px 0px;
} 

.div-left{
  font-weight: 500;
}
.div-challenge-start-title span{
  font-weight:500;
  font-size: 1.1rem;
}

.button-challenge-complete{
  width: 100%;
  background-color: rgb(255, 147, 147);
  height: 40px;
  border-radius:10px;
  color: white;
  font-weight: 600;
  margin-bottom: 10px;
}
.div-video-detail-view{
  width:100%;
  display: flex;
  align-items: center;
  justify-content:center;
}
</style>