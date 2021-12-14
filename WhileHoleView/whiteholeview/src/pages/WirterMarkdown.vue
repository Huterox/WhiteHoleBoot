
<template>
  <div class="m_container">

    <!-- 博客内容 -->
    <div class="m_content">
      <el-form ref="editForm" status-icon :model="editForm"   label-width="80px">

        <input type="text" name="blogname" placeholder="请输入文章标题" v-model="editForm.title">
        <el-button  id="submit" type="primary" @click="tosubmitForm('editForm')">发布文章</el-button>


        <br>
        <br>
        <mavon-editor
          v-model="editForm.content"
          ref="md"
          @imgAdd="imgAdd"
          @change="change"
          style="min-height: 800px;width: 100%"
        />

      </el-form>
    </div>

    <!-- 对话框内容 -->
    <el-dialog title="发布文章" :visible.sync="dialogFormVisible" width="35%">
      <el-form :model="editForm" ref="editForm2">

        <el-form-item label="描述" prop="description">
          <textarea :maxlength="120"
                    v-model="editForm.description" style="  width: 80%;height: 150px;border-color: lightgrey;border-radius: 5px"
                    class="texti" placeholder="请编写博文描述（必填）"
          ></textarea>
        </el-form-item>
        <el-form-item label="分类专栏" prop="channel_id" :rules="{ required: true, message: '分类专栏不能为空', trigger: 'blur'}">

          <el-select v-model="editForm.channel_id" placeholder="请选择频道">
            <el-option v-for="(item,index) in baseChannels" :key="item.index" :label="item.channelName" :value="item.id + ''"></el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="博客封面" prop="first_picture" >
          <el-input v-model="editForm.first_picture" placeholder="请输入封面URL"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitBlog('editForm2')">确定</el-button>
      </div>
    </el-dialog>




  </div>
</template>
<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import axios from "axios";

export default {
  name: "Writeboke",
  data() {
    return {
      editForm: {  //博客文章表单

        title: '',
        description: '',
        first_picture: '',
        content: '',
        channel_id: '',
        flag:'',
        published: null,

      },

      okimg: 1,

      baseChannels: [],  // 进入页面的时候需要被加载的玩意儿
      success: null, //交互状态！



      editForm2: {  //用来校验的表单

        channel_id: null,
        flag:'',
        published: null,
      },
      oldtags:'',  //字符串类型的标签
      type:{  //分类专栏
        name:''
      },

      dialogFormVisible: false,  //控制发布博客对话框
      dialog2: false,  //控制新增分类专栏对话框


    }
  },
  beforeRouteEnter: (to, from, next) => {
    console.log("准备进入个人信息页");
    let islogin = localStorage.getExpire("tokenhole")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },

  mounted() {
    //获取频道，本来是打算直接在前端获取的，但是想了想还是算了吧，从服务器这边拿数据吧
    this.axios({
      url: "/boot/getbasechannels",
      method: 'post',
    }).then(res=>{
      this.baseChannels = res.data.baseChannels
      this.success = res.data.success
      if(this.success == '0'){
        alert("数据加载异常")
      }
    })

  },
  methods: {
    //去发布文章

    isokPic(){
      this.axios({
        url:this.editForm.first_picture,

      }).catch(error=>{
          this.okimg = 0
      })
    },

    toSubmit() {
      this.dialogFormVisible = true
      this.initType()
    },
    //初始化文章专栏
    initType(){

    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addNewType()
        } else {
          return false;
        }
      });
    },
    //校验博客基本内容表单
    tosubmitForm(formName) {
      if(this.editForm.title == ''){
        alert("文章标题不能为空")
        return
      }
      if(this.editForm.content == ''){
        alert("文章内容不能为空")
        return
      }

      this.toSubmit()

    },
    //校验发布博客表单,校验成功后发布博客
    submitBlog(formName) {
      if(this.editForm.description == ''){
        alert("文章描述不能为空")
        return
      }
      if(this.editForm.channel_id == ''){
        alert("请选择分类")
        return;
      }

      this.isokPic()

      console.log(this.okimg)
      setTimeout(this.sendBlog,2000)

    },

    sendBlog(){
      if(this.okimg == 1){

            //发布博客
            this.editForm.content = this.html;

            this.axios({
              url: "/boot/userblog/saveblog",
              method: "post",
              data: {
                boke: this.editForm
              },
              headers:{

                "token": localStorage.getExpire("tokenhole"),
              }
            }).then(res=>{
              if(res.data.success == 0){
                this.dialogFormVisible = false
                alert("博客发送失败")
                return
              }
              alert("博客发布成功")
              this.dialogFormVisible = false

            })

            console.log("博客发布")

          } else {
            return false;
          }


      if(this.okimg == 0){
        alert("目标图片地址拒绝了我们的访问,请更换图片源！")
        return
      }

    },

    imgAdd(pos, $file){
      let param = new FormData()
      param.append("file",$file)
      this.axios({
        url: "/boot/boke/bokeImg",
        method: "post",
        data: param,
        headers:{
          'Content-Type': 'multipart/form-data',
          "token": localStorage.getExpire("tokenhole"),
        }
      }).then(res=>{
        if(res.data.success == 0){
          alert("图片上传失败")
          return
        }
        let url = "/boot"+ res.data.bokeImg
        this.$refs.md.$img2Url(pos,url)

      })

    },


    // 所有操作都会被解析重新渲染
    change(value, render){        //value为编辑器中的实际内容，即markdown的内容，render为被解析成的html的内容
      this.html = render;
    },
    // 提交
    submit(){                    //点击提交后既可以获取html内容，又可以获得markdown的内容，之后存入到服务端就可以了
      console.log(this.editForm.content);
      console.log(this.html);
    },

  }
}
</script>

<style>
.m_container{
  margin-top: 20px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}



input {

  width: 85%;
  height: 30px;
  border-width: 2px;
  border-radius: 5px;
  border-color: #00c4ff;
  border-bottom-color: #2C7EEA;
  color: #586e75;
  font-size: 15px;

}

#submit {
  width: 10%;
  height: 35px;
  border-width: 0px;
  margin-left: 3%;
  border-radius: 10px;
  background: #1E90FF;
  cursor: pointer;
  outline: none;
  color: white;
  font-size: 17px;
}
#submit:hover {
  background-color: #1E90FF;
  box-shadow: 0 4px 0 powderblue;
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
