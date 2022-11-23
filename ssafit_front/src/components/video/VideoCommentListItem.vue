<template>
  <div class="container-comment-whole">
    <div class="container-comment">
      <div class="div-left">
        <!-- {{comment}} -->
        {{comment.nickname}}
        <h3>{{comment.commentContent}}</h3>
        <span class="createdD">작성: {{comment.commentCreatedAt}}</span>
      </div>
      <div class="div-right">
        <button class="button-reply" @click="showReply">답글 보기</button>
      </div>
    <!-- {{comment}} -->
    
    
    </div>
    <div class="div-reply-whole" v-if="replyShow">
      <hr style="width: 100%; margin:10px 0px;" >
      <div class="div-comment-write">
      <textarea id="reply-content" class="textarea-comment" v-model="commentContent" placeholder="답글을 달아보세요"></textarea>
      <div class="div-comment-block">
        <button class="button-write-reply" @click="getContent">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-reply" viewBox="0 0 16 16">
            <path d="M6.598 5.013a.144.144 0 0 1 .202.134V6.3a.5.5 0 0 0 .5.5c.667 0 2.013.005 3.3.822.984.624 1.99 1.76 2.595 3.876-1.02-.983-2.185-1.516-3.205-1.799a8.74 8.74 0 0 0-1.921-.306 7.404 7.404 0 0 0-.798.008h-.013l-.005.001h-.001L7.3 9.9l-.05-.498a.5.5 0 0 0-.45.498v1.153c0 .108-.11.176-.202.134L2.614 8.254a.503.503 0 0 0-.042-.028.147.147 0 0 1 0-.252.499.499 0 0 0 .042-.028l3.984-2.933zM7.8 10.386c.068 0 .143.003.223.006.434.02 1.034.086 1.7.271 1.326.368 2.896 1.202 3.94 3.08a.5.5 0 0 0 .933-.305c-.464-3.71-1.886-5.662-3.46-6.66-1.245-.79-2.527-.942-3.336-.971v-.66a1.144 1.144 0 0 0-1.767-.96l-3.994 2.94a1.147 1.147 0 0 0 0 1.946l3.994 2.94a1.144 1.144 0 0 0 1.767-.96v-.667z"/>
          </svg>

        </button>
      </div>
          
      </div>  
      <!-- <hr style="width: 100%; margin:10px 0px;" v-if="commentCnt!=0"> -->
      <div class="div-reply" v-for="(reply, index) in comment.replys" :key="index">
        <video-reply-list-item-vue :reply="reply"></video-reply-list-item-vue>
      </div>  
    </div>
  </div>
</template>

<script>
import VideoReplyListItemVue from './VideoReplyListItem.vue';
export default {
  components: {
    VideoReplyListItemVue,
  },
  props: [
    'comment'
  ],
  data(){
    return{
      commentCnt: this.comment.replys.length,
      replyShow: false,
      replyContent: "",
      commentSeq: this.comment.commentSeq,
      videoSeq: this.comment.videoSeq,
    }
  },
  computed: {

  },
  filters:{
    dateFilter(value){
      const dates = value.split(" ")[0].split("-")
      return `${dates[0]}년 ${dates[1]}월 ${dates[2]}일`
    }
  },
  methods:{
    showReply(){
      this.replyShow = !this.replyShow;
    },
    getContent() {
      this.replyContent = document.getElementById("reply-content").value;
      console.log(this.commentSeq);
      this.$store.dispatch('registComment', {videoSeq: this.videoSeq, commentContent: this.replyContent, bundleId: this.commentSeq});
    }
  }
}

</script>

<style>
.container-comment-whole{
  display:flex;
  flex-direction: column;
}

.container-comment{
  width: 100%;
  display:flex;
  justify-content: space-between;
  align-items: flex-end;
  
}

.button-reply{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(213, 213, 213);
  border-radius: 30px;
}

.div-left{
  /* background-color: rgb(238, 238, 238); */
  /* border-left: 2px solid rgb(223, 225, 255); */
  margin: 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 10px;
}
.createdD{
  font-size: 0.8rem;
  color: rgb(165, 165, 165);
}

.div-reply{
  margin-top:10px;
}

.div-comment-write{
  padding:20px;
  display:flex;
  flex-direction:column;
  align-items: flex-start;
  width: 100%;
  margin-bottom:10px;
  border: 1px solid rgb(227, 227, 227);
  background-color: rgb(255, 255, 255);
}
.textarea-comment{
  width: 100%;
  background-color: white;
  height: 2.25em;
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

.button-write-reply{
    background-color:rgb(254, 254, 254);
  border: 1px solid rgb(172, 172, 172);
  color: rgb(136, 136, 136);
  border-radius:20px;
  font-weight:400
  
}


.div-reply-whole{
  /* display:none; */
}

</style>