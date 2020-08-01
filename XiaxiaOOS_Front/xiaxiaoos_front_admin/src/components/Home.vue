<template>
  <!-- <el-row>
            <el-button type="info" @click="logout">退出</el-button>
        </el-row> -->
  <el-container>
    <el-header>
      <div class="title">
        <img src="../assets/xia.png" alt="" />
        <div>
          <span>虾虾订餐系统后台</span>
        </div>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
          default-active="activePath"
        >
          <el-submenu
            :index="item.id + ''"
            v-for="item in menuList"
            :key="item.id"
          >
            <template slot="title">
              <i :class="menuIcon[item.id]"></i>
              <span>{{ item.authName }}</span>
            </template>
            <el-menu-item
              :index="'/' + i.path"
              v-for="i in item.children"
              :key="i.id"
              @click="saveCurrent('/' + i.path)"
              >{{ i.authName }}</el-menu-item
            >
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      activePath: "",
      menuIcon: [
        "el-icon-goods",
        "el-icon-fork-spoon",
        "el-icon-s-data",
        "el-icon-document-remove",
      ],
      menuList: [
        {
          id: "0",
          authName: "用户管理",
          path: null,
          children: [
            {
              id: "00",
              authName: "用户管理",
              path: "userManage",
            },
          ],
        },
        {
          id: "1",
          authName: "菜品管理",
          level: "0",
          path: null,
          children: [
            {
              id: "10",
              authName: "菜品列表",
              path: "foodManage",
              children: [],
            },
          ],
        },
        {
          id: "2",
          authName: "数据统计",
          path: null,
          children: [
            {
              id: "20",
              authName: "销量",
              path: "sale",
              children: [],
            },
            {
              id: "21",
              authName: "收入",
              path: "income",
              children: [],
            },
          ],
        },
        {
          id: "3",
          authName: "订单管理",
          path: null,
          children: [
            {
              id: "20",
              authName: "订单管理",
              path: "orderManage",
              children: [],
            },
          ],
        },
      ],
    };
  },
  created() {
    this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
      this.$message.success("退出成功");
    },
    saveCurrent(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
  },
};
</script>
<style lang="less" scoped>
.el-container {
  height: 100%;
  .el-header {
    display: flex;
    justify-content: space-between;
    background-color: rgb(90, 130, 110);
    img {
      width: 50px;
      height: 50px;
      border: yellow solid;
      border-radius: 50%;
    }
    .title {
      width: 20%;
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: x-large;
    }
  }
  .el-aside {
    background-color: rgb(84, 92, 100);
  }
  .el-main {
    padding: 0;
  }
}
</style>
