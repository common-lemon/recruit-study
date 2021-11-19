<template>
  <div class="Search-div">
    <input
        v-model="searchText"
        class="form-control"
        type="text"
        placeholder="제목"
        @keyup.enter="apply"/>
    <button class="btn btn-primary"
            @click="apply">
      검색
    </button>
    <div class="selects">
      <select
          id="categories"
          v-model="sort"
          class="form-select"
          placeholder="sort"
          @change="selectChange()"
      >
        <option value="" disabled selected hidden>정렬↓</option>
        <option
            v-for="item in sortList"
            :key="item.key"
            :value="item.key">
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
  methods:{
    async selectChange(){
      await this.$store.dispatch('book/sortList', {
        sort: this.sort
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
  width: 70px;
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
  width: 60px;
}
.Search-div{
  text-align: center;
  display: flex;
  .form-control{
    background-color: #d8e2eb;
    border-radius: 10px;
    margin-right: 5px;
    margin-left: 15px;
    padding: 1px 10px;
    border: 2px solid #b4c1d5;
    width: 100%;
  }
}
</style>