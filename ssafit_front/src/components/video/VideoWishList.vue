<template>
  <div class="container-video-wish">
    <div class="div-video-wish-title">
      <h1>내가 찜한 영상</h1>
      <div class="div-decorate" style="margin-left:0px; margin-bottom: 10px; width: 20%"></div>
    </div>
    <div class="div-sorted-videos">
      <div v-for="(video, index) in videos" :key="index">
        <video-list-item :video="video"></video-list-item>
      </div>
      &nbsp;
    </div>
    <v-pagination class="div-page"
      v-model="page"
      :length="10"
      :color="`#81C784`"
      @input="inputPage"
    ></v-pagination>
  </div>
</template>

<script>
import VideoListItem from "./VideoListItem.vue";

export default {
  components:{
    VideoListItem
  },
  data(){
    return{
      page: 1,
      videoList:[],
      selectedVideoList:[]
    }
  },
  methods: {
    inputPage(pageNum){
      return pageNum;
    },
    createChallenage(){
      //selectedVideoList 가지고 challenge 만들기
    }
  },
  created(){
    //created말고 이 컴포넌트가 불러질때(router-link) 최초 1페이지 불러지고
    //다른 페이지 눌리면 그때마다 dispatch하고
    // this.$store.dispatch('getWishVideoList');
    // 근데 내가 지금 필요한 영상 리스트는 이 컴포넌트에서만 필요한데
    // vuex가 필요한가?
  },

  
}
</script>

<style scoped>
.container-video-wish{
  height: 100%;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
}

.container-video-wish{
  margin: 20px;
}

.input.disabled{
  background-color:rgb(231, 231, 231);
}

.div-video-wish{
  margin-top: 10px;
  background-color: rgba(224, 224, 224, 0.295);
  padding: 20px;
  border-radius: 10px;
}

.div-video-wish-title{
  min-height: 15%;
  margin-top: 20px;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
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

.div-sorted-videos{
  min-height: 40%;
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

.div-page{
  height: 5%;
}

</style>