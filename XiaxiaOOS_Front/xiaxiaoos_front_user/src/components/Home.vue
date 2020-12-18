<template>
  <el-container>
    <el-header>
      <div class="title">
        <img src="../assets/xia.png" alt="" />
        <div>
          <span>虾虾订餐前台</span>
        </div>
      </div>
      <el-dropdown trigger="click">
        <span class="el-dropdown-link"
          >{{ user.uname }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="value in myNav" :key="value.id">
            <span @click="showDialog(value.id)">{{ value.name }}</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-main>
      <!-- main -->
      <el-tabs v-model="activeName">
        <el-tab-pane
          v-for="(food, index) in foodList"
          :key="food.id"
          :label="food.name"
          :name="index + ''"
        >
          <div v-for="item in food.next" :key="item.id" class="item">
            <el-image
              :src="getImgUrl(item.img)"
              @click="clickImg(item)"
            ></el-image>
            <div>名称：{{ item.name }}</div>
            <div>价格：{{ item.price }}元</div>
          </div>
        </el-tab-pane>
      </el-tabs>
      <!-- dialog -->
      <!-- personal information -->
      <div class="dialog">
        <el-dialog :visible.sync="userInfoDialogVisible" width="50%">
          <el-form label-position="left" label-width="80px" :model="user">
            <el-form-item label="名称">
              <el-input v-model="user.uname" disabled></el-input>
            </el-form-item>
            <el-form-item label="地址">
              <el-input v-model="user.uaddress" disabled></el-input>
            </el-form-item>
            <el-form-item label="电话号码">
              <el-input v-model="user.uphone" disabled=""></el-input>
            </el-form-item>
          </el-form>
        </el-dialog>
        <!-- addFood -->
        <el-dialog :visible.sync="foodDetailDialogVisible" width="40%">
          <el-card class="box-card">
            <div>名称：{{ this.currentFood.name }}</div>
            <div>价格：{{ this.currentFood.price }}</div>
            <div>描述：{{ this.currentFood.desc }}</div>
            <div>销售量：{{ this.currentFood.amount }}</div>
            <div>
              图片：
              <img
                :src="getImgUrl(this.currentFood.img)"
                style="width:250px;heigth:250px"
              />
            </div>
            <div>
              数量:
              <el-input-number
                v-model="num"
                :min="1"
                :max="10"
              ></el-input-number>
            </div>
          </el-card>
          <span slot="footer" class="dialog-footer">
            <el-button @click="foodDetailDialogVisible = false"
              >取 消</el-button
            >
            <el-button type="primary" @click="addToCart()"
              >加入购物车</el-button
            >
          </span>
        </el-dialog>
        <!-- cartShow -->
        <el-dialog :visible.sync="cartDialogVisible" width="60%">
          <el-card class="box-card">
            <div v-for="cartItem in this.cart" :key="cartItem.id">
              名称：{{ cartItem.name }} 价格：{{ cartItem.price }} 数量：{{
                cartItem.amount
              }}
            </div>
            <span v-if="cart != ''">支付方式：</span>
            <el-select v-model="payment" v-if="cart != ''">
              <el-option
                v-for="item in payments"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
            <div v-if="cart != ''">总价：{{ totalPrice }}</div>
          </el-card>
          <span slot="footer" class="dialog-footer">
            <el-button @click="cartDialogVisible = false">取 消</el-button>
            <el-button v-if="cart != ''" type="primary" @click="submitCart"
              >提交</el-button
            >
          </span>
        </el-dialog>
        <!-- orderShow -->
        <el-dialog
          :visible.sync="orderDialogVisible"
          width="60%"
          title="订单详细查询"
        >
          <el-table :data="orderList" style="width: 100%">
            <el-table-column prop="user.uname" label="用户名" width="100">
            </el-table-column>
            <el-table-column prop="odate" label="日期"> </el-table-column>
            <el-table-column prop="ocontent" label="内容"> </el-table-column>
            <el-table-column prop="opayment" label="支付方式">
            </el-table-column>
            <el-table-column prop="oaddress" label="地址">
            </el-table-column>
            <el-table-column prop="oprice" label="总价"> </el-table-column>
            <el-table-column prop="ostatus" label="订单状态"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  circle
                  @click="edit(scope.row)"
                ></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-dialog>
        <el-dialog :visible.sync="dialogVisible" width="50%">
          <div>
            <el-steps
              :space="200"
              :active="statusTransfer(currentOrderRow.ostatus)"
              finish-status="success"
            >
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
              <el-button type="primary" @click="submitOrders">已接收</el-button>
            </div>
          </span>
        </el-dialog>
      </div>
    </el-main>
  </el-container>
</template>
<script>
export default {
  async created() {
    // localStorage.removeItem("cart");
    //得到全部菜品信息
    const { data: res } = await this.$axios.get("food/findAllFood");
    // console.log(res);
    this.foodList = res.data;
    this.user = JSON.parse(sessionStorage.getItem("user"));
    //得到购物车信息
    let cart = localStorage.getItem("cart");
    if (cart != null) {
      this.cart = JSON.parse(cart);
    }
  },
  data() {
    return {
      activeName: "0",
      foodList: [],
      currentFood: {},
      num: "",
      currentOrderRow: "",
      myNav: [
        {
          id: "0",
          name: "个人信息",
        },
        {
          id: "1",
          name: "购物车",
        },
        {
          id: "2",
          name: "我的订单",
        },
        {
          id: "3",
          name: "退出登录",
        },
      ],
      payments: ["微信支付", "支付宝", "银行卡"],
      payment: "",
      cart: [],
      user: {},
      userInfoDialogVisible: false,
      foodDetailDialogVisible: false,
      cartDialogVisible: false,
      orderDialogVisible: false,
      dialogVisible: false,
      totalPrice: 0,
      orderList: [],
      // cartItem: { id: "", name: "", amount: "" },
    };
  },
  methods: {
    getImgUrl(img) {
      return this.$axios.defaults.baseURL + img;
    },
    async showDialog(id) {
      // console.log(id);
      switch (id) {
        case "0":
          this.userInfoDialogVisible = true;
          break;
        case "1":
          this.totalPrice = 0;
          for (var i = 0; i < this.cart.length; i++) {
            this.totalPrice += this.cart[i].price;
          }
          this.cartDialogVisible = true;
          break;
        case "2":
          // console.log(res);
          this.getOrder();
          this.orderDialogVisible = true;
          break;
        case "3":
          this.logout();
          break;
        case "4":
          this.foodDetailDialogVisible = true;
          break;
        default:
          console.log("no exit" + id);
          break;
      }
    },
    clickImg(data) {
      this.currentFood = data;
      this.showDialog("4");
    },
    addToCart() {
      // console.log(this.cartItem)
      let cartItem = {
        id: "",
        name: "",
        price: "",
        amount: "",
      };
      cartItem.id = this.currentFood.rid;
      cartItem.name = this.currentFood.name;
      cartItem.price = this.currentFood.price;
      cartItem.amount = this.num;
      let i = 0;
      for (i = 0; i < this.cart.length; i++) {
        if (this.cart[i].id == cartItem.id) {
          this.cart[i].amount += cartItem.amount;
          break;
        }
      }
      if (i == this.cart.length) this.cart.push(cartItem);
      localStorage.setItem("cart", JSON.stringify(this.cart));
      this.foodDetailDialogVisible = false;
      this.num = "1";
    },
    async submitCart() {
      let order = {
        opayment: "",
        ouid: "",
        oprice: "",
        // ocontent: "",
        orderDetailList:[]
      };
      let orderDetailList = []
      order.opayment = this.payment;
      order.ouid = JSON.parse(sessionStorage.getItem("user")).uid;
      order.oprice = this.totalPrice;
      // let content = "";
      // for (var i = 0; i < this.cart.length; i++) {
      //   content += "#";
      //   content += this.cart[i].name;
      //   content += "-";
      //   content += this.cart[i].price;
      // }
      // order.ocontent = content;
      for (var i = 0; i < this.cart.length; i++) {
        let orderDetail = {
          odFAmount:'',
          odFId:'',
          fname:''
        }
        // console.log('cart')
        // console.log(this.cart[i])
        orderDetail.odFAmount = this.cart[i].amount
        orderDetail.odFId = this.cart[i].id
        orderDetail.fname = this.cart[i].name
        console.log(this.cart[i].name)
        orderDetailList.push(orderDetail)
      }
      console.log(orderDetailList)
      order.orderDetailList = orderDetailList
      const { data: res } = await this.$axios.post("/order/addOrder", order);
      console.log(res);
      this.cart = [];
      localStorage.removeItem("cart");
      this.cartDialogVisible = false;
      this.$message.success("提交成功");
    },
    edit(row) {
      console.log(row);
      this.currentOrderRow = row;
      console.log(row);
      this.dialogVisible = true;
    },
    statusTransfer(status) {
      if (status == "饭店接单中") return 1;
      if (status == "外卖小哥送餐中") return 2;
      if (status == "用户已接收") return 3;
    },
    async submitOrders() {
      console.log(this.currentOrderRow.oid);
      const { data: res } = await this.$axios.get(
        "/order/changeStatus?oid=" + this.currentOrderRow.oid
      );
      console.log(res);
      this.getOrder();
      this.dialogVisible = false;
      this.$message.success("更改成功");
    },
    confirmStatus() {
      return this.statusTransfer(this.currentOrderRow.ostatus) == 2
        ? true
        : false;
    },
    async getOrder() {
      const { data: res } = await this.$axios.get("order/getOrdersByUser");
      this.orderList = res.data;
    },
    logout() {
      sessionStorage.removeItem("token");
      this.$router.push("/login");
      this.$message.success("退出成功");
    },
  },
};
</script>
<style lang="less" scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  .el-dropdown {
    .el-dropdown-menu {
    }
  }
  img {
    width: 50px;
    height: 50px;
    border: yellow solid;
    border-radius: 50%;
  }
  .title {
    width: 20%;
    // background: rgb(84, 92, 100);
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: x-large;
    // background: red;
  }
}
.el-main {
  .el-tabs {
    .el-tab-pane {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      // margin: 5px;
      align-items: center;
      // justify-content: center;
      .item {
        height: 300px;
        // background-color: red;
        margin: 15px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        .el-image {
          border-radius: 10%;
          height: 250px;
          width: 250px;
        }
      }
    }
  }
}
.dialog {
  .button {
    margin: 15px;
    display: inline-block;
  }
}
</style>
