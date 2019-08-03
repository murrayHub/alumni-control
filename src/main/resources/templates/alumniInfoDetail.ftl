<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<#include 'components/top-header.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoDetail" class="layout_container" style="position: relative;">
    <div><img src="/static/images/home1.jpg" style="width: 100%"></div>
    <div class="baofu-shop-nav--control" style="position: absolute;margin-top: -660px;">
        <top-header></top-header>
        <div class="layout_content" style="float: left;">
        <title-page title="Refund order"></title-page>
        <div class="refund-order-list--control">
            <div style="margin: 20px;"></div>
            <el-form :label-position="right" label-width="80px" :model="tableRowData" style="margin-bottom: 50px;">
                <el-form-item label="姓名">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.studentName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.genderValue"></el-input>
                </el-form-item>
                <el-form-item label="省">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.province"></el-input>
                </el-form-item>
                <el-form-item label="市">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.city"></el-input>
                </el-form-item>
                <el-form-item label="学校">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.collegeName"></el-input>
                </el-form-item>
                <el-form-item label="培养单位">
                    <el-select size="large" style="width: 300px;" v-model="tableRowData.instituteName" @change="selectInstitute($event)">
                        <el-option :label="item.instituteName" v-for="(item,index) in tableRowData.ucasInstituteDoList" :value="item.instituteNo"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年级">
                    <el-input maxlength="50" id="detail_grade" style="width: 300px;" v-model="tableRowData.grade"></el-input>
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
                <el-form-item label="学位">
                    <el-select size="large" style="width: 300px;" v-model="tableRowData.degreeName" @change="selectDegree($event)">
                        <el-option :label="item.value" v-for="(item,index) in degreeList" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="专业">
                    <el-input maxlength="20" style="width: 300px;" v-model="professionName"></el-input>
                </el-form-item>
                <el-form-item label="工作经历">
                    <el-input disabled style="width: 500px;" autosize type="textarea" v-model="jobsList"></el-input>
                </el-form-item>
                <el-form-item label="社会兼职">
                    <el-input disabled style="width: 500px;" autosize type="textarea" v-model="partTimejobList"></el-input>
                </el-form-item>
                <el-form-item label="学号">
                    <el-input maxlength="30" style="width: 300px;" v-model="studentNo"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.idCardNo"></el-input>
                </el-form-item>
                <el-form-item label="认证方式">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.identifyTypeValue"></el-input>
                </el-form-item>
                <el-form-item label="认证状态">
                    <el-input disabled style="width: 300px;" v-model="tableRowData.identifyStatusStr"></el-input>
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
        el: '#alumniInfoDetail',
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
                },
                {
                    key:4,
                    value:'其他'
                },
            ],
            identifyTypeList: [
                {
                    key:0,
                    value:'未认证'
                },
                {
                    key:1,
                    value:'二级认证待审核'
                },
                {
                    key:2,
                    value:'二级认证初审通过'
                },
                {
                    key:3,
                    value:'二级认证初审失败'
                },
                {
                    key:4,
                    value:'二级认证复审通过'
                },
                {
                    key:5,
                    value:'二级认证复审失败'
                },
                {
                    key:6,
                    value:'一级认证待审核'
                },
                {
                    key:7,
                    value:'一级认证初审通过'
                },
                {
                    key:8,
                    value:'一级认证初审失败'
                },
                {
                    key:9,
                    value:'一级认证复审通过'
                },
                {
                    key:10,
                    value:'一级认证复审失败'
                },
            ],
            searchText: '',
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
            professionName:'',
            studentNo:'',
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
                location.href = "${ctx!}/audit/alumni-manage";
            },
            // 修改
            save() {
                let reqData = {};
                reqData.managerId = localStorage.getItem("managerId"),
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
                let json = getAlumniInfo({
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
                    setTimeout(() =>{
                        location.href = ctx+ "/base/loginPage";
                },1000);
                }
            });
            }


        }
    })

</script>
