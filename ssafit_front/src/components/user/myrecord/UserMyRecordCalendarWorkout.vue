<template>
  <div class="fieldset-write" >
    <div class="loading" v-if="isLoading">
      <FadeLoader/>
    </div>
    <h3>운동 기록하기</h3>
    <h2>{{today|dateFilter}}</h2>
    <div class="div-decorate" style="width:100%; margin:5px 0px 15px 0px; background-color:rgb(150, 200, 255);">&nbsp;</div>
    <div class="div-workouts">
      <div v-for="(workout, index) in workouts" :key="index">
        <user-my-record-calendar-workout-item :workout="workout" @selectWorkout="selectWorkout"></user-my-record-calendar-workout-item>
      </div>
      <v-pagination
      v-model="page"
      :length="length"
      :color="`#81C784`"
      ></v-pagination>
    </div>
    
  </div>
</template>

<script>
// import UserMyRecordCalendarWorkoutItem from "./UserMyRecordCalendarDietItem.vue";
import axios from 'axios';
import FadeLoader from 'vue-spinner/src/FadeLoader.vue'
import { mapState } from "vuex";
import UserMyRecordCalendarWorkoutItem from "./UserMyRecordCalendarWorkoutItem.vue"

export default {
  name:'UserMyRecordCalendarWorkout',
  components:{
    UserMyRecordCalendarWorkoutItem,
    FadeLoader
  },
  data(){
    return{
      today: new Date(),
      keyword:"",
      page:1,
      length:0,
      workouts:[],
    }
  },
  filters:{
    dateFilter(value){
      const year = value.getFullYear();
      const month = ('0' + (value.getMonth() + 1)).slice(-2);
      const day = ('0' + value.getDate()).slice(-2);
      return `${year}년 ${month}월 ${day}일`
    }
  },
  methods:{
    selectWorkout(data){
      if(!window.confirm(`"${data.videoTitle}" 등록하시겠습니까?`)){
        return;
      }
    
      const API_URL=`http://localhost:331/user/workout`;
      let param={
        videoSeq: data.videoSeq,
        videoTitle: data.videoTitle,
        videoCal: data.videoCal
      }
      console.log(param);

      this.$store.commit("PAGE_LOAD",true);
      axios({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params: param,
      })
      .then((res) => {
        this.$store.commit("PAGE_LOAD",false);
        return res;
      })
      .then((res) => {
        // this.loading=false;
        console.log(res)
      }).catch((err)=>{
        console.log(err)
      })

      window.location.replace(document.location);
    },
  },
  computed:{
    ...mapState(['isLoading'])
  },
  created(){
    const API_URL = `http://localhost:331/user/wish`

      axios ({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res)=>{
        let total = res.data.total;
        this.workouts = res.data.res;
        console.log(this.workouts);
        this.length = Math.ceil(total/10);
      }).catch((err)=>{
        console.log(err);
      })
    
  },
  
}
</script>

<style scoped>
.fieldset-write{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  background-color:rgb(244, 244, 244);
  border: 1px solid rgb(235, 235, 235);
  min-width: 60%;
  min-height: 70%;
  border-radius: 10px;
  padding: 20px 20px;
}

.button-search {
  height: 32px;
  margin: 0px 5px;
  background-color:white;
  border: 1px solid rgb(238, 238, 238);
  border-radius: 25px;
}

.input-search{
  width: 60%;
  margin: 0px 5px;
}

.div-search{
  display: flex;
  justify-content: center;
  width: 100%;
  margin: 10px 0px;
}

.div-workouts{
  width:100%
}

.loading {
  z-index: 2;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;
}

</style>