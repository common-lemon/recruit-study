<template>
  <div class="book">
    <div class="info">
      <input type="hidden" class="form-control" id="bookId" v-model="book.id" placeholder="" readonly />
      <div class="">
        <label for="title">부서</label>
        <input type="text" class="form-control" id="bookNo" v-model="book.bookNo" placeholder="" readonly />
      </div>
      <div class="">
        <label for="title">부서</label>
        <input type="text" class="form-control" id="deptName" v-model="book.deptName" placeholder="" readonly />
      </div>
      <div class="">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" v-model="book.title" placeholder="" readonly />
      </div>
      <div class="">
        <label for="count">수량</label>
        <input type="text" class="form-control" id="count" v-model="book.count" placeholder="" readonly />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props:{
    book:{
      type: Object,
      default:()=>({})
    }
  }
}
</script>
<style lang="scss" scoped>
.book{
  width: 150px;
  height: 200px;
  margin: 5px;
  border-radius: 4px;
  background-color: grey;
  background-size: cover;
  overflow: hidden;
  position: relative;
  &:hover::after{
    position: absolute;
    top:0;
    bottom:0;
    left:0;
    right:0;
    border:6px solid ;
  }
  .info{
    background-color: rgba(black, .3);
    width: 100%;
    height: 100%;
    padding: 14px;
    font-size: 14px;
    text-align: left;
    position:absolute;
    left:0;
    bottom:0;
    backdrop-filter: blur(10px);
  }
}

</style>