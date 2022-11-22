<template>
  <div class="container-signup">
    <h1> 회원가입 </h1>
    <div class="div-decorate" style="width: 10%"></div>
    <div class="div-signup-level1">
      <h2>아이디와 비밀번호를 입력해주세요!</h2>
      <fieldset>
        <div class="div-id">
          <input type="text" class="input-id" v-model="user.userId" placeholder="아이디 입력" ref="inputId" required/>
          <button class="button-check"  @click="checkDuplicatedUserId">✓</button>
        </div>
        <input type="password" placeholder="비밀번호 입력" ref="inputPassword" v-model="user.userPassword" required/>
        <input type="password" placeholder="비밀번호 확인" v-model="user.userPasswordCheck" ref="inputPasswordCheck" required/>
        <button @click="completedLevel1">다음 단계로</button>
      </fieldset>
    </div>
    <div class="div-decorate" style="width: 10%" :class="{isVisible:isCompletedLevel1}"></div>
    <div class="div-signup-level2" :class="{isVisible:isCompletedLevel1}">
      <h2>상세 정보를 입력해주세요!</h2>
      <fieldset>
        <input type="text" placeholder="이름 입력" v-model="user.userName" ref="inputName" required/>
        <input type="email" placeholder="이메일 입력" v-model="user.userEmail" ref="inputEmail" required/>
        <input type="date" placeholder="생년월일 입력" style=" color: grey;" v-model="user.userBirth" ref="inputBirth" required/>
        <div class="div-id" >
          <input type="text" class="input-id" placeholder="닉네임 입력" v-model="user.userNickname" ref="inputNickname" required/>
          <button class="button-check" @click="checkDuplicatedUserNickname">✓</button>
        </div>
        <select ref="inputGender" v-model="user" required>
          <option value="" selected disabled hidden >성별 선택</option>
          <option :value="{userGender: 'M'}" default>남성</option>
          <option :value="{userGender: 'F'}">여성</option>
          <option :value="{userGender: 'N'}">선택하지 않음</option>
        </select>
        <button @click="completedLevel2">다음 단계로</button>
      </fieldset>
    </div>

    <div class="div-decorate" style="width: 10%" :class="{isVisible:isCompletedLevel2}"></div>
    <div class="div-signup-level2" :class="{isVisible:isCompletedLevel2}">
      <h2 style="margin-bottom: 5px;">운동 목적을 선택해주세요!</h2>
      <span>코치를 매칭해드려요</span>
      <fieldset style="margin-top: 20px">
        <button class="button-super" @click="selectType">스트레칭</button>
        <button class="button-super" @click="selectType">근력강화</button>
        <button class="button-super" @click="selectType">체중감량</button>
        <button class="button-super" @click="selectType">교정</button>
        <button class="button-super" @click="selectType">스포츠</button>
        <button @click="signup">완료하기</button>
      </fieldset>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data(){
    return{
      isCompletedLevel1: true,
      isCompletedLevel2: true,
      isDuplicatedUserId: true,
      isDuplicatedUserNickname: true,
      user:{
        userId:"",
        userPassword:"",
        userPasswordCheck:"",
        userName:"",
        userEmail:"",
        userBirth:"",
        userNickname:"",
        userGender:"",
        userType:""
      }
    }
  },
  methods:{
    completedLevel1(){
      if(this.user.userPassword!=this.user.userPasswordCheck){
        window.alert("비밀번호가 다릅니다.");
        this.$refs.inputPasswordCheck.focus();
        return;
      }

      if(this.isDuplicatedUserId){
        window.alert("아이디 중복 확인을 수행해주세요.");
        this.$refs.inputId.focus();
        return;
      }

      this.isCompletedLevel1 = false;
    },
    completedLevel2(){
      if(this.isDuplicatedUserNickname){
        window.alert("닉네임 중복 확인을 수행해주세요.");
        this.$refs.inputNickname.focus();
        return;
      }
      this.isCompletedLevel2 = false;
    },
    checkDuplicatedUserId(){
        let params = {
          value:this.user.userId
        }
        axios({
          url: `http://localhost:331/user/userId`,
          method: 'GET',
          params: params
        })
        .then((res) => {
          if(res.data.res){
            window.alert("중복된 아이디입니다.");
            this.isDuplicatedUserId = true;
            this.$refs.inputId.focus();
          }else{
            window.alert("사용 가능한 아이디입니다.");
            this.isDuplicatedUserId = false;
            this.$refs.inputPassword.focus();
          }
          
        }).catch((err)=>{
          console.log(err)
        })
    },
    checkDuplicatedUserNickname(){
        let params = {
          value:this.user.userNickname
        }
        axios({
          url: `http://localhost:331/user/nickname`,
          method: 'GET',
          params: params
        })
        .then((res) => {
          if(res.data.res){
            window.alert("중복된 닉네임입니다.");
            this.isDuplicatedUserNickname = true;
            this.$refs.inputNickname.focus();
          }else{
            window.alert("사용 가능한 닉네임입니다.");
            this.isDuplicatedUserNickname = false;
            this.$refs.inputGender.focus();
          }
          
        }).catch((err)=>{
          console.log(err)
        })
    },
    selectType(event){
      if(this.user.userType!=""){
        let v = document.querySelector('.button-active');
        v.classList.remove('button-active');
      } 
      this.user.userType = event.target.innerHTML;
      event.target.classList.add('button-active');
    },
    signup(){
      this.$store.dispatch('userSignup',this.user);
      location.href=("/");
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
  border-radius: 8px;
  margin-top: 20px;
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
}

.button-super.button-active{
  background-color:rgb(255, 210, 210);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.06) 0px 2px 4px -1px inset;
}
</style>