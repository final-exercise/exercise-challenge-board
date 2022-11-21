<template>
  <div class="container-search">
    <h2>운동 영상 검색하기</h2>
    <div class="div-decorate">&nbsp;</div>
    <div class="search-bar">
    <input class="input-video-search" type="text" placeholder="검색어를 입력하세요" />
    <button><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots"
        viewBox="0 0 16 16">
        <path
          d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
      </svg></button>
    <button class="button-search"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
        class="bi bi-search" viewBox="0 0 16 16">
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
      </svg></button>
    </div>
  </div>
</template>

<script>
export default {

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

.container-search h2{
  margin: 10px;
}

.search-bar{
  margin: 10px
}
.input-video-search {
  height: 40px;
  width: 300px;
  background-color: rgb(250, 250, 250);
  border: 1px solid rgba(0, 0, 0, 0.206);
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px;
  border-radius: 20px;
}

button {
  margin-left: 10px;
  height: 40px;
  border: 1px solid rgba(0, 0, 0, 0.017);
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 5px;
  border-radius: 10px;
}
</style>