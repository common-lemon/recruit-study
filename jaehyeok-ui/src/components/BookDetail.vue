<template>
    <div class="container">
        <form >
            <div class="form-title">
                <router-link to="/home" class="back-link">
                    <button class="btn btn-primary btn-back" >⭠</button>
                </router-link>
                <h2>도서 상세보기</h2>
            </div>
            <input type="hidden" id="id" v-model="id"  required readonly />
            <div class="form-group">
                <label for="deptName" >신청부서</label>
                <input id="deptName" v-model="deptName" placeholder="신청부서" required readonly />
            </div>
            <div class="form-group">
                <label for="registerNm" >신청자</label>
                <input id="registerNm" v-model="registerNm" placeholder="신청자"  required readonly />
            </div>
            <div class="form-group">
                <label for="title" >도서명</label>
                <input id="title" v-model="title" placeholder="도서명"   />
            </div>
            <div class="form-group">
                <label for="publisher" >출판사</label>
                <input id="publisher" v-model="publisher" placeholder="출판사"   />
            </div>
            <div class="form-group">
                <label for="bookPrice" >도서 금액</label>
                <input id="bookPrice" v-model="bookPrice" placeholder="도서금액"   />
            </div>
            <div class="form-group">
                <label>신청사유</label>
                <textarea id="regRsn"  class="form-control" v-model="theBook.regRsn" placeholder="내용을 입력하세요" />
            </div>
            <div class="btn-cover">
                <button type="button"  @click="udtSubmit">수정</button>
            </div>
        </form>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import image from "/src/assets/goBack2.png";
import axios from "axios";
export default {
    data() {
        return {
            deptName: '',
            registerNm:'',
            title:'',
            publisher:'',
            bookPrice:'',
            regRsn:'',
            id:'',
            img : image
        }
    },
     async created() {
         await this.$store.dispatch('book/searchBookWidthId',{
            id: this.$route.params.id
         })
         this.id = this.$route.params.id
         this.deptName = this.theBook.deptName;
         this.registerNm = this.theBook.registerNm;
         this.title = this.theBook.title;
         this.publisher = this.theBook.publisher;
         this.bookPrice = this.theBook.bookPrice;
         this.regRsn = this.theBook.regRsn;
    },
    computed:{
        ...mapState('book',[
            'theBook'
        ]),
    },
    methods:{
        goBack(){
            this.$router.go(-1);
        },
        udtSubmit(){
            let data = {
                id : this.id,
                deptName : this.deptName,
                registerNm : this.registerNm,
                title : this.title,
                publisher : this.publisher,
                bookPrice : this.bookPrice,
                regRsn : this.regRsn
            };
            axios
                .put( "/api/book", data)
                .then(response => {
                    console.log(response);
                    alert("도서정보가 수정되었습니다.");
                    this.$router.push('/home');
                })
                .catch(error =>{
                    console.log(error)
                    alert("수정에 실패했습니다.");
                })
        }
    }
}
</script>
<style lang="scss" scoped>
textarea{
    width:350px;
    height:150px;
    resize: none;  //세로크기만 조절가능
    background-color: #e3e9ef;
    color: #134775;
    outline: none;

}
.container {
    form {
        .form-title {
            display: flex;
            position: relative;
            .back-link {
                color: #134775;
                .btn-back {
                    position: absolute;
                    left: -100px;
                    top: -6px;
                    font-size: 35px;
                    font-weight: bold;
                }
            }

            h2 {
                font-size: 1.5rem;
                margin-bottom: 15px;
                font-family: 'Titillium Web';
            }
        }
        .form-group{
            display: flex;
            flex-direction: column;
        }
        label {
            color: #134775;
            margin-bottom: 10px;
        }
        input {
            padding: 10px;
            width: 350px;
            margin-bottom: 15px;
            border: none;
            background-color: #e3e9ef;
            color: #134775;
            outline: none;
        }
        .btn-cover{
            text-align: center;
            button {
                margin-top: 15px;
                border: none;
                background: #134775;
                color: #f4f2db;
                font-size: 1.1rem;
                padding: 15px 60px;
                font-weight: bold;
                border-radius: 30px;
                cursor: pointer;
                width: 350px;
            }
        }

        display: flex;
        width: 100%;
        height: 730px;
        background-color: aliceblue;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: #134775;
    }
}


</style>