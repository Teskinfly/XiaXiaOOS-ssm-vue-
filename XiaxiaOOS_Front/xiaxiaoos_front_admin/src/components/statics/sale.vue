<template>
  <div class="main"></div>
</template>
<script>
import echarts from 'echarts'
export default {
  created() {
    //   this.makeCharts();
  },
  mounted(){
      this.makeCharts();
  },
  data() {
    return {
    };
  },
  methods: {
    async makeCharts() {
      const myChart = echarts.init(
        document.getElementsByClassName("main")[0]
      );
      const {data:res} = await this.$axios.get("/dataAnalyze/totalSale") 
      const option = res.data[0]
      // console.log(option)
      option.tooltip = {}
      myChart.setOption(option);
    },
  },
};
</script>
<style lang="less">
.main {
  width: 800px;
  height: 500px;
}
</style>