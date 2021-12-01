<template>

    <div class="book">
        <div class="info">
            <input type="hidden" class="form-control" id="bookId" v-model="book.id" placeholder="" readonly />
            <div class="row title-row">
                <div class="title" style="width: 55px; height: 10px;">책번호: &nbsp;</div>
                <div style="width: 210px">{{book.bookNo}}</div>
                <button :id="book.id" class="btn btn-danger" @click="deleteBook(book.id)">X</button>
            </div>
            <v-badge
                v-if="authority === 'ROLE_ADMIN'"
                style="display: none"
            ></v-badge>
            <v-badge
                v-else-if="book.status === 'APPLY'"
                content="신청중"
                ></v-badge>
            <v-badge
                v-else-if="book.status === 'CANCEL'"
                content="취소"
            ></v-badge>
            <RouterLink
                :to="`/bookdetail/id=${book.id}`"
                class="btn-link"
            >
                <div class="row">
                    <p>{{book.title}}</p>
                </div>
                <div class="row">
                    <div class="title">부서: &nbsp;</div>
                    <div>{{book.deptName}}&nbsp;&nbsp;|&nbsp;&nbsp;
                    </div><div class="title">수량: &nbsp;
                    </div><div>{{book.count}}권</div>
                </div>
            </RouterLink>
            <div
                v-if="authority === 'ROLE_ADMIN'"
                class="admin-row"
            >
                <div  class="selects">
                    <select
                        id="status"
                        v-model="status"
                        class="form-select"
                        placeholder="status"
                    >
                        <option
                            v-for="item in statusList"
                            :key="item.key"
                            :value="item.key"
                        >
                            {{item.name}}
                        </option>
                    </select>
                </div>
                <button class="btn btn-primary"
                        @click="saveStatus(book.id)">
                    수정
                </button>
            </div>
        </div>
    </div>

</template>
<script>
import {mapState} from 'vuex'
import axios from "axios";
export default {
    data(){
      return{
          today: '',
          deptName: '',
          registerNm:'',
          title:'',
          publisher:'',
          bookPrice:'',
          regRsn:'',
          count:'',
          status:this.book.status,
          statusList:[
              {
                  name:'신청',
                  key:'APPLY'
              },
              {
                  name:'완료',
                  key:'FINISH'
              },
              {
                  name:'취소',
                  key:'CANCEL'
              }
          ],
      }
    },
    props:{
        book:{
          type: Object,
          default:()=>({})
        },
        statusName:{
            type: Object,
            default:()=>({})
        }
    },
    computed:{
        ...mapState('member',[
            'token',
            'authority',
        ])
    },
    created() {
        const date = new Date()
        const current = date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();
        this.today = current
        this.deptName = this.book.deptName;
        this.registerNm = this.book.registerNm;
        this.title = this.book.title;
        this.publisher = this.book.publisher;
        let bookPrice = comma(this.book.bookPrice);
        this.bookPrice = bookPrice;
        this.count = this.book.count;
        this.status = this.book.status;
        this.regRsn = this.book.regRsn;
    },
    methods:{
        saveStatus(id) {
            let bookPrice = uncomma(this.bookPrice);
            let data = {
                id : id,
                deptName : this.deptName,
                registerNm : this.registerNm,
                title : this.title,
                publisher : this.publisher,
                bookPrice : bookPrice,
                count: this.count,
                status: this.status,
                regRsn : this.regRsn
            }
            console.log(data)
            axios
                .put( "/api/book", data)
                .then(response => {
                    console.log(response);
                    this.$alert(response.data.resMsg, "", "warning");
                    this.$store.dispatch('book/statusList',{
                        cancel: 'CANCEL',
                        apply: 'APPLY',
                        finish: 'FINISH',
                    })
                })
                .catch(error =>{
                    console.log(error)
                    this.$alert("수정에 실패했습니다.", "", "warning");

                })
        },
        deleteBook(bookId){
            console.log(bookId);
            this.$confirm("삭제 하시겠습니까?", "", "", "question")
                .then(()  => {
                    axios.delete('/api/book/' + bookId)
                        .then(response => {
                            console.log(response.data);
                            this.$store.dispatch('book/deleteUpdate', {
                                searchText : ''
                            })
                            this.$alert(response.data.resMsg, "", "success");
                        })
                        .catch(error => {
                            console.log(error);
                            alert("삭제를 실패했습니다..");
                        })
                })
                .catch(() => console.log("canceled"));


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
<style lang="scss" >
.v-badge__badge{
    color: #f7f5f5;
    background: #e32e26;
    top: -28px !important;
}
.book{
    width: 300px;
    height: 150px;
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
        padding: 10px;
        font-size: 14px;
        text-align: left;
        position:absolute;
        left:0;
        bottom:0;
        backdrop-filter: blur(10px);
        color: #07223a;
        .title-row{
            padding-left: 10px;
            padding-top: 10px;
            padding-bottom: 5px;
            margin-bottom: 20px;
            color: #134775;
            background-color: #d8e2eb;
            border-bottom: 1px solid #134775;
            .btn-danger{
                color: #df0f0f;
                font-size: 15px;
                &:focus{
                    font-weight: bold;
                }
            }
            .title {
                font-weight: 500;
            }
        }
        .admin-row{
            display: flex;
            select{
                padding-left: 10px;
                border-radius: 10px;
                border: 2px solid #b4c1d5;
                width: 100px;
                height: 25px;
                background-color: #e3e9ef;
                color: #134775;
                margin-top: 10px;
                margin-right: 10px;
                outline: none;
            }
            button{
                border: none;
                background: #b4c1d5;
                color: #134775;
                border: 2px solid #b4c1d5;
                width: 40px;
                height: 25px;
                margin-top: 10px;
                border-radius: 10px;
                cursor: pointer;
            }
        }
        .btn-link {
            text-decoration: none;
            color: #134775;
            .row {
                padding-left: 10px;
                margin-bottom: 0px;
                p {
                    font-weight: 700;
                }

            }
        }
    }
}

</style>