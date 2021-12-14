<template>
<div>
  <el-form :model="formLogin" :rules="rules" ref="ruleForm" label-width="0px" class="login-bok">
    <el-form-item prop="username">
      <el-input v-model="formLogin.username" placeholder="账号">
        <i slot="prepend" class="el-icon-s-custom"/>
      </el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" placeholder="密码" v-model="formLogin.password">
        <i slot="prepend" class="el-icon-lock"/>
      </el-input>
    </el-form-item>
    <el-form-item prop="code">
      <el-row :span="24">
        <el-col :span="12">
          <el-input v-model="formLogin.code" auto-complete="off" placeholder="请输入验证码" size=""></el-input>
        </el-col>
        <el-col :span="12">
          <div class="login-code" @click="refreshCode">
            <!--验证码组件-->
            <s-identify :identifyCode="identifyCode"></s-identify>
          </div>
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item>
      <div class="login-btn">
        <el-button type="primary" @click="goregist()" style="margin-left: auto;width: 35%" >注册</el-button>
        <el-button type="primary" @click="submitForm()" style="margin-left: 27%;width: 35%">登录</el-button>
      </div>
    </el-form-item>
  </el-form>

</div>
</template>

<script>
import SIdentify from "../components/SIdentify"

export default {
  name: "login",
  components: { SIdentify },
  data() {
    return{
      formLogin: {
        username: "",
        password: "",
        code: "",

        token: '',
        success: '',
      },
      identifyCodes: '1234567890abcdefjhijklinopqrsduvwxyz',//随机串内容
      identifyCode: '',
      // 校验
      rules: {
        username:
          [
          { required: true, message: "请输入用户名", trigger: "blur" }
          ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      }

    }
  },
  mounted () {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods:{
    refreshCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode (o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },

    goregist(){
      this.$router.push("/regist")
    }
    ,
    logincount(){
      this.axios({
        url: "/boot/login",
        method: 'post',
        data: {
          username:  this.formLogin.username,
          password: this.formLogin.password.toLowerCase(),
        }
      }).then(res =>{
        this.formLogin.success = res.data.success
        this.formLogin.token = res.data.token
        if(this.formLogin.success =='1'){
          //设置token七天过期
          localStorage.setExpire("tokenhole",this.formLogin.token,604800000);
          alert("登录成功~")
          this.$router.push("/space")
        }
        else {
          alert("用户名或密码错误！")
        }
      })
    },

    submitForm(){

      // sessionStorage.setItem("isLogin",true)
      if (this.formLogin.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()

      }
      else {
        //这边后面做一个提交，服务器验证，通过之后获得token
        this.logincount()
      }
    }

  },

}
</script>

<style scoped>
.login-bok{
  width: 30%;

  margin: 150px auto;
  border: 1px solid #DCDFE6;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 30px #DCDFE6;
}
</style>
