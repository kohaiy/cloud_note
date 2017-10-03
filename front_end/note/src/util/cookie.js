// 获取cookie、
export function getCookie (name) {
  var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
  var arr = document.cookie.match(reg);
  if (arr) {
    return unescape(arr[2]);
  } else {
    return null;
  }
}

// 设置cookie,增加到vue实例方便全局调用
export function setCookie (name, value, expiredays) {
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + expiredays);
  document.cookie = name + '=' + escape(value) + ((expiredays == null) ? '' : ';expires=' + exdate.toGMTString());
}

// 删除cookie
export function delCookie (name) {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval = getCookie(name);
  if (cval != null) {
    document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString();
  }
}
