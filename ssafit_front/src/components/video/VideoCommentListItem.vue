<template>
  <div>
    <h6>{{comment.commentContent}}</h6>
    <div v-for="(reply, index) in comment.replys" :key="index">
      <video-reply-list-item-vue :reply="reply"></video-reply-list-item-vue>
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
  computed: {

  },
}
</script>

<style>

</style>