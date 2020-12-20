<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜品管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜品管理</el-breadcrumb-item>
    </el-breadcrumb>
    <template>
      <el-card class="box-card">
        <el-button type="primary" size="mini" @click="add('category')"
          >添加分类</el-button
        >
        <el-button type="primary" size="mini" @click="add('food')"
          >添加菜品</el-button
        >
        <el-tree
          :data="allFood"
          :props="myProps"
          node-key="id"
          default-expand-all
          :expand-on-click-node="false"
        >
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini" @click="() => editNode(data)">
                编辑
              </el-button>
              <el-button
                type="text"
                size="mini"
                @click="() => deleteNode(node, data)"
              >
                删除
              </el-button>
            </span>
          </span>
        </el-tree>
      </el-card>
      <!-- categoryDialog -->
      <el-dialog title="分类" :visible.sync="categoryDialogVisible" width="30%">
        <div style="margin: 20px"></div>
        <el-form label="left" label-width="80px" :model="category">
          <el-form-item label="分类名称">
            <el-input v-model="category.cname"></el-input>
          </el-form-item>
          <el-form-item label="分类描述">
            <el-input v-model="category.cdesc"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="categoryDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="categorySubmit">确 定</el-button>
        </span>
      </el-dialog>
      <!-- foodDialog -->
      <el-dialog title="提示" :visible.sync="foodDialogVisible" width="40%">
        <!-- <div style="margin: 20px;"></div> -->
        <el-form label="left" label-width="80px" :model="food">
          <el-form-item label="菜品名称">
            <el-input v-model="food.fname"></el-input>
          </el-form-item>
          <el-form-item label="分类名称">
            <el-select v-model="food.fcid" placeholder="请选择">
              <el-option
                v-for="item in allFood"
                :key="item.rid"
                :label="item.name"
                :value="item.rid"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜品描述">
            <el-input v-model="food.fdesc"></el-input>
          </el-form-item>
          <el-form-item label="菜品价格">
            <el-input v-model="food.fprice"></el-input>
          </el-form-item>
          <el-form-item label="菜品库存">
            <el-input v-model="food.finventory"></el-input>
          </el-form-item>
          <el-form-item label="图片">
            <el-upload
              action="http://localhost:8080/XiaXiaOOS/food/upload"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              name="picture"
              :limit="1"
              :on-success="handleSuccess"
              :file-list="fileList"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">不超过10M</div>
            </el-upload>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="foodDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="foodSubmit">确 定</el-button>
        </span>
      </el-dialog>
    </template>
  </div>
</template>
<script>
export default {
  created() {
    this.getAllFood();
  },
  data() {
    return {
      foodDialogVisible: false,
      categoryDialogVisible: false,
      operation: "",
      category: {
        cid: "",
        cname: "",
        cdesc: "",
      },
      food: {
        fid: "",
        fname: "",
        fdesc: "",
        fprice: "",
        fimg: "",
        fcid: "",
      },
      allFood: [],
      myProps: {
        label: "name",
        children: "next",
      },
      fileList: [],
    };
  },
  methods: {
    async getAllFood() {
      const { data: res } = await this.$axios.get("/food/findAllFood");
      // console.log(data)
      console.log(res.data);
      this.allFood = res.data;
      // console.log(this.allFood);
    },
    handleNodeClick(data) {
      console.log(data);
    },
    async deleteNode(node, data) {
      console.log(data.rid);
      if (data.type == "category") {
        const { data: res } = await this.$axios.get(
          "food/deleteCategory?cid=" + data.rid
        );
      } else {
        const { data: res } = await this.$axios.get(
          "/food/deleteFood?fid=" + data.rid
        );
      }
      this.getAllFood();
    },
    async editNode(data) {
      this.fileList = [];
      this.operation = "edit";
      if (data.type == "category") {
        console.log(data);
        this.category.cname = data.name;
        this.category.cid = data.rid;
        this.category.cdesc = data.desc;
        this.categoryDialogVisible = true;
      } else {
        const { data: res } = await this.$axios.get(
          "/food/getFoodById?fid=" + data.rid
        );
        console.log(res.data[0])
        this.food = res.data[0];
        this.fileList = [
          {
            name: this.food.fimg,
            url: this.$axios.defaults.baseURL + "/" + this.food.fimg,
          },
        ];
        this.foodDialogVisible = true;
      }
    },
    async categorySubmit() {
      if (this.operation == "edit") {
        const { data: res } = await this.$axios.post(
          "food/updateCategory",
          this.category
        );
      } else {
        const { data: res } = await this.$axios.post(
          "/food/addCategory",
          this.category
        );
      }
      this.categoryDialogVisible = false;
      this.$message.success("操作成功");
      this.getAllFood();
    },
    async foodSubmit() {
      if (this.operation == "add") {
        const { res: data } = await this.$axios.post("food/addFood", this.food);
      } else if (this.operation == "edit") {
        const { res: data } = await this.$axios.post(
          "food/updateFood",
          this.food
        );
      }
      this.$message.success("菜品信息上传成功");
      this.foodDialogVisible = false;
      this.getAllFood();
    },
    add(type) {
      this.fileList = [];
      this.operation = "add";
      if (type == "category") {
        this.category = {};
        this.categoryDialogVisible = true;
      } else {
        this.food = {};
        this.foodDialogVisible = true;
      }
    },
    handleRemove(file, fileList) {
      console.log("remove");
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log("preview");
      console.log(file);
    },
    handleSuccess(res, file) {
      this.food.fimg = res.data[0];
    },
  },
};
</script>
<style lang="less" scoped>
.el-card {
  .el-tree {
    .custom-tree-node {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 14px;
      padding-right: 8px;
    }
  }
}
</style>
