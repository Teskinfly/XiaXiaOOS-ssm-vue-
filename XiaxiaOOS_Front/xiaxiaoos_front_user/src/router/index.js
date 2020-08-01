import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../components/Home.vue";
import Login from '../components/Login.vue'
import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/XiaXiaOOS/'
Vue.prototype.$axios = axios
Vue.use(VueRouter);
axios.interceptors.request.use(config=>{
  config.headers.Authorization = window.sessionStorage.getItem("token")
  return config
})
const routes = [
  {
    path: "/",
    redirect:"/login"
  },
  {
    path:'/login',
    component: Login
  },
  {
    path:"/home",
    component:Home
  }
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
