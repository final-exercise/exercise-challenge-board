<template>
  <div class="container-my-challenge">
    <div class="div-my-challenge-menu">
      <h1>최근 생성된 챌린지</h1>
    </div>
    <div class="div-my-challenge-list">
        <div v-for="(challenge, index) in validChallenges" :key="index">
 <challenge-item :challenge="challenge"></challenge-item>
        </div>
       
        <!-- <challenge-item></challenge-item> -->
        <!-- <challenge-item></challenge-item> -->
    </div>
    <div class="div-my-challenge-page">
      <!--pagehelper에 page 가져와야함 -->
      <!-- <div style="margin: 20px;">◀ 1 2 3 4 5 ▶</div> -->
    </div>
    <!-- <v-pagination
      v-model="page"
      :length="10"
      :color="`#87A2FB`"
      @input="inputPage"
    ></v-pagination> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeItem from "./ChallengeItem.vue"
export default {
  components:{
    ChallengeItem
  },
  data(){
    return{
      page:1,
    }
  },
  methods:{
    inputPage(pageNum){
      console.log(pageNum);
    }
  },
  computed: {
    ...mapState(['validChallenges'])
  },
  created() {
    // console.log('totalcreated')
    this.$store.dispatch('getVaildChallenges', this.page);
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
  width: 80%;
  flex-direction: column;
  margin: 10px 10px 10px 20px;
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
</style>