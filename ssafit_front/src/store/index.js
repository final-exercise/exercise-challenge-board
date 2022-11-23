import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

const REST_API = `http://localhost:331/`;

export default new Vuex.Store({
  state: {
    user:"",
    videoList:[{
      videoSeq:1,
      videoTitle:"dudtkd1",
      videoId: "6j5CB0yeemg",
      part: "bottom",
      channelName:"hi",
      
    },],
    selectedVideoList:[],
    videos: [],
  },
  getters: {
  },
  mutations: {
    GET_USER(state, data){
      state.user = data;
    },
    GET_WISH_VIDEO(state,data){
      // state.videoList = data;
    },
    GET_VIDEO_LIST(state, data){
      state.videos = data;
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
    getVideoList({commit}, obj){
      //추가 필요 ->  super, sub 가지고 영상 가져오기
      //페이지 잡아서
      console.log(obj.super);
      console.log(obj.sub);
      let SearchCondition = {
        superType: obj.super,
        subType: obj.sub,
      }

      const API_URL = `${REST_API}/video`
      axios ({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
        params: SearchCondition,
      }).then((res)=>{
        commit('GET_VIDEO_LIST', res.data.res);
      }).catch((err)=>{
        console.log(err);
      })
      



    },
    getWishVideoList({commit}){
      //추가 필요-> 헤더에서 userseq 추출하고 wish 비디오 다 가져오기
      //페이지 잡아서
    },
  },
  modules: {
  }
})
