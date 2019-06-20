<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoDetail" class="layout_container">
    <div class="baofu-shop-nav--control">
    <right-header></right-header>
        <div class="layout_content">
        <title-page title="Refund order"></title-page>
        <div class="refund-order-list--control">
            <div style="margin: 20px;"></div>
            <el-form :label-position="right" label-width="80px" :model="tableRowData">
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
                    <el-input style="width: 300px;" v-model="tableRowData.grade"></el-input>
                </el-form-item>
                <el-form-item label="入学时间">
                    <div class="block">
                        <el-date-picker
                                style="width: 300px;"
                                v-model="tableRowData.entranceTime"
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
                                v-model="tableRowData.graduationTime"
                                type="date"
                                placeholder="选择日期"
                                default-value="1999-10-01">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="学位">
                    <el-select size="large" style="width: 300px;" v-model="tableRowData.degreeNo" @change="selectDegree($event)">
                        <el-option :label="item.value" v-for="(item,index) in degreeList" :value="item.key"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="专业">
                    <el-input style="width: 300px;" v-model="tableRowData.professionName"></el-input>
                </el-form-item>
                <el-form-item label="工作经历">
                    <el-input disabled style="width: 500px;" autosize type="textarea" v-model="jobsList"></el-input>
                </el-form-item>
                <el-form-item label="社会兼职">
                    <el-input disabled style="width: 500px;" autosize type="textarea" v-model="partTimejobList"></el-input>
                </el-form-item>
                <el-form-item label="学号">
                    <el-input style="width: 300px;" v-model="tableRowData.studentNo"></el-input>
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
            refundType:'',
            refundStatus:'',
            searchReturnId:'',
            collegeNo: "4111014430",
            identifyCollegeId: "1000001",
            managerId: "4000001",
            genderType:'',
            degreeType:'',
            identifyType:'',
            identifyStatus:'',
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            selectInstitute(val){
                console.log('val',val);
            },
            selectDegree(val){
                console.log('val',val);
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
            goToInfoDetail(val) {
                location.href = ctx+ "/orderReturn/refundOrderDetail?returnId=" + val;
            },
            initMethod(){
                let json = getAlumniInfo({
                    collegeNo: "4111014430",
                    managerId: "4000001",
                    identifyCollegeId: "1000001",
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableRowData = respData.data.result;
                    var that = this;
                    var jobs = this.tableRowData.jobsInfoDto;
                    var partjobs = this.tableRowData.partTimeJobsDto;
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
