import Vue from 'vue';
import Router from 'vue-router';
import Login from '../components/login/Login.vue';
import Register from '../components/register/Register.vue';
import Main from '../components/main/Main.vue';
import User from '../components/user/User.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/main',
      name: 'main',
      component: Main
    },
    {
      path: '/user',
      name: 'user',
      component: User
    }
  ]
});
