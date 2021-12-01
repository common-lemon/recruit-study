import Vue from "vue";
import VueRouter from "vue-router";
const storage = window.sessionStorage;

Vue.use(VueRouter);

const routes = [
    {
        path: "",
        redirect: '/home'
    },
    {
        path: "/home",
        name: "Home",
        component: () => import("@/components/Home"),
    },
    {
        path: "/bookapply",
        name: "BookApply",
        component: () => import("@/components/BookApply"),
    },
    {
        path: "/bookdetail/id=:id",
        name: "BookDetail",
        component: () => import("@/components/BookDetail"),
    },
    {
        path: "/login",
        name: "Login",
        component: () => import("@/components/Login"),

    },
    {
        path: "/signup",
        name: "SignUp",
        component: () => import("@/components/SignUp"),
    },
    {
        path: "/myinfo",
        name: "MyInfo",
        component: () => import("@/components/MyInfo"),
    },
    {
        path: "/applyList",
        name: "ApplyList",
        component: () => import("@/components/ApplyStatusList"),
    },
    {
        path: "/finishList",
        name: "FinishList",
        component: () => import("@/components/FinishStatusList"),
    },
    {
        path: "/cancelList",
        name: "CancelList",
        component: () => import("@/components/CancelStatusList"),
    },
]
const router = new VueRouter({
    mode: "history",
    routes
});

router.beforeEach((to, from, next) => {

    if(to.path === '/login' || to.path === '/signup'){
        return next();
    }
    if (storage.getItem('token') === null){
        return next({path: "/login"})
    }
    return next();
})

export default router;