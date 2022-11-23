<template>
  <div class="container-workout-item" @click="selectWorkout">
    <h5>{{workout.videoTitle|lengthFilter(15)}}</h5>
    <span> {{workout.videoCal|calcFilter}}</span>
    <span> | </span>
    <span style="color: rgb(116, 150, 255); font-weight:600">
      <a :href="`/video/`+workout.videoSeq">상세보기</a>
    </span>
  </div>
</template>

<script>
export default {
  props:["workout"],
  data(){
    return{
    }
  },
  filters:{
    calcFilter(value){
      return `${value}kcal`
    },
    lengthFilter(value,tar){
      if(value.length>tar){
       return value.substring(0,tar)+"...";
      } else{
        return value;
      }
    }
  },
  methods:{
    selectWorkout(event){
      this.$emit("selectWorkout",this.workout);
    }
  },
  created(){
  }

}
</script>

<style scoped>
.container-workout-item{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(153, 153, 153);
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  margin-bottom: 5px;
  border-radius: 10px;
  padding: 0px 20px;
  cursor: pointer;
}

span{
  margin-left: 10px;
  font-size:0.8rem;
}
</style>