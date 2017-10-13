import { post } from '../util/http';
import urls from '../util/urls';
import Cookies from 'js-cookie';

export let CommonService = {
  isLogin (store, t = function () {}, f = function () {}, e = function () {}) {
    let user = store.state.user;
    if (user === null) {
      let id = Cookies.get('user_id');
      let name = Cookies.get('user_name');
      let token = Cookies.get('user_token');
      if (id != null && token != null) {
        user = {id, name, token};
        post(urls.validator, user).then(res => {
          console.log(res);
          res = res.data;
          if (res.status === 0) {
            store.commit('user', user);
            t();
          } else {
            store.commit('user', null);
            f();
          }
        }, err => {
          e(err);
        });
      } else {
        f();
      }
    } else {
      t(user);
    }
  }
};
