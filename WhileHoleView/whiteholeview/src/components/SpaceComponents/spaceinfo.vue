<template>
<div>
  <div class="show">
    <p v-model="userinfo" style="margin-left:6%;margin-top: 5%">昵称:<span v-model="userinfo.userName" style="margin-left: 3%;line-height:40px">{{userinfo.userName}}</span></p>
    <p v-model="userinfo" style="margin-left:6%;margin-top: 5%">性别:<span v-model="userinfo.userSex" style="margin-left: 3%;line-height:40px">{{userinfo.userSex}}</span></p>
    <p v-model="userinfo" style="margin-left:6%;margin-top: 5%">邮箱:<span v-model="userinfo.userEmail" style="margin-left: 3%;line-height:40px">{{userinfo.userEmail}}</span></p>
    <p v-model="userinfo" style="margin-left:6%;margin-top: 5%">简介:<span v-model="userinfo.userInfo" style="margin-left: 3%;line-height:40px">{{userinfo.userInfo}}</span></p>
  </div>
</div>
</template>

<script>
//还是等跳转到这里的时候再来加载数据好一点

export default {

  name: "spaceinfo",
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
      userinfo: this.$userinfo,
      success:'',
    }
  },

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
</style>
