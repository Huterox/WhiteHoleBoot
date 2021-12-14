import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import VueRouter from 'vue-router'
import routes from "./routers/index.js"
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'
import './assets/style.css'
import fHeader from './components/f-header'
import fFooter from './components/f-footer'
import i18n from './i18n/i18n'


import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import axios from "axios";
import {userinfo} from "./userinfo/userinfo";
Vue.config.productionTip = false
Vue.prototype.axios = axios
Vue.use(ElementUI)
Vue.use(VueRouter)
// use

Vue.use(mavonEditor)
Vue.component("f-header",fHeader)
Vue.component("f-footer",fFooter)

//Vue.use(ElementUI, {
//i18n: (key, value) => i18n.t(key, value)
//})
Vue.prototype.$userinfo = userinfo;
Storage.prototype.setExpire=(key, value, expire) =>{
  let obj={
    data:value,
    time:Date.now(),
    expire:expire
  };
  localStorage.setItem(key,JSON.stringify(obj));
}

Storage.prototype.getExpire= key =>{
  let val =localStorage.getItem(key);
  if(!val){
    return val;
  }
  val =JSON.parse(val);
  if(Date.now()-val.time>val.expire){
    localStorage.removeItem(key);
    return null;
  }
  return val.data;
}



const router =new VueRouter({
	routes,
	mode: 'history'
})

new Vue({
	router,
	i18n,
	render: h => h(App),
}).$mount('#app')
