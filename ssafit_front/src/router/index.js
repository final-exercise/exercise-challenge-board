import Vue from 'vue'
import VueRouter from 'vue-router'
import IndexView from '../views/IndexView.vue'

import UserLogin from "../components/user/UserLogin.vue"
import CoachLogin from "../components/coach/CoachLogin.vue"
import VideoView from "../views/VideoView"
import ChallengeView from "../views/ChallengeView"
import ChallengeCreate from "../components/challenge/ChallengeCreate.vue"
import ChallengeIndex from "../components/challenge/ChallengeIndex.vue"
import UserSignupEmail from "../components/user/signup/UserSignupEmail.vue"
import UserMyPage from "../views/UserMyPageView.vue"
import UserSignupView from "../views/UserSignupView.vue"


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: "/login",
    name: "login",
    component: UserLogin
  },{
    path: "/signup",
    component: UserSignupView,
    children:[
      {
      path: "email",
      component: UserSignupEmail
      }
      
    
    ]
     
  },
  {
    path: "/login/coach",
    name: "login/coach",
    component: CoachLogin
  },
  {
    path: "/video",
    component: VideoView
  },
  {
    path: "/mypage",
    component: UserMyPage
  },
  {
    path: "/challenge",
    component: ChallengeView,
    children:[
      {
        path:"/",
        name: "challenge-index",
        component: ChallengeIndex
      },
      {
        path: "create",
        name: 'challenge-create',
        component: ChallengeCreate
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
