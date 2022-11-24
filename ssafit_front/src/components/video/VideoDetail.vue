<template>
  <div class="container-video-detail">
    <div class="div-type-button">
      <button class="button-superPart" :class="video.superType">{{video.superType}}</button>
      <button class="button-part" :class="video.subType">{{video.subType}}</button>
    </div>
    <h1>{{video.videoTitle}}</h1>
    <div class="div-video-info">
      <div class="div-video-info-comments">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-chat-left-text" viewBox="0 0 16 16">
          <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
          <path d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6zm0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
        </svg>
        <span>{{video.videoCommentCnt}}</span>
      </div>
      <div class="div-video-info-hearts-false" v-if="videoIsWish==0" @click="changeLike">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
          <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
        </svg>
        <span>{{video.videoWishCnt}}</span>
      </div>

      <div class="div-video-info-hearts-true" v-if="videoIsWish==1" @click="changeLike">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
        </svg>
        <span>{{video.videoWishCnt}}</span>
      </div>
      <div class="div-video-info-view">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
          <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
          <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
        </svg>
        <span>{{video.videoViewCnt}}</span>
      </div>
    </div>
    <div class="div-decorate" style="width: 30%; margin:20px 0px;">&nbsp;</div>
    <div class="div-video">
      <iframe class="iframe-video" :src="`https://www.youtube.com/embed/`+video.videoId"></iframe>
    </div>
    <div class="div-decorate" style="width: 30%; margin:20px 0px;">&nbsp;</div>
    <div class="div-comment-write">
      <div class="div-comment-header">
        <h3>{{userNickname}}</h3>
        <span>{{commentContent|stringLength}}/100자</span>
      </div>
      <hr style="width:100%; margin-top:10px">
      <textarea id="comment-content" class="textarea-comment" v-model="commentContent" placeholder="댓글을 남겨보세요"></textarea>
        
      <div class="div-comment-block">
        <button class="button-write" @click="getContent">등록하기</button>
      </div>

    </div>
    <div class="div-comments">
      <div v-for="(comment, index) in comments" :key="index">
        <comment-list-item :comment="comment"></comment-list-item>
        <hr class="hr-reply">
      </div>
    </div>
    
  </div>
</template>

<script>
import CommentListItem from './VideoCommentListItem.vue';
import { mapState } from 'vuex';
import axios from 'axios';

export default {
  name: "VideoDetail",
  components: {
    CommentListItem
  },
  data() {
    return {
      videoSeq: 0,
      userNickname:sessionStorage.getItem("nickname"),
      commentContent:"",
      
    }
  },
  computed: {
    ...mapState(['video', 'comments', 'videoIsWish']),
    
  },
  created() {
    const PathName = new URL(document.location).pathname.split("/");
    this.videoSeq = PathName[PathName.length - 1];

    this.$store.dispatch('getVideo', this.videoSeq);
    // console.log(this.video);

    // this.userNickname=sessionStorage.getItem("userNickname");

    // const API_URL = `http://localhost:331/video/${this.videoSeq}/mylist`

    // axios({
    //   url: API_URL,
    //   method: 'GET',
    //   headers: {
    //     "access-token": sessionStorage.getItem("access-token")
    //   }
    // })
    // .then((res) => {
    //   if(res.data.res==0){
    //     this.isLike = false;
        
    //   }else{
    //     this.isLike = true;
    //   }
    // }).catch((err)=>{
    //   console.log(err)
    // })
  },
  methods: {
    changeLike(){
      if(this.videoIsWish==1){
        this.$store.dispatch('deleteUserWish',this.videoSeq)
        this.$store.state.videoIsWish=0
        this.$store.state.video.videoWishCnt--;
      } else{
        this.$store.dispatch('registUserWish',this.videoSeq)
        this.$store.state.videoIsWish=1
        this.$store.state.video.videoWishCnt++;
      }
    },
    getContent() {
      this.commentContent = document.getElementById("comment-content").value;
      
      this.$store.dispatch('registComment', {videoSeq: this.videoSeq, commentContent: this.commentContent, bundleId: 0});
    }
  },
  filters:{
    stringLength(val){
      return val.length;
    }
  }

}
</script>

<style scoped>
.container-video-detail{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.container-video-detail h1{
  margin: 10px 0px 10px 0px;
}

.div-video-info{
  display: flex;
  align-items: center;
  padding-left: 30px
}

.div-video-info div{
  display: flex;
  align-items: center;
}
.div-video-title{
  display:flex;
  justify-content: center;
}

.div-video-info span{
  margin: 5px 30px 5px 5px;
}



.button-part {
  border-radius: 18px;
  padding: 0px 5px;
  width: 80px;
  color: white;
  font-weight: 700;
  font-size: 1rem;
  height: 30px;
  margin-bottom: 5px;
  margin-right: 5px;
}

.div-video-info-hearts-true{
  color: rgb(255, 89, 117);
}

.button-part.전신{
  background-color: rgb(255, 117, 117);
}
.button-part.상체{
  background-color: rgb(123, 217, 80);
}
.button-part.하체{
  background-color: rgb(117, 122, 255);
}
.button-part.복부{
  background-color: rgb(255, 220, 92);
}

.button-superPart {
  border-radius: 18px;
  padding: 0px 5px;
  width: 80px;
  color: white;
  font-weight: 700;
  font-size: 1rem;
  height: 30px;
  margin-bottom: 5px;
  margin-right: 5px;
}
.button-superPart.체중감량{
  background-color: rgb(255, 170, 117);
}
.button-superPart.스트레칭{
  background-color: rgb(89, 80, 217);
}
.button-superPart.근력강화{
  background-color: rgb(161, 255, 117);
}
.button-superPart.체형교정{
  background-color: rgb(255, 92, 92);
}
.button-superPart.스포츠{
  background-color: rgb(255, 92, 250);
}

.div-video{
  width: 70vh;
  height: 40vh;
}

.iframe-video{
  width:100%;
  height: 100%;
}

.div-type-button{
  margin-top: 30px;
}
.div-comment-write{
  padding:20px;
  display:flex;
  flex-direction:column;
  align-items: flex-start;
  width: 70%;
  border-radius: 10px;
  margin-bottom:10px;
  border: 1px solid rgb(227, 227, 227);
  background-color: rgb(255, 255, 255);
}
.textarea-comment{
  margin-top:10px;
  width: 100%;
  background-color: white;
  padding: 10px;
  height: 6.25em;
  border: none;
  resize: none;
}
.textarea-comment:focus{
  border: none;
  outline:none;
}

.div-comment-block{
  width: 100%;
  display: flex;
  justify-content: flex-end;
  align-items:flex-end;
}
.div-comments{
  background-color: rgba(248, 248, 248, 0.637);
  width: 70%;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 30px;

}

.button-write{
  background-color:rgb(255, 155, 155);
  width: 15%;
  min-width: 80px;
  color: white;
  
}

.div-comment-header{
  width:100%;
  display: flex;
  justify-content: space-between;
}

.hr-reply{
  margin-top:20px;
  background-color: rgb(227, 227, 227);
}

.hr-reply{
  margin-top:20px;
  background-color: rgb(227, 227, 227);
}
</style>