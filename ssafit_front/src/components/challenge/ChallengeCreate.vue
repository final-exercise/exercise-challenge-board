<template>
  <div class="container-challenge-create">
    <div class="div-challenge-make">
      <div class="div-challenge-make-title">
        <h2>나만의 챌린지</h2>
      </div>
      <div class="div-challenge-video-list">
        <div class="div-challenge-video">
          <h3>안녕하세요??</h3>
        </div>
        <div class="div-challenge-video">
          <h3>두번째영상??</h3>
        </div>
      </div>
      <div class="div-challenge-make-button">
        <button class="button-challenge-make">만들기! 👉</button>
      </div>
    </div>
    <div class="div-wish-video-list">
      <h1>내가 찜한 영상</h1>
      <div class="div-decorate"></div>
      <video-list></video-list>
    </div>
  </div>
</template>

<script>
import VideoList from "@/components/video/VideoList.vue"
export default {
  components:{
    VideoList
  }
}
</script>

<style scoped>
.container-challenge-create{
  display: flex;
}

.div-challenge-make{
  background-color: rgb(219, 219, 219);
  /* flex-shrink:; */
  min-width: 30%;
}

.div-challenge-make h2{
  padding: 20px;
  background-color: rgb(210, 210, 210);
}

.div-challenge-video-list{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
}

.div-wish-video-list{
  display: flex;
  flex-direction: column;
  margin: 25px;
  justify-content: center;
  align-items: center;
}

.div-wish-video-list h1{
  margin: 10px;
}

.div-challenge-video{
  background-color: white;
  border-radius: 10px;
  height: 40px;
  display: flex;
  justify-content:center;
  align-items:center;
  width: 80%;
  margin-top: 15px;
}

.button-challenge-make{
  background-color: rgb(255, 147, 147);
  width: 80%;
  height: 40px;
  border-radius:10px;
  margin-top: 15px;
  color: white;
  font-weight: 600;
}
</style>