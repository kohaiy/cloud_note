var base = '/api';
var suffix = '.api';

function join (url) {
  return base + url + suffix;
}

function noteBook (url) {
  return join('/book' + url);
}

export default {
  login: join('/login'),
  register: join('/register'),
  checkUsername: join('/user/checkUsername'),
  validator: join('/user/validator'),
  updatePassword: join('/user/updatePassword'),
  nb_FindAll: noteBook('/findAll'),
  nb_create: noteBook('/create'),
  nb_update: noteBook('/update'),
  nb_delete: noteBook('/delete')
};
