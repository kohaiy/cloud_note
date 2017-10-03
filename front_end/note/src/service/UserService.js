import { post } from '../util/http';
import urls from '../util/urls';
import Cookies from 'js-cookie';

export var UserService = {
  login (data, success, error) {
    post(urls.login, data).then(res => {
      res = res.data;
      if (res.status === 0) {
        Cookies.set('user_name', res.data.cn_user_name);
        Cookies.set('user_id', res.data.cn_user_id);
        Cookies.set('user_token', res.data.cn_user_token);
        success();
      } else {
        error(res.message);
      }
    }, err => {
      console.log(err);
      error('请求错误！');
    });
  },
  register (data, success, error) {
    post(urls.register, data).then(res => {
      res = res.data;
      if (res.status === 0) {
        success(res.message);
      } else {
        error(res.message);
      }
    }, err => {
      console.log(err);
      error('请求错误！');
    });
  },
  checkUsername (data, success, error) {
    post(urls.checkUsername, data).then(res => {
      res = res.data;
      if (res.status === 0) {
        success();
      } else {
        error();
      }
    });
  },
  updatePassword (data, success, error) {
    let id = Cookies.get('user_id');
    let token = Cookies.get('user_token');
    let data2 = {
      id, token, ...data
    };
    console.log(data2);
    post(urls.updatePassword, data2).then(res => {
      res = res.data;
      if (res.status === 0) {
        success();
      } else {
        error(res.message);
      }
    }, err => {
      console.log(err);
      error('请求错误！');
    });
  },
  logout (store, callback = () => {}) {
    Cookies.remove('user_id');
    Cookies.remove('user_name');
    Cookies.remove('user_token');
    store.commit('user', null);
    callback();
  }
};
