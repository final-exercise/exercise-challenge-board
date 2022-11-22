<template>
  <div class="container-myrecord-calendar">
    <div class="div-myrecord-calendar-title">
      <h1>나의 한달은?</h1>
      <div class="div-decorate" style="margin-left:0px; margin-bottom: 10px; width: 20%"></div>
    </div>
    <div class="div-myrecord-calendar-main">
      <div class="div-myrecord-calendar">
        <v-btn icon class="ma-2" @click="$refs.calendar.prev()">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>

        <v-btn icon class="ma-2" @click="$refs.calendar.next()">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>

      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="value"
          :weekdays="weekday"
          :type="type"
          :events="events"
          :event-overlap-threshold="30"
          :event-color="getEventColor"
          @click:day="tmpEvent"
        ></v-calendar>
      </v-sheet>
      </div>
      <div class="div-myrecord-write">
        <div class="fieldset-write">&nbsp;</div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data: () => ({
    type: 'month',
    mode: 'stack',
    weekday: [0, 1, 2, 3, 4, 5, 6],
    value: '',
    events: [
      {
        "name":"스쿼트영상...(-400kcal)",
        "start": "2022-11-10",
        "color":"red",
        "timed": true
      },
    ],
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
    names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
  }),
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
    }
  },
}
</script>

<style scoped>
.container-myrecord-calendar{
  width: 100%;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  margin: 20px;
  padding-right: 20px;
}

.div-myrecord-calendar-title{
  min-height: 15%;
  margin-top: 20px;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
}

.div-myrecord-calendar-main{
  display: flex;
  width: 100%;
}

.div-myrecord-calendar{
  min-width: 60%;
}

.div-myrecord-write{
  margin-top: 30px;
  margin-left: 20px;
  min-width: 40%;
}
.fieldset-write{
  background-color:black;
  min-width: 60%;
  min-height: 70%;
  border-radius: 30px;
}
</style>