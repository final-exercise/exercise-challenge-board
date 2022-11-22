<template>
  <div class="container-signup">
    <h1> 회원가입 </h1>
    <div class="div-decorate" style="width: 10%"></div>
    <div class="div-signup-level1">
      <h2>아이디와 비밀번호를 입력해주세요!</h2>
      <fieldset>
        <div class="div-id">
          <input type="text" class="input-id" placeholder="아이디 입력" />
          <button class="button-check">✓</button>
        </div>
        <input type="password" placeholder="비밀번호 입력" />
        <input type="password" placeholder="비밀번호 확인" />
        <button @click="completedLevel1">다음 단계로</button>
      </fieldset>
    </div>
    <div class="div-decorate" style="width: 10%" :class="{isVisible:isCompletedLevel1}"></div>
    <div class="div-signup-level2" :class="{isVisible:isCompletedLevel1}">
      <h2>상세 정보를 입력해주세요!</h2>
      <fieldset>
        <input type="text" placeholder="이름 입력" />
        <input type="email" placeholder="이메일 입력" />
        <input type="date" placeholder="생년월일 입력" style=" color: grey;"/>
        <div class="div-id">
          <input type="text" class="input-id" placeholder="닉네임 입력" />
          <button class="button-check">✓</button>
        </div>
        <select>
          <option>남성</option>
          <option>여성</option>
          <option>선택하지 않음</option>
        </select>
        
        <input type="text" placeholder="아이디 입력" />
        <button @click="completedLevel2">다음 단계로</button>
      </fieldset>
    </div>

    <div class="div-decorate" style="width: 10%" :class="{isVisible:isCompletedLevel2}"></div>
    <div class="div-signup-level2" :class="{isVisible:isCompletedLevel2}">
      <h2 style="margin-bottom: 5px;">운동 목적을 선택해주세요!</h2>
      <span>코치를 매칭해드려요</span>
      <fieldset style="margin-top: 20px">
        <button class="button-super">스트레칭</button>
        <button class="button-super">근력강화</button>
        <button class="button-super">체중감량</button>
        <button class="button-super">교정</button>
        <button class="button-super">스포츠</button>
        <button>완료하기</button>
      </fieldset>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      isCompletedLevel1: true,
      isCompletedLevel2: true,
    }
  },
  methods:{
    completedLevel1(){
      this.isCompletedLevel1 = false;
    },
    completedLevel2(){
      this.isCompletedLevel2 = false;
    }
  }
}
</script>

<style scoped>
h1 {
  margin: 30px 0px 10px 0px;
}

h2 {
  margin: 20px;
}

.div-id{
  display: flex;
  width: 250px;
  align-items: center;
}

.container-signup {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.isVisible{
  display: none;
}

.button-super{
  background-color: rgba(223, 223, 223, 0.635);
  color: black;
  border-radius: 20px;
  border: 2px solid rgba(0, 0, 0, 0.036);
}

.button-check{
  background-color: #ffe9e9;
  color: rgb(101, 101, 101);
  border: 2px solid rgb(246, 173, 173);
  margin-left: 10px;
  width: 40px;
  font-weight: 1000;
}

button {
  margin-top: 20px;
  width: 250px;
  background-color: #a37ad2;
  color: white;
  font-weight: 500;
}

.input-id{
  width: 200px;
}


fieldset {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 20px;
}

select{
  appearance:auto;
  margin-top: 20px;
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
  border-radius: 8px;
  padding-left: 8px;
  color: grey;
}

fieldset {
  margin-top: 10px;
  padding: 0px 30px 20px 30px;
  border: 1px solid rgba(125, 125, 125, 0.115);
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.09) 0px 1px 2px;
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