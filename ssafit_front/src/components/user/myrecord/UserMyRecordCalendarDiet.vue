<template>
  <div class="fieldset-write">
    <h3>식단 기록하기</h3>
    <h2>{{today|dateFilter}}</h2>
    <div class="div-search">
      <input class="input-search" placeholder="오늘 뭘 먹었냐면..." v-model="keyword"/>
      <button class="button-search"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
          fill="currentColor" class="bi bi-search" viewBox="0 0 16 16"
          @click="search">
          <path
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
          </svg>
      </button>
    </div>
    <div class="div-decorate" style="width:100%; margin:5px 0px 15px 0px; background-color:rgb(150, 200, 255);">&nbsp;</div>
    <div class="div-foods">
      <div v-for="(food, index) in foods" :key="index">
        <user-my-record-calendar-diet-item :food="food"></user-my-record-calendar-diet-item>
      </div>
      <v-pagination
      v-model="page"
      :length="10"
      :color="`#81C784`"
      @input="inputPage"
    ></v-pagination>
    </div>
    
  </div>
</template>

<script>
import UserMyRecordCalendarDietItem from "./UserMyRecordCalendarDietItem.vue";
import axios from 'axios';

export default {
  components:{
    UserMyRecordCalendarDietItem,
  },
  data(){
    return{
      today: new Date(),
      keyword:"",
      foods:[]
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
    search(event){
      const API_URL=`https://openapi.foodsafetykorea.go.kr/api/${process.env.VUE_APP_DIET_API_KEY}/I2790/json/1/10/DESC_KOR=${this.keyword}`;

      axios({
        url: API_URL,
        method: 'GET',
      })
      .then((res) => {
        this.foods = res.data.I2790.row
        console.log(this.foods);
      }).catch((err)=>{
        console.log(err)
      })
    }
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

</style>