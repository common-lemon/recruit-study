<template>
    <div class="container">
        <form  >
            <h2>도서 신청</h2>
            <div class="form-group">
                <label for="deptName" >신청부서</label>
                <input type="text" id="deptName" v-model="deptName" placeholder="신청부서" required/>
            </div>
            <div class="form-group">
                <label for="registerNm" >신청자</label>
                <input type="text" id="registerNm" v-model="registerNm" placeholder="신청자"  required/>
            </div>
            <div class="form-group">
                <label for="title" >도서명</label>
                <input id="title" v-model="title" placeholder="도서명"   />
            </div>
            <div class="form-group">
                <label for="publisher" >출판사</label>
                <input id="publisher" v-model="publisher" placeholder="출판사"   />
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="bookPrice" >도서 금액</label>
                    <input style="width: 165px;" id="bookPrice" v-model="bookPrice" min="0" maxlength="7" @input="numberOnly"  placeholder="도서금액"   />
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="form-group">
                    <label for="count" >수량</label>
                    <input style="width: 165px;" type='number' id="count" v-model="count" min="1" placeholder="수량"/>

                </div>
            </div>
            <div class="form-group">
                <label>신청사유</label>
                <textarea id="regRsn"  class="form-control" v-model="regRsn" placeholder="내용을 입력하세요" />
            </div>
            <div class="btn-cover">
                <button type="button"  @click="submitApply">신청</button>
            </div>
        </form>
    </div>
</template>
<script>
import axios from "axios";
//import Loader from './Loader';

export default {
    components:{
       // Loader
    },
    data() {
        return {
            deptName:'',
            registerNm:'',
            title:'',
            publisher:'',
            bookPrice:'',
            regRsn:'',
            count:''
        }
    },
    watch: {
        bookPrice(val){
            let result =  uncomma(val);
            if(result > 100000)
                this.bookPrice = "100,000";

        }
    },
    methods:{
        async submitApply(){
            const date = new Date()
            const current = date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();
            let url = '/api/book';
            let bookPrice =  uncomma(this.bookPrice);
            const data = {
                deptName: this.deptName,
                registerNm: this.registerNm,
                title: this.title,
                date: current,
                publisher: this.publisher,
                bookPrice: bookPrice,
                count: this.count,
                regRsn: this.regRsn,
            }
            await axios.post(url, data)
                .then(response => {
                    console.log(response);
                    this.$router.push('/home');
                })
                .catch(error =>{
                    console.log(error)
                })
        },
        numberOnly(){
            let result = comma(this.bookPrice);
            this.bookPrice = result;
        }

    }
}
function comma(str) {
    str = str.replace(/[^0-9]/g,'');   // 입력값이 숫자가 아니면 공백
    str = str.replace(/,/g,'');          // ,값 공백처리
    return str.replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 정규식을 이용해서 3자리 마다 , 추가
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
    resize: vertical;  //세로크기만 조절가능
    background-color: #e3e9ef;
    color: #134775;
    outline: none;
}
.container {
    form {
        h2 {
            font-size: 24px;
            margin-bottom: 10px;
            font-family: 'Titillium Web';
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
        }
        .btn-cover{
            text-align: center;
            button {
                margin-top: 15px;
                border: none;
                background: #134775;
                color: #f4f2db;
                font-size: 1rem;
                padding: 15px 50px;
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