import "babel-polyfill";
import Vue from 'vue'
import App from './App.vue'
import router from "./plugins/router"
import store from "./plugins/store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import Slider from '@jeremyhamm/vue-slider'

Vue.config.productionTip = false
Vue.use(Slider);
new Vue({
  vuetify,
  store,
  axios,
  router,
  render: h => h(App),
}).$mount('#app')
