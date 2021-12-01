<template>
    <div class="container">
        <Loader v-if="loading"/>
        <form>
            <div class="form-title">
                <router-link to="/home" class="back-link">
                    <button class="btn btn-primary btn-back" >⭠</button>
                </router-link>
                <h2>내 정보</h2>
            </div>
            <div class="form-group">
                <label for="userName" >아이디</label>
                <input type="text" class="form-control" id="userName" v-model="userName" placeholder="아이디"  required readonly>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" class="form-control" id="password" v-model="password" @blur="passwordMach" placeholder="비밀번호"  required>
                <div class="is-invalid" v-if="!passwordMatch" >비밀번호가 잘못되었습니다.</div>
            </div>
            <div class="form-group">
                <label for="newPassword">새 비밀번호</label>
                <input type="password" class="form-control" id="newPassword" v-model="NewPassword" placeholder="비밀번호 확인"   required>
            </div>
            <div class="form-group">
                <label for="NewPasswordCheck">새 비밀번호 확인</label>
                <input type="password" class="form-control" id="NewPasswordCheck" v-model="NewPasswordCheck" placeholder="비밀번호 확인"  @keyup="passCheck"   required>
                <div :class="passCheckClass">비밀번호가 일치하지않습니다.</div>
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" class="form-control" id="name" v-model="name" placeholder="이름"  required>
            </div>
            <div class="form-group">
                <label >부서</label>
                <div  class="selects">
                    <select
                        id="deptName"
                        v-model="deptName"
                        class="form-select"
                    >
                        <option value="" disabled selected hidden>부서</option>
                        <option
                            v-for="item in deptNameList"
                            :key="item.key"
                            :value="item.name"
                        >
                            {{item.name}}
                        </option>
                    </select>
                </div>
            </div>
            <div class="btn-cover">
                <button type="button" @click="myInfoUpdate" >수정</button>
            </div>
        </form>
    </div>
</template>
<script>
const storage = window.sessionStorage;
import axios from "axios";
import Loader from './Loader';
export default {
    name: "MyInfo",
    components:{
        Loader
    },
    data:()=>{
        return{
            userName:JSON.parse(storage.getItem('authentication')).userName,
            name:'',
            password:'',
            NewPassword:'',
            NewPasswordCheck:'',
            deptName: '',
            loading: false,
            deptNameList:[
                {
                    name:'공통개발팀',
                    key:'comDev'
                },
                {
                    name:'웹개발팀',
                    key:'webDev'
                },
                {
                    name:'앱개발팀',
                    key:'appDev'
                },
                {
                    name:'서비스개발팀',
                    key:'serviceDev'
                },
                {
                    name:'마케팅팀',
                    key:'marketing'
                },
                {
                    name:'인사총무팀',
                    key:'hrd'
                },
                {
                    name:'회계팀',
                    key:'account'
                }
            ],
            passwordMatch:true,
            passCheckClass: 'invalid-feedback'
        }
    },
    created() {
        this.fetchData();
    },
    methods:{
        fetchData(){
            this.loading = true;
            let id = this.userName;
            axios.get(`/api/membercheck/${id}`)
                .then(response => {
                    console.log(response.data.data);
                    this.id = response.data.data.id
                    this.name = response.data.data.name
                    this.deptName = response.data.data.deptName

                })
                .catch(error => {
                    console.log(error)
                })
            this.loading = false;
        },
        myInfoUpdate(){
            let data = {
                id : this.id,
                userName:this.userName,
                password:this.NewPassword,
                name:this.name,
                deptName:this.deptName
            };
            if(this.passwordMatch === false) {
                this.$alert("기존 비밀번호가 틀립니다.", "", "warning");
                return false
            }else if(this.password===""){
                this.$alert("비밀번호를 입력해주세요.", "", "warning");
                return false
            }else if(this.NewPassword===""){
                this.$alert("새비밀번호를 입력해주세요.", "", "warning");
                return false
            }else if(!this.passCheck()){
                this.$alert("새비밀번호가 일치하지않습니다.", "", "warning");
                return false
            }else{
                console.log(data)
                axios
                    .put( "/api/member", data)
                    .then(response => {
                        console.log(response);
                        this.$alert(response.data.resMsg, "", "warning");
                        this.password = ''
                        this.NewPassword = ''
                        this.NewPasswordCheck = ''
                    })
                    .catch(error =>{
                        console.log(error)
                        this.$alert("수정에 실패했습니다.", "", "warning");

                    })
            }
        },
        passCheck(){
            if(this.NewPassword !== this.NewPasswordCheck || this.NewPassword === ""){
                this.passCheckClass = "is-invalid"
                return false;
            }else{
                this.passCheckClass = "invalid-feedback"
                return true;
            }
        },
        async passwordMach(){
            this.passwordMatch = true;
            const response = await checkPasswordMach(this.userName,this.password);
            console.log(response)
            if (!response){
                this.passwordMatch = false;
            }else{
                this.passwordMatch = true;
            }
        }
    }
}
function checkPasswordMach(id,password){
    const data = {
        userName:id,
        password:password,
    }
    console.log(data)
    return new Promise((resolve, reject) => {
        axios.post("/api/passwordcheck",data)
            .then(response => {
                console.log(response.data)
                resolve(response.data)
            })
            .catch(error =>{
                console.log(error)
                reject(error.message)
            })
    });
}
</script>
<style lang="scss" scoped>
.container {
    form {
        .form-title {
            display: flex;
            position: relative;
            margin-top: 20px;
            margin-bottom: 20px;
            .back-link {
                color: #134775;
                .btn-back {
                    position: absolute;
                    left: -125px;
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
            margin-top: 10px;
            flex-direction: row;
            width: 350px;
            height: fit-content;
            label{
            }
            input {
                width: 50px;
            }
        }
        .form-group{
            display: flex;
            flex-direction: column;
            .invalid-feedback{
                display: none;
                color: #f10e0e;

            }
            .is-invalid{
                display: block;
                color: #f10e0e;
                margin-bottom: 15px;
            }
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
        select{
            padding: 10px;
            width: 350px;
            background-color: #e3e9ef;
            color: #134775;
            margin-bottom: 15px;
            outline: none;
        }
        .btn-cover{
            text-align: center;
            button {
                margin-top: 15px;
                border: none;
                background: #134775;
                color: aliceblue;
                font-size: 1rem;
                padding: 10px 50px;
                font-weight: bold;
                border-radius: 30px;
                cursor: pointer;
                width: 320px;
                outline: none;
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