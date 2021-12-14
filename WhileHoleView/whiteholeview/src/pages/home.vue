<template>
	<div class="home">
		<el-row id="artList" type="flex" justify="space-around">
			<el-col :span="16">

				<el-row class="art-item" v-model="userBlogs"  v-for="userblog in userBlogs">
					<el-card shadow="hover">
<!--            这个连接要需要传递参数 文章名，作者，公开状态（待定）-->
						<h5><router-link :to="{name:'Article',params:{'blogsId':userblog.id,'title':userblog.title,'time':userblog.createTime,'autoer':userblog.autoer} }" tag="span" class="art-title">查看全文</router-link></h5>
						<el-row class="art-info d-flex align-items-center justify-content-start">
							<div class="art-time"><i class="el-icon-apple"></i>：{{userblog.autoer}}</div>
							<div class="d-flex align-items-center"><img class="tag" src="../assets/tag.png" />：
								<el-tag size="mini">{{userblog.title}}</el-tag>
							</div>
						</el-row>
						<el-row class="art-body">
							<div class="side-img hidden-sm-and-down"><img class="art-banner" v-model="userblog.blogPic" v-bind:src="userblog.blogPic"></div>
							<div class="side-abstract">
								<div class="art-abstract">
									{{userblog.shuoMing}}
								</div>
								<div class="art-more">
									<router-link :to="{name:'Article',params:{'blogsId':userblog.id,'title':userblog.title,'time':userblog.createTime,'autoer':userblog.autoer} }" tag="span">
										<el-button plain>{{$t('home.readMore')}}</el-button>
									</router-link>

								</div>
							</div>
						</el-row>
					</el-card>
					<img class="star" src="../assets/star.png" />
				</el-row>


        <!-- 分页 -->
				<div class="block pagination">

          <el-pagination
            style="margin-left: 30%;"
            background

            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="PageSize"
            layout="total, prev, pager, next, jumper"
            :total="total"

          >
          </el-pagination>


				</div>

			</el-col>
			<el-col :span="6" class="hidden-sm-and-down" id="side">
				<div class="item">
					<tag></tag>
				</div>
				<div class="item">
					<friend></friend>
				</div>
			</el-col>
		</el-row>

	</div>
</template>

<script>
	import friend from '../components/friend'
	import tag from '../components/tag'
	export default {
		name: 'home',
    inject: ['reload'],
		components: {
			friend,
			tag
		},
    data() {
      return{
        currentPage:1,
        // 总条数，根据接口获取数据长度(注意：这里不能为空)
        total: null,
        // 默认每页显示的条数（可修改）
        PageSize:5,
        success: null,
        userBlogs: [],
      }
    },
    mounted() {

      let total_s = sessionStorage.getItem("totals")
      let userBlogs_s = sessionStorage.getItem("blogs")

      if(!total_s) {
        this.init_get()
      }else {
        if(total_s == 0) {
          alert("未搜索到结果")
          this.init_get()
        }else {
          this.userBlogs = window.JSON.parse(userBlogs_s)
          this.total = total_s
          sessionStorage.removeItem("totals")
          sessionStorage.removeItem("blogs")
        }
      }

    },

    methods:{

      init_get(){

        this.axios({
          url: "/boot/base/bolgs"
        }).then(res => {
          this.success = res.data.success
          this.userBlogs = res.data.userBlogs
          this.total = res.data.total
          this.PageSize = 5
          console.log(status)
          if (this.success != 1) {
            alert("页面加载异常！请重新刷新")
          }
        })

      },
      handleCurrentChange(val){

        this.currentPage = val
        console.log(val)
        this.axios({
          url :"/boot/base/bolgs",
          method:'get',
          params:{
            page: val,
            size: 5,
          }
        }).then(res=>{
          this.success = res.data.success
          this.userBlogs = res.data.userBlogs
          this.total = res.data.total
          console.log(status)
          if(this.success != 1){
            alert("页面加载异常！请重新刷新")

          }
        })

      },
    }

	}

</script>

<style scoped>
	#side .item {
		margin-bottom: 30px;
	}

	.art-item {
		margin-bottom: 30px;
		position: relative;
	}

	.art-item .star {
		width: 60px;
		height: 60px;
		position: absolute;
		top: 0;
		right: 0;
	}

	img.tag {
		width: 16px;
		height: 16px;
	}

	.art-title {
		border-left: 3px solid #F56C6C;
		padding-left: 5px;
		cursor: pointer;
	}

	.art-title:hover {
		padding-left: 10px;
		color: #409EFF;
	}

	.art-time {
		margin-right: 20px;
	}

	.art-body {
		display: flex;
		padding: 10px 0;
	}

	.side-img {
		height: 150px;
		width: 270px;
		overflow: hidden;
		margin-right: 10px;
	}

	img.art-banner {
		width: 100%;
		height: 100%;
		transition: all 0.6s;
	}

	img.art-banner:hover {
		transform: scale(1.4);
	}

	.side-abstract {
		flex: 1;
		display: flex;
		flex-direction: column;
	}

	.art-abstract {
		flex: 1;
		color: #aaa;
	}

	.art-more {
		height: 40px;
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
	}

	.art-more .view {
		color: #aaa;
	}
	h5{
		font-size: 18px;
	}
	.pagination {
		background-color: #F9F9F9;

	}

</style>
