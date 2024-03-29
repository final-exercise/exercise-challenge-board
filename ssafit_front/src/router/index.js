import Vue from 'vue'
import VueRouter from 'vue-router'
import IndexView from '../views/IndexView.vue'
import CoachManageView from "../views/CoachManageView.vue"
import UserLogin from "../components/user/UserLogin.vue"
import VideoView from "../views/VideoView.vue"
import VideoDetailView from "../views/VideoDetailView.vue"
import ChallengeView from "../views/ChallengeView"
import ChallengeCreate from "../components/challenge/ChallengeCreate.vue"
import ChallengeIndex from "../components/challenge/ChallengeIndex.vue"
import UserSignupEmail from "../components/user/signup/UserSignupEmail.vue"
import UserMyPageView from "../views/UserMyPageView.vue"
import UserSignupView from "../views/UserSignupView.vue"
import UserMyPageInfo from "../components/user/UserMyPageInfo.vue"
import VideoWishList from "../components/video/VideoWishList.vue"
import UserMyRecordView from "../views/UserMyRecordView.vue"
import UserMyRecordCoach from "../components/user/myrecord/UserMyRecordCoach.vue";
import UserMyRecordCalendar from "../components/user/myrecord/UserMyRecordCalendar.vue"
import UserMyRecordCalendarDiet from "../components/user/myrecord/UserMyRecordCalendarDiet.vue"
import UserMyRecordCalendarWorkout from "../components/user/myrecord/UserMyRecordCalendarWorkout.vue"
import UserMyRecordCalendarDate from "../components/user/myrecord/UserMyRecordCalendarDate"
import CoachMyPageInfo from "../components/coach/CoachMyPageInfo.vue"
import CoachManagerUserInfo from "../components/coach/CoachManagerUserInfo.vue"
import CoachManageCalendarDate from "../components/coach/CoachManageCalendarDate.vue"
import ChallengeStart from "../components/challenge/ChallengeStart.vue";
import ChallengeStartItem from "../components/challenge/ChallengeStartItem.vue"

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
    component: UserLogin,
  },
  {
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
    path: "/video",
    component: VideoView,
  },
  {
    path: "/video/:vidoeSeq",
    name: "video-detail",
    component: VideoDetailView,
  },
  {
    path: "/mypage",
    component: UserMyPageView,
    children:[
      {
        path:"info",
        name:"mypage-info",
        component: UserMyPageInfo
      },
      {
        path:"wish",
        name:"mypage-wish",
        component: VideoWishList
      },
    ]
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
      },
      {
        path: ":challengeSeq",
        name: 'challenge-start',
        component: ChallengeStart,
        children:[
          {
            path:"video/:videoSeq",
            name: 'challenge-item',
            component: ChallengeStartItem,
          }
        ]
      }
    ]
  },
  {
    path: "/manage",
    component: CoachManageView,
    children:[
      {
        path: ":userSeq",
        name: "manage-user-coach",
        component: CoachManagerUserInfo,
        props: true,
        children:[
          {
            path:":date",
            name:"coach-calendar-date",
            component: CoachManageCalendarDate,
            props: true
          }
        ]
      },
     
    ]
  },
  {
    path:"/coach/mypage",
    name:"mypage-coach",
    component: CoachMyPageInfo
  },
  {
    path: "/myrecord",
    component: UserMyRecordView,
    children:[
      {
        path:"",
        alias:["","coach"],
        name: "myrecord-coach",
        component: UserMyRecordCoach
      },
      {
        path: "calendar",
        name: 'myrecord-calendar',
        component: UserMyRecordCalendar,
        children:[
          {
            path:"diet",
            name:"myrecord-calendar-diet",
            component: UserMyRecordCalendarDiet,
          },
          {
            path:"workout",
            name:"myrecord-calendar-workout",
            component: UserMyRecordCalendarWorkout
          },
          {
            path:":date",
            name:"myrecord-calendar-date",
            component: UserMyRecordCalendarDate,
            props: true
          }
        ]
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
