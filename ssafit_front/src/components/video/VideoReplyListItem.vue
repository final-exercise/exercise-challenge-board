<template>
  <div class="container-reply">
    <div class="reply-icon">
      ↳
    </div>
    <div class="div-reply">
      <div class="reply-header">
        <div class="author">{{reply.nickname}}</div>
        <div class="createdAt">{{reply.commentCreatedAt}}</div>
      </div>
      <div class="reply-hr"> &nbsp; </div>
      <div class="content">{{reply.commentContent}}</div>
     
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'reply'
  ]
}
</script>

<style scoped>

.container-reply{
  display:flex;
  width: 100%;
 
}

.reply-icon{
   margin-right: 10px;
   width:20px;
}

.div-reply{
  width: calc(100% - 20px);
  display:flex;
  flex-direction: column;
}

.author{
  font-weight:500
}
.createdAt{
  font-size: 0.7rem;
}

.reply-header{
  display:flex;
  width:100%;
  justify-content: space-between;
}

.content{
  text-align:left;
}

.reply-hr{
  margin: 5px 0px;
  width:100%;
  height:1px;
  background-color:rgba(197, 197, 197, 0.491);
}
</style>