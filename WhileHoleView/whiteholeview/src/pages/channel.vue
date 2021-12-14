<template>
	<div class="show">

    <el-row class="art-item" v-for="channel in channels ">
      <el-card shadow="hover">
        <h5><router-link :to="{ name: 'bokelistpage', params: {channel_id:channel.channel_id,name:channel.channelname}}" tag="span" class="art-title">进入频道</router-link></h5>
        <el-row class="art-info d-flex align-items-center justify-content-start">
          <div class="d-flex align-items-center"><img class="tag" src="../assets/tag.png" />：
            <el-tag size="mini">{{channel.channelname}}</el-tag>
          </div>
        </el-row>
        <el-row class="art-body">
          <div class="side-img hidden-sm-and-down"><img class="art-banner" v-bind:src="channel.pic" ></div>
          <div class="side-abstract">
            <div class="art-abstract">
             {{channel.info}}
            </div>
            <div class="art-more">
              <router-link :to="{ name: 'bokelistpage', params: {name:channel.channelname,channel_id:channel.channel_id }}" tag="span">
                <el-button plain>进入频道</el-button>
              </router-link>

            </div>
          </div>
        </el-row>
      </el-card>

    </el-row>
  </div>
</template>

<script>

	export default {

		name: 'channel',

    data(){
      return{
        channels: [
          {channel_id:1,  channelname:'java',pic: 'static/channelpic/java.jpg',info:'Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程 。 Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点 。 Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等 。'},
          {channel_id:2,  channelname: 'python',pic:'static/channelpic/python.jpg',info:'Python 是一个高层次的结合了解释性、编译性、互动性和面向对象的脚本语言。 Python 的设计具有很强的可读性，相比其他语言经常使用英文关键字，其他语言的一些标点符号，它具有比其他语言更有特色语法结构。 Python 是一种解释型语言： 这意味着开发过程中没有了编译这个环节。'},
          {channel_id:3,  channelname: '前端',pic:'static/channelpic/前端.png',info: '前端开发 是创建 WEB 页面或 APP 等前端界面呈现给用户的过程，通过 HTML ， CSS 及 JavaScript 以及衍生出来的各种技术、框架、解决方案，来实现互联网产品的 用户界面 交互 。'},
          {channel_id:4,  channelname: '数据库',pic:'static/channelpic/数据库.jpg',info: '数据库是数据管理的有效技术，是由一批数据构成的有序集合，这些数据被存放在结构化的数据表里。 数据表之间相互关联，反映客观事物间的本质联系。'},
          {channel_id:5,  channelname: 'Go',pic:'static/channelpic/Go.jpg',info: 'Go语言（或 Golang）起源于 2007 年，并在 2009 年正式对外发布。. Go 是非常年轻的一门语言，它的主要目标是"兼具 Python 等动态语言的开发速度和 C/ C++ 等编译型语言的性能与安全性"。'},
          {channel_id:6,  channelname:'移动开发',pic:'static/channelpic/移动开发.jfif',info: '移动开发也称为 手机开发 ，或叫做移动互联网开发，移动应用开发等。. 是指以手机、PDA、UMPC等便携终端为基础，进行相应的开发工作，由于这些随身设备基本都采用无线上网的方式，因此，业内也称作为无线开发。'},
          {channel_id:7,  channelname: '运维',pic:'static/channelpic/运维.jfif',info: '运维是一件对知识面要求很高的工作，它要求运维者不仅要懂得基本的系统与网络知识，还要对运维的业务系统有较深的理解，知道整套业务系统的工作模式与工作原理。'},
          {channel_id:8,  channelname: '操作系统',pic:'static/channelpic/操作系统.jpg',info: '操作系统（operation system，简称OS）是管理 计算机硬件 与 软件 资源的 计算机程序 。 操作系统需要处理如管理与配置 内存 、决定 系统资源 供需的优先次序、控制 输入设备 与 输出设备 、操作网络与管理文件系统等基本事务。 操作系统也提供一个让用户与系统交互的操作界面。'},
          {channel_id:9,  channelname: '大数据',pic:'static/channelpic/大数据.jfif',info: '“大数据”是一个涵盖多种技术的概念，简单地说，是指无法在一定时间内用常规软件工具对其内容进行抓取、管理和处理的数据集合。. IBM将“大数据”理念定义为4个V，即大量化 (Volume)、多样化 (Variety)、快速化 (Velocity)及由此产生的价值 (Value)。'},
          {channel_id:10, channelname: '微软技术',pic:'static/channelpic/微软技术.jfif',info: '微软技术是面向微软，拥抱微软的开发社区，面向微软，同时为技术社区提供活力。'},
          {channel_id:11, channelname: '服务器',pic:'static/channelpic/服务器.jfif',info: '服务器英文名称为“Server”，指的是在网络环境中为客户机（Client）提供各种服务的、特殊的专用计算机。. 在网络中，服务器承担着数据的存储、转发、发布等关键任务，是各类基于客户机／服务器（C／S）模式网络中不可或缺的重要组成部分。'},
          {channel_id:12, channelname: '智能优化',pic:'static/channelpic/智能优化.jpg',info: '智能优化算法（intelligent optimization algorithms）是基于计算智能的机制求解复杂优化问题最优解或满意解的方法，学术界也称之为meta-heuristics。'},
          {channel_id:13, channelname: '机器学习',pic:'static/channelpic/机器学习.jfif',info: '器学习是人工智能的一个子集，即用机器去学习以前的经验。 与传统的编程不同，开发人员需要预测每一个潜在的条件进行编程，一个机器学习的解决方案可以有效地基于数据来适应输出的结果。' },
          {channel_id:14, channelname: '分布式',pic:'static/channelpic/分布式.jpg',info: '分布式系统是多个处理机通过通信线路互联而构成的松散耦合的系统。 从系统中某台处理机来看，其余的处理机和相应的资源都是远程的，只有它自己的资源才是本地的。 至今，对分布式系统的定义尚未形成统一的见解。'},
          {channel_id:15, channelname: '深度学习',pic:'static/channelpic/深度学习.jfif',info: '深度学习就是 让计算机模拟人类的认知过程，从经验中学习（也就是所谓的直觉） 。 让计算机像人一样，根据 层次化的概念体系 来理解世界，而每个概念则通过与某些相对简单的概念之间的关系来定义（ 构建较简单的概念来学习复杂概念 ）。'},
          {channel_id:16, channelname: '神经网络',pic:'static/channelpic/神经网络.jfif',info: '神经网络（Neural Networks,NN）是由大量的、简单的处理单元（称为神经元）广泛地互相连接而形成的复杂网络系统，它反映了人脑功能的许多基本特征，是一个高度复杂的非线性动力学习系统。'},
          {channel_id:17, channelname: 'MATLAB',pic:'static/channelpic/MATLAB.jfif',info: 'MATLAB是美国 MathWorks 公司出品的商业 数学软件 ，用于 数据分析 、 无线通信 、 深度学习 、 图像处理 与 计算机视觉 、 信号处理 、量化金融与风险管理、机器人， 控制系统 等领域。'},
        ]
      }
    },
    mounted() {
      console.log("s",this.name,this.$route.params.name)

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
.show{
  width: 70%;
  margin: auto ;
}
</style>
