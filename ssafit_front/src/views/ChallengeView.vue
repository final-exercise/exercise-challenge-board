<template>
  <div class="container-challenge-view">
    <challenge-my></challenge-my>
    <div class="div-decorate" stype="width: 100%"></div>
    <challenge-total></challenge-total>
  </div>
</template>

<script>
import ChallengeMy from "../components/challenge/ChallengeMy.vue"
import ChallengeTotal from "../components/challenge/ChallengeTotal.vue"

export default {
  components:{
    ChallengeMy,
    ChallengeTotal
  }
}
</script>

<style scoped>
.container-challenge-view{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>