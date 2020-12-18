<template>
  <div class="container">
    <div class="block">
      <!-- <span class="demonstration">默认</span> -->
      <el-date-picker
        v-model="date"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      >
      </el-date-picker>
      <el-button type="primary" @click="find">查询</el-button>
    </div>
    <div class="main"></div>
  </div>
</template>
<script>
import echarts from "echarts";
export default {
  created() {
    //   this.makeCharts();
  },
  mounted() {
    this.makeCharts();
  },
  data() {
    return {
      date: [],
    };
  },
  methods: {
    async makeCharts() {
      const myChart = echarts.init(document.getElementsByClassName("main")[0]);
      const { data: res } = await this.$axios.get("/dataAnalyze/totalSale");
      const option = res.data[0];
      // console.log(option)
      option.tooltip = {};
      myChart.setOption(option);
    },
    async find() {
      let begin = this.convertDate(this.date[0]);
      let end = this.convertDate(this.date[1]);
      const { data: res } = await this.$axios.get(
        "/dataAnalyze/totalSale?begin=" + begin + "&end=" + end
      );
      console.log(res)
      const myChart = echarts.init(document.getElementsByClassName("main")[0]);
      const option = res.data[0];
      // console.log(option)
      option.tooltip = {};
      myChart.setOption(option);
    },
    convertDate(date) {
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      if (month <= 9) {
        month = "0" + month;
      }
      let day = date.getDate();
      if (day <= 9) {
        day = "0" + day;
      }
      return year + "-" + month + "-" + day;
    },
  },
};
</script>
<style lang="less">
.block {
  margin-left: 30px;
  .el-button {
    margin: 15px;
  }
}
.main {
  width: 800px;
  height: 500px;
}
</style>