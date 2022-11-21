<template>
  <div class="modal-coach-login">
    <h1>코치 로그인</h1>
    <!-- <div class="form-user-login">
      <h1>회원 로그인</h1>
      <fieldset>
      <input class="input-id" type="text" placeholder="아이디 입력"/> <br>
      <input class="input-password" type="password" placeholder="비밀번호 입력" />
      <button>로그인</button>
      </fieldset>
    </div> -->
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.modal-coach-login{
  position: absolute;
  top: 0;
  left: 0;
  width: 50%;
  height: 50%;
  /* display: none; */
  background-color: rgba(0, 0, 0, 0.4);
}

.form-user-login{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-user-login h1{
  margin: 10px;
}

.form-user-login input{
  display: flex;
  justify-content: flex-end;
}

button{
  margin-top: 20px;
  width: 250px;
  background-color: #ADDDD0;
  color: white;
  font-weight: 500;
}

fieldset{
  padding: 30px;
  border: 1px solid rgba(125, 125, 125, 0.115);
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.09) 0px 1px 2px;
}

.input-id{
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
}

.input-password{
  background-color: #EEEEEE;
  width: 250px;
  height: 40px;
}
</style>