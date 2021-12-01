<template>
    <div class="container">
        <h2>신청/완료/취소 조회</h2>
        <div class="title-div">
            <div class="form-group">
                <RouterLink to="./applyList" active-class="active" class="nav-link">
                    {{ applyList.length }}
                    <div>신청중</div>
                </RouterLink>
            </div>
            <div class="form-group">
                <RouterLink to="./finishList" active-class="active" class="nav-link">
                    {{ finishList.length }}
                    <div>완료</div>
                </RouterLink>
            </div>
            <div class="form-group">
                <RouterLink to="./cancelList" active-class="active" class="nav-link">
                    {{ cancelList.length }}
                    <div>취소</div>
                </RouterLink>
            </div>
        </div>
    </div>
</template>
<script>
import {mapState} from 'vuex'
export default {

    data(){
      return{
          applyCnt: 0,
          finishCnt: 0,
          cancelCnt:0,
          status: 'apply',
          statusList:[]
      }
    },
    mounted() {
        this.fetchData();
    },
    computed: {
        ...mapState('book',[
            'bookList',
            'cancelList',
            'applyList',
            'finishList'
        ]),
    },
    methods:{
        fetchData(){
            this.$store.dispatch('book/statusList',{
                cancel: 'CANCEL',
                apply: 'APPLY',
                finish: 'FINISH',
            })
        }
    }
}
</script>
<style lang="scss" scoped>
.container{
    width: 100%;
    align-items: center;
    h2{
        margin-top: 20px;
        margin-bottom: 15px;
        margin-left: 20px;
        color: #134775;
    }
    .title-div{
        margin-left: 15px;
        display: flex;
        border-top: 1px solid #134775;
        .form-group{
            margin-top: 15px;
            width: 30%;
            text-align: center;
            a{
                text-decoration: none;
                color: #134775;
                font-size: 16px;
            }
        }
    }
}
</style>
