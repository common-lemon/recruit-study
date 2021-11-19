import Vue from "vue";
import VueRouter from "vue-router";

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
]
const router = new VueRouter({
    mode: "history",
    routes
});

export default router;