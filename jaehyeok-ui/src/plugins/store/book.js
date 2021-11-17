import BookList from '../../assets/data/json_sample_bookList.json'
export default {
    namespaced:true,
    state:()=>({
        bookList:BookList,
    }),
    getters:{},

}