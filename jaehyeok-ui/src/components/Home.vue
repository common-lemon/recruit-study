<template>
    <div class="container">
        <Search/>
        <BookList/>
    </div>
</template>
<script>
import BookList from "../components/BookList";
import Search from "../components/Search";
export default {
    components:{
        BookList,
        Search
    }

}
</script>
<style lang="scss" scoped>
/*common*/
button {
    margin-top: 10px;
    margin-bottom: 10px;
    margin-right: 15px;
    border: none;
    background: #134775;
    color: #f4f2db;
    font-size: 0.9rem;
    //padding: 6px 20px;
    width: 80px;
    height: 25px;
    font-weight: bold;
    border-radius: 30px;
    cursor: pointer;
}
.container {
    background-color: aliceblue;
    .btn-div{
        text-align: right;
    }
}
</style>