<template>
  <div class="container-search">
    <h2>운동 영상 검색하기</h2>
    <div class="div-decorate">&nbsp;</div>
    <div class="search-bar">
    <input class="input-video-search" type="text" placeholder="검색어를 입력하세요" />
    <button @click="searchActive"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots"
        viewBox="0 0 16 16">
        <path
          d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
      </svg></button>
    <button class="button-search" @click="searchVideos"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
        class="bi bi-search" viewBox="0 0 16 16">
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
      </svg></button>
    </div>
    <div class="div-search" :class="{'searchActive':isActive}">
      <select v-model="key" required>
          <option disalbed value="">검색 기준</option>
          <option value="video_title">제목</option>
          <option value="video_channel_name">채널 이름</option>
      </select>
      <input v-model="word" required placeholder="검색어" class="input-key"/>
      <select v-model="sort" required>
          <option value="" selected disabled hidden >정렬 기준</option>
          <option value="video_title" default>제목순</option>
          <option value="duration">길이순</option>
          <option value="view_cnt">조회수순</option>
          <option value="video_Cal">칼로리순</option>
      </select>
      <select v-model="sortDir" required>
          <option value="" selected disabled hidden >정렬 기준</option>
          <option value="ASC" default>오름차순</option>
          <option value="DESC">내림차순</option>
      </select>
    </div>

    <div class="div-sorted-videos">
      <div v-for="(video, index) in videos" :key="index">
        <search-list-item :video="video"></search-list-item>
      </div>
    </div>

  </div>
</template>

<script>
import SearchListItem from "./VideoSearchListItem.vue"
import {mapState} from 'vuex'

export default {
  name: "VideoSearch",
  components: {
    SearchListItem, // 이게 아까는 VideoListItem이었는데 그럼 아예 같이 돌아가고, 이렇ㄱㅔ하면 왜 검색한게 위에 안떠
  },

  data(){
    return{
      key:"",
      word:"",
      sort:"",
      sortDir:"",
      //key: 검색 기준
      //word: 단어
      //sort: 정렬 기준
      isActive: true
    }
  }, 
  methods:{
    selectType(){
      return 1;
    },
    searchActive(){
      this.isActive = !this.isActive
    },
    searchVideos() {
      console.log(this.word)
      this.$store.dispatch('searchVideos', {key: this.key, word: this.word, sort: this.sort, sortDir: this.sortDir})
    }
  },
  computed: {
    ...mapState(['videos', 'videoListTotal'])
  },
}
</script>

<style scoped>

.div-decorate{
  height: 3px;
  width: 50px;
  background-color: rgb(235, 229, 246);
  margin: 10px;
}
.container-search {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 20px 0px;
}
.input-key{
  border: 1px solid rgba(0, 0, 0, 0.195);
}
.container-search h2{
  margin: 10px;
}

.search-bar{
  margin: 10px;
  display: flex;
  align-items: center;
}

.input-video-search {
  height: 40px;
  width: 300px;
  background-color: rgb(250, 250, 250);
  border: 1px solid rgba(0, 0, 0, 0.206);
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px;
  border-radius: 20px;
  margin: 0px;
}

button {
  margin-left: 10px;
  height: 40px;
  border: 1px solid rgba(0, 0, 0, 0.017);
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px;
  border-radius: 10px;
}

div.searchActive{
  display: none;
}

select{
  appearance:auto;
  margin-top: 20px;
  background-color: #ffffff;
  width: 100px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
  border-radius: 8px;
  padding-left: 8px;
  color: grey;
  margin-left: 10px;
  border: 1px solid rgba(0, 0, 0, 0.195);
}

input{
  appearance:auto;
  margin-top: 20px;
  background-color: #ffffff;
  width: 200px;
  height: 40px;
  display: flex;
  justify-content: flex-end;
  border-radius: 8px;
  padding-left: 8px;
  color: grey;
  margin-left: 10px;
}

.div-search{
  display:flex;
}

</style>