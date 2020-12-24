<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <div class="img">
          <img src="../assets/xia.png" />
        </div>
      </div>
      <div class="title">
        <span>虾虾订餐系统</span>
      </div>
      <div class="info">
        <el-form :model="user" label-width="0px" :rules="rules" ref="formRef">
          <el-form-item prop="uname">
            <el-input
              prefix-icon="el-icon-user"
              v-model="user.uname"
            ></el-input>
          </el-form-item>
          <el-form-item prop="upwd">
            <el-input
              prefix-icon="el-icon-key"
              v-model="user.upwd"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {
        uname: "123123",
        upwd: "123",
      },
      rules: {
        uname: [
          { required: true, message: "请输如用户名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        upwd: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 3,
            max: 20,
            message: "字符长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    login() {
      this.$refs["formRef"].validate((valide) => {
        if (!valide) {
          return;
        } else {
          let that = this
          that.$axios
            .post("user/login", this.user)
            .then(function(response) {
              if (response.data.status == "200") {
                that.$message.success("登录成功")
                window.sessionStorage.setItem("token",response.data.token)
                console.log(response.data.data)
                window.sessionStorage.setItem("user",JSON.stringify(response.data.data[0]))
                that.$router.push('/home')
              } else {
                that.$message.error("登录失败")
              }
            })
            .catch(function(error) {
              console.log(error);
            });
        }
      });
    },
    resetForm() {
      // this.$refs.formRef.resetFields()
      this.$refs["formRef"].resetFields();
    },
  },
};
</script>
<style lang="less" scoped>
.login_container {
  display: flex;
  // background-color: red;
  height: 100%;
  align-items: center;
  justify-content: center;
  .login_box {
    border: green 2px solid;
    width: 600px;
    height: 500px;
    // background-color: gray;
    .avatar_box {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      // background-color: rgb(76, 0, 255);
      height: 130px;
      .img {
        width: 130px;
        height: 130px;
        border-radius: 50%;
        background-color: yellow;
        transform: translate(0, -50%);
        img {
          width: 100%;
          height: 100%;
        }
      }
    }
    .title {
      height: 60px;
      width: 100%;
      text-align: center;
      font-size: 50px;
    }
    .info {
      padding: 40px 20px 0px 20px;
      // width: 80%;
      .btns {
        display: flex;
        justify-content: flex-end;
      }
    }
  }
}
</style>
