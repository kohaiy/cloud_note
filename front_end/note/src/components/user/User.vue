<template>
  <div class="user">
    <el-row>
      <el-col :xs="{span:22,offset:1}" :sm="{span:12,offset:6}" :md="{span:8,offset:8}">
        <h1>用户中心</h1>
        <hr>
        <table class="table table-bordered">
          <tbody>
          <tr>
            <td>用户ID</td>
            <td v-text="user.id">0X00001</td>
          </tr>
          <tr>
            <td>用户名</td>
            <td v-text="user.name">adminF</td>
          </tr>
          </tbody>
        </table>
        <div id="btn-show">
          <el-button type="primary" @click="showChangePassword=!showChangePassword">修改密码</el-button>
        </div>
        <transition name="el-zoom-in-top">
          <el-form v-show="showChangePassword" ref="form" :model="form" :rules="rules" label-position="right"
                   label-width="100px">
            <el-form-item label="原密码：" prop="oldPassword">
              <el-input type="password" v-model="form.oldPassword"></el-input>
            </el-form-item>
            <el-form-item label="新密码：" prop="newPassword">
              <el-input type="password" v-model="form.newPassword"></el-input>
            </el-form-item>
            <el-form-item label="确认密码：" prop="newPassword2">
              <el-input type="password" v-model="form.newPassword2"></el-input>
            </el-form-item>
            <el-button @click="submitChange('form')" type="success" class="pull-right">确认修改</el-button>
          </el-form>
        </transition>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import { CommonService } from '../../service/CommonService';
  import { UserService } from '../../service/UserService';

  export default {
    created () {
      CommonService.isLogin(this.$store, (user) => {
        this.user = user;
      }, () => {
        this.$message.error('您还没有登录！');
        this.$router.push('login');
      });
    },
    data () {
      var checkPassword2 = (rule, value, callback) => {
        if (value !== this.form.newPassword) {
          return callback(new Error('两次密码输入不一致！'));
        } else {
          callback();
        }
      };
      return {
        showChangePassword: false,
        user: null,
        form: {
          oldPassword: '',
          newPassword: '',
          newPassword2: ''
        },
        rules: {
          oldPassword: [
            {required: true, message: '请输入原密码！', trigger: 'blur'},
            {min: 6, max: 20, message: '密码长度必须在6-20字符之间！', trigger: 'change'}
          ],
          newPassword: [
            {required: true, message: '请输入新密码！', trigger: 'blur'},
            {min: 6, max: 20, message: '密码长度必须在6-20字符之间！', trigger: 'change'}
          ],
          newPassword2: [
            {required: true, message: '请确认新密码！', trigger: 'blur'},
            {validator: checkPassword2, trigger: 'change'},
            {min: 6, max: 20, message: '密码长度必须在6-20字符之间！', trigger: 'change'}
          ]
        }
      };
    },
    methods: {
      submitChange (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            UserService.updatePassword(this.form, () => {
              this.$message.success('密码修改成功，请牢记！');
            }, (msg) => {
              this.$message.error(msg);
            });
          }
        });
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .user
    #btn-show
      margin-bottom: 10px
</style>
