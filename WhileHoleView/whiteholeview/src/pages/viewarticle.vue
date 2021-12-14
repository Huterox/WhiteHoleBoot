<template>
	<div style="background-color: #f8f8e7">
		<el-row class="main" type="flex" justify="center">
			<el-col :span="16">


        <div style="margin: 0 auto">
          <h3>{{title}}</h3>
        </div>
        <br>
        <h5>
          作者：{{author}}
        </h5>

        <h4>
          <i class="el-icon-time"></i>: {{time}}
        </h4>
        <br>
        <p> 原文： </p>
        <div class="markdown-body" style="border-radius: 5px;border: solid 2px #47aeef" v-model="body" v-html="body"></div>






			</el-col>
		</el-row>
	</div>
</template>

<script>

import 'mavon-editor/dist/css/index.css'

import 'highlight.js/styles/googlecode.css'


	export default {
		name: 'article',



    data(){
      return{

        author :this.$route.params.autoer,
        blogsId: this.$route.params.blogsId,
        title: this.$route.params.title,
        time: this.$route.params.time,
        body: null

      }
    },
    mounted() {

      this.axios({
        url: "/boot/base/viewblog",
        params:{
          BlogId:this.blogsId
        }
      }).then(res=>{
        this.body = res.data.body.body
        if(res.data.success == "0"){
          alert("博客加载异常，请重试~")
        }
      })
    }
  }
</script>

<style scoped>
	#artcle-info {
		padding: 20px;
		background-image: url(../assets/vue.jpg);
		margin-bottom: 40px;
	}

	#artcle-info .abstract {
		color: #ffffff;
		border-left: 3px solid #F56C6C;
		padding: 10px;
		background-color: rgba(126, 129, 135, 0.3);
	}

	#artcle-info .timeAndView {
		padding: 20px;
		line-height: 30px;
		font-size: 16px;
		color: #ffffff;
	}

	pre.has {
		color: #ffffff;
		background-color: rgba(0, 0, 0, 0.8);
	}

	img.has {
		width: 100%;
	}

	#statement {
		border-left: 3px solid #F56C6C;
		padding: 20px;
		background-color: #EBEEF5;
	}

 /deep/ img{
    width: 70%;
    height: 400px;
    margin-left: 15%;
   margin-top: 2%;
}

</style>
