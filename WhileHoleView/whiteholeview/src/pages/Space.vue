<template>
<div style="background-color: white">

  <el-container style="height: 850px; border: 1px solid #eee;">

    <el-aside width="200px" style="margin-top: 1%" >
      <div style="width: 190px;background-color: white;padding: 5px;margin: auto">
        <img v-model="userinfo.userPic"  v-bind:src="userinfo.userPic" width="150px" height="150px" style="border-radius: 300px;margin-left: 8%">
        <br>
        <br>
        <p style="margin:0 auto;text-align: center" v-model="userinfo.userName">{{userinfo.userName}}</p>
      </div>

      <br>
      <br>
      <el-menu :default-openeds="['1','2']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>用户中心</template>
          <el-menu-item-group>

            <router-link class="alink" to="/space/spaceinfo"><el-menu-item index="1-1">个人信息</el-menu-item></router-link>
            <router-link class="alink" to="/space/changepic"><el-menu-item index="1-2">修改头像</el-menu-item></router-link>
            <router-link class="alink" to="/space/changeinfo"><el-menu-item index="1-3">修改信息</el-menu-item></router-link>
            <el-menu-item index="2-2" @click="loginout()">退出登录</el-menu-item>
          </el-menu-item-group>

        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>博客管理</template>
          <el-menu-item-group>
            <router-link class="alink" to="/space/bokelist"><el-menu-item index="2-1">博客列表</el-menu-item></router-link>
            <router-link class="alink" to="/writer"><el-menu-item index="2-2">书写文章</el-menu-item></router-link>

          </el-menu-item-group>

        </el-submenu>


      </el-menu>
    </el-aside>


    <el-container style="background-color: white;background-image: url(static/userpic/background.jpg);">
      <el-main>
        <router-view/>
      </el-main>
    </el-container>



  </el-container>

</div>
</template>

<script>
import Spaceinfo from "../components/SpaceComponents/spaceinfo";
import vue from "vue";
export default {

  name: "Space",
  components: {Spaceinfo},
  beforeRouteEnter: (to, from, next) => {
    console.log("准备进入个人信息页");
    let islogin = localStorage.getExpire("tokenhole")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },
  data() {

    return {
      userinfo: this.$userinfo,
    }
  },

  methods:{
    loginout(){
      localStorage.removeItem("tokenhole");
      this.$router.push('/login')
    }
    ,
    getUserInfo(){
      this.axios({
        url: "/boot/space/userinfo",
        method: 'post',
        headers: { "token": localStorage.getExpire("tokenhole") },

      }).then(res =>{
        let data = res.data
        if(data.success == '0'){
          alert("数据获取异常，请重新登录！")

          this.loginout()
          return;
        }
        if(data.userPic==null){
          data.userPic = this.userinfo.userPic
        }
        if(data.userSex==null){
          data.userSex = this.userinfo.userSex
        }
        this.userinfo = data
        vue.prototype.$userinfo = this.userinfo
        vue.prototype.$userinfo.userPic = "/boot" +this.userinfo.userPic
        this.userinfo.userPic = vue.prototype.$userinfo.userPic



      })

    },

  },

  mounted() {
    this.getUserInfo()

  }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.alink{

  text-decoration: none;
}
</style>
