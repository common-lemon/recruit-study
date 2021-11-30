
<template>
    <div class="container">
        <form>
            <h2>Login</h2>
            <label><input placeholder="아이디" v-model="userName" required /></label>
            <label>
                <input :type="passwordFieldType" placeholder="패스워드" v-model="password" required />
                <div id="keyShow" @click="switchVisibility">{{this.keyShow}}</div>
            </label>
            <RouterLink to="/signup" class="signup">
                회원가입
            </RouterLink>
            <button type="button" @click="login">로그인</button>
        </form>
    </div>
</template>

<script>
import axios from "axios";
import {mapState} from 'vuex'
export default {
    name: "Login",
    data: () => ({
        userName: '',
        password: '',
        passwordFieldType : 'password',
        keyShow: 'SHOW'
    }),
    computed:{
        ...mapState('member',[
            'token',
            'authority',
        ]),
    },
    methods: {
        switchVisibility(){
            if (this.passwordFieldType === "password"){
                this.passwordFieldType = "text"
                this.keyShow = "H I D E"
            }else {
                this.passwordFieldType = "password"
                this.keyShow = "SHOW"
            }
        },
        async login(){
            let data = {
                userName: this.userName,
                password: this.password
            }
            axios.post('/api/login', data)
                .then(async response => {

                    if (response.status === 200) {
                        await this.$store.dispatch('member/login', {
                            data: response
                        })
                        if (this.authority === "ROLE_ADMIN") {
                            await this.$router.push('/applyList');
                        }else{
                            await this.$router.push('/home');
                        }
                    } else {
                        await this.$alert("로그인에 실패했습니다.", "", "warning");
                    }

                })
                .catch(error =>{
                    this.$alert("로그인에 실패했습니다.","","warning");
                    console.log(error)
                })
        }

    }
}
</script>

<style lang="scss" scoped>
.container {
    form {
        h2 {
            font-size: 2rem;
            margin-bottom: 20px;
        }
        a{
            text-decoration: none;
            color: #134775;
        }
        label {
            input {
                padding: 10px;
                width: 350px;
                margin-bottom: 10px;
                border: none;
                background: #e3e9ef;
                outline: none;
            }
            #keyShow {
                position: absolute;
                display: inline-block;
                margin-left: -55px;
                margin-top: 12px;
                font-size: 14px;
                cursor: pointer;
                color: grey;
            }
        }
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
        }
        display: flex;
        width: 100%;
        height: 730px;
        background: aliceblue;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: #134775;
    }
}
</style>