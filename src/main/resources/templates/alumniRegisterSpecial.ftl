<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="RegisterSpecial" class="layout_container">
    <div><img src="/static/images/background.jpg" style="width: 100%"></div>
    <div class="registerSpec-include-control">
        <div class="layout_content">
        <div class="login-control">
            <div style="margin: 20px;"></div>
            <el-form :model="registerForm" status-icon :rules="rules2" ref="registerForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="姓名">
                    <el-input v-model="registerForm.username" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="registerForm.gender" size="large" style="width: 300px;">
                        <el-option :label="item.value" v-for="(item,index) in genderTypes" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="省">
                    <el-select size="large" v-model="registerForm.province" filterable clearable style="width: 300px;" placeholder="请选择所在省" @change="selectProvince($event)">
                        <el-option :label="item.name" v-for="(item,index) in provinceList" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="市">
                    <el-select size="large" v-model="registerForm.city" filterable clearable style="width: 300px;" placeholder="请选择所在市" @change="selectCity($event)">
                        <el-option :label="item.name" v-for="(item,index) in citiesList" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所在学校" >
                    <el-select size="large" v-model="registerForm.collegeNo" filterable clearable style="width: 300px;" placeholder="请选择所在学校" @change="selectCollege($event)">
                        <el-option :label="item.schoolName" v-for="(item,index) in collegeList" :value="item.schoolId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="培养单位" >
                    <el-select size="large" v-model="registerForm.instituteNo" filterable clearable style="width: 300px;" placeholder="请选择培养单位" @change="selectInstitute($event)">
                        <el-option :label="item.instituteName" v-for="(item,index) in instituteList" :value="item.instituteNo"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年级">
                    <div class="block">
                        <el-date-picker
                                style="width: 300px;"
                                v-model="registerForm.grade"
                                type="year"
                                placeholder="选择入学年级"
                                >
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="入学时间">
                    <div class="block">
                        <el-date-picker
                                style="width: 300px;"
                                v-model="registerForm.entranceTime"
                                type="date"
                                placeholder="选择日期"
                                default-value="1999-10-01">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="毕业时间">
                    <div class="block">
                        <el-date-picker
                                style="width: 300px;"
                                v-model="registerForm.graduationTime"
                                type="date"
                                placeholder="选择日期"
                                default-value="1999-10-01">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="学位">
                    <el-select v-model="registerForm.degree" size="large" style="width: 300px;" @change="selectDegree($event)">
                        <el-option :label="item.value" v-for="(item,index) in degreeList" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="registerForm.email" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="registerForm.phone_no" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="微信号">
                    <el-input v-model="registerForm.weixin_no" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="核心标签">
                    <el-input v-model="registerForm.core_label" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="所属行业">
                    <el-input v-model="registerForm.domain" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="专业">
                    <el-input v-model="registerForm.profession" maxlength="20" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="工作经历">
                    <el-input v-model="registerForm.jobs" style="width: 500px;" autosize type="textarea"></el-input>
                </el-form-item>
                <el-form-item label="社会兼职">
                    <el-input v-model="registerForm.partTimejob" style="width: 500px;" autosize type="textarea"></el-input>
                </el-form-item>
                <el-form-item label="认证方式">
                    <el-select v-model="registerForm.identifyType" size="large" style="width: 300px;" @change="selectIdentifyType($event)">
                        <el-option :label="item.value" v-for="(item,index) in identifyTypes" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学号" v-if="identifyFlag == 2">
                    <el-input v-model="registerForm.studentNo" maxlength="30" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="身份证号" v-if="identifyFlag == 1">
                    <el-input v-model="registerForm.idCardNo" style="width: 300px;"></el-input>
                </el-form-item>
                <div style="margin-left: 200px">
                    <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
                    <el-button @click="resetForm('registerForm')">重置</el-button>
                </div>
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
        el: '#RegisterSpecial',
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
                tableRowData:'',
                jobsList:'',
                partTimejobList:'',
                identifyFlag:'1',
                ucasInstituteList: [],
                instituteList:[],
                collegeList:[],
                provinceList:[],
                citiesList:[],
                degreeList: [
                    {
                        key:1,
                        value:'学士'
                    },
                    {
                        key:2,
                        value:'硕士'
                    },
                    {
                        key:3,
                        value:'博士'
                    },
                    {
                        key:4,
                        value:'其他'
                    },
                ],
                identifyTypes: [
                    {
                        key:1,
                        value:'身份证号'
                    },
                    {
                        key:2,
                        value:'学号'
                    }
                ],
                genderTypes: [
                    {
                        key:1,
                        value:'男'
                    },
                    {
                        key:2,
                        value:'女'
                    }
                ],
                registerForm: {
                    username: '',
                    gender: '',
                    province: '',
                    city: '',
                    collegeNo: '',
                    instituteNo: '',
                    grade: '',
                    degree: '',
                    entranceTime: '',
                    graduationTime: '',
                    email: '',
                    phone_no: '',
                    weixin_no: '',
                    core_label: '',
                    domain: '',
                    profession: '',
                    jobs: '',
                    partTimejob: '',
                    studentNo: '',
                    idCardNo: '',
                    identifyType: '身份证号',
                },
                rules2: {
                    // username: [
                    //     { validator: validateUsername, trigger: 'blur' }
                    // ],
                    // gender: [
                    //     { validator: validateGender, trigger: 'blur' }
                    // ],
                    // province: [
                    //     { validator: validateProvince, trigger: 'blur' }
                    // ],
                    // city: [
                    //     { validator: validateCity, trigger: 'blur' }
                    // ],
                    // collegeNo: [
                    //     { validator: validateCollegeNo, trigger: 'blur' }
                    // ],
                    // instituteNo: [
                    //     { validator: validateInstituteNo, trigger: 'blur' }
                    // ],
                    // grade: [
                    //     { validator: validateGrade, trigger: 'blur' }
                    // ],
                    // degree: [
                    //     { validator: validateDegree, trigger: 'blur' }
                    // ],
                    // entranceTime: [
                    //     { validator: validateEntranceTime, trigger: 'blur' }
                    // ],
                    // graduationTime: [
                    //     { validator: validateGraduationTime, trigger: 'blur' }
                    // ],
                    // email: [
                    //     { validator: validateEmail, trigger: 'blur' }
                    // ],
                    // phone_no: [
                    //     { validator: validatePhoneNo, trigger: 'blur' }
                    // ],
                    // weixin_no: [
                    //     { validator: validateWeixinNo, trigger: 'blur' }
                    // ],
                    // core_label: [
                    //     { validator: validateCoreLabel, trigger: 'blur' }
                    // ],
                    // domain: [
                    //     { validator: validateDomain, trigger: 'blur' }
                    // ],
                    // profession: [
                    //     { validator: validateProfession, trigger: 'blur' }
                    // ],
                    // jobs: [
                    //     { validator: validateJobs, trigger: 'blur' }
                    // ],
                    // partTimejob: [
                    //     { validator: validatePartTimejob, trigger: 'blur' }
                    // ],
                    // studentNo: [
                    //     { validator: validateStudentNo, trigger: 'blur' }
                    // ],
                    // idCardNo: [
                    //     { validator: validateIdCardNo, trigger: 'blur' }
                    // ],
                    // identifyType: [
                    //     { validator: validateIdentifyType, trigger: 'blur' }
                    // ],
                },
            }
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            selectIdentifyType(val){
                this.identifyFlag = val;
            },
            submitForm(formName) {
                var reqData = this.registerForm;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log('提交成功!');
                        <#--let json = registerSubmit(reqData);-->
                        <#--json.then((respData) => {-->
                            <#--if(respData.data.code == 0){-->
                            <#--this.$message({-->
                                <#--message: '注册成功',-->
                                <#--type: 'success'-->
                            <#--});-->
                            <#--setTimeout(() =>{-->
                                <#--location.href = "${ctx!}/base/loginPage";-->
                            <#--},1000);-->
                        <#--}else{-->
                            <#--this.$message({-->
                                <#--message: respData.data.message,-->
                                <#--type: 'error'-->
                            <#--});-->
                        <#--}-->
                        <#--});-->
                    } else {
                        console.log('提交失败!');
                        return false;
                }
                });
            },
            // 修改
            save() {
                let reqData = {};
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
            selectCollege(val){
                this.registerForm.collegeNo = val;
            },
            selectProvince(val){
                this.registerForm.province = val;
                this.getCities(val);
            },
            selectCity(val){
                this.registerForm.city = val;
            },
            selectInstitute(val){
                this.registerForm.instituteNo = val;
            },
            resetForm(formName) {
                    this.$refs[formName].resetFields();
            },
            getCities(proId){
                var that = this;
                let jsonProvinces = getCities(proId);
                jsonProvinces.then((respData) => {
                    if(respData.data.code == 0){
                    if(respData.data.result != null){
                        that.citiesList = respData.data.result;
                    }
                }
                });
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
                //获取省份集合
                let jsonProvinces = getProvinces();
                jsonProvinces.then((respData) => {
                    if(respData.data.code == 0){
                    if(respData.data.result != null){
                        that.provinceList = respData.data.result;
                    }
                }
            });
            }
        }
    })

</script>
