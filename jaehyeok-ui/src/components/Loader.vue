<template>
    <div class="spinner-border">
        <img :src="img" alt="로딩" style="width: 80px; height: 80px" />
    </div>
</template>

<script>
import image from "/src/assets/loading.gif";
export default {
    data(){
        return {
            img: image
        }
    }
}
</script>
<style lang="scss" scoped>
.spinner-border{
    margin:auto;
/*    top:0;
    bottom:0;
    left:0;
    right:0;*/
    height: 80%;
    display: flex;
    width: 90%;
    z-index: 10;
    align-items: center;
    justify-content: center;
    position: absolute;
    &.absolute{
        position: absolute;
    }
    &.fixed{
        position: fixed;
    }
}
</style>