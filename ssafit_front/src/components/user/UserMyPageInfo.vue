<template>
  <div class="container-mypage-info">
    <h1>나의 정보</h1>
    <div class="div-decorate" style="margin-left:0px; margin-bottom: 10px; width: 10%"></div>
    <div class="div-mypage-info">
      <div class="div div-id">
        <label>아이디</label>
        <input class="input disabled" type="text" v-model="getUser.userId" disabled/>
      </div>
      <div class="div div-nickname">
        <label>닉네임</label>
        <input class="input" type="text" v-model="getUser.userNickname"/>
      </div>
      <div class="div div-name">
        <label>이름</label>
        <input class="input" type="text" v-model="getUser.userName"/>
      </div>
      <div class="div div-id">
        <label>생일</label>
        <input class="input" type="date" v-model="getUser.userBirth"/>
      </div>
      <div class="div div-id">
        <label>성별</label>
        <select ref="inputGender" v-model="userGender" placeholder="성별 선택" required>
          <option :value="{userGender: 'M'}">남성</option>
          <option :value="{userGender: 'F'}">여성</option>
          <option :value="{userGender: 'N'}">선택하지 않음</option>
        </select>
      </div>
      <button class="button-modify">수정하기</button>
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
  created(){
    this.$store.dispatch('getUser');
  },
  computed:{
    getUser(){
      return this.$store.state.user;
    }
  },
  filter:{
    getDate(value){
      return new Date().toISOString().slice(0, 7);
    }
  }
  
}
</script>

<style scoped>
.container-mypage-info{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.container-mypage-info{
  margin: 20px;
}

.input.disabled{
  background-color:rgb(231, 231, 231);
}

.div-mypage-info{
  margin-top: 10px;
  background-color: rgba(224, 224, 224, 0.295);
  padding: 20px;
  border-radius: 10px;
}

.div{
  display: flex;
  align-items:center;
  justify-content: center;
  margin-bottom: 15px;
}

select{
  appearance:auto;
  background-color: #ffffff;
  height: 40px;
  width: 300px;
  display: flex;
  justify-content: flex-end;
  border: 2px solid rgba(200, 200, 200, 0.334);
  border-radius: 10px;
  padding-left: 8px;
  color: grey;
}

.div label{
  width: 80px;
  text-align: left;
  font-weight: 600;
  font-size: 1.1rem;
}

.input{
  background-color: #ffffff;
  width: 300px;
  /* box-shadow: rgba(0, 0, 0, 0.15) 1px 1px 2px; */
  border: 2px solid rgba(200, 200, 200, 0.334);
  border-radius:10px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
}

.button-modify{
  margin:10px 0px;
  width: 100%;
  border-radius: 8px;
  font-weight: 500;
  font-size: 1.1rem;
  background-color:rgb(255, 162, 162);
}

</style>