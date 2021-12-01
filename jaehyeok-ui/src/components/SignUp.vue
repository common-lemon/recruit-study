<template>
    <div class="container">
        <form >
            <div class="form-title">
                <router-link to="/login" class="back-link">
                    <button class="btn btn-primary btn-back" >⭠</button>
                </router-link>
                <h2>회원가입</h2>
            </div>
            <div class="form-group">
                <label for="userName" >아이디</label>
                <input type="text" class="form-control" id="userName" v-model="userName" placeholder="아이디" @blur="checkDuplicate"  required>
                <div class="is-invalid" v-if="!availableId">이미 사용중인 아이디입니다.</div>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" class="form-control" id="password" v-model="password" placeholder="비밀번호"  required>
            </div>
            <div class="form-group">
                <label for="passwordCheck">비밀번호 확인</label>
                <input type="password" class="form-control" id="passwordCheck" v-model="passwordCheck" placeholder="비밀번호 확인" @keyup="passCheck"  required>
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
            <div class="row">
                <label class="form-check-label" for="invalidCheck">
                    개인정보 수집 및 이용에 동의합니다.
                </label>
                <input type="checkbox" class="form-check-input" id="invalidCheck" v-model="invalidCheck" required>
            </div>
            <div class="btn-cover">
                <button type="button" @click="signUp" >회원가입</button>
            </div>

        </form>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "SignUp",
    data:()=>{
        return{
            userName:'',
            name:'',
            password:'',
            passwordCheck:'',
            invalidCheck:'',
            deptName: '',
            availableId:true,
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
            passCheckClass: 'invalid-feedback'
        }
    },
    methods:{
        async signUp(){
            let data={
                userName: this.userName,
                name: this.name,
                password: this.password,
                deptName: this.deptName,
            }
            if (this.userName === "") {
                await this.$alert("아이디를 입력해주세요.", "", "warning");
                return false;
            }else if (this.availableId === false){
                await this.$alert("이미 사용중인 아이디입니다.", "", "warning");
                return false;
            }else if (this.password === ""){
                await this.$alert("비밀번호를 입력해주세요.", "", "warning");
                return false;
            }else if (this.passwordCheck === ""){
                await this.$alert("비밀번호 확인을 입력해주세요.", "", "warning");
                return false;
            }else if (this.passwordCheck !== this.password){
                await this.$alert("비밀번호가 일치하지않습니다.", "", "warning");
                return false;
            }else if (this.name === ""){
                await this.$alert("이름을 입력해주세요.", "", "warning");
                return false;
            }else if(this.invalidCheck !== true){
                await this.$alert("개인정보 수집을 동의해주세요.", "", "warning");
                return false;
            }else{
                axios
                    .post( "/api/auth/member", data)
                    .then(response => {
                        console.log(response);
                        this.$alert("회원가입에 성공했습니다.", "", "success");

                        this.$router.push("/login");
                    })
                    .catch(error =>{
                        console.log(error)
                        this.$alert("회원가입에 실패했습니다.", "", "warning");
                    })
            }
        },
        async checkDuplicate(){
            this.availableId = true;
            const response = await checkDuplicateId(this.userName);
            console.log(response)
            this.availableId = !response;
        },
        passCheck(){
            if(this.password !== this.passwordCheck || this.password === ""){
                this.passCheckClass = "is-invalid"
                return false;
            }else{
                this.passCheckClass = "invalid-feedback"
                return true;
            }
        }
    }
}

function checkDuplicateId(id){
    const url = `/api/membercheck/${id}`
    return new Promise((resolve, reject) => {
        axios.get(url)
            .then(res => {
                console.log(res.data.data)
                resolve(res.data.data)
            })
            .catch(err => {
                reject(err.message)
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
                margin-bottom: 15px;
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
        height: 700px;
        background-color: aliceblue;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: #134775;
        font-size: 12px;
    }
}


</style>