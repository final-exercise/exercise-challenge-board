<template>
  <div class="container-list-item">
    <div class="video-cropped-img">
      <router-link :to="`/video/${video.videoSeq}`">
        <img :src="'https://img.youtube.com/vi/'+video.videoId+'/0.jpg'">
        </router-link>
    </div>
    <div class="hr">&nbsp;</div>
    <div class="container-videos-video-content">
      <button class="button-part" :class="video.part">{{video.part}}</button>
      <h3>{{video.videoTitle|titleLength}}</h3>
      <p>{{video.channelName}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VideoListItem',
  props: [
    'video'
  ],
  computed: {

  },
  filters: {
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
.hr {
  margin: 10px 0px;
  width: 200px; 
  height: 1px;
  background-color:rgba(128, 128, 128, 0.268);
}

.container-list-item {
  margin: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 240px;
  height: 230px;
  border: 1px solid rgba(227, 227, 227, 0.486);
  background-color: rgba(237, 237, 237, 0.232);
  box-shadow: rgba(0, 0, 0, 0.041) 0px 1px 4px;
  padding: 10px;
  border-radius: 10px;
}

.video-cropped-img {
  max-height: 98px;
  overflow: hidden;
}

.video-cropped-img img {
  width: 80%;
  max-width: initial;
  margin-top: -17px;
}

.container-videos-video-content h3 {
  font-size: 100%;
}

.button-part {
  border-radius: 18px;
  padding: 0px 5px;
  width: 70px;
  color: white;
  font-weight: 500;
  font-size: 0.9rem;
  height: 30px;
  margin-bottom: 5px;
}

.button-part.total{
  background-color: rgb(255, 117, 117);
}
.button-part.upper{
  background-color: rgb(161, 255, 117);
}
.button-part.bottom{
  background-color: rgb(117, 122, 255);
}
.button-part.belly{
  background-color: rgb(255, 220, 92);
}


.container-videos-video-content p{
  font-size: 0.8rem;
}
</style>