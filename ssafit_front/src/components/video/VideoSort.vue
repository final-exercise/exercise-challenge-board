<template>
  <div class="container-video-sort">
    <h1>{{this.sub}} {{this.super}}</h1>
    <div class="div-decorate">&nbsp;</div>
    <div class="div-sorted-videos">
      <div v-for="(video, index) in videos" :key="index">
        <video-list-item :video="video"></video-list-item>
      </div>
    </div>
  </div>
</template>

<script>
import VideoListItem from "./VideoListItem.vue"

export default {
  name: "VideoSort",
  components: {
    VideoListItem,
  },
  data(){
    return{
      super:"",
      sub:"",
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
  },
  created(){
    const params = new URL(document.location).searchParams;
    this.super=params.get("super");
    this.sub=params.get("sub");
  }
}
</script>

<style scoped>

.container-video-sort{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.container-video-sort h1{
  margin: 30px 0px 20px 0px;
}

.div-sorted-videos{
  margin: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

</style>