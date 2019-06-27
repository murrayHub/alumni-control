<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<#include 'components/top-header.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoOneLevelList" class="layout_container" style="position: relative;">
    <div><img src="/static/images/home1.jpg" style="width: 100%"></div>
    <div class="baofu-shop-nav--control" style="position: absolute;margin-top: -660px;">
    <top-header></top-header>
    <div class="layout_content" >
        <title-page title="Refund order"></title-page>
        <div class="one-level-list-control">
            <div>
                <el-input placeholder="Search order" v-model="searchText" prefix-icon="el-icon-search"
                          @change="searchOrder($event)"></el-input>
            </div>
            <el-table
                    :data="tableDataResp"
                    border
                    style="width: 100%">
                <el-table-column
                        align="center"
                        width="100"
                        fixed
                        label="姓名">
                    <template slot-scope="scope">
                        <div>{{scope.row.userRealName}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        prop="genderValue">
                    <template slot="header" slot-scope="scope">
                        <el-dropdown trigger="click" @command="handleGender">
                        <span class="el-dropdown-link">
                            性别<i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item :command="-1">全部</el-dropdown-item>
                                <el-dropdown-item :command="1">男</el-dropdown-item>
                                <el-dropdown-item :command="2">女</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="国家">
                    <template slot-scope="scope">
                        <div>{{scope.row.country}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="省">
                    <template slot-scope="scope">
                        <div>{{scope.row.city}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="市">
                    <template slot-scope="scope">
                        <div>{{scope.row.area}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="民族">
                    <template slot-scope="scope">
                        <div>{{scope.row.nation}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        label="出生日期"
                        prop="birthDate">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="250"
                        label="证件号码（身份证或护照）">
                    <template slot-scope="scope">
                        <div>{{scope.row.idCard}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="原证件号码">
                    <template slot-scope="scope">
                        <div>{{scope.row.originalIdCard}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100">
                    <template slot="header" slot-scope="scope">
                        <el-dropdown trigger="click" @command="handleDegree">
                        <span class="el-dropdown-link">
                            学位<i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item :command="-1">全部</el-dropdown-item>
                                <el-dropdown-item :command="1">学士</el-dropdown-item>
                                <el-dropdown-item :command="2">硕士</el-dropdown-item>
                                <el-dropdown-item :command="3">博士</el-dropdown-item>
                                <el-dropdown-item :command="4">其他</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <el-tag v-if="scope.row.trainingLevel == '硕士'" type="success" size="medium">{{ scope.row.trainingLevel }}</el-tag>
                            <el-tag v-if="scope.row.trainingLevel == '学士'" type="info" size="medium">{{ scope.row.trainingLevel }}</el-tag>
                            <el-tag v-if="scope.row.trainingLevel == '博士'" type="danger" size="medium">{{ scope.row.trainingLevel }}</el-tag>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="录取单位">
                    <template slot-scope="scope">
                        <div>{{scope.row.admissionUnit}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="管理单位">
                    <template slot-scope="scope">
                        <div>{{scope.row.manageUnit}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="培养单位">
                    <template slot-scope="scope">
                        <div>{{scope.row.trainingUnit}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="学号">
                    <template slot-scope="scope">
                        <div>{{scope.row.studentNo}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="新学号">
                    <template slot-scope="scope">
                        <div>{{scope.row.newStudentNo}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        prop="identifyStatusStr">
                    <template slot="header" slot-scope="scope">
                        <el-dropdown trigger="click" @command="handleIdentifyStatus">
                        <span class="el-dropdown-link">
                            认证状态<i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item :command="-1">全部</el-dropdown-item>
                                <el-dropdown-item :command="0">未认证</el-dropdown-item>
                                <el-dropdown-item :command="1">二级认证待审核</el-dropdown-item>
                                <el-dropdown-item :command="2">二级认证初审通过</el-dropdown-item>
                                <el-dropdown-item :command="3">二级认证初审失败</el-dropdown-item>
                                <el-dropdown-item :command="4">二级认证复审通过</el-dropdown-item>
                                <el-dropdown-item :command="5">二级认证复审失败</el-dropdown-item>
                                <el-dropdown-item :command="6">一级认证待审核</el-dropdown-item>
                                <el-dropdown-item :command="7">一级认证初审通过</el-dropdown-item>
                                <el-dropdown-item :command="8">一级认证初审失败</el-dropdown-item>
                                <el-dropdown-item :command="9">一级认证复审通过</el-dropdown-item>
                                <el-dropdown-item :command="10">一级认证复审失败</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                    <template slot-scope="scope" align="center">
                        <div class="order-status-val" >
                            <div class="color-point0" v-if="scope.row.identifyStatus==0"></div>
                            <div class="color-point1" v-if="scope.row.identifyStatus==1"></div>
                            <div class="color-point2" v-if="scope.row.identifyStatus==2"></div>
                            <div class="color-point3" v-if="scope.row.identifyStatus==3"></div>
                            <div class="color-point4" v-if="scope.row.identifyStatus==4"></div>
                            <div class="color-point5" v-if="scope.row.identifyStatus==5"></div>
                            <div class="color-point6" v-if="scope.row.identifyStatus==6"></div>
                            <div class="color-point7" v-if="scope.row.identifyStatus==7"></div>
                            <div class="color-point8" v-if="scope.row.identifyStatus==8"></div>
                            <div class="color-point9" v-if="scope.row.identifyStatus==9"></div>
                            <div class="color-point10" v-if="scope.row.identifyStatus==10"></div>
                            <div>{{scope.row.identifyStatusStr}}</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        label="入学时间"
                        prop="entranceTime">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        label="毕业时间"
                        prop="graduationTime">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="导师">
                    <template slot-scope="scope">
                        <div>{{scope.row.tutorName}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="专业">
                    <template slot-scope="scope">
                        <div>{{scope.row.majorName}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="学生状态">
                    <template slot-scope="scope">
                        <div>{{scope.row.studentStatus}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="工作状态">
                    <template slot-scope="scope">
                        <div>{{scope.row.jobStatus}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="工作单位">
                    <template slot-scope="scope">
                        <div>{{scope.row.employer}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="单位性质">
                    <template slot-scope="scope">
                        <div>{{scope.row.unitNature}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="专家类别">
                    <template slot-scope="scope">
                        <div>{{scope.row.expertCategory}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="专业技术职务">
                    <template slot-scope="scope">
                        <div>{{scope.row.specializedTechnicalJob}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="职务">
                    <template slot-scope="scope">
                        <div>{{scope.row.position}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="通讯地址">
                    <template slot-scope="scope">
                        <div>{{scope.row.mailingAddress}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="电子邮件">
                    <template slot-scope="scope">
                        <div>{{scope.row.email}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="联系电话">
                    <template slot-scope="scope">
                        <div>{{scope.row.phoneNo}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="qq">
                    <template slot-scope="scope">
                        <div>{{scope.row.qq}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="微信号">
                    <template slot-scope="scope">
                        <div>{{scope.row.weixinNo}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        fixed="right"
                        align="center"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.identifyStatus == 1" @click="handleFirstReview(scope.row)" type="text" size="mini">初审</el-button>
                        <el-button v-if="scope.row.identifyStatus == 2" @click="handleSecondReview(scope.row)" type="text" size="mini">复审</el-button>
                        <el-button type="text" @click="editEvent(scope.row)" size="small">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @current-change="handleCurrentChange"
                    @size-change="handleSizeChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 50, 100]"
                    :total="total"
                    layout="total, sizes, prev ,pager, next, jumper">
            </el-pagination>
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
        el: '#alumniInfoOneLevelList',
        data: {
            tableDataResp:[],
            currentPage: 1,
            pageSize: 10,
            pages: 1,
            total: 0,
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
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            handleFirstReview(val) {
                console.log('val',val);
                this.$msgbox({
                    title: '初审操作提示',
                    showClose: false,
                    center: true,
                    message: '信息全部属实，确认认证通过?',
                    roundButton: true,
                    showCancelButton: true,
                    closeOnClickModal: false,
                    confirmButtonText: '通过',
                    cancelButtonText: '不通过',
                    beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                    instance.confirmButtonLoading = true;
                    instance.confirmButtonText = '执行中...';

                    let json = levelOneUpdateFirstAudit({
                        collegeNo: localStorage.getItem("collegeNo"),
                        managerId: localStorage.getItem("managerId"),
                        identifyStatus: 2,
                        identifyCollegeId: val.identifyCollegeId
                    });
                    json.then((respData) => {
                        if(respData.data.code == 0){
                        instance.confirmButtonLoading = false;
                        console.log(respData);
                        done();
                        this.$message({
                            type: 'success',
                            message: '成功'
                        });
                        window.setInterval(function() {
                            location.reload();
                        },1000);

                    } else{
                        done();
                        instance.confirmButtonLoading = false;
                        this.$message({
                            message: respData.data.message,
                            type: 'error'
                        });
                    }
                });
                } else if (action === 'cancel') {
                    instance.confirmButtonLoading = true;
                    instance.confirmButtonText = '执行中...';
                    let json = levelOneUpdateFirstAudit({
                        collegeNo: localStorage.getItem("collegeNo"),
                        managerId: localStorage.getItem("managerId"),
                        identifyStatus: 3,
                        identifyCollegeId: val.identifyCollegeId
                    });
                    json.then((respData) => {
                        if(respData.data.code == 0){
                        instance.confirmButtonLoading = false;
                        console.log(respData);
                        done();
                        this.$message({
                            type: 'success',
                            message: '成功'
                        });
                        window.setInterval(function() {
                            location.reload();
                        },1000);
                    } else{
                        done();
                        instance.confirmButtonLoading = false;
                        this.$message({
                            message: respData.data.message,
                            type: 'error'
                        });
                    }
                });
                }else {
                    done();
                }
            }
            })
            },
            handleSecondReview(val) {
                this.$msgbox({
                    title: '复审操作提示',
                    showClose: false,
                    center: true,
                    message: '信息全部属实，确认认证通过?',
                    roundButton: true,
                    showCancelButton: true,
                    closeOnClickModal: false,
                    confirmButtonText: '通过',
                    cancelButtonText: '不通过',
                    beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                    instance.confirmButtonLoading = true;
                    instance.confirmButtonText = '执行中...';

                    let json = levelOneUpdateAudit({
                        collegeNo: localStorage.getItem("collegeNo"),
                        managerId: localStorage.getItem("managerId"),
                        identifyStatus: 4,
                        identifyCollegeId: val.identifyCollegeId
                    });
                    json.then((respData) => {
                        if(respData.data.code == 0){
                        instance.confirmButtonLoading = false;
                        console.log(respData);
                        done();
                        this.$message({
                            type: 'success',
                            message: '成功'
                        });
                        window.setInterval(function() {
                            location.reload();
                        },1000);

                    } else{
                        done();
                        instance.confirmButtonLoading = false;
                        this.$message({
                            message: respData.data.message,
                            type: 'error'
                        });
                    }
                });
                } else if (action === 'cancel') {
                    instance.confirmButtonLoading = true;
                    instance.confirmButtonText = '执行中...';
                    let json = levelOneUpdateAudit({
                        collegeNo: localStorage.getItem("collegeNo"),
                        managerId: localStorage.getItem("managerId"),
                        identifyStatus: 5,
                        identifyCollegeId: val.identifyCollegeId
                    });
                    json.then((respData) => {
                        if(respData.data.code == 0){
                        instance.confirmButtonLoading = false;
                        console.log(respData);
                        done();
                        this.$message({
                            type: 'success',
                            message: '成功'
                        });
                        window.setInterval(function() {
                            location.reload();
                        },1000);
                    } else{
                        done();
                        instance.confirmButtonLoading = false;
                        this.$message({
                            message: respData.data.message,
                            type: 'error'
                        });
                    }
                });
                } else {
                    done();
                }
            }
            })
            },
            editEvent(val){
                var identifyId = val.identifyCollegeId;
                location.href = ctx+ "/audit/alumni-manage-one-level-detail?identifyCollegeId=" + identifyId;
            },
            handleCurrentChange(val) {
                this.pageHandler(val);
            },
            handleSizeChange(val){
                var curPg = this.currentPage;
                this.pageSize = val;
                this.pageHandler(curPg);
            },
            pageHandler(pageNo){
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:pageNo,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    genderType:this.genderType,
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            },
            handleIdentifyStatus(command){
                this.identifyStatus = command;
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    degreeType:this.degreeType,
                    genderType:this.genderType,
                    identifyStatus:command
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            },
            handleDegree(command){
                this.degreeType = command;
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    genderType:this.genderType,
                    degreeType:command
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            },
            handleGender(command){
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    genderType:command
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            },
            searchOrder(searchVal) {
                // 需要加入正则校验
                this.studentName = searchVal;
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    genderType:this.genderType,
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    studentName:searchVal
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
                    console.log(respData);
                } else{
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            });
            },
            initMethod(){
                let json = getLevelOneIdentifyInfo({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize
                });
                json.then((respData) => {
                    if(respData.data.code == 0){
                    this.tableDataResp = respData.data.result.list;
                    this.currentPage = respData.data.result.pagination.current;
                    this.pageSize = respData.data.result.pagination.pageSize;
                    this.pages = respData.data.result.pagination.pages;
                    this.total = respData.data.result.pagination.total;
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
