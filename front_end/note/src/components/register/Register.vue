<template>
  <div class="register">
    <el-row>
      <el-col :xs="{span:22,offset:1}" :sm="{span:12,offset:6}" :md="{span:6,offset:9}">
        <h1>注册</h1>
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input v-model="form.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码：" prop="password2">
            <el-input v-model="form.password2" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            已有账号？
            <router-link to="/login">
              <el-button type="text">登录</el-button>
            </router-link>
            <el-button type="primary" class="pull-right" @click="submitRegister('form')">注册</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import { CommonService } from '../../service/CommonService';
  import { UserService } from '../../service/UserService';

  export default {
    created () {
      CommonService.isLogin(this.$store, () => {
        this.$router.push('main');
      });
    },
    methods: {
      submitRegister (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            UserService.register(this.form, () => {
              this.$message.success('注册成功！');
              this.$router.push('/login');
            }, (error) => {
              this.$message.error(error);
            });
          } else {
            this.$message({
              message: '请正确填写！',
              type: 'error'
            });
          }
        });
      }
    },
    data () {
      var checkPassword2 = (rule, value, callback) => {
        if (value !== this.form.password) {
          return callback(new Error('两次密码输入不一致！'));
        } else {
          callback();
        }
      };
      var checkUsername = (rule, value, callback) => {
        UserService.checkUsername({username: value}, callback, () => {
          callback(new Error('用户名已经存在！'));
        });
      };
      return {
        form: {
          username: '',
          password: '',
          password2: ''
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名！', trigger: 'blur'},
            {min: 3, max: 10, message: '用户名长度必须控制在3-10字符之间！', trigger: 'change'},
            {validator: checkUsername, trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码！', trigger: 'blur'},
            {min: 6, max: 20, message: '密码长度必须控制在6-20字符之间！', trigger: 'change'}
          ],
          password2: [
            {required: true, message: '请输入确认密码！', trigger: 'blur'},
            {validator: checkPassword2, trigger: 'change'}
          ]
        }
      };
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
</style>
