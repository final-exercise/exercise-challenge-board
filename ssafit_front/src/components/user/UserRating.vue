<template>
  <div class="container-user-rating">
    <h2>SSAFIT RANKING</h2>
    <div class="div-decorate">&nbsp;</div>
    <div class="container-rating-list">
      <user-rating-list></user-rating-list>
      <user-rating-list></user-rating-list>
      <user-rating-list></user-rating-list>
    </div>
  </div>
</template>

<script>
import UserRatingList from "./UserRatingList.vue"
export default {
  components:{
    UserRatingList,
  }
}
</script>

<style scoped>
.div-decorate{
  height: 3px;
  width: 50px;
  background-color: rgb(235, 229, 246);
  margin: 10px;

}
.container-user-rating {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 20px 0px;
  margin-top: 50px;
}

.container-rating-list{
  margin:10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>