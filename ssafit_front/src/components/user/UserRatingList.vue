<template>
  <div class="container-user-rating-list">
    <div class="div-color">
      <h3>댓글</h3>
    </div>
    <div class="div-decorate">&nbsp;</div>
    <div class="container-user-rating-list-item">
      <ul style="padding-left:0px; padding-right: 5px;">
        <li>
          <div class="div-user-item">
            <span class="medal">🥇</span>
            <h4>김소정</h4>
            <span>15개</span>
          </div>
        </li>
        <li>
          <div class="div-user-item">
            <span class="medal">🥈</span>
            <h4>김소정</h4>
            <span>15개</span>
          </div>
        </li>
        <li>
          <div class="div-user-item">
            <span class="medal">🥉</span>
            <h4>김소정</h4>
            <span>15개</span>
          </div>
        </li>
        <li>
          <div class="div-user-item">
            <span class="medal">4️⃣</span>
            <h4>김소정</h4>
            <span>15개</span>
          </div>
        </li>
        <li>
          <div class="div-user-item">
            <span class="medal">5️⃣</span>
            <h4>김소정</h4>
            <span>15개</span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.div-color{
  /* background-color:#fdf8763b; */
  width: 50%;
  text-align: center;
  padding: 5px;

}

.div-color h3{
  margin: 0px;
}
.container-user-rating-list{
  margin: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  border: 1px solid rgba(227, 227, 227, 0.363);
  background-color: rgba(237, 237, 237, 0.179);
  box-shadow: rgba(0, 0, 0, 0) 0px 1px 4px;
  padding: 10px;
  border-radius: 10px;
}


.div-decorate{
  margin-top: 10px;
  width: 80%;
  background-color:rgba(103, 36, 110, 0.21);
  height: 1px;
}
.container-user-rating-list-item{
  margin-top: 10px;
  width: 200px
}

.container-user-rating-list-item ul li{
  margin: 5px;
}

.div-user-item{
  display:flex;
  justify-content: space-around;
  align-content: center;
}

.medal{
  width: 30px;
}




</style>