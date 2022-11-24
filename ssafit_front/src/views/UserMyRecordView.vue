<template>
  <div class="container-user-mypage">
    <div class="div-mypage-menu">
      <router-link to="/myrecord/coach">
        <div class="div-menu-item active" @click="makeActive">나의 코치</div>
      </router-link>
      <router-link :to="{name:'myrecord-calendar'}">
        <div class="div-menu-item" @click="makeActive">나의 한달</div>
      </router-link>
    </div>
    <div class="div-router-view">
      <router-view/>
    </div>
  </div>
</template>

<script>
// import UserMyPageInfo from '@/compnents/video/VideoWishList.vue';

export default {
  components:{
    // UserMyPageInfo,
    // VideoWishList
  },
  data(){
    return{
    }
  },
  methods:{
    makeActive(event){
      const curActive = document.querySelector('.div-menu-item.active');
      curActive.classList.remove('active');
      event.target.classList.add('active');
    }
  }
}
</script>

<style scoped>

.container-user-mypage{
  display: flex;
}

.div-decorate{
  background-color: rgba(231, 231, 231, 0.365);
  height: 1px;
  width: 100%;
}

.div-mypage-menu{
  background-color: rgb(245, 245, 245);
  box-shadow: 2px 2px 7px -5px rgba(0,0,0,0.36);
  height: 100%;
  width: 25%;
}

.div-router-view{
  display: flex;
  justify-content:center;
  align-items: center;
  width: 75%;
}

.active{
  /* box-shadow: rgba(0, 0, 0, 0.15) 1px 1px 2px inset; */
  background-color: white;
}

.div-menu-item{
  height: 55px;
  display: flex;
  justify-content: center;
  align-items:center;
  /* background-color: rgb(237, 237, 237); */
  border-bottom: 3px solid rgb(229, 229, 229);
  /* border-top: 3px solid rgb(229, 229, 229); */
  margin: 10px 20px;
}

a:link,
a:visited{
  color: rgb(94, 94, 94);
  font-weight:700;
}


</style>