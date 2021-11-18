<template>
  <div class="container">
    <BookList/>
  </div>
</template>
<script>
import BookList from "../components/BookList";
export default {
  components:{
    BookList
  }
}
</script>
<style scoped>
.container{
  display: block;
}
</style>