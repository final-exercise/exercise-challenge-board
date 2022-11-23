<template>
  <div class="fieldset-write" >
    <h3>하루 기록</h3>
    <h2>{{date|dateFilter}}</h2>
    <div class="div-decorate" style="width:100%; margin:5px 0px 15px 0px; background-color:#FFD54F;">&nbsp;</div>
    <div class="div-foods">
      <div v-for="(item, index) in dayEvent" :key="index">
        <!-- <user-my-record-calendar-diet-item :food="food" @selectFood="selectFood"></user-my-record-calendar-diet-item> -->
        <user-my-record-calendar-date-item :item="item" @calcSum="calcSum"></user-my-record-calendar-date-item>
      </div>

    </div>
    
  </div>
</template>

<script>
import UserMyRecordCalendarDietItem from "./UserMyRecordCalendarDietItem.vue";
import axios from 'axios';
import FadeLoader from 'vue-spinner/src/FadeLoader.vue'
import {mapState} from 'vuex';
import { mapGetters } from "vuex";
import UserMyRecordCalendarDateItem from "./UserMyRecordCalendarDateItem.vue";

export default {
  name:'UserMyRecordCalendarDiet',
  components:{
    // UserMyRecordCalendarDietItem,
    // FadeLoader,
    UserMyRecordCalendarDateItem
  },
  props:['date','dayEvent'],
  data(){
    return{
      totalCal:0
    }
  },
  filters:{
    dateFilter(value){
      const dates = value.split("-")
      return `${dates[0]}년 ${dates[1]}월 ${dates[2]}일`
    }
  },
  methods:{
    calcSum(val){

      this.totalCal+=val;
    }
  },
  created(){
    // for(let e in events){
    //   if(e.date===this.date){
    //     this.dayEvent.push(e);
    //   }
    // }
  },
  computed:{
    ...mapState(['events'])
  }
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

.div-foods{
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