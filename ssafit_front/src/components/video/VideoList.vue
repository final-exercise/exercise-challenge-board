<template>
  <div class="container-list">
    <div v-for="(video, index) in videos" :key="index">
        <video-list-item :video="video"></video-list-item>
    </div>
    
  </div>

</template>

<script>
import VideoListItem from './VideoListItem.vue';
export default {
  components:{
    VideoListItem
  },
  data(){
    return{
      videos:[{
        videoSeq:1,
        videoTitle:"dudtkd1",
        videoId: "6j5CB0yeemg",
        videoPart: "total",
        channelName:"hi"
      }]
    }
  },
  methods: {
    inputPage(pageNum){
      console.log(pageNum);
    }
  }
}
</script>

<style scoped>
.container-list{
  gap: 20px 2%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin: 10px 10px;
}


</style>