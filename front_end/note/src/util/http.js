import axios from 'axios';
import qs from 'qs';

export function post (url, params) {
  return axios.post(url, qs.stringify(params));
}

export function get (url, params) {
  return axios.get(url + '?' + qs.stringify(params));
}
