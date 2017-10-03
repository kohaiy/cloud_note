// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import store from './store';
import router from './router';
import 'bootstrap/dist/css/bootstrap.min.css';
import './common/style/base.styl';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';
import { getCookie } from './util/cookie';

Vue.use(ElementUI);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  created () {
    let user = this.$store.state.user;
    if (!user) {
      var username = getCookie('username');
      if (username) {
        this.$store.commit('user', username);
      }
    }
  },
  router,
  store,
  render: h => h(App)
});
