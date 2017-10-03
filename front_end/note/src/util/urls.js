var base = '/api';
var suffix = '.api';

function join (url) {
  return base + url + suffix;
}

function noteBook (url) {
  return join('/notebook' + url);
}

export default {
  login: join('/login'),
  register: join('/register'),
  checkUsername: join('/user/checkUsername'),
  validator: join('/user/validator'),
  updatePassword: join('/user/updatePassword'),
  nbFindNormal: noteBook('/findNormal')
};
