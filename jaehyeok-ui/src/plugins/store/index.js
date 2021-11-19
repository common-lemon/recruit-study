import Vuex from "vuex";
import Vue from "vue";
import book from "@/plugins/store/book";

Vue.use(Vuex);

const store = new Vuex.Store({
    strict: true,
    modules:{
        book: book
    }
});

export default store;
