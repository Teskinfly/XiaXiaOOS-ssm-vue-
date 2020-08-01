<template>
  <div class="container">
    <div class="breadcrumb">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>订单管理</el-breadcrumb-item>
        <el-breadcrumb-item>订单管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="seach"></div>
    <div class="orderList">
      <el-table :data="orderList" style="width: 100%">
        <el-table-column prop="user.uname" label="用户名" width="100">
        </el-table-column>
        <el-table-column prop="odate" label="日期"> </el-table-column>
        <el-table-column prop="ocontent" label="内容"> </el-table-column>
        <el-table-column prop="opayment" label="支付方式"> </el-table-column>

        <el-table-column prop="oprice" label="总价"> </el-table-column>
        <el-table-column prop="oaddress" label="地址"> </el-table-column>
        <el-table-column prop="ostatus" label="订单状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="edit(scope.row)"
            ></el-button>

            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteOrder(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tableData.pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="tableData.total"
        layout="total, sizes, prev, pager, next, jumper"
        :total="100"
      >
      </el-pagination>
    </div>
    <div class="dialog">
      <el-dialog :visible.sync="dialogVisible" width="50%">
        <div>
          <el-steps :space="200" :active="status" finish-status="success">
            <el-step title="饭店接单中"></el-step>
            <el-step title="外卖小哥送餐中"></el-step>
            <el-step title="用户已接收"></el-step>
          </el-steps>
        </div>
        <span slot="footer" class="dialog-footer">
          <div class="button">
            <el-button @click="dialogVisible = false">取 消</el-button>
          </div>
          <div v-if="confirmStatus()" class="button">
            <el-button type="primary" @click="submitOrders()"
              >开始送餐</el-button
            >
          </div>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  async created() {
    this.getOrderList();
  },
  data() {
    return {
      dialogVisible: false,
      tableData: {
        total: 5,
        pageNum: 1,
        query: "",
      },
      currentStatus: "",
      orderList: [],
      status: "",
      oid: "",
      //   orders: {
      //     oid: "",
      //     ocontent: "",
      //     odate: "",
      //     opayment: "",
      //     ouid: "",
      //     oprice: "",
      //     ostatus: "",
      //   },
    };
  },
  methods: {
    async getOrderList() {
      const { data: res } = await this.$axios.post(
        "order/getOrders",
        this.tableData
      );
      console.log(res);
      this.orderList = res.data;
    },
    handleSizeChange(newSize) {
      this.tableData.total = newSize;
      this.getOrderList();
    },
    handleCurrentChange(current) {
      this.tableData.pageNum = current;
      this.getOrderList();
    },
    edit(row) {
      this.status = this.statusTransfer(row.ostatus);
      this.oid = row.oid;
      this.dialogVisible = true;
    },
    async deleteOrder(row) {
      console.log(row)
      const { data: res } = await this.$axios.get(
        "order/delOrder?oid=" + row.oid
      );
      console.log(res);
      this.$message.success("删除成功");
      this.getOrderList()
    },
    async submitOrders() {
      const { data: res } = await this.$axios.get(
        "/order/changeStatus?oid=" + this.oid
      );
      this.dialogVisible = false;
      this.getOrderList();
    },
    statusTransfer(status) {
      if (status == "饭店接单中") return 1;
      if (status == "外卖小哥送餐中") return 2;
      if (status == "用户已接收") return 3;
    },
    confirmStatus() {
      return this.status == 1 ? true : false;
    },
  },
};
</script>
<style lang="less" scoped>
.breadcrumb {
  margin: 15px;
}
.orderList {
  margin: 15px;
}
.dialog {
  .button {
    margin: 15px;
    display: inline-block;
  }
}
</style>
