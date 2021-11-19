<template>
    <footer>
        <a href="/"
           target=""
          :to="{path: '/home'}"
          >
            (c) LemonHealthCare
        </a>
    </footer>
</template>
<script>
export default {

}
</script>

<style lang="scss" scoped>
a{
    text-decoration: none;
    font-size: 18px;
    font-weight: 500;
    color: #07223a;
}

footer{
    padding: 30px 0;
    text-align: center;
    opacity: .4;
}
</style>