<template>
  <div class="container-video-rating">
    <h2>인기 영상 둘러보기</h2>
    <div class="div-decorate">&nbsp;</div>
    <div class="div-type-button">
      <button class="button-type active" @click="getKey">최신 영상</button>
      <button class="button-type" @click="getKey">좋아요순</button>
      <button class="button-type" @click="getKey">조회수순</button>
    </div>
    <video-list></video-list>
  </div>
</template>

<script>
import VideoList from "./VideoList.vue"

export default {
  components:{
    VideoList,
  },
  data(){
    return{
      sort:""
    }
  },
  methods:{
    getKey(event){
      const selectKey = event.target.innerHTML;
      
      const curA = document.querySelector('.active');
      curA.classList.remove('active');
      event.target.classList.add('active');

      if(selectKey=="최신 영상"){
        this.sort = "created_at"
      } else if(selectKey=="좋아요순"){
        this.sort = "like"
      } else{
        this.sort = "video_view_cnt"
      }
     
    }
  }
}
</script>

<style scoped>

.div-decorate{
  height: 3px;
  width: 50px;
  background-color: rgb(235, 229, 246);
  margin: 10px;

}
.container-video-rating {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 20px 0px;
  margin-top: 50px;
}

.div-type-button{
  margin: 10px
}

button {
  margin: 0px 20px;
  height: 30px;
  border: 1px solid rgba(0, 0, 0, 0.017);
  padding: 10px;
  /* box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px; */
  border-radius: 10px;
}

button.active{
  background-color: #6F38C5;
  color: white;
  font-weight: 500;
}
</style>