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
    }
]
const router = new VueRouter({
    mode: "history",
    routes
});

router.beforeEach((to, from, next) => {


    if(to.path === '/login'){
        return next();
    }
    if (storage.getItem('token') === null){
        return next({path: "/login"})
    }
    return next();
})

export default router;