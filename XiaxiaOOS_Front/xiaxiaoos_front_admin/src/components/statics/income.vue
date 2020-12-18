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
    <div class="graph"></div>
  </div>
</template>
<script>
import echarts from 'echarts'
export default {
  data() {
    return {
      date: [],
    };
  },
  methods: {
    async find() {
      let dbegin = this.convertDate(this.date[0]);
      let dend = this.convertDate(this.date[1]);
      const { data: res } = await this.$axios.get(
        "dataAnalyze/income?beginDate=" + dbegin + "&endDate=" + dend
      );
      const mychart = echarts.init(document.getElementsByClassName("graph")[0])
      const option = res.data[0]
      console.log(option)
      option.tooltip = {}
      mychart.setOption(option)
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
<style lang="less" scoped>
.block {
  margin-left: 30px;
  .el-button {
    margin: 15px;
  }
}
.graph {
  margin-left: 30px;
  // background-color: greenyellow;
  width: 1000px;
  height: 450px;
}
</style>
