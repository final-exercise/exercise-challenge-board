<template>
  <div>
    <div class="container food-item" v-if="item.food!=null">
      <div class="div-upper">
        <span class="item-name ">{{name}}</span>
      </div>
      <div class="div-lower">
        <div class="item-type food">음식</div>
        <h4>{{calc}}</h4>
      </div>
    </div>

    <div class="container workout-item" v-if="item.food==null">
      <div class="div-upper">
        <span class="item-name">{{name}}</span>
      </div>
      <div class="div-lower">
        <div class="item-type workout">운동</div>
        <h4>{{calc}}</h4>
      </div>
    </div>
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
  },
  computed:{
    name(){
      return (this.item.name.split("("))[0];
    },
    calc(){
      const temp = this.item.name.split("(")[1];
      return temp.substr(0,temp.length-1);
      
    }
  },
  methods:{

  },
  beforeMount(){

  }

}
</script>

<style scoped>
.container{
  width: 100%;
  display: flex;
  flex-direction:column;
  align-items: flex-start;
  margin-bottom: 5px;
  border-radius: 10px;
  padding: 10px 10px;
  background-color: rgba(255, 255, 255, 0.552);
  border: 1px solid rgb(196, 196, 196);
}


.item-name{
  font-size:1rem;
  font-weight: 600;
}

.item-type{
  display:block;
  border-radius: 15px;
  width: 45px;
  font-size: 0.8rem;
}

.item-type.workout{
  background-color: rgb(106, 106, 207);
  color: white;
}

.item-type.food{
  background-color: rgb(255, 109, 109);
  color: white;
}

.div-lower{
  width:100%;
  display:flex;
  justify-content: space-between;
  align-items: center;
}
</style>