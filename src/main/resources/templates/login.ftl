<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="Login" class="layout_container" style="position: relative;text-align: center;">
    <div><img src="/static/images/background.jpg" style="width: 100%"></div>
    <div class="login-include-control">
        <div class="layout_content">
        <div class="login-control">
            <div style="margin: 20px;"></div>
            <el-form :model="loginForm" status-icon :rules="rules2" ref="loginForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="选择身份类型" prop="loginNo" >
                    <el-select>
                        <el-option :label="item.value" v-for="(item,index) in identifyType" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="账号" prop="loginNo" >
                    <el-input v-model="loginForm.loginNo" placeholder="请输入手机号" ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pwd">
                    <el-input type="password" v-model="loginForm.pwd" auto-complete="off" placeholder="请输入密码" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
                    <el-button @click="resetForm('loginForm')">重置</el-button>
                    <el-button type="success" @click="navToRegister">注册</el-button>
                    <el-button type="info" @click="navToRegisterSpec">校友注册</el-button>
                </el-form-item>
            </el-form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
<script src="${ctx}/static/modules/public_template.js"></script>
<script>
    const vm = new Vue({
        el: '#Login',
        data: function(){
            var checkAccountNo = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('账号不能为空'));
                }
                setTimeout(() => {
                var phone = value;
                var regs = /^((13[0-9])|(17[0-1,6-8])|(15[^4,\\D])|(18[0-9]))\d{8}$/;
                if (!regs.test(phone)) {
                    callback(new Error('请输入正确的手机号'));
                } else {
                    callback();
                }
            }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };
            return {
                instituteNo:'',
                managerId: "4000001",
                instituteList:[],
                collegeList:[],
                identifyType: [
                    {
                        key:1,
                        value:'管理员'
                    },
                    {
                        key:2,
                        value:'校友'
                    }
                ],
                loginForm: {
                    pwd: '',
                    loginNo: '',
                },
                rules2: {
                    pwd: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    loginNo: [
                        { validator: checkAccountNo, trigger: 'blur' }
                    ],
                },
            }
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            navToRegister(){
                location.href = ctx+ "/base/registerPage";
            },
            navToRegisterSpec(){
                location.href = ctx+ "/audit/alumni-register-special";
            },
            submitForm(formName) {
                var reqData = this.loginForm;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let json = loginSubmit(reqData);
                        json.then((respData) => {
                            if(respData.data.code == 0){
                                this.$message({
                                    message: '登录成功',
                                    type: 'success'
                                });
                                var token = respData.data.result.tokenVal;
                                var managerId = respData.data.result.userId;
                                var collegeNo = respData.data.result.collegeNo;
                                localStorage.setItem("Authorization",token);
                                localStorage.setItem("managerId",managerId);
                                localStorage.setItem("collegeNo",collegeNo);
                                setTimeout(() =>{
                                    location.href = "${ctx!}/audit/alumni-manage";
                            },1000);
                            }else{
                                this.$message({
                                    message: respData.data.message,
                                    type: 'error'
                                });
                            }
                        });
                    } else {
                        console.log('登录失败!');
                        return false;
                }
                });
            },
            selectCollege(val){
                this.collegeNo = val;
            },
            selectInstitute(val){
                this.institute = val;
            },
            resetForm(formName) {
                    this.$refs[formName].resetFields();
            },
            goBack(){
                window.history.back(-1);
            },
            initMethod(){
            },
        }
    })

</script>
