<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<#include 'components/top-header.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoOneLevelDetail" class="layout_container" style="position: relative;">
    <div><img src="/static/images/home1.jpg" style="width: 100%"></div>
    <div class="baofu-shop-nav--control" style="position: absolute;margin-top: -660px;">
        <top-header></top-header>
        <div class="layout_content">
        <title-page title="Refund order"></title-page>
        <div class="one-level-list-control">
            <div style="margin: 20px;"></div>
            <el-form label-width="230px" :label-position="right" label-width="80px" :model="tableRowData" style="margin-bottom: 50px;">
                <el-form-item label="姓名">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.userRealName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.genderValue"></el-input>
                </el-form-item>
                <el-form-item label="国家">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.country"></el-input>
                </el-form-item>
                <el-form-item label="省">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.city"></el-input>
                </el-form-item>
                <el-form-item label="市">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.area"></el-input>
                </el-form-item>
                <el-form-item label="民族">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.nation"></el-input>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.birthDate"></el-input>
                </el-form-item>
                <el-form-item label="证件号码（身份证或护照）">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.idCard"></el-input>
                </el-form-item>
                <el-form-item label="原证件号码">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.originalIdCard"></el-input>
                </el-form-item>
                <el-form-item label="学位">
                    <el-select size="large" style="width: 300px;" v-model="tableRowData.trainingLevel" @change="selectDegree($event)">
                        <el-option :label="item.value" v-for="(item,index) in degreeList" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="录取单位">
                    <el-input maxlength="32" style="width: 300px;" v-model="admissionUnit"></el-input>
                </el-form-item>
                <el-form-item label="管理单位">
                    <el-input maxlength="32" style="width: 300px;" v-model="manageUnit"></el-input>
                </el-form-item>
                <el-form-item label="培养单位">
                    <el-input maxlength="32" style="width: 300px;" v-model="trainingUnit"></el-input>
                </el-form-item>
                <el-form-item label="学号">
                    <el-input maxlength="32" style="width: 300px;" v-model="studentNo"></el-input>
                </el-form-item>
                <el-form-item label="新学号">
                    <el-input maxlength="32" style="width: 300px;" v-model="newStudentNo"></el-input>
                </el-form-item>
                <el-form-item label="认证状态">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.identifyStatusStr"></el-input>
                </el-form-item>
                <el-form-item label="入学时间">
                    <div class="block">
                        <el-date-picker
                                style="width: 300px;"
                                v-model="entranceTime"
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
                                v-model="graduationTime"
                                type="date"
                                placeholder="选择日期"
                                default-value="1999-10-01">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="导师">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.tutorName"></el-input>
                </el-form-item>
                <el-form-item label="专业">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.majorName"></el-input>
                </el-form-item>
                <el-form-item label="学生状态">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.studentStatus"></el-input>
                </el-form-item>
                <el-form-item label="工作状态">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.jobStatus"></el-input>
                </el-form-item>
                <el-form-item label="工作单位">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.employer"></el-input>
                </el-form-item>
                <el-form-item label="单位性质">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.unitNature"></el-input>
                </el-form-item>
                <el-form-item label="专家类别">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.expertCategory"></el-input>
                </el-form-item>
                <el-form-item label="专业技术职务">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.specializedTechnicalJob"></el-input>
                </el-form-item>
                <el-form-item label="职务">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.position"></el-input>
                </el-form-item>
                <el-form-item label="通讯地址">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.mailingAddress"></el-input>
                </el-form-item>
                <el-form-item label="电子邮件">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.email"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.phoneNo"></el-input>
                </el-form-item>
                <el-form-item label="qq">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.qq"></el-input>
                </el-form-item>
                <el-form-item label="微信号">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.weixinNo"></el-input>
                </el-form-item>
                <div style="margin-left: 200px">
                    <el-button type="primary" @click="save" plain>保存</el-button>
                    <el-button type="primary" @click="goBack" plain>返回</el-button>
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
    var orderType = 0;
    const vm = new Vue({
        el: '#alumniInfoOneLevelDetail',
        data: {
            tableRowData:'',
            jobsList:'',
            partTimejobList:'',
            ucasInstituteList: [],
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
                }
            ],
            searchText: '',
            refundType:'',
            refundStatus:'',
            searchReturnId:'',
            collegeNo: "",
            identifyCollegeId: "",
            managerId: "",
            genderType:'',
            degreeType:'',
            identifyType:'',
            identifyStatus:'',
            instituteNo:'',
            grade:'',
            entranceTime:'',
            graduationTime:'',
            degreeNo:'',
            majorName:'',
            studentNo:'',
            newStudentNo:'',
            trainingUnit:'',
            manageUnit:'',
            admissionUnit:'',
        },
        mounted: function () {
            this.initParam();
            this.initMethod();
            // 菜单选择
        },
        methods: {
            initParam: function () {
                if (isNotNull(getLocationParameter().identifyCollegeId)) {
                    this.identifyCollegeId = getLocationParameter().identifyCollegeId;
                }
            },
            selectInstitute(val){
                this.instituteNo = val;
            },
            selectDegree(val){
                this.degreeNo = val;
            },
            selectIdentifyStatus(val){
                console.log('val',val);
            },
            foreachJob(jobs){
                var result = '';
                for (var job of jobs){
                    result = result +" "+ job.companyName +" "+ job.workStartTime +" "+ job.workEndTime +" "+ job.positionName + "\n";
                }
                return result;
            },
            goBack(){
                location.href = "${ctx!}/audit/alumni-manage-one-level";
            },
            // 修改
            save() {
                let reqData = {};
                reqData.managerId = localStorage.getItem("managerId");
                reqData.identifyCollegeId = this.identifyCollegeId;
                reqData.collegeNo = localStorage.getItem("collegeNo");
                reqData.identifyStatus = this.identifyStatus;
                reqData.instituteNo = this.instituteNo;
                reqData.admissionUnit = this.admissionUnit;
                reqData.manageUnit = this.manageUnit;
                reqData.trainingUnit = this.trainingUnit;
                reqData.trainingLevel = this.degreeNo;
                reqData.entranceTime = this.format(this.entranceTime,"yyyy-MM-dd");
                reqData.graduationTime = this.format(this.graduationTime,"yyyy-MM-dd");
                reqData.majorName = this.majorName;
                reqData.studentNo = this.studentNo;
                reqData.newStudentNo = this.newStudentNo;
                console.log('reqData',reqData);
                let json = levelOneIdentifyUpdate(reqData);
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
             format(str,fmt) {
                var usedDate = new Date(str);
                var o = {
                    "M+": usedDate.getMonth() + 1, //月份
                    "d+": usedDate.getDate(), //日
                    "h+": usedDate.getHours(), //小时
                    "m+": usedDate.getMinutes(), //分
                    "s+": usedDate.getSeconds(), //秒
                    "q+": Math.floor((usedDate.getMonth() + 3) / 3), //季度
                    "S": usedDate.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (usedDate.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            },
            initMethod(){
                let json = getLevelOneIdentifyInfoDetail({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
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
                    this.admissionUnit = this.tableRowData.admissionUnit;
                    this.manageUnit = this.tableRowData.manageUnit;
                    this.trainingUnit = this.tableRowData.trainingUnit;
                    this.majorName = this.tableRowData.majorName;
                    this.studentNo = this.tableRowData.studentNo;
                    this.newStudentNo = this.tableRowData.newStudentNo;
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
                    setTimeout(() =>{
                        location.href = ctx+ "/base/loginPage";
                },1000);
                }
            });
            }


        }
    })

</script>
