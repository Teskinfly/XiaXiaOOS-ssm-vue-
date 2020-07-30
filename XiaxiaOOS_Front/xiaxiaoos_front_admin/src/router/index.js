import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login.vue";
import Home from "../components/Home.vue";
import Welcome from "../components/welcome.vue";
// import goodsList from "../components/admin/goodsList.vue"
import userManage from "../components/user/userManage.vue"
import foodManage from '../components/food/foodManage.vue'
import sale from '../components/statics/sale.vue'
import income from '../components/statics/income.vue'
import orderManage from '../components/order/orderManage.vue'
Vue.use(VueRouter);

const routes = [
  { path: "/login", component: Login },
  { path: "/", redirect: "/login" },
  {
    path: "/home",
    component: Home,
    redirect:'/welcome',
    children: [
      {
        path:'/welcome',component:Welcome
      },
      {
        path:'/userManage',component:userManage
      },
      {
        path:'/foodManage',component:foodManage
      },
      {
        path:'/sale',component:sale
      },
      {
        path:'/income',component:income
      },
      {
        path:'/orderManage',component:orderManage
      }
    ],
  },
];

const router = new VueRouter({
  routes,
});
router.beforeEach((to, from, next) => {
  if (to.path == "/login") return next();
  let i = window.sessionStorage.getItem("token");
  if (!i) return next("/login");
  next();
});
export default router;
