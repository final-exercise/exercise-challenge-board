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
    videos:[
      {
        videoSeq: 1,
        videoId: "g2ZeT9v7wt0",
        part: "total",
        channelName: "hello",
        videoTitle: "heihihihihihih"
      },{
        videoSeq: 1,
        videoId: "g2ZeT9v7wt0",
        part: "total",
        channelName: "hello",
        videoTitle: "heihihihihihih"
      },{
        videoSeq: 1,
        videoId: "g2ZeT9v7wt0",
        part: "total",
        channelName: "hello",
        videoTitle: "heihihihihihih"
      },{
        videoSeq: 1,
        videoId: "g2ZeT9v7wt0",
        part: "total",
        channelName: "hello",
        videoTitle: "heihihihihihih"
      }, {
        videoSeq: 1,
        videoId: "g2ZeT9v7wt0",
        part: "total",
        channelName: "hello",
        videoTitle: "heihihihihihih"
      }
    ]
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