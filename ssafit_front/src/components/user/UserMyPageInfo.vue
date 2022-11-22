<template>
  <div class="container-mypage-info">
    <h1>나의 정보</h1>
    <div class="div-decorate" style="margin-left:0px"></div>
    <div clas="div-mypage-info">
      <div class="div-id">
          <label>아이디</label>
          <input type="text" class="input-id" placeholder="아이디 입력" ref="inputId" required/>
          <button class="button-check">✓</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{

    }
  },
  methods: {

  },
}
</script>

<style>
.container-mypage-info{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.container-mypage-info{
  margin: 20px;
}

.div-mypage-info{
  background-color: white;
  border-radius: 10px;
}


input {
  margin-top: 20px;
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
}

</style>