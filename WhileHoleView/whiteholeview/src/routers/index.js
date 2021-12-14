import home from '../pages/home'
import about from '../pages/about'
import viewarticle from '../pages/viewarticle'
import channel from '../pages/channel'
import apply from '../pages/apply'
import Space from '../pages/Space'
import WirterMarkdown from "../pages/WirterMarkdown"
import Interest from "../pages/Interest"
import Login from "../pages/login"
import Regist from "../pages/regist"
import BokeList from "../components/SpaceComponents/bokelist"
import Changeinfo from "../components/SpaceComponents/changeinfo"
import Changpic from "../components/SpaceComponents/changpic"
import Spaceinfo from "../components/SpaceComponents/spaceinfo"
import Wirterboke from "../components/SpaceComponents/wirterboke"
import Bokelistpage from "../pages/bokelistpage";



const routes = [
{
	path:'/',
    component:home
},
{
	path:'/home',
	name:'home',
  component:home
},
{
    path:'/about',
    name:'about',
    component:about
},
{
    path:'/channel',
    name:'channel',
    component:channel
},
{
    path:'/tag/:name',
    name:'tag',
    component:channel,
    props: true
},
{
    path:'/article',
    name:'Article',
    component:viewarticle
},
{
    path: "/bokelistpage",
    name: "bokelistpage",
    component: Bokelistpage,
},
{
    path:'/apply',
    name:'apply',
    component:apply
},
{
    path: '/space',
    name: 'space',
    component: Space,
    children:[
      {
        path: 'bokelist',
        name: 'bokelist',
        component: BokeList,
      },
      {
        path: "changeinfo",
        name: 'changeinfo',
        component: Changeinfo,
      },
      {
        path: "changepic",
        name: "changepic",
        component: Changpic
      },
      {
        path: "spaceinfo",
        name: "spaceinfo",
        component: Spaceinfo,
      },
      {
        path: "wirterboke",
        name: "wirterboke",
        component: Wirterboke
      }
    ]
},
{
  path: '/writer',
  name: 'writer',
  component: WirterMarkdown

},

{
  path: '/interest',
  name: 'interest',
  component: Interest,

},

{
  path: '/login',
  name: 'login',
  component: Login,
},
{
  path: '/regist',
  name: 'regist',
  component: Regist,
},

{
    path:'*',
    redirect:'/'
},


]
export default routes;
