<template>
  <div class="container-food-item" @click="selectDiet">
    {{item.name}}
    <hr>
  </div>
</template>

<script>
export default {
  props:["item"],
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