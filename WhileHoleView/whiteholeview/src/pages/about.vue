<template>
	<div class="about">
		<el-row class="main" type="flex" justify="center">
			<el-col :span="16">
				<h5 class="title"><i class="el-icon-star-on"></i>{{$t('about.aboutMe')}}</h5>
				<div class="statement" style="background-color: white">
					<div class="item">Huterox</div>
					<div class="item">大二单身狗一只，学识有限仍需努力！</div>
				</div>
				<div class="statement" style="background-color: white">
					<div class="item">Email：3139541502@qq.com</div>
					<div class="item">QQ：3139541502</div>
					<div class="item">CSDN：
						<a target="_blank" href="https://blog.csdn.net/FUTEROX">https://blog.csdn.net/FUTEROX</a>
					</div>
				</div>
				<h5 class="title"><i class="el-icon-star-on"></i>{{$t('about.aboutBlog')}}</h5>
				<el-card shadow="always">
					<dl class="dl-blog">

						<p>&nbsp;&nbsp;&nbsp;&nbsp;这是真正自由，和谐且没有约束的技术社区，我们支持每一位社区内的朋友创建自己的频道，
              你可以将其公开也可以保持私密，你将拥有对频道的管理权，其中包括频道的内容管理，
              这将意味着每个人都可以成为内容的提供者与管理者。平台只负责对上传内容的安全与合法检测。
              当然我们也拥有官方的频道，我们希望每位朋友也能够在这儿发表自己的文章。如果您有什么宝贵的意见，可以在下方给我留言，
              我们希望我们能够与您一起成长！（由于当前还处在起步阶段，所以暂时不支持自创频道）
            </p>
					</dl>
				</el-card>
				<h5 class="title"><i class="el-icon-star-on"></i>{{$t('about.contactMe')}}</h5>
				<el-card shadow="always">
					<el-form label-position="left" :rules="rules" label-width="80px" ref="formLabelAlign" :model="formLabelAlign">
						<el-form-item :label="$t('about.yourName')" prop="name">
							<el-input v-model="formLabelAlign.name"></el-input>
						</el-form-item>
						<el-form-item :label="$t('about.email')" prop="email">
							<el-input v-model="formLabelAlign.email"></el-input>
						</el-form-item>
						<el-form-item :label="$t('about.content')" prop="content">
							<el-input type="textarea" v-model="formLabelAlign.content"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submitForm('formLabelAlign')">{{$t('about.submit')}}</el-button>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: 'about',
		data() {
			return {
				formLabelAlign: {
					name: '',
					email: '',
					content: ''
				},
				rules: {
					name: [{
						required: true,
						message: this.$t('about.nameTip'),
						trigger: 'blur'
					}],
					email: [{
							required: true,
							message: this.$t('about.emailTip1'),
							trigger: 'blur'
						},
						{
							type: 'email',
							message: this.$t('about.emailTip2'),
							trigger: ['blur', 'change']
						}
					],
					content: [{
						required: true,
						message: this.$t('about.contentTip'),
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if(valid) {
						alert('submit!');
					} else {
						return false;
					}
				});
			}
		}
	}
</script>

<style scoped>
	.dl-blog dd {
		margin-left: 30px;
	}

	.dl-blog .icon {
		width: 20px;
		height: 20px;
	}

	.title {
		margin-top: 40px;
	}

	.statement {
		border-left: 3px solid #F56C6C;
		padding: 20px;
		background-color: #EBEEF5;
		margin-top: 20px;
	}
</style>
