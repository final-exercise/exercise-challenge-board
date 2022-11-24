<template>
  <div class="container-my-challenge">
    <div class="div-my-challenge-menu">
      <h1>나의 챌린지</h1>
      <button class="button-create-challenge">
        <router-link :to="{name: 'challenge-create'}">나만의 챌린지 만들기</router-link>
      </button>
    </div>

    <div class="div-my-challenge-list" v-for="(challenge, index) in myChallenges" :key="index">
        <challenge-my-item :challenge="challenge"></challenge-my-item>
        <!-- <challenge-my-item></challenge-my-item> -->
        <!-- <challenge-my-item></challenge-my-item> -->
    </div>
    <v-pagination
      v-model="page"
      :length="10"
      :color="`#87A2FB`"
      @input="inputPage"
    ></v-pagination>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeMyItem from "../challenge/ChallengeMyItem.vue"

export default {
  name: "ChallengeMy",
  components:{
    ChallengeMyItem
  },
  data(){
    return{
      page: 1,

    }
  },
  methods:{
    inputPage(pageNum){
      console.log(pageNum);
    }
  },
  computed: {
    //여기에 Page<챌린지 인포 디티오>들어있고, 그안에 챌린지디티오, 비디오리스트, 참여회원리스트 있음
   // 새로고침 하면안됨
    ...mapState(['myChallenges'])  
  },
  created() {
    console.log('created')
    this.$store.dispatch('getMyChallenges', this.page);
  }
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