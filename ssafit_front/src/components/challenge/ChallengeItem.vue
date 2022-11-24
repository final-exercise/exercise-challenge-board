<template>
  <div class="container-challenge-item">
    <div class="div-challenge-info">
      <div class="challenge-title">
        <h2>{{challenge.challengeDto.challengeTitle}}</h2>
        <button>{{challenge.challengeUsers.length}}명</button>
      </div>
      <div class="challenge-duration">{{challenge.challengeDto.endDate}}까지</div>
      <div class="challenge-description">{{challenge.challengeDto.challengeDescription}}</div>
    </div>
    <hr>
    <div class="div-challenge-videos">
      <div class="div-challenge-video">
        <h4>{{challenge.challengeVideos[0].videoTitle|titleLength}}</h4>
      </div>
      <div class="div-challenge-video">
        <h4>{{challenge.challengeVideos[1].videoTitle|titleLength}}</h4>
      </div>
      <div >
        <h4>...</h4>
      </div>
    </div>
    <div class="div-challenge-button">
      <button class="button-start" @click="joinChallenge">참여하기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChallengeItem',
  props: [
    'challenge'
  ],
  filters: {
    titleLength(value){
      if(value.length>15){
       return value.substring(0,15)+"...";
      } else{
        return value;
      }
    }
  },
  methods: {
    joinChallenge() {
      console.log(this.challenge.challengeDto.challengeSeq)
      this.$store.dispatch('joinChallenge', this.challenge.challengeDto.challengeSeq);
    }
  }
}
</script>

<style scoped>
.container-challenge-item {
  background-color: rgba(209, 209, 209, 0.356);
  padding: 20px;
  width: 300px;
  min-width: 300px;
  height: 400px;
  flex-direction: column;
  justify-content: flex-start;
  box-shadow: rgba(0, 0, 0, 0.058) 1.95px 1.95px 2.6px;
  margin: 20px;
  border-radius: 5px;
}

.container-challenge-item * {
  margin-bottom: 5px;
}

.challenge-duration{
  font-size: 0.8rem;
  color:rgb(111, 111, 111);
}

.challenge-description{
  font-size: 1.1rem;
  margin-top:5px;
}

.challenge-title {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.div-challenge-info{
  height: 30%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.div-challenge-info button{
  background-color: #ff899b;
  color: white;
}

.div-challenge-video{
  margin: 10px 0px;
  background-color: white;
  height: 50px;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: rgba(0, 0, 0, 0.021) 1.95px 1.95px 2.6px;
}

.div-challenge-videos{
  height: 50%;
}

.button-start{
  background-color:#87A2FB;
  width: 100%;
  height: 50px;
  font-weight: 700;
  color: rgb(255, 255, 255);
}

</style>