<template>
    <header class="header">
        <slider
            :width="300"
            format="overlay"
            direction="left"
            :opacity="0.5"
            :links=this.links
            v-if="this.authority === 'ROLE_USER'"
        >
        </slider>
        <slider
            :width="300"
            format="overlay"
            direction="left"
            :opacity="0.5"
            :links=this.linksAdmin
            v-if="this.authority === 'ROLE_ADMIN'"
        >
        </slider>
        <h2>Lemon library</h2>
        <div class="login" v-if="!token">
            <RouterLink to="/login" class="login">
                로그인
            </RouterLink>
        </div>
        <div class="authMenu" v-else>
            <RouterLink to="/myinfo" class="myPage">
                MY페이지
            </RouterLink>
            <button class="logout" v-on:click="logout">
                로그아웃
            </button>
        </div>

    </header>
</template>

<script>
import Slider from '@jeremyhamm/vue-slider'
import {mapState} from 'vuex'
const storage = window.sessionStorage;
export default {
    components:{
        'slider': Slider
    },
    data(){
        return {
            links:[
                {'id': 1, 'text': '도서 현황', 'url': '/home'},
                {'id': 2, 'text': '도서 신청', 'url': '/bookapply'}
            ],
            linksAdmin:[
                {'id': 1, 'text': '도서 관리', 'url': '/applyList'}
            ],
            closeOnClick: true
        }
    },
    computed:{
        ...mapState('member',[
            'token',
            'authority',
        ]),
    },
    methods:{
        logout(){
            storage.removeItem("token");
            storage.removeItem("authentication");
            this.$store.dispatch('member/refreshToken',{
                token: "",
                authentication: "",
            })
            this.$router.push('/login');
        }
    }
}
</script>
<style lang="scss">
.navIcon{
    margin-left: -15px !important;
    margin-right: 15px !important;
    margin-top: 10px !important;
}
.navMenu{
    background-color: #d8dde7  !important;
    padding-top: 70px !important;
}
.navMenu a{
    color: #134775  !important;
}
header{
  height: 60px;
  background-color: #134775;
  color: #f4f2db;padding-left: 40px;
  display: flex;
  align-items: center;
  position: relative;
    .authMenu{
        a {
            color: aliceblue;
            text-decoration: none;
            font-size: 14px;
            margin-right: 5px;
            font-family: 'Titillium Web';
        }
        button{
            color: aliceblue;
            font-size: 14px;
            font-family: 'Titillium Web';
        }
    }
    .login{
        width: 40%;
        text-align: right;
        a {
            color: aliceblue;
            text-decoration: none;
            font-size: 14px;
            font-family: 'Titillium Web';
        }
    }
    h2{
        color: aliceblue;
        font-family: 'Titillium Web';
        width: 190px;
    }
}
</style>