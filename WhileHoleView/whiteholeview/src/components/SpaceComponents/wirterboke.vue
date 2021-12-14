<template>
<div>
  <p>
    这个是个人页面编写博客页面
  </p>
</div>
</template>

<script>
export default {
  name: "wirterboke",
  beforeRouteEnter: (to, from, next) => {
    console.log("准备进入个人信息页");
    let islogin = sessionStorage.getItem("isLogin")
    if(!islogin){
      next({path:'/login'});
    }
    next();
  },
}
</script>

<style scoped>

</style>
