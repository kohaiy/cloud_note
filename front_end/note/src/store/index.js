import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentPath: '/',
    user: null
  },
  mutations: {
    currentPath (state, path) {
      state.currentPath = path;
    },
    user (state, user) {
      state.user = user;
    }
  }
});
