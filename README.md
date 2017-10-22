# cloud_note
在线云笔记

[在线预览](http://note.gqxqd.cn)

## 前端

+ Vue
+ Element UI

## 后台

+ Spring
+ SpringMVC
+ Mybatis

## 数据库设计

### cn_user

|field|type|
|:--|:--|
|cn_user_id|int(11)|
|cn_user_name|varchar(50)|
|cn_user_password|varchar(255)|
|cn_user_desc|varchar(255)|
|cn_user_token|varchar(255)|
|cn_user_update_time|timestamp|

### cn_notebook

|field|type|
|:--|:--|
|cn_notebook_id|int(11)|
|cn_user_id|int(11)|
|cn_notebook_name|varchar(50)|
|cn_notebook_desc|varchar(255)|
|cn_notebook_createtime|timestamp|

### cn_note

|field|type|
|:--|:--|
|cn_note_id|int(11)|
|cn_notebook_id|int(11)|
|cn_user_id|int(11)|
|cn_note_title|varchar(255)|
|cn_note_content|text|
|cn_note_createtime|timestamp|
|cn_note_updatetime|timestamp|
|cn_note_type|int(11)|


### cn_note_type

|field|type|
|:--|:--|
|cn_note_type_id|int(11)|
|cn_note_type_name|varchar(50)|
|cn_note_type_desc|varchar(255)|

### cn_share

|field|type|
|:--|:--|
|cn_share_id|int(11)|
|cn_share_code|varchar(128)|
|cn_note_id|int(11)|
|cn_share_desc|varchar(255)|
|cn_share_time|timestamp|

## 进度

- [x] 登录、注册
- [x] 笔记本列表
- [x] 笔记本的增删查改
- [x] 笔记列表
- [ ] 笔记的增删查改
- [ ] 笔记的分享
