<template>

    <div class="book">
        <div class="info">
            <input type="hidden" class="form-control" id="bookId" v-model="book.id" placeholder="" readonly />
            <div class="row title-row">
                <div class="title" style="width: 55px; height: 10px;">책번호: &nbsp;</div>
                <div style="width: 210px">{{book.bookNo}}</div>
                <button :id="book.id" class="btn btn-danger" @click="deleteBook(book.id)">X</button>
            </div>
            <v-badge
                v-if="this.today === book.date"
                content="new"
                ></v-badge>
            <RouterLink
                :to="`/bookdetail/id=${book.id}`"
                class="btn-link"
            >
                <div class="row">
                    <p>{{book.title}}</p>
                </div>
                <div class="row">
                    <div class="title">부서: &nbsp;</div>
                    <div>{{book.deptName}}&nbsp;&nbsp;|&nbsp;&nbsp;
                    </div><div class="title">수량: &nbsp;
                    </div><div>{{book.count}}권</div>
                </div>

            </RouterLink>
        </div>
    </div>

</template>
<script>
import axios from "axios";
export default {
    data(){
      return{
          today: ''
      }
    },
    props:{
        book:{
          type: Object,
          default:()=>({})
        }
    },
    mounted() {
        const date = new Date()
        const current = date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();
        this.today = current
    },
    methods:{
        deleteBook(bookId){
            console.log(bookId);
            this.$confirm("삭제 하시겠습니까?", "", "", "question")
                .then(()  => {
                    axios.delete('/api/book/' + bookId)
                        .then(response => {
                            console.log(response.data);
                            this.$store.dispatch('book/deleteUpdate', {
                                searchText : ''
                            })
                            this.$alert(response.data.resMsg, "", "success");
                        })
                        .catch(error => {
                            console.log(error);
                            alert("삭제를 실패했습니다..");
                        })
                })
                .catch(() => console.log("canceled"));


        }
    }
}
</script>
<style lang="scss" >
.v-badge__badge{
    color: #f7f5f5;
    background: #e32e26;
    top: -28px !important;
}
.book{
    width: 300px;
    height: 140px;
    margin: 5px;
    border-radius: 10px;
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
        border:6px solid;
    }
    .info{
        background-color: #fff;
        width: 100%;
        height: 100%;
        padding: 10px;
        font-size: 14px;
        text-align: left;
        position:absolute;
        left:0;
        bottom:0;
        backdrop-filter: blur(10px);
        color: #07223a;
        .title-row{
            padding-left: 10px;
            padding-top: 10px;
            padding-bottom: 5px;
            margin-bottom: 20px;
            color: #134775;
            background-color: #d8e2eb;
            border-bottom: 1px solid #134775;
            .btn-danger{
                color: #df0f0f;
                font-size: 15px;
                &:focus{
                    font-weight: bold;
                }
            }
            .title {
                font-weight: 500;
            }
        }
        .btn-link {
            text-decoration: none;
            color: #134775;
            .row {
                padding-left: 10px;
                margin-bottom: 0px;
                p {
                    font-weight: 700;
                }

            }
        }
    }
}

</style>