//import axios from "axios";
const storage = window.sessionStorage;

export default {
    namespaced: true,
    state: {
        userName: '',
        name: '',
        authority:'',
        token: '',
        loginSuccess: false,
        loginError: false
    },
    mutations: {
        updateState(state, payload) {
            Object.keys(payload).forEach(key => {
                state[key] = payload[key]
            })
        }
    },
    actions:{
        async login({commit},payload){
            console.log( payload.data.data);
            storage.setItem('token' ,payload.data.data.token);
            storage.setItem('authentication' ,JSON.stringify(payload.data.data.authentication));
            commit('updateState', {
                userName: payload.data.data.authentication.userName,
                authority: payload.data.data.authentication.authority,
                token: payload.data.data.token,
            })
        },
        refreshToken({commit},payload){
            commit('updateState', {
                token: payload.token,
                authority: payload.authentication.authority,
                userName:payload.authentication.userName
            })
        }
    }

}