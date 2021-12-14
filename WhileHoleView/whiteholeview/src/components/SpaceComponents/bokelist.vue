<template>
<div>
  <div class="showlist" >


    <el-row class="art-item" v-model="userBlogs"  v-for="userblog in userBlogs">
      <el-card shadow="hover" style="height: 220px">
        <el-row class="art-info d-flex align-items-center justify-content-start">
          <div class="art-time"><i class="el-icon-apple"></i>{{userblog.autoer}}</div>
          <div class="d-flex align-items-center">
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
    </el-row>


    <div class="block pagination">
      <!-- 					<el-pagination style="margin-left: 100px;" background="#f9f9f9" layout="prev, pager, next" :total="50">
                </el-pagination> -->

      <el-pagination

        background


        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="PageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"

      >
      </el-pagination>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "bokelist",
  beforeRouteEnter: (to, from, next) => {
    console.log("准备查看列表");
    let islogin = localStorage.getExpire("tokenhole")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },

  data(){
    return{

      currentPage:1,
      // 总条数，根据接口获取数据长度(注意：这里不能为空)
      total: null,

      // 默认每页显示的条数（可修改）
      PageSize:3,


      success: null,
      userBlogs: [],

    }
  },

  mounted() {

    this.axios(
      {
        url:"/boot/space/myblogs",
        method: 'get',
        headers: { "token": localStorage.getExpire("tokenhole") },
        params: {
          size: 3,
        }
      }
    ).then(res=>{
      this.success = res.data.success
      this.userBlogs = res.data.userBlogs
      this.total = res.data.total

      if(this.success != 1){
        alert("页面加载异常！请重新刷新")
      }

      if(this.userBlogs == ''){
        alert("这里还暂时没有文章，期待你的发布呦~")
      }

    })
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val
      console.log(val)
      this.axios({
        url: "/boot/space/myblogs",
        method: 'get',
        headers: { "token": localStorage.getExpire("tokenhole") },
        params: {
          page: val,
          size: 3,
        }
      }).then(res => {
        this.success = res.data.success
        this.userBlogs = res.data.userBlogs
        this.total = res.data.total
        console.log(status)
        if (this.success != 1) {
          alert("页面加载异常！请重新刷新")

        }
      })


    },
  }

}
</script>

<style scoped>

.showlist{
  margin-left: 10%;
  width: 80%;

}


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
