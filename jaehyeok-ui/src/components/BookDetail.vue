<template>
    <div class="container">
        <Loader v-if="loading"/>
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
                <input type="text" id="deptName" v-model="deptName" placeholder="신청부서" maxlength="30" required readonly />
            </div>
            <div class="form-group">
                <label for="registerNm" >신청자</label>
                <input type="text" id="registerNm" v-model="registerNm" placeholder="신청자" maxlength="30" required readonly />
            </div>
            <div class="form-group">
                <label for="title" >도서명</label>
                <input id="title" v-model="title" placeholder="도서명"  maxlength="255" />
            </div>
            <div class="form-group">
                <label for="publisher" >출판사</label>
                <input id="publisher" v-model="publisher" maxlength="50" placeholder="출판사"   />
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="bookPrice" >도서 금액</label>
                    <input style="width: 165px;" id="bookPrice" v-model="bookPrice" min="0" maxlength="7" @input="numberOnly"  placeholder="도서금액"   />
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="form-group">
                    <label for="count" >수량</label>
                    <input style="width: 165px;" type='text' id="count" v-model="count" maxlength="3" placeholder="수량"/>

                </div>
            </div>

            <div class="form-group">
                <label>신청사유</label>
                <textarea id="regRsn"  class="form-control" v-model="theBook.regRsn" maxlength="255"  placeholder="내용을 입력하세요" />
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
import Loader from './Loader';

export default {
    components:{
        Loader
    },
    data() {
        return {
            deptName: '',
            registerNm:'',
            title:'',
            publisher:'',
            bookPrice:'',
            regRsn:'',
            id:'',
            count:'',
            status:'',
            loading: false,
            img : image
        }
    },
     async mounted() {
        this.loading = true;
         await this.$store.dispatch('book/searchBookWidthId',{
            id: this.$route.params.id
         })
         this.id = this.$route.params.id
         this.deptName = this.theBook.deptName;
         this.registerNm = this.theBook.registerNm;
         this.title = this.theBook.title;
         this.publisher = this.theBook.publisher;
         let bookPrice = comma(this.theBook.bookPrice);
         this.bookPrice = bookPrice;
         this.count = this.theBook.count;
         this.status = this.theBook.status;
         this.regRsn = this.theBook.regRsn;
         this.loading = false;
    },
    watch: {
        count(){
            return this.count = this.count.replace(/[^0-9]/g,'');
        },
        publisher(){
            return this.publisher = this.publisher.replace(/[0-9]/g,'');
        },
        bookPrice(val){
            let result =  uncomma(val);
            if(result > 100000)
                this.bookPrice = "100,000";
        }
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
            let bookPrice = uncomma(this.bookPrice);
            let data = {
                id : this.id,
                deptName : this.deptName,
                registerNm : this.registerNm,
                title : this.title,
                publisher : this.publisher,
                bookPrice : bookPrice,
                count: this.count,
                status: this.status,
                regRsn : this.regRsn
            };
            if(this.title === ""){
                this.$alert("도서명을 입력해주세요.", "", "warning");
                return false
            }else if(this.publisher === "") {
                this.$alert("출판사를 입력해주세요.", "", "warning");
                return false
            }else if(this.bookPrice===""){
                this.$alert("도서 금액을 입력해주세요.", "", "warning");
                return false
            }else {
                axios
                    .put("/api/book", data)
                    .then(response => {
                        console.log(response.data);
                        this.$alert(response.data.resMsg,"","success");
                        this.$router.push('/home');
                    })
                    .catch(error => {
                        console.log(error)
                        this.$alert("수정에 실패했습니다.","","error");

                    })
            }
        },
        numberOnly(){
            let result = comma(this.bookPrice);
            this.bookPrice = result;
        }
    }
}
function comma(str) {
    str = str.toString().replace(/[^0-9]/g,'');   // 입력값이 숫자가 아니면 공백
    str = str.toString().replace(/,/g,'');          // ,값 공백처리
    return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 정규식을 이용해서 3자리 마다 , 추가
}

//콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}
</script>
<style lang="scss" scoped>
textarea{
    width:350px;
    height:150px;
    resize: none;
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
                    font-size: 32px;
                    font-weight: bold;
                }
            }

            h2 {
                font-size: 24px;
                margin-bottom: 10px;
                font-family: 'Titillium Web';
            }
        }
        .row{
            margin-top: 5px;
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
                font-size: 1rem;
                padding: 10px 50px;
                font-weight: bold;
                border-radius: 30px;
                cursor: pointer;
                width: 320px;
            }
        }

        display: flex;
        width: 100%;
        height: 675px;
        background-color: aliceblue;
        flex-direction: column;
        //justify-content: center;
        align-items: center;
        color: #134775;
        font-size: 12px;
    }
}


</style>