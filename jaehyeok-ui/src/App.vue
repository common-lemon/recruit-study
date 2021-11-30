<template>
    <div id="app">
        <Header/>
        <RouterView />
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Footer from "./components/Footer";
const storage = window.sessionStorage;

export default {
    name: 'App',
    components: {
        Header,
        Footer
    },
    created() {
        if (storage.getItem('token') === null){
            this.$router.push('/login');
        }else if (storage.getItem('token') !== null || storage.getItem('token') !== ""){
            this.$store.dispatch('member/refreshToken',{
                token: storage.getItem('token'),
                authentication: JSON.parse(storage.getItem('authentication')),
            })

        }
    }
}
</script>

