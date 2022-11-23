import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import FadeLoader from 'vue-spinner/src/FadeLoader.vue'

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
    video:"",
    comments: [],
    videoIsWish: "",
    events:[],
    isLoading:false,
    videoListTotal:0
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
    GET_RATING_VIDEOS(state, data) {
      state.videos = data.res;
    },
    GET_SEARCH_VIDEO_LIST(state, data) {
      state.videoListTotal = data.total;
      state.videos = data.res;
    },
    GET_VIDEO_LIST(state, data){
      state.videoListTotal = data.total;
      state.videos = data.res;
    },
    GET_VIDEO(state, data) {
      console.log(data)
      state.video = data.videodto;
      state.comments = data.comments;
      state.videoIsWish = data.videoIsWish;
    },
    PAGE_LOAD(state,data){
      state.isLoading = data;
    },
    GET_DIET_LIST(state, data){
      for(let obj of data){

        const event = {
          name: `${obj.dietName}(+${obj.dietCal}cal)`,
          start: (obj.createdAt).substr(0,10),
          color: "indigo",
          food: obj
        }

        state.events.push(event);
      }
    },
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
          userType: payload.userType,
          userHeight: payload.userHeight
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
    getRatingVideos({commit}, payload) {
      let SearchCondition = {
        sort: payload.sort,
        limit: payload.limit
      }

      const API_URL = `${REST_API}/video`
      axios ({
        url :API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
        params: SearchCondition,

      }).then((res) => {
        commit('GET_RATING_VIDEOS', res.data);
      }).catch((err) => {
        console.log(err);
      })
    },
    getVideoList({commit}, payload){
      //추가 필요 ->  super, sub 가지고 영상 가져오기
      //페이지 잡아서
      let SearchCondition = {
        superType: payload.super,
        subType: payload.sub,
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
        commit('GET_VIDEO_LIST', res.data);
      }).catch((err)=>{
        console.log(err);
      })
    },
    getVideo({commit}, payload) {
      console.log(payload);
      let videoSeq = payload
      const API_URL = `${REST_API}video/${videoSeq}`
      console.log(API_URL);
      axios ( {
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        console.log(res);
        commit('GET_VIDEO', res.data.res);
      }).catch((err) => {
        console.log(err);
      })
    }, 
    
    getDietList({commit}){
      const API_URL=`http://localhost:331/user/diet`;

      this.isLoading=true;
      axios({
        url: API_URL,
        method: 'GET',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
      })
      .then((res) => {
        this.isLoading=false;
        commit("GET_DIET_LIST",res.data.res);
      }).catch((err)=>{
        console.log(err)
      })
    },
    registComment({commit}, payload) {
      let videoSeq = payload.videoSeq;
      let commentDto = {
        commentContent: payload.commentContent,
        bundleId: payload.bundleId,
      }
      console.log(commentDto.bundleId)
      const API_URL = `${REST_API}video/${videoSeq}`

      axios ({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params: commentDto,

      }).then((res) => {

      }).catch((err) => {
        console.log(err)
      })
      window.location.replace(document.location);
    },
    registUserWish({commit}, payload) {
      console.log("등록")
      console.log(payload);
      let videoSeq = payload;

      const API_URL = `${REST_API}video/mylist/${videoSeq}`

      axios ({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
      }
      ).then((res) => {

      }).catch((err) => {
        console.log(err)
      })
    },
    deleteUserWish({commit}, payload) {
      console.log("삭제");
      console.log(payload)

      let videoSeq = payload;

      const API_URL = `${REST_API}video/mylist/${videoSeq}`

      axios ({
        url: API_URL,
        method: 'DELETE',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
      }
      ).then((res) => {
        

      }).catch((err) => {
        console.log(err)
      })
    },
    searchVideos({commit}, payload) {
      let SearchCondition = {
        key: payload.key,
        word: payload.word,
        sort: payload.sort,
        sortDir: payload.sortDir,
      }

      const API_URL = `${REST_API}video`

      axios ({
        url: API_URL,
        method: 'GET',
        params: SearchCondition,
      }).then((res) => {
        commit('GET_SEARCH_VIDEO_LIST', res.data);
      }).catch((err) => {
        console.log(err)
      })

    }
   },
  modules: {
    FadeLoader
  }
}
)
