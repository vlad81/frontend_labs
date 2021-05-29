import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Articles from '@/views/Articles'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(Vuex)
Vue.use(Router)

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

Vue.config.productionTip = false

const store = new Vuex.Store({
    state: {

    },
    getters: {

    },
    mutations: {

    },
    actions: {
        
    }
})


const router = new Router({
    mode: "history",
    routes : [
        {
            path: '/',
            component: Home
        },
        {
          path: '/login',
          component: Login
      },
      {
        path: '/register',
        component: Register
      },
      {
        path: '/articles',
        component: Articles
      },
    ]
})



new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
