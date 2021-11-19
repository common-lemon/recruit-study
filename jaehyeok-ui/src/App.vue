<template>
    <div id="app">
        <Header/>
        <RouterView />
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
//import Home from './components/Home'
import Footer from "./components/Footer";

export default {
    components: {
        Header,
        Footer
    }
}
</script>

