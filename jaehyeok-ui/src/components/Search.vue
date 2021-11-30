<template>
    <div class="Search-div">
        <select
            id="page-size"
            v-model="pageSize"
            class="page-select"
            @change="selectChangePage()"
        >
            <option value="" disabled selected hidden>5</option>
            <option
                v-for="item in pageSizeList"
                :key="item.key"
                :value="item.key"
            >
                {{item.name}}
            </option>
        </select>
        <input
            v-model="searchText"
            class="form-control"
            type="text"
            placeholder="도서명"
            @keyup.enter="apply"/>
        <button class="btn btn-primary"
                @click="apply">
          검색
        </button>
        <div class="form-group">
            <select
                id="categories"
                v-model="sort"
                class="form-select"
                placeholder="sort"
                @change="selectChangeSort()"
            >
                <option value="" disabled selected hidden>정렬↓</option>
                <option
                    v-for="item in sortList"
                    :key="item.key"
                    :value="item.key"
                >
                    {{item.name}}
                </option>
            </select>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return{
            searchText:'',
            sort:'',
            pageSize:'',
            pageSizeList:[
                {
                    name:'5',
                    key:5
                },
                {
                    name:'10',
                    key:10
                },
                {
                    name:'20',
                    key:20
                }
            ],
            sortList:[
                {
                  name:'제목↑',
                  key:'titleAsc'
                },
                {
                  name:'제목↓',
                  key:'titleDesc'
                },
                {
                  name:'부서↑',
                  key:'deptAsc'
                },
                {
                  name:'부서↓',
                  key:'deptDesc'
                },
                {
                  name:'수량↑',
                  key:'cntAsc'
                },
                {
                  name:'수량↓',
                  key:'cntDesc'
                }
            ]
        }
    },
    created() {
        this.$store.dispatch('book/searchList', {
            searchText: this.searchText
        })
    },
    methods:{
        async selectChangeSort(){
            await this.$store.dispatch('book/sortList', {
                sort: this.sort
            })
        },
        async selectChangePage(){
            await this.$store.dispatch('book/pageSize', {
                pageSize: this.pageSize
            })
        },
        async apply(){
            await this.$store.dispatch('book/searchList', {
            searchText: this.searchText
            })
        }
    }
}
</script>
<style lang="scss" scoped>
/*common*/
button {
    //margin-top: 10px;
    margin-right: 5px;
    border: none;
    background: #134775;
    color: #f4f2db;
    font-size: 0.9rem;
    //padding: 6px 20px;
    width: 100px;
    height: 28px;
    font-weight: bold;
    border-radius: 10px;
    cursor: pointer;
}
select{
    background-color: #d8e2eb;
    border-radius: 10px;
    margin-right: 10px;
    padding: 1px 10px;
    border: 2px solid #b4c1d5;
    width: 70px;
    outline: none;
}
.Search-div{
    text-align: center;
    display: flex;
    .page-select{
        margin-right: 0px;
        margin-left: 10px;
        padding: 1px 5px;
        width: 40px;
        text-align: center;
    }
    .form-control{
        background-color: #d8e2eb;
        border-radius: 10px;
        margin-right: 5px;
        margin-left: 5px;
        padding: 1px 10px;
        border: 2px solid #b4c1d5;
        width: 100%;
    }
}
</style>