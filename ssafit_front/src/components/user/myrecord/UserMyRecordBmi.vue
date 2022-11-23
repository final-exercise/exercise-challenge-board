<template>
  <div class="container-myrecord-bmi">
    <div class="loading" v-if="loading">
      <FadeLoader/>
    </div>
    <div class="div-myrecord-bmi-title">
      <h1>얼마나 빠졌나</h1>
      <div class="div-decorate" style="margin-left:0px; margin-bottom: 10px; width: 20%"></div>
    </div>
    <div class="div-myrecord-bmi-main">
      
    </div>

  </div>
</template>

<script>
import FadeLoader from 'vue-spinner/src/FadeLoader.vue'
import axios from 'axios'
import { mapState } from 'vuex';

export default {
  components:{
    FadeLoader
  },
  data: () => ({
    // loading:false,
    type: 'month',
    mode: 'stack',
    weekday: [0, 1, 2, 3, 4, 5, 6],
    value: '',
    // events: [],
    foods:[],
  }),
  computed:{
    ... mapState(['events','loading'])
  },
  methods: {
    getEvents ({ start, end }) {
      const events = []

      const min = new Date(`${start.date}T00:00:00`)
      const max = new Date(`${end.date}T23:59:59`)
      const days = (max.getTime() - min.getTime()) / 86400000
      const eventCount = this.rnd(days, days + 20)

      for (let i = 0; i < eventCount; i++) {
        const allDay = this.rnd(0, 3) === 0
        const firstTimestamp = this.rnd(min.getTime(), max.getTime())
        const first = new Date(firstTimestamp - (firstTimestamp % 900000))
        const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
        const second = new Date(first.getTime() + secondTimestamp)

        events.push({
          name: this.names[this.rnd(0, this.names.length - 1)],
          start: first,
          end: second,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay,
        })
      }

      this.events = events
    },
    getEventColor (event) {
      return event.color
    },
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
    tmpEvent(event){
      console.log(event);
    },
    makeActive(event){
      const curActive = document.querySelector('.menu-item.active');
      if(curActive)curActive.classList.remove('active');
      event.target.classList.add('active');
    },
    specificDayEvent(event){
      let dayEvent = [];
      for(let e of this.$store.state.events){
        if(e.start==event.date){
          console.log(e);
          dayEvent.push(e);
        }
      }
      this.$router.push({name:'myrecord-calendar-date',params:{date:event.date, dayEvent:dayEvent}, props:true });
    }
  },
  created(){
    this.$store.dispatch('getDietList');
  },
}
</script>

<style scoped>
a:link,
a:hover,
a:visited,
a:active{
  color: rgb(46, 46, 46);
  font-weight: 600;
}

.container-myrecord-bmi{
  width: 100%;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  margin: 20px;
  padding-right: 20px;
}

.div-myrecord-bmi-title{
  min-height: 15%;
  margin-top: 20px;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
}

.div-myrecord-bmi-main{
  display: flex;
  width: 100%;
}

.menu-item{
  background-color:rgb(219, 219, 219);
  padding: 0px 20px;
  margin: 10px;
  font-weight: 500;
}

.active{
  /* box-shadow: rgba(0, 0, 0, 0.15) 1px 1px 2px inset; */
  background-color: rgb(255, 161, 161);
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 3px;
}

.div-myrecord-calendar{
  min-width: 60%;
}

.div-myrecord-write{
  margin-left: 20px;
  min-width: 40%;
}

.div-myrecord-button{
  height: 60px;
}

.div-myrecord-button .ma-2{
  background-color:rgb(255, 255, 255);
}


</style>