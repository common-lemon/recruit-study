<template>
    <header class="header">
        <slider
            :width="300"
            format="overlay"
            direction="left"
            :opacity="0.5"
            :links=this.links
        >

        </slider>

        <h2>Lemon library</h2>
    </header>
</template>

<script>
import Slider from '@jeremyhamm/vue-slider'
export default {
    components:{
        'slider': Slider
    },
    data(){
        return {
            links:[
                {'id': 1, 'text': '도서 현황', 'url': '/home'},
                {'id': 2, 'text': '도서 신청', 'url': '/bookapply'},
            ],
            closeOnClick: true

        }
    },
    methods:{

    }
}
</script>
<style lang="scss">
.navIcon{
    margin-left: -15px !important;
    margin-right: 15px !important;
    margin-top: 10px !important;
}
.navMenu{
    background-color: #d8dde7  !important;
    padding-top: 70px !important;
}
.navMenu a{
    color: #134775  !important;
}
header{
  height: 60px;
  background-color: #134775;
  color: #f4f2db;
  padding: 0 40px;
  display: flex;
  align-items: center;
  position: relative;
  h2{
    color: aliceblue;
    font-family: 'Titillium Web';
  }
}
</style>