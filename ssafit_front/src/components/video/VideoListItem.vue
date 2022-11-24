<template>
  <div class="container-list-item" @click="videoSelect">
    <div class="video-cropped-img">
      <router-link :to="`/video/${video.videoSeq}`">
        <img :src="'https://img.youtube.com/vi/'+video.videoId+'/0.jpg'">
      </router-link>
    </div>
    <div class="hr">&nbsp;</div>
    <div class="container-videos-video-content">
      <button class="button-superPart" :class="video.superType">{{video.superType}}</button>
      <button class="button-part" :class="video.subType">{{video.subType}}</button>
      <h3>{{video.videoTitle|titleLength}}</h3>
      <p>{{video.videoChannelName}}</p>
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
  },
  methods:{
    videoSelect(){
      this.$emit("videoSelect",this.video);
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
  width: 50px;
  color: white;
  font-weight: 500;
  font-size: 0.9rem;
  height: 25px;
  margin-bottom: 5px;
}

.button-part.전신{
  background-color: rgb(255, 117, 117);
}
.button-part.상체{
  background-color: rgb(123, 217, 80);
}
.button-part.하체{
  background-color: rgb(117, 122, 255);
}
.button-part.복부{
  background-color: rgb(255, 220, 92);
}

.button-superPart {
  border-radius: 18px;
  padding: 0px 5px;
  width: 65px;
  color: white;
  font-weight: 500;
  font-size: 0.9rem;
  height: 25px;
  margin-bottom: 5px;
  margin-right: 5px;
}
.button-superPart.체중감량{
  background-color: rgb(255, 170, 117);
}
.button-superPart.스트레칭{
  background-color: rgb(89, 80, 217);
}
.button-superPart.근력강화{
  background-color: rgb(161, 255, 117);
}
.button-superPart.체형교정{
  background-color: rgb(255, 92, 92);
}
.button-superPart.스포츠{
  background-color: rgb(255, 92, 250);
}

.container-videos-video-content p{
  font-size: 0.8rem;
}
</style>