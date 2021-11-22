<template>
    <div class="book">
        <div class="info">
            <input type="hidden" class="form-control" id="bookId" v-model="book.id" placeholder="" readonly />
            <div class="row">
              <div class="title">책번호: &nbsp;</div>  <div>{{book.bookNo}}</div>
            </div>
            <div class="row">
              <p>{{book.title}}</p>
            </div>
            <div class="row">
              <div class="title">부서: &nbsp;</div> <div>{{book.deptName}} &nbsp;|&nbsp; </div><div class="title">수량: &nbsp;</div><div>{{book.count}}권</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props:{
        book:{
          type: Object,
          default:()=>({})
        }
    }
}
</script>
<style lang="scss" scoped>
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
        padding: 14px;
        font-size: 14px;
        text-align: left;
        position:absolute;
        left:0;
        bottom:0;
        backdrop-filter: blur(10px);
        color: #07223a;
        .row{
            padding-left: 10px;
            padding-top: 10px;
                p{
                    font-weight: 700;
                }
                .title{
                    font-weight: 500;
                }
        }
    }
}

</style>