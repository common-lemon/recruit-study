<template>
    <div class="spinner-border">
        <v-progress-circular
            :size="50"
            indeterminate
            color="primary"
        ></v-progress-circular>
    </div>
</template>

<script>

</script>
<style lang="scss" >
.v-progress-circular--indeterminate .v-progress-circular__overlay{
    color: #134775;
}
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