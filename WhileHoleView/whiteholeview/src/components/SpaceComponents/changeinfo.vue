<template>
  <div>
    <div class="show">
      <form>
      <p  style="margin-left:16%;margin-top: 3%;height:30px">昵称:<span style="margin-left: 5%"><input ref="username" :maxlength="10" placeholder="修改昵称" class="texti" style="height: 8%" type="text" maxlength="10"></input></span></p>
      <p  style="margin-left:16%;margin-top: 3%;height:30px">性别:<span style="margin-left: 5%"><input v-model="checkedman" ref="man" checked id="man" type="radio" value="1"  name="1" />男 &nbsp;&nbsp;&nbsp;<input v-model="checkedman" ref="woman" id="woman" type="radio" value="2" name="1"/>女</span></p>
      <p  style="margin-left:16%;margin-top: 3%;height:30px">邮箱:<span style="margin-left: 5%"><input  ref="email" :maxlength="30" id="emailAddress" type="email" placeholder="修改邮箱" style="height: 8%" class="texti"></input></span></p>
      <p  style="margin-left:16%;margin-top: 3%;height:200px">简介:<span style="margin-left: 5%"><textarea ref="jianjie" style="  width: 60%;height: 80%;"  class="texti" placeholder="请开始编辑" :maxlength="120"></textarea></span></p>
        <el-button style="margin-left: 10%;width: 20%;height: 15%" class="btn" @click="cancle()">取消</el-button>
        <el-button style="margin-left: 40%;width: 20%;height: 15%" class="btn" @click="submitForm()">确定</el-button>
      </form>
    </div>
  </div>
</template>

<script>
//还是等跳转到这里的时候再来加载数据好一点

export default {

  name: "changeinfo",
  beforeRouteEnter: (to, from, next) => {
    console.log("准备进入个人信息页");
    let islogin = localStorage.getExpire("tokenhole")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },
  data(){
    return{
      checkedman:"1",
      checkedwoman:""

    }
  },
  methods:{
    cancle(){
      this.$router.push("/space")
    },

    changeinfo(){
      this.axios({
        url: "/boot/space/changeuserinfo",
        method: 'post',
        headers: { "token": localStorage.getExpire("tokenhole") },
        data: {
          name: this.$userinfo.userName,
          email: this.$userinfo.userEmail,
          sex: this.$userinfo.userSex,
          userinfo: this.$userinfo.userInfo
        },

      }).then(res =>{
        let data = res.data
        if(data.success == '0'){
          alert("数据提交异常请重新提交~")
        }
        else {
          alert("信息修改成功")
          this.$router.push("/space")
        }

      })
    }
    ,
    submitForm(){
    //  先改变本地的数值，然后上传到服务器

      if(this.$refs.email.value!=''){

        if(this.$refs.email.value!==this.$userinfo.userEmail){
          if(this.$refs.email.value.indexOf("@") >= 0){
            this.$userinfo.userEmail = this.$refs.email.value
          }
          else {
            alert("请输入正确的邮箱地址！")
            return
          }

        }
      }

      if(this.$refs.jianjie.value!=''){
        if(this.$refs.jianjie.value!==this.$userinfo.userInfo){
          this.$userinfo.userInfo= this.$refs.jianjie.value
        }
      }

      if(this.$refs.username.value!=''){
        if(this.$refs.username.value!==this.$userinfo.userName){
          this.$userinfo.userName = this.$refs.username.value
        }
      }


    //  由于男女是存储值的，所以还是要判断一下的，默认是男的所以只需要看看是说不是小姐姐就好了
      if(this.checkedwoman===2){
        this.$userinfo.userSex='女'
      }


    //这部分是后面和后端打交道的，最后回去space页面
    this.changeinfo()

    }

  }
}
</script>

<style scoped>
.show{
  margin: 100px auto;
  width: 80%;
  height: 450px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 10px;

}

.show:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 90px;
}

.texti{
  border: 1px solid #ccc;
  padding: 13px 14px;
  width: 30%;

  font-size: 14px;
  font-weight: 300;

  border-radius: 5px; /* 边框半径 */
  background: white; /* 背景颜色 */
  cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
  outline: none; /* 不显示轮廓线 */


}
.texti:focus{
  border-color: #1e88e1;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}
textarea {
  resize: none;

}
</style>
