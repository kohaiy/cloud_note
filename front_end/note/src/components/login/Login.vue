<template>
  <div class="login">
    <el-row>
      <el-col :xs="{span:22,offset:1}" :sm="{span:12,offset:6}" :md="{span:6,offset:9}">
        <h1>登录</h1>
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="form.username">
              <template slot="prepend"><i class="glyphicon glyphicon-user"></i></template>
            </el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input type="password" v-model="form.password">
              <template slot="prepend"><i class="glyphicon glyphicon-eye-close"></i></template>
            </el-input>
          </el-form-item>
          <el-form-item>
            没有账号？
            <router-link to="/register">
              <el-button type="text">注册</el-button>
            </router-link>
            <el-button class="pull-right" type="primary" @click="submitLogin('form')">登录</el-button>
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
    data () {
      return {
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            {required: true, message: '请输入您的用户名！', trigger: 'blur'},
            {
              min: 3,
              max: 10,
              message: '用户名长度必须在3-10字符之间！',
              trigger: 'change'
            }
          ],
          password: [
            {required: true, message: '请输入您的密码！', trigger: 'blur'},
            {
              min: 6,
              max: 20,
              message: '密码长度必须在6-20字符之间！',
              trigger: 'change'
            }
          ]
        }
      };
    },
    methods: {
      submitLogin (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.form);
            UserService.login(this.form, () => {
              this.$message.success('哟，客官快进来瞧瞧(＾Ｕ＾)ノ~ＹＯ~~');
              this.$router.push('/main');
            }, (msg) => {
              this.$message.error(msg);
            });
          } else {
            this.$message({
              message: '请正确填写！',
              type: 'error'
            });
            return false;
          }
        });
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
</style>
