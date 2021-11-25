<template>
    <div class="container">
        <div class="inner">
            <div
                v-if="message"
                class="message">
                {{message}}
            </div>
            <div
                v-else
                class="books">
                <Book
                    v-for="book in paginatedData"
                    :key="book.id"
                    :book="book"
                >
                </Book>
            </div>
        </div>
        <div class="btn-cover">
            <button
                :disabled="pageNum === 0"
                @click="prevPage"
                class="page-btn"
            >
                이전
            </button>
            <span class="page-count"> {{ pageNum + 1 }} / {{ pageCount }} 페이지 </span>
            <button
                :disabled="pageNum >= pageCount - 1"
                @click="nextPage"
                class="page-btn"
            >
                다음
            </button>
        </div>
    </div>
</template>
<script>
import {mapState} from 'vuex'
//import BookList from '../assets/data/json_sample_bookList.json'
//import Loader from '@/components/Loader'
import Book from './book'

export default {
    components:{
        Book
    },
    data(){
        return {
            pageNum: 0
        }
    },
    methods: {
        nextPage () {
            this.pageNum += 1;
        },
        prevPage () {
            this.pageNum -= 1;
        }
    },
    computed:{
        ...mapState('book',[
            'bookList',
            'message',
            'loading',
            'sort',
            'pageSize'
        ]),
        pageCount () {
            let listLeng = this.bookList.length,
                listSize = this.pageSize,
                page = Math.floor(listLeng/listSize);

            if (listLeng % listSize > 0) page += 1;

            if (page === 0) page += 1;

            return page
        },
        paginatedData () {
          const start = this.pageNum * this.pageSize,
              end = start + this.pageSize;
          return this.bookList.slice(start, end);
        }
    }
}
</script>

<style lang="scss" scoped>
/*common*/
button {
    margin-top: 10px;
    margin-bottom: 10px;
    border: none;
    background: #134775;
    color: #f4f2db;
    font-size: 0.9rem;
    //padding: 6px 20px;
    width: 80px;
    height: 25px;
    font-weight: bold;
    border-radius: 30px;
    cursor: pointer;
}
.container{
     //margin-top: 10px;
     height: 700px;
     background-color: #e3e9ef;
    .btn-div{
      text-align: right;
    }
    .inner{
        background-color: #e3e9ef;
        padding: 10px 0;
        border: 4px;
        border-radius: 10px;
        text-align: center;
        .message{
            line-height: 300px;
            height: 600px;
            color: #134775;
        }
    }
    .books{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        height: 600px;
        overflow-y: auto;
    }
    .btn-cover{
        text-align: center;
        margin-top: 15px;
        .page-count{
            font-size: 12px;
            font-weight: 500;
        }
        .page-btn{
            width: 45px;
            font-size: 12px;
        }
    }
}
</style>