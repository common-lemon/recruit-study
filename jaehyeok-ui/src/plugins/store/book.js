//import BookList from '../../assets/data/json_sample_bookList.json'
import axios from "axios";
export default {
    // module화
    namespaced:true,
    // data, 상태 관리
    state:()=>({
        bookList:[],
        theBook:{},
        message: '',
        loading: false,
        searchText: '',
        sort: '',
        pageSize: 5
    }),
    // computed
    getters:{},
    // methods
    // 변이
    // Mutations에서만 State(Data)를 변경할 수 있다.
    mutations: {
        updateState(state, payload) {
            Object.keys(payload).forEach(key => {
                state[key] = payload[key]
            })
        },
        sortList(state,payload){

            let list = state.bookList;
            this.sort = payload.sort.sort

            fn_sortBook(this.sort, list);

        }
    },
    //사용자의 입력에 따라 데이터를 변경하는 methods
    //컴포넌트에서 비동기 로직(Method를 선언해서 API 콜 하는 부분 등)인 Actions를 콜하고,
    //Actions는 비동기 로직만 처리할 뿐 State(Data)를 직접 변경하진 않는다.
    //Actions가 동기 로직인 Mutations를 호출해서 State(Data)를 변경한다.
    actions:{
        async searchList({commit},payload){

            const res = await _fetchBook(payload);

            commit('updateState', {
                bookList: res.data.data,
                searchText: payload.searchText
            })

            let searchText = payload.searchText;
            let list = res.data.data;
            let result = await fn_searchBook(searchText, list);

            let message = "";
            if (result.length < 1){
                message = "도서를 찾을수 없습니다.";
            }

            commit('updateState', {
                bookList:result,
                message: message
            })

        },
        sortList({commit},payload){
            commit('sortList', {
                sort:payload
            })
        },
        pageSize({commit},payload){
            console.log(payload)
            commit('updateState', {
                pageSize:payload.pageSize
            })
        },
        async searchBookWidthId({commit}, payload){
            const res = await _fetchBook(payload)
            console.log(res.data.data)
            commit('updateState',{
                theBook:res.data.data
            })
        },
        async deleteUpdate({commit, state}, payload){
            const res = await _fetchBook(payload);

            commit('updateState', {
                bookList: res.data.data,
            })

            let searchText = state.searchText;
            let list = res.data.data;

            let result = await fn_searchBook(searchText, list);

            let message = "";
            if (result.length < 1){
                message = "도서를 찾을수 없습니다.";
            }

            commit('updateState', {
                bookList:result,
                message: message
            })
            fn_sortBook(this.sort, result);
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

async function fn_searchBook (searchText , list) {
    let result = []
    await list.forEach(function(item) {
        if (item.title.indexOf(searchText) >= 0 ){
            result.push(item)
        }
    })
    return result;
}

function fn_sortBook (sort, list) {

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
    }

    return list;
}
