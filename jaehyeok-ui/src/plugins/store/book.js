import BookList from '../../assets/data/json_sample_bookList.json'
export default {
    namespaced:true,
    state:()=>({
        bookList:BookList,
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
            console.log(payload.sort.sort)
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
        searchList({commit},payload){
            commit('updateState',{
                message:'',
                loading:true
            })

            let searchText = payload.searchText
            let list = BookList

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
        }
    }

}