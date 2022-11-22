<template>
  <div class="container-coach-manage-view">
    <div class="div-coach-manage-menu">
        <div v-for="(user, index) in userList" :key="index" @click="makeActive">
           <div class="div-user-menu">{{user.userName}}</div>
           <div style="display:none">{{user.userSeq}}</div>
        </div>
    </div>
    <!-- <challenge-my></challenge-my>
    <div class="div-decorate" stype="width: 100%"></div>
    <challenge-total></challenge-total> -->
  </div>
</template>

<script>
export default {
    data(){
        return{
            userList:[
                {
                userName: "김소정",
                userSeq: 1,
                },{
                userName: "김지현",
                userSeq: 2,
                },
            ],
            selectedUser:"",
        }
    },
    methods:{
        makeActive(event){
            const curActive = document.querySelector('.div-user-menu.active');
            if(curActive)curActive.classList.remove('active');
            event.target.classList.add('active');
            //얘가 seq
            console.log(event.target.nextSibling.innerHTML);
            //event.target으로 유저 이름
            //이걸 가지고 axios -> bmi랑 캘린더 
        }
    }
}
</script>

<style scoped>
.container-coach-manage-view{
  display: flex;
}

.div-coach-manage-menu{
  background-color: rgb(245, 245, 245);
  box-shadow: 2px 2px 7px -5px rgba(0,0,0,0.36);
  height: 100%;
  width: 20%;
}

.div-user-menu{
  height: 55px;
  display: flex;
  justify-content: center;
  align-items:center;
  /* background-color: rgb(237, 237, 237); */
  border-bottom: 3px solid rgb(229, 229, 229);
  /* border-top: 3px solid rgb(229, 229, 229); */
  margin: 10px 20px;
}
.active{
  /* box-shadow: rgba(0, 0, 0, 0.15) 1px 1px 2px inset; */
  background-color: white;
}

</style>