<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="Register" class="layout_container">
    <div><img src="/static/images/background.jpg" style="width: 100%"></div>
    <div class="register-include-control">
        <div class="layout_content">
        <div class="login-control">
            <div style="margin: 20px;"></div>
            <el-form :model="registerForm" status-icon :rules="rules2" ref="registerForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户姓名" prop="username" >
                    <el-input v-model="registerForm.username" placeholder="请输入姓名" ></el-input>
                </el-form-item>
                <el-form-item label="所在学校" prop="college" >
                    <el-select size="large" v-model="registerForm.college" filterable clearable style="width: 300px;" placeholder="请选择所在学校" v-model="collegeNo" @change="selectCollege($event)">
                        <el-option :label="item.schoolName" v-for="(item,index) in collegeList" :value="item.schoolId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所在学院" prop="institute" >
                    <el-select size="large" filterable clearable style="width: 300px;" placeholder="请选择所在学院" v-model="registerForm.institute" @change="selectInstitute($event)">
                        <el-option :label="item.instituteName" v-for="(item,index) in instituteList" :value="item.instituteNo"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="账号" prop="accountNo" >
                    <el-input v-model="registerForm.accountNo" placeholder="仅支持手机号" ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="registerForm.pass" auto-complete="off" placeholder="请输入密码" ></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="registerForm.checkPass" auto-complete="off" placeholder="请输入确认密码" ></el-input>
                </el-form-item>
                <el-form-item label="注册邀请码" prop="invitation" >
                    <el-input v-model.number="registerForm.invitation" placeholder="请输入注册邀请码" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
                    <el-button @click="resetForm('registerForm')">重置</el-button>
                    <el-button type="warning" @click="navToLogin" style="margin-left: 161px;">返回登录</el-button>
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
        el: '#Register',
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
                    if (this.registerForm.checkPass !== '') {
                        this.$refs.registerForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.registerForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validateUsername = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户姓名不能为空'));
                }else {
                    callback();
                }
            };
            var checkInvitation = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('注册邀请码不能为空'));
                }else {
                    callback();
                }
            };
            var checkCollege = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('所在学校不能为空'));
                }else {
                    callback();
                }
            };
            return {
                instituteNo:'',
                instituteList:[],
                collegeList:[],
                registerForm: {
                    pass: '',
                    checkPass: '',
                    accountNo: '',
                    invitation: '',
                    college: '',
                    institute: '',
                    username: ''
                },
                rules2: {
                    username: [
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    accountNo: [
                        { validator: checkAccountNo, trigger: 'blur' }
                    ],
                    invitation: [
                        { validator: checkInvitation, trigger: 'blur' }
                    ],
                    college: [
                        { validator: checkCollege, trigger: 'blur' }
                    ],
                },
            }
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            navToLogin(){
                location.href = ctx+ "/base/loginPage";
            },
            submitForm(formName) {
                var reqData = this.registerForm;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let json = registerSubmit(reqData);
                        json.then((respData) => {
                            if(respData.data.code == 0){
                            this.$message({
                                message: '注册成功',
                                type: 'success'
                            });
                            setTimeout(() =>{
                                location.href = "${ctx!}/base/loginPage";
                            },1000);
                        }else{
                            this.$message({
                                message: respData.data.message,
                                type: 'error'
                            });
                        }
                        });
                    } else {
                        console.log('提交失败!');
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
                //获取学校集合
                var that = this;
                let jsonCollege = getColleges();
                jsonCollege.then((respData) => {
                    if(respData.data.code == 0){
                    if(respData.data.result != null){
                        that.collegeList = respData.data.result;
                    }
                    }
                });
                //获取学院集合
                let jsonInstitute = getInstitute();
                jsonInstitute.then((respData) => {
                    if(respData.data.code == 0){
                    if(respData.data.result != null){
                        that.instituteList = respData.data.result;
                    }
                }
                });
            }
        }
    })

</script>
