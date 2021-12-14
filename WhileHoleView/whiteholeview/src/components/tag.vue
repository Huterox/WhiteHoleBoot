<template>
	<div class="tag">
		<el-card class="box-card">
			<div slot="header" class="d-flex align-items-center">
				<img class="card-icon" src="../assets/biaoqian.png" />
				<span>{{$t('tag.tag')}}</span>
			</div>
			<div class="text item">
				<el-tag size="mini" class="tag-item" @click="tag('Java',1)">Java</el-tag>
				<el-tag size="mini" class="tag-item" type="success" @click="tag('Python',2)">Python</el-tag>
				<el-tag size="mini" class="tag-item" type="info" @click="tag('前端',3)">前端</el-tag>
				<el-tag size="mini" class="tag-item" type="warning" @click="tag('数据库',4)">数据库</el-tag>
				<el-tag size="mini" class="tag-item" type="danger" @click="tag('Go',5)">Go</el-tag>
				<el-tag size="mini" class="tag-item" type="info" @click="tag('移动开发',6)">移动开发</el-tag>
				<el-tag size="mini" class="tag-item" type="success" @click="tag('运维',7)">运维</el-tag>
        <el-tag size="mini" class="tag-item" type="danger" @click="tag('操作系统',8)">操作系统</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('大数据',9)">大数据</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('微软技术',10)">微软技术</el-tag>
        <el-tag size="mini" class="tag-item" type="info" @click="tag('服务器',11)">服务器</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('智能优化',12)">智能优化</el-tag>
        <el-tag size="mini" class="tag-item" type="info" @click="tag('机器学习',13)">机器学习</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('分布式',14)">分布式</el-tag>
        <el-tag size="mini" class="tag-item" type="danger" @click="tag('深度学习',15)">深度学习</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('神经网络',16)">神经网络</el-tag>
        <el-tag size="mini" class="tag-item" type="success" @click="tag('MATLAB',17)">MATLAB</el-tag>
			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'tag',
		methods: {
			tag(name,channel_id) {
				this.$router.push({
					name: 'bokelistpage',
					params: {
						'name': name,
            "channel_id":channel_id,
					}
				});
			}
		}
	}
</script>

<style scoped>
	.box-card .item:hover {
		color: #409EFF;
		cursor: pointer;
	}

	.box-card span {
		font-weight: bold;
	}

	.card-icon {
		width: 20px;
		height: 20px;
		margin-right: 10px;
	}

	.tag-item {
		margin-right: 10px;
	}
</style>
