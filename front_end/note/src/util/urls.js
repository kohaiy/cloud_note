var base = 'http://localhost:8080/api';
var suffix = '.api';

function join (url) {
  return base + url + suffix;
}

function noteBook (url) {
  return join('/book' + url);
}

function note (url) {
  return join('/note' + url);
}

export default {
  login: join('/login'),
  register: join('/register'),
  checkUsername: join('/user/checkUsername'),
  validator: join('/user/validator'),
  updatePassword: join('/user/updatePassword'),
  nb_findAll: noteBook('/findAll'),
  nb_create: noteBook('/create'),
  nb_update: noteBook('/update'),
  nb_delete: noteBook('/delete'),
  n_findAll: note('/findAll')
};
