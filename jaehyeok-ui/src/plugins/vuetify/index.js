import Vue from "vue";
import Vuetify from "vuetify/lib";
import ko from "vuetify/es5/locale/ko";
import "vuetify/dist/vuetify.min.css";

Vue.use(Vuetify);

export default new Vuetify({
    lang: {
        locales: { ko },
        current: "ko"
    }
});