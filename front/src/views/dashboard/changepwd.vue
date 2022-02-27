<template>
  <div class="app-container">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" v-loading="Loading">
      <el-form-item label="原密码:" prop="oldPass">
        <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
      </el-form-item>
      <el-form-item label="新密码:" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
      </el-form-item>
      <el-form-item label="确认密码:" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')" >提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* eslint-disable */
import { changepwd } from '@/api/changepwd'

export default {
  data() {
    var validateoldPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入原密码"));
      } else {
        if (this.ruleForm.oldPass !== "") {
          this.$refs.ruleForm.validateField("pass");
        }
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      const reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/
      if (value === "") {
        callback(new Error("请输入密码"));
      } 
      else if (value.length < 6) {
        callback(new Error("密码长度不能低于6位"));
      }
      else if (value === this.ruleForm.oldPass) {
        callback(new Error("新旧密码不能相同"));
      }
      else if (!reg.test(value)) {
        callback(new Error("只允许输入数字、字母和下划线"));
      }
      else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      Loading: false,
      ruleForm: {
        oldPass: "",
        pass: "",
        checkPass: ""
      },
      rules: {
        oldPass: [{ validator: validateoldPass, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }]
      }
    };
  },
  created() {
  },
  methods: {

    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if(valid){
          this.Loading = true
          console.log(this.user_account)
          changepwd({user_account:"auditor", user_password:this.ruleForm.oldPass, new_password:this.ruleForm.pass}).then(response => {
            this.$store.dispatch("user/logout");
            this.$message({
              type: 'success',
              message: '修改密码成功，请重新登陆!'
            })
            this.Loading = false  
          }).catch(()  => {
            this.Loading = false  
          })
        }else{
            alert("请按要求输入原密码和新密码！")
            return false
        }
      });
      this.$refs[formName].resetFields();
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
.line {
  text-align: center;
}
.el-form-item {
  width: 300px
}
</style>

