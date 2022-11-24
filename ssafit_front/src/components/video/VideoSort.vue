<template>
  <div class="container-video-sort">
    <h1>{{this.sub}} {{this.super}}</h1>
    <div class="div-decorate">&nbsp;</div>
    <div class="div-sorted-videos">
      <div v-for="(video, index) in videos" :key="index">
        <video-list-item :video="video"></video-list-item>
      </div>
    </div>
    <v-pagination
      v-model="page"
      :length="Math.ceil(videoListTotal/10)"
      :color="`#81C784`"
      @input="inputPage"
    ></v-pagination>
  </div>
</template>

<script>
import VideoListItem from "./VideoListItem.vue"
import {mapState} from 'vuex'

export default {
  name: "VideoSort",
  components: {
    VideoListItem,
  },
  data(){
    return{
      super:"",
      sub:"",
      page:1,
    //   videos:[
    //   {
    //     videoSeq: 1,
    //     videoId: "g2ZeT9v7wt0",
    //     part: "total",
    //     channelName: "hello",
    //     videoTitle: "heihihihihihih"
    //   },{
    //     videoSeq: 1,
    //     videoId: "g2ZeT9v7wt0",
    //     part: "total",
    //     channelName: "hello",
    //     videoTitle: "heihihihihihih"
    //   },{
    //     videoSeq: 1,
    //     videoId: "g2ZeT9v7wt0",
    //     part: "total",
    //     channelName: "hello",
    //     videoTitle: "heihihihihihih"
    //   },{
    //     videoSeq: 1,
    //     videoId: "g2ZeT9v7wt0",
    //     part: "total",
    //     channelName: "hello",
    //     videoTitle: "heihihihihihih"
    //   }, {
    //     videoSeq: 1,
    //     videoId: "g2ZeT9v7wt0",
    //     part: "total",
    //     channelName: "hello",
    //     videoTitle: "heihihihihihih"
    //   }
    // ]
    }
  },
  methods: {
    inputPage(pageNum){
      this.page = pageNum
      this.$store.dispatch('getVideoList',{super: this.super,sub: this.sub, page:this.page})
      console.log(pageNum);
    }
  },
  computed: {
    ...mapState(['videos','videoListTotal'])
  },
  created(){
    const params = new URL(document.location).searchParams;
    this.super=params.get("superType");
    this.sub=params.get("subType");
    console.log(this.sub)
    if(this.sub == 'none') {
      this.$store.dispatch('getVideoList',{super: this.super,sub: "none"});
      this.sub = "";
    }
    else {
      this.$store.dispatch('getVideoList',{super: this.super,sub: this.sub, page:0});
    }
  }
}
</script>

<style scoped>

.container-video-sort{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.container-video-sort h1{
  margin: 30px 0px 20px 0px;
}

.div-sorted-videos{
  margin: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

</style>