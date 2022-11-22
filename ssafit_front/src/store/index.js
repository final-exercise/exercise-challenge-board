import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

const REST_API = `http://localhost:331/`;

export default new Vuex.Store({
  state: {
    user:"",
  },
  getters: {
  },
  mutations: {
    GET_USER(state, data){
      state.user = data;
    }
  },
  actions: {
    userSignup({commit}, payload){
      let userDto = {
          userId: payload.userId,
          userPassword: payload.userPassword,
          userName: payload.userName,
          userEmail: payload.userEmail,
          userBirth: payload.userBirth,
          userNickname: payload.userNickname,
          userGender: payload.userGender,
          userType: payload.userType
      }
      const API_URL = `${REST_API}/user/signup`
      axios({
        url: API_URL,
        method: 'POST',
        params: userDto
      })
      .then((res) => {
        commit
        return(res.data);
      }).catch((err)=>{
        console.log(err)
      })
    },
    userLogin({commit}, payload){
      let userDto = {
          userId: payload.userId,
          userPassword: payload.userPassword,
      }
      
      const API_URL = `${REST_API}/user/login`
      axios({
        url: API_URL,
        method: 'POST',
        params: userDto,
      })
      .then((res) => {
        if(!res.data.isSuccess){
          window.alert("로그인 실패");
        } else{
          sessionStorage.setItem("access-token", res.data["access-token"])
          sessionStorage.setItem("userNickname", res.data.res.userNickname);
          window.location.href = "/";
        }
      }).catch((err)=>{
        console.log(err)
      })
    },
    getUser({commit}){
      const API_URL = `${REST_API}/user`
      axios({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
      .then((res) => {
        commit('GET_USER', res.data.res);
      }).catch((err)=>{
        console.log(err)
      })
    },
    logout({commit}){
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("userNickname");
      window.location.href = "/";
    },
  },
  modules: {
  }
})
