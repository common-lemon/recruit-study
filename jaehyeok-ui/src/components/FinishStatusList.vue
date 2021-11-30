<template>
    <div>
        <BookAdmin/>
        <div class="container2">
            <div class="inner">
                <div
                    class="books">
                    <Book
                        v-for="book in paginatedData"
                        :key="book.id"
                        :book="book"
                        statusName="완료"
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
    </div>
</template>

<script>
import {mapState} from 'vuex'
import BookAdmin from './BookAdmin';
import Book from './book';
export default {
    components:{
        BookAdmin,
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
            'pageSize',
            'finishList'
        ]),
        pageCount () {
            let listLeng = this.finishList.length,
                listSize = this.pageSize,
                page = Math.floor(listLeng/listSize);

            if (listLeng % listSize > 0) page += 1;

            if (page === 0) page += 1;

            return page
        },
        paginatedData () {
            const start = this.pageNum * this.pageSize,
                end = start + this.pageSize;
            return this.finishList.slice(start, end);
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
.container2{
    //margin-top: 10px;
    height: 600px;
    background-color: aliceblue;
    display: flex;
    align-items: center;
    flex-direction: column;
    .inner{
        background-color: #e3e9ef;
        margin-top: 10px;
        width: 85%;
        padding: 10px 0;
        border: 4px;
        border-radius: 10px;
        text-align: center;
    }
    .books{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        height: 500px;
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