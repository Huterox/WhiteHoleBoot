<template>
  <div >
    <div class="show">
      <p class="tips">
        这个是趣味功能，将来将上线用pytorch训练出来的趣味dome。此功能将在Django当中部署，敬请期待~
      </p>
    </div>
  </div>
</template>

<script>


export default {

  name: "Interest",

}
</script>

<style scoped>
.show {
  margin: 100px auto;
  width: 80%;
  height: 450px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 10px;
  background-image: url("../assets/interest.jpg");

}

.show:hover {
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 90px;
}
.tips{

  text-align:center;
  font: 50px helvetica,arial,sans-serif;
  color:#fff;
  text-shadow: #1e88e1 5px 5px 5px;
}
</style>
