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
    videoListTotal:0,
    coach:"",
    searchVideos:[],
    manageUserNickname:"",
    challengeVideo:[],
    curChallenge:{}
  },
  getters: {
    getChallengeVideo(state){
      return state.challengeVideo;
    }
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
      state.searchVideos = data.res;
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
      state.events=[];

      for(let obj of data){

        const event = {
          name: `${obj.dietName}(+${obj.dietCal}cal)`,
          start: (obj.createdAt).substr(0,10),
          color: `rgb(255, 109, 109)`,
          food: obj
        }
        
        state.events.push(event);
      }
    },
    GET_WORKOUT_LIST(state, data){
      for(let obj of data){

        const event = {
          name: `${obj.videoTitle}(-${obj.videoCal}cal)`,
          start: (obj.createdAt).substr(0,10),
          color: `rgb(106, 106, 207)`,
          workout: obj
        }
        
        state.events.push(event);
      }
    },
    GET_COACH(state, data){
      state.coach = data;
    },
    VIDEO_SELECT(state,data){
      state.challengeVideo.push(data);
      console.log(state.challengeVideo)
    },
    VIDEO_DELETE(state,data){
      // console.log(data);
      // console.log(state.challengeVideo);

      state.challengeVideo.splice(data,1);
      // console.log(state.challengeVideo);
    },
    GET_MY_CHALLENGES(state, data) {
      // console.log(data.res);
      state.myChallenges = data.res;
    },
    DETAIL_CHALLENGE(state,data){
      state.curChallenge = data;
    },
    CLEAR_CHALLENGE(state,data){
      state.curChallenge = "";
    }
    ,
    GET_VALID_CHALLENGES(state, data) {
      console.log(data.res);
      state.validChallenges = data.res;
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
          console.log(res.data.res);
          sessionStorage.setItem("nickname", res.data.res.userNickname);
          sessionStorage.setItem("authority", "u");
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
      sessionStorage.removeItem("nickname");
      sessionStorage.removeItem("authority");
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
        page: payload.page, 
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
        console.log(res);
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
    getWorkoutList({commit}){
      const API_URL=`http://localhost:331/user/workout`;

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
        commit("GET_WORKOUT_LIST",res.data.res);
      }).catch((err)=>{
        this.isLoading=false;
      })
    },
    coachLogin({commit}, payload){
      let coachDto = {
          coachId: payload.coachId,
          coachPassword: payload.coachPassword,
      }
      
      const API_URL = `${REST_API}/coach/login`
      axios({
        url: API_URL,
        method: 'POST',
        params: coachDto,
      })
      .then((res) => {
        if(!res.data.isSuccess){
          window.alert("로그인 실패");
        } else{
          sessionStorage.setItem("access-token", res.data["access-token"])
          sessionStorage.setItem("nickname", res.data.res.coachNickname);
          sessionStorage.setItem("authority", "c");
          window.location.href = "/";
        }
      }).catch((err)=>{
        console.log(err)
      })
    },
    getCoach({commit}){
      const API_URL = `${REST_API}coach/`
      axios({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
      .then((res) => {
        commit('GET_COACH', res.data.res);
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
    getSearchVideos({commit}, payload) {
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

    },
    getManageDietList({commit}, payload){
      let userSeq = payload;
      const API_URL = `${REST_API}coach/${userSeq}/diet`
      console.log(API_URL)
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
    getManageWorkoutList({commit}, payload){
      let userSeq = payload;
      const API_URL = `${REST_API}coach/${userSeq}/workout`
      console.log(API_URL)
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
        commit("GET_WORKOUT_LIST",res.data.res);
      }).catch((err)=>{
        console.log(err)
      })
    },
    createChallenge({commit}, data){
     
      const date = new Date();
      date.setDate(date.getDate() + data.duration); 
      const tmp = date.toLocaleDateString().replace(/(\s*)/g, "").split(".");
      const du = `${tmp[0]}-${tmp[1]}-${tmp[2]}`

      // console.log(this.getters.getChallengeVideo);

      let videoList = "";
      for(let video of this.getters.getChallengeVideo){
        videoList  = videoList + video.videoSeq + " ";
      }

      let isPublicBoolean;
      if(data.isPublic=="true"){
        isPublicBoolean = true;
      } else{
        isPublicBoolean = false;
      }

      const challengeDto = {
          duration: data.duration,
          endDate: du,
          isPublicStr: data.isPublic,
          videoList: videoList,
          challengeDescription: data.challengeDescription,
          challengeTitle: data.challengeTitle
      }

      console.log(challengeDto);
      // const challengeVideos = [];

      // for(let video of this.getters.getChallengeVideo){
      //   console.log(video);
      // }


      // console.log(challengeDto);

      const API_URL = `${REST_API}challenge`
      this.isLoading=true;
      axios({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params: challengeDto,
      })
      .then((res) => {
        this.isLoading=false;
        console.log(res)
        // commit("GET_WORKOUT_LIST",res.data.res);
        window.location.replace("/challenge");
      }).catch((err)=>{
        console.log(err)
      })
    },
    getMyChallenges({commit}, payload) {
      let param = {
        page: payload,
      }

      const API_URL = `${REST_API}challenge/my`
      axios({
        url: API_URL,
        method: 'GET',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params: param,
      }).then((res) => {
        commit('GET_MY_CHALLENGES', res.data);
      }).catch((err)=>{
        console.log(err)
      })
    },
    getVaildChallenges({commit}, payload) {
      let param = {
        page:payload,
      }
      console.log("여기")
      const API_URL = `${REST_API}challenge`
      axios({
        url: API_URL,
        method: 'GET',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params: param,
      }).then((res) => {
        commit('GET_VALID_CHALLENGES', res.data);
      }).catch((err)=>{
        console.log(err)
      })
    },
    getChallenge({commit},challengeSeq){
      const API_URL = `${REST_API}challenge/detail/${challengeSeq}`
      axios({
        url: API_URL,
        method: 'GET',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        // console.log(res);
        commit('DETAIL_CHALLENGE', res.data.res);
      }).catch((err)=>{
        console.log(err)
      })
    },
    updateExp({commit}){
      const API_URL = `${REST_API}user/activity`

      const param={
        key: "exp",
        value: 200
      }
      axios({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
        params:param
      }).then((res) => {
        // console.log(res);
        console.log(res);
      }).catch((err)=>{
        console.log(err)
      })
    },
    joinChallenge({commit}, payload) {
      let challengeSeq = payload
      const API_URL = `${REST_API}challenge/${challengeSeq}`
      axios({
        url: API_URL,
        method: 'POST',
        headers:{
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
      }).catch((err)=>{
        console.log(err)
      })
      
      window.location.href = `/challenge/${challengeSeq}`;

    }
  },
  modules: {
    FadeLoader
  }
})
