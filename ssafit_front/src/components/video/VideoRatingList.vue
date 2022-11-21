<template>
  <div class="container-rating-list">
    <div v-for="(video, index) in videos" :key="index">
        <video-rating-list-item :video="video"></video-rating-list-item>
    </div>
  
  </div>

</template>

<script>
import VideoRatingListItem from './VideoListItem.vue';
export default {
  components:{
    VideoRatingListItem
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
.container-rating-list{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 10px 10px;
}
</style>