<template>
  <div>
    <el-form :model="formRegist" :rules="rules" ref="ruleForm" label-width="0px" class="login-bok">
      <el-form-item prop="username">
        <el-input v-model="formRegist.username" placeholder="创建账号" :maxlength="16" >
          <i slot="prepend" class="el-icon-s-custom"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"  placeholder="输入密码" :maxlength="16"  v-model="formRegist.password">
          <i slot="prepend" class="el-icon-lock"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="againpassword">
        <el-input type="password" placeholder="再次输入密码" :maxlength="16"  v-model="formRegist.againpassword">
          <i slot="prepend" class="el-icon-lock"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-row :span="24">
          <el-col :span="12">
            <el-input v-model="formRegist.code" auto-complete="off" placeholder="请输入验证码" size=""></el-input>
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
          <el-button type="primary" @click="gologin()" style="margin-left: auto;width: 35%">返回登录</el-button>
          <el-button type="primary" @click="submitForm()" style="margin-left: 27%;width: 35%" >确定</el-button>
        </div>
      </el-form-item>
    </el-form>


  </div>
</template>

<script>
import SIdentify from "../components/SIdentify"
import axios from "axios";

export default {
  name: "login",
  components: {SIdentify},
  data() {
    return {
      formRegist: {
        username: "",
        password: "",
        againpassword: "",
        code: ""
      },
      flag: '',
      pass: '1',
      identifyCodes: '1234567890abcdefjhijklinopqrsduvwxyz',//随机串内容
      identifyCode: '',
      // 校验
      rules: {
        username:
          [
            {required: true, message: "请输入用户名", trigger: "blur"}
          ],
        password: [{required: true, message: "请输入密码", trigger: "blur"}],
        againpassword: [{required: true, message: "请再次输入密码", trigger: "blur"}],
        code: [{required: true, message: "请输入验证码", trigger: "blur"}]
      }

    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },

    gologin() {
      this.$router.push("/login")
    }
    ,

    RightDataInput(){

      if(this.formRegist.username.length<4){
        this.pass='0'
        alert("账号长度不得小于4")

      }
      if(this.formRegist.password.length<8){
        this.pass='0'
        alert("账号长度不得小于8")
      }
      if(this.formRegist.username===null || this.formRegist.password===null){
        this.pass='0'
        this.$message.error('请填写账号或密码')

      }
      if(this.formRegist.password.toLowerCase() !== this.formRegist.againpassword.toLowerCase()){
        this.pass='0'
        this.$message.error('密码与上次输入不匹配')
        alert('密码与上次输入不匹配')
      }
    }
    ,
    Register(){
      this.axios({
        url: "/boot/regist",
        method: 'post',
        headers: { "type": "hello" },
        data: {
          username:  this.formRegist.username,
          password: this.formRegist.password.toLowerCase(),
        }
      }).then(res =>{
        this.flag = res.data.flag;
        if(this.flag =='1'){
          alert("注册成功")
          this.$router.push("/login")
        }
        else {
          alert("注册失败用户名已存在！")
        }
      })


    }
    ,
    submitForm() {

      this.RightDataInput()
      if (this.formRegist.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()
      }
      else {
        if(this.pass=='1'){
          console.log("账号提交注册")
          this.Register()
        }
       }

      },

    }

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
