//import BookList from '../../assets/data/json_sample_bookList.json'
import axios from "axios";
export default {
    namespaced:true,
    state:()=>({
        bookList:[],
        theBook:{},
        message: '',
        loading: false,
        sort:''
    }),
    getters:{},
    mutations: {
        updateState(state, payload) {
            Object.keys(payload).forEach(key => {
                state[key] = payload[key]
            })
        },
        sortList(state,payload){

            let sort = payload.sort.sort
            let list = state.bookList
            if(sort === "deptDesc") {
                list.sort(function(a, b) {
                    a = a.deptName.toString().toLowerCase();
                    b = b.deptName.toString().toLowerCase();
                    return ( a < b ) ? -1 : ( a == b ) ? 0 : 1;
                });
            } else if(sort === "deptAsc") {
                list.sort(function(a, b) {
                    a = a.deptName.toString().toLowerCase();
                    b = b.deptName.toString().toLowerCase();
                    return ( b < a ) ? -1 : ( b == a ) ? 0 : 1;
                });
            } else if(sort === "titleDesc") {
                list.sort(function(a, b) {
                    a = a.title.toString().toLowerCase();
                    b = b.title.toString().toLowerCase();
                    return ( a < b ) ? -1 : ( a == b ) ? 0 : 1;
                });
            } else if(sort === "titleAsc") {
                list.sort(function(a, b) {
                    a = a.title.toString().toLowerCase();
                    b = b.title.toString().toLowerCase();
                    return ( b < a ) ? -1 : ( b == a ) ? 0 : 1;
                });
            } else if(sort === "cntAsc") {
                list.sort(function(a, b) {
                    a = a.count.toString().toLowerCase();
                    b = b.count.toString().toLowerCase();
                    return a - b
                });
            } else if(sort === "cntDesc") {
                list.sort(function(a, b) {
                    a = a.count.toString().toLowerCase();
                    b = b.count.toString().toLowerCase();
                    return b - a
                });
            } else {
                list.sort(function(a, b) {
                    return a.id - b.id;
                });
            }

        }
    },
    actions:{
        async searchList({commit},payload){
            commit('updateState',{
                message:'',
                loading:true
            })

            const res = await _fetchBook(payload);

            commit('updateState', {
                bookList: res.data.data
            })

            let searchText = payload.searchText;
            let list = res.data.data;

            let result = [];
            list.forEach(function(item) {
                if (item.title.indexOf(searchText) >= 0 ){
                    result.push(item)
                }
            })

            let message = "";
            if (result.length < 1){
                message = "도서를 찾을수 없습니다.";
            }

            commit('updateState', {
                bookList:result,
                message: message,
                loading:false
            })

        },
        sortList({commit},payload){
            commit('sortList', {
                sort:payload
            })
        },
        async searchBookWidthId({commit}, payload){
            const res = await _fetchBook(payload)
            console.log(res.data.data)
            commit('updateState',{
                theBook:res.data.data
            })
        }
    }

}
function _fetchBook(payload){

    const{id} = payload
    const url = id
        ? `/api/book/${id}`
        : "/api/book"

    return new Promise((resolve, reject) => {
        axios.get(url)
            .then(response => {
                if(response.data.Error){
                    reject(response.data.Error)
                }
                resolve(response)
            })
            .catch(error => {
                reject(error.message)
            })
    })
}