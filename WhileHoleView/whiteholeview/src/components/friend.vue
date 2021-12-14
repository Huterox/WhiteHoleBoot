<template>
	<div class="friend">
		<el-card class="box-card">
		  <div slot="header" class="d-flex align-items-center">
		  	<img class="card-icon" src="../assets/lianjie.png"/>
		    <span>{{$t('friend.friend')}}</span>
		    <el-button class="ml-auto" style="padding: 3px 0" type="text">
		    	<router-link to="/apply" tag="span">{{$t('friend.exchange')}}</router-link>
		    </el-button>
		  </div>
		  <div class="text item"><a href="https://blog.csdn.net/FUTEROX">站长博客</a></div>
<!--		  在这里后面渲染已经获得许可的友情链接-->
		</el-card>
	</div>
</template>

<script>
export default {
  name: 'friend'
}
</script>

<style scoped>
.box-card .item a{
	text-decoration: none;
	/*color: #F56C6C;*/
}
.box-card span{
	font-weight: bold;
}
.card-icon{
	width: 20px;
	height: 20px;
	margin-right: 10px;
}
</style>
