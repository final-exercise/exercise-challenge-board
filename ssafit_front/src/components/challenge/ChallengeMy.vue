<template>
  <div class="container-my-challenge">
    <div class="div-my-challenge-menu">
      <h1>나의 챌린지</h1>
      <button class="button-create-challenge">
        <router-link :to="{name: 'challenge-create'}">나만의 챌린지 만들기</router-link>
      </button>
    </div>
    <div class="div-my-challenge-list">
        <challenge-my-item></challenge-my-item>
        <challenge-my-item></challenge-my-item>
        <challenge-my-item></challenge-my-item>
    </div>
    <div class="div-my-challenge-page">
      <!--pagehelper에 page 가져와야함 -->
      <div style="margin: 20px;">◀ 1 2 3 4 5 ▶</div>
    </div>
  </div>
</template>

<script>
import ChallengeMyItem from "../challenge/ChallengeMyItem.vue"
export default {
  components:{
    ChallengeMyItem
  },
  data(){
    return{
      pages: 6,
      pageNum: 1,
    }
  }
  // ,
  // beforeMount(){
  //   if (this.pages<=1) return; 
  //   var pageGroup = Math.ceil(this.pages/5);
  //   var last = pageGroup * 5;
  // }
}
</script>

<style scoped>
.container-my-challenge{
  display:flex;
  flex-direction: column;
  justify-content: center;
  margin: 10px 10px 10px 20px;
}

a:link,
a:active,
a:hover,
a:visited{
  color: white;
}
.div-my-challenge-menu{
  margin: 20px 30px 0px 30px;
  display:flex;
  justify-content: space-between;
  align-items: center;
}

.button-create-challenge{
  background-color:#6F38C5;
  color: white;
  font-weight: 500;
  padding: 10px 20px;
  border-radius: 20px;
}

.div-my-challenge-list{
  display: flex;
  flex-wrap: nowrap;
}

a{
  color: white;
}
</style>