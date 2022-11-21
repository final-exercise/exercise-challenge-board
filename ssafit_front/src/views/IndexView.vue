<template>
  <div class="container-indexView">
    <hero-img></hero-img>
    <video-search></video-search>
    <div class="div-decorate">&nbsp;</div>
    <video-rating></video-rating>
    <div class="div-decorate">&nbsp;</div>
    <user-rating></user-rating>
  </div>
</template>

<script>
import HeroImg from "../components/common/HeroImg.vue"
import VideoSearch from "../components/video/VideoSearch.vue"
import VideoRating from "../components/video/VideoRating.vue"
import UserRating from "../components/user/UserRating.vue"

export default {
  name: "IndexView",
  components: {
    HeroImg,
    VideoSearch,
    VideoRating,
    UserRating
  }
}
</script>

<style scoped>
.container-indexView{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.div-decorate{
  width: 300px;
  height: 1px;
  background-color: rgba(128, 128, 128, 0.127);
  margin-top: 20px;
}
</style>