<template>
  <div class="container-food-item" @click="selectDiet">
    <h5>{{food.DESC_KOR|lengthFilter(8)}}</h5>
    <span> {{food.NUTR_CONT1|calcFilter}}</span>
    <span> | </span>
    <span style="color: rgb(116, 150, 255);">{{food.MAKER_NAME|lengthFilter(5)}}</span>
  </div>
</template>

<script>
export default {
  props:["food"],
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
    selectDiet(event){
      this.$emit("selectFood",this.food);
    }
  }

}
</script>

<style scoped>
.container-food-item{
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