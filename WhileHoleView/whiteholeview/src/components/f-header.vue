<template>
	<div>
		<el-menu :default-active="activeIndex" class="d-flex" mode="horizontal" router="true" type="flex">
			<el-menu-item class="mr-auto">White Hole</el-menu-item>

      <el-input class="search"
        placeholder="请输入搜索内容"
        v-model="search"

        clearable>
      </el-input>

      <el-button type="primary" plain class="searchbutton" @click="SearchTo">{{$t("header.search")}}</el-button>

			<el-menu-item index="/home">{{$t("header.home")}}</el-menu-item>
			<el-menu-item index="/channel">{{$t("header.channel")}}</el-menu-item>
      <el-menu-item index="/about">{{$t("header.about")}}</el-menu-item>

      <el-menu-item index="/writer">{{$t("header.wirter")}}</el-menu-item>
      <el-menu-item index="/space">{{$t("header.space")}}</el-menu-item>
      <el-menu-item index="/interest">{{$t("header.interester")}}</el-menu-item>

      <el-submenu>
        <template slot="title">{{$t("header.language")}}</template>
				<el-menu-item @click="toggleLang('zh')">{{$t("header.chinaese")}}</el-menu-item>
				<el-menu-item @click="toggleLang('en')">{{$t("header.english")}}</el-menu-item>
			</el-submenu>
		</el-menu>
	</div>
</template>

<script>

export default {

    inject: ['reload'],
		data() {
			return {
				activeIndex: '/home',
        search: "",
        success:null,
        userBlogs:null,
        total:null
			};
		},
		methods: {
      SearchTo(){
        if(this.search == ""){
          alert("搜索内容不能为空")
          return
        }
        this.SendToSearch()
      }
      ,
      SendToSearch(){
        this.axios({
          url: "/boot/base/searchblogs",
          method: 'post',
          data:{
            "search":this.search
          }
        }).then(res=>{
          this.success = res.data.success
          this.userBlogs = res.data.userBlogs
          this.total = res.data.total
          if(typeof this.userBlogs == "object"){
            this.userBlogs = window.JSON.stringify(this.userBlogs)
          }
          sessionStorage.setItem("blogs",this.userBlogs)
          sessionStorage.setItem("totals",this.total)

          if(this.success != 1){
            alert("页面加载异常！请重新刷新")
          }
          this.reload()
          // this.$router.go(0)
        })

      }
      ,
			toggleLang(lang) {
				if(lang === 'zh') {
					localStorage.setItem('locale', 'zh')
					this.$i18n.locale = localStorage.getItem('locale')
					this.$message({
						message: '切换为中文！',
						type: 'success'
					})
				} else if(lang === 'en') {
					localStorage.setItem('locale', 'en')
					this.$i18n.locale = localStorage.getItem('locale')
					this.$message({
						message: 'Switch to English!',
						type: 'success'
					})
				}
			}
		}
	}
</script>

<style scoped>

.search {
  width: 25%;
  margin-top: 10px;
  margin-right: 1%;
  margin-left: 16%;

}

.searchbutton {
  margin-top: 10px;
width: 100px;
height: 40px;
margin-right: auto;


}

</style>
