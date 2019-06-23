<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="Register" class="layout_container">
    <div class="register-include-control">
        <div class="layout_content">
        <div class="login-control">
            <div style="margin: 20px;"></div>
            <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
                <el-form-item label="账号" prop="accountNo" >
                    <el-input v-model.number="ruleForm2.accountNo" placeholder="仅支持手机号" ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm2.pass" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
                    <el-button @click="resetForm('ruleForm2')">重置</el-button>
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
                    if (this.ruleForm2.checkPass !== '') {
                        this.$refs.ruleForm2.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm2.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                tableRowData:'',
                collegeNo: "4111014430",
                identifyCollegeId: "1000001",
                managerId: "4000001",
                instituteNo:'',
                pass: '',
                checkPass: '',
                accountNo: '',
                ruleForm2: {
                    pass: '',
                    checkPass: '',
                    accountNo: ''
                },
                rules2: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    accountNo: [
                        { validator: checkAccountNo, trigger: 'blur' }
                    ]
                },
            }
        },
        mounted: function () {
            // this.initMethod();
            // 菜单选择
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                return false;
                }
                });
            },
            resetForm(formName) {
                    this.$refs[formName].resetFields();
            },
            goBack(){
                window.history.back(-1);
            },
            // 修改
            save() {
                let reqData = {};
                reqData.managerId = '4000004';
                reqData.identifyCollegeId = this.identifyCollegeId;
                reqData.identifyStatus = this.identifyStatus;
                reqData.instituteNo = this.instituteNo;
                reqData.degreeType = this.degreeNo;
                reqData.entranceTime = this.format(this.entranceTime,"yyyy-MM-dd");
                reqData.graduationTime = this.format(this.graduationTime,"yyyy-MM-dd");
                reqData.grade = $('#detail_grade').val();
                reqData.studentNo = $('#studentNo').val();
                reqData.professionName = this.professionName;
                reqData.studentNo = this.studentNo;
                console.log('reqData',reqData);
                let json = levelTwoIdentifyUpdate(reqData);
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    setTimeout(() =>{
                        location.reload();
                    },1000);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
                });
            },
            initMethod(){
                let json = getAlumniInfo({
                    collegeNo: "4111014430",
                    managerId: "4000004",
                    identifyCollegeId: this.identifyCollegeId,
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableRowData = respData.data.result;
                    var that = this;
                    var jobs = this.tableRowData.jobsInfoDto;
                    var partjobs = this.tableRowData.partTimeJobsDto;
                    this.instituteNo = this.tableRowData.instituteNo;
                    this.grade = this.tableRowData.grade;
                    this.entranceTime = this.tableRowData.entranceTime;
                    this.graduationTime = this.tableRowData.graduationTime;
                    this.degreeNo = this.tableRowData.degreeNo;
                    this.professionName = this.tableRowData.professionName;
                    this.studentNo = this.tableRowData.studentNo;
                    this.identifyStatus = this.tableRowData.identifyStatus;
                    if(jobs != null){
                        that.jobsList = that.foreachJob(jobs);
                    }
                    if(partjobs != null){
                        that.partTimejobList = that.foreachJob(partjobs);
                    }
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            }


        }
    })

</script>
