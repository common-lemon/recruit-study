<template>
    <div class="container">
        <form  >
            <h2>도서 신청</h2>
            <div class="form-group">
                <label for="deptName" >신청부서</label>
                <input type="text" id="deptName" v-model="deptName" placeholder="신청부서" maxlength="30" required readonly/>
            </div>
            <div class="form-group">
                <label for="registerNm" >신청자</label>
                <input type="text" id="registerNm" v-model="registerNm" placeholder="신청자" maxlength="30" required readonly/>
            </div>
            <div class="form-group">
                <label for="title" >도서명</label>
                <input id="title" v-model="title" placeholder="도서명"  maxlength="255" />
            </div>
            <div class="form-group">
                <label for="publisher" >출판사</label>
                <input id="publisher" v-model="publisher" maxlength="50" placeholder="출판사" />
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
                <textarea id="regRsn"  class="form-control" v-model="regRsn" maxlength="255" placeholder="내용을 입력하세요" />
            </div>
            <div class="btn-cover">
                <button type="button"  @click="submitApply">신청</button>
            </div>
        </form>
    </div>
</template>
<script>
import axios from "axios";
const storage = window.sessionStorage;

export default {
    components:{
       // Loader
    },
    data() {
        return {
            userName:JSON.parse(storage.getItem('authentication')).userName,

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
        count(){
            return this.count = this.count.replace(/[^0-9]/g,'');
        },
        publisher(){
            return this.publisher = this.publisher.replace(/[0-9]/g,'');
        },
        bookPrice(val){
            let result = uncomma(val);
            if(result > 100000)
                this.bookPrice = "100,000";

        }
    },
    created() {
        this.fetchData();
    },
    methods:{
        fetchData(){
            let id = this.userName;
            axios.get(`/api/membercheck/${id}`)
                .then(response => {
                    console.log(response.data.data);
                    this.registerNm = response.data.data.name
                    this.deptName = response.data.data.deptName
                })
                .catch(error => {
                    console.log(error)
                })
        },
        async submitApply(){
            const date = new Date()
            getFormatDate(date);
            const current =  getFormatDate(date);  //date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();
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
            console.log(data);
            if(this.title === ""){
                await this.$alert("도서명을 입력해주세요.", "", "warning");
                return false
            }else if(this.publisher === "") {
                await this.$alert("출판사를 입력해주세요.", "", "warning");
                return false
            }else if(this.bookPrice===""){
                await this.$alert("도서 금액을 입력해주세요.", "", "warning");
                return false
            }else {
                await axios.post(url, data)
                    .then(response => {
                        console.log(response.data);
                        this.$alert(response.data.resMsg,"","success");
                        this.$router.push('/home');
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
        },
        numberOnly(){
            this.bookPrice = comma(this.bookPrice);
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
//날짜 변환
function getFormatDate(date){
    let year = date.getFullYear();              //yyyy
    let month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    let day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
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