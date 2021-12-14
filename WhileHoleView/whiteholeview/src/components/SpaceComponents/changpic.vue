<template>
  <div>
    <div class="show">
        <div class="show1" >
          <img ref="showing" src="" id="showimg" style="margin-left: 1px;margin-top: 3px">
        </div>
        <br>
        <input multiple="multiple" id="file" ref="file" @click="changepic(this)" type="file" name="userpic"
               style="margin-left: 10%;height: 50px; display:inline-block;width: 50%;">
        <button @click="subchangepic()" style="height: 40px;position: relative; margin-left:15%;">确定</button>
    </div>
  </div>
</template>

<script>
//还是等跳转到这里的时候再来加载数据好一点
export default {

  name: "changepic",
  data(){
    return {
      filename: null,
      f64: null,
      loadImage: ""
    }
  },

  beforeRouteEnter: (to, from, next) => {

    let islogin = localStorage.getExpire("tokenhole")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },

  methods:{



    changepic() {
      document.getElementById('file').onchange = function () {
        var imgFile = this.files[0];
        var fr = new FileReader();

        fr.onload = function () {
          let showing = document.getElementById('showimg')
          let img = fr.result;
          this.f64 = img;
          this.filename = imgFile.name
          showing.src = img;
          showing.style.height = "220px";
          showing.style.width = "220px";
          showing.style.borderRadius="200px"
        };
        fr.readAsDataURL(imgFile);
      }
      },
    cancle(){
      this.$router.push("/space")
    },

    subchangepic(){


      if(this.$refs.file.files[0]!=null){
        this.f64 = this.$refs.file.files[0]
        console.log(this.f64.name)
        let param = new FormData();
        param.append("file",this.f64);
        this.axios({
          url: "/boot/space/changepic",
          method: 'post',
          headers: {
            'Content-Type': 'multipart/form-data',
            "token": localStorage.getExpire("tokenhole"),
          },
          data: param,

        }).then(res=>{
          if(res.data.success == '0'){
            alert("头像上传失败请检查图片格式")
          }
          else {
            alert("图片上传成功")
            this.$router.push("/space")
            this.$router.push("/space")
          }
        })
      }


    }


  }
}
</script>

<style scoped>


button {
  margin-left: 70%;
  width: 15%;
  height: 35px;
  border-width: 0px;
  border-radius: 3px;
  background: #1E90FF;
  cursor: pointer;
  outline: none;
  font-family: Microsoft YaHei;
  color: white;
  font-size: 17px;
}
.show{
  margin: 100px auto;
  width: 80%;
  height: 450px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 10px;

}
.show1{
  margin: 50px auto;
  width: 222px;
  height: 226px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 150px;

}

.show1:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 90px;
}

.show:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 90px;
}

.texti{
  border: 1px solid #ccc;
  padding: 13px 14px;
  width: 30%;

  font-size: 14px;
  font-weight: 300;

  border-radius: 5px; /* 边框半径 */
  background: white; /* 背景颜色 */
  cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
  outline: none; /* 不显示轮廓线 */


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
