<template>
    <div class="container">
      <div class="inner">
        <div class="books">
          <Book
              v-for="book in bookList"
              :key="book.id"
              :book="book"
          >
          </Book>
        </div>
      </div>
    </div>
</template>
<script>
import {mapState} from 'vuex'
//import BookList from '../assets/data/json_sample_bookList.json'
import Book from './book'

export default {
  components:{
    Book
  },
  data(){
    return {
      search:'',
      //bookList:BookList
    }
  },
  computed:{
    ...mapState('book',[
        'bookList'
    ])
  }
}
</script>

<style lang="scss" scoped>
.container{
   margin-top: 30px;
   height: 680px;
   background-color: antiquewhite;
   overflow-y: auto;
  .inner{
    background-color: darkgray;
    padding: 10px 0;
    border: 4px;
    text-align: center;
  }
  .books{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style>