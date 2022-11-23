<template>
  <div class="container-video-detail">
    <h1>{{video.videoTitle}}</h1>
    <div class="div-decorate">&nbsp;</div>
    <div>
      <iframe :src="`https://www.youtube.com/embed/`+video.videoId"></iframe>
    </div>
    <div class="div-decorate">&nbsp;</div>
    <div>
      <div v-for="(comment, index) in comments" :key="index">
        <comment-list-item :comment="comment"></comment-list-item>
      </div>

    </div>
    
  </div>
</template>

<script>
import CommentListItem from './VideoCommentListItem.vue';
import { mapState } from 'vuex';

export default {
  name: "VideoDetail",
  components: {
    CommentListItem,
  },
  data() {
    return {
 
    }
  },
  computed: {
    ...mapState(['video', 'comments', 'videoIsWish'])
  },
  created() {
    const PathName = new URL(document.location).pathname.split("/");
    const videoSeq = PathName[PathName.length - 1];
    this.$store.dispatch('getVideo', videoSeq);
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
  margin: 30px 0px 20px 0px;
}
</style>