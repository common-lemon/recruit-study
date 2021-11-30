import Vuex from "vuex";
import Vue from "vue";
import book from "@/plugins/store/book";
import member from "@/plugins/store/member";

Vue.use(Vuex);

const store = new Vuex.Store({
    strict: true,
    modules:{
        book: book,
        member: member
    }
});

export default store;
