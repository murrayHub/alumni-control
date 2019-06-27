<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<#include 'components/top-header.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoList" class="layout_container" style="position: relative;">
    <div><img src="/static/images/home1.jpg" style="width: 100%"></div>
    <div class="baofu-shop-nav--control" style="position: absolute;margin-top: -660px;">
    <top-header></top-header>
        <div class="layout_content">
        <title-page title="Refund order"></title-page>
        <div class="refund-order-list--control">
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
                        <div>{{scope.row.studentName}}</div>
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
                        label="省">
                  <template slot-scope="scope">
                       <div>{{scope.row.province}}</div>
                  </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="市">
                    <template slot-scope="scope">
                        <div>{{scope.row.city}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="学校">
                    <template slot-scope="scope">
                        <div>{{scope.row.collegeName}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="培养单位">
                    <template slot-scope="scope">
                        <div>{{scope.row.instituteName}}</div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="100"
                        label="年级"
                        prop="grade">
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
                                <el-tag v-if="scope.row.degreeName == '硕士'" type="success" size="medium">{{ scope.row.degreeName }}</el-tag>
                                <el-tag v-if="scope.row.degreeName == '学士'" type="info" size="medium">{{ scope.row.degreeName }}</el-tag>
                                <el-tag v-if="scope.row.degreeName == '博士'" type="danger" size="medium">{{ scope.row.degreeName }}</el-tag>
                            </div>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        label="专业"
                        prop="professionName">
                </el-table-column>

                <el-table-column
                        align="center"
                        width="500"
                        label="工作经历">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <el-tag style="margin-bottom: 7px;" v-for="(item,index) in scope.row.jobsInfoDto"  type="success" size="medium">{{ item.companyName }}&nbsp;&nbsp;&nbsp;{{ item.workStartTime }}&nbsp;&nbsp;&nbsp;{{ item.workEndTime }}&nbsp;&nbsp;&nbsp;{{ item.positionName }}</el-tag>
                        </div>
                    </template>
                </el-table-column>

                <el-table-column
                        align="center"
                        width="500"
                        label="社会兼职">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <el-tag style="margin-bottom: 7px;" v-for="(item,index) in scope.row.partTimeJobsDto"  type="warning" size="medium">{{ item.companyName }}&nbsp;&nbsp;&nbsp;{{ item.workStartTime }}&nbsp;&nbsp;&nbsp;{{ item.workEndTime }}&nbsp;&nbsp;&nbsp;{{ item.positionName }}</el-tag>
                        </div>
                    </template>
                </el-table-column>

                <el-table-column
                        align="center"
                        width="150"
                        label="学号"
                        prop="studentNo">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        label="身份证号"
                        prop="idCardNo">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="200"
                        prop="identifyTypeValue">
                    <template slot="header" slot-scope="scope">
                        <el-dropdown trigger="click" @command="handleIdentifyType">
                        <span class="el-dropdown-link">
                            认证方式<i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item :command="-1">全部</el-dropdown-item>
                                <el-dropdown-item :command="1">身份证号</el-dropdown-item>
                                <el-dropdown-item :command="2">学号</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
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
        el: '#alumniInfoList',
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

                    let json = levelTwoIdentifyUpdate({
                        collegeNo: localStorage.getItem("collegeNo"),
                        instituteNo: val.instituteNo,
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
                    let json = levelTwoIdentifyUpdate({
                        collegeNo: localStorage.getItem("collegeNo"),
                        instituteNo: val.instituteNo,
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

                    let json = levelTwoUpdateAudit({
                        collegeNo: localStorage.getItem("collegeNo"),
                        instituteNo: val.instituteNo,
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
                    let json = levelTwoIdentifyUpdate({
                        collegeNo: localStorage.getItem("collegeNo"),
                        instituteNo: val.instituteNo,
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
                location.href = ctx+ "/audit/alumni-manage-detail?identifyCollegeId=" + identifyId;
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
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:pageNo,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    identifyType:this.identifyType,
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
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    degreeType:this.degreeType,
                    identifyType:this.identifyType,
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
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    identifyType:this.identifyType,
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
            handleIdentifyType(command){
                this.identifyType = command;
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    genderType:this.genderType,
                    identifyType:command
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
                this.genderType = command;
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    identifyType:this.identifyType,
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
                let json = getAlumniInfos({
                    collegeNo: localStorage.getItem("collegeNo"),
                    managerId: localStorage.getItem("managerId"),
                    identifyStatus:this.identifyStatus,
                    degreeType:this.degreeType,
                    identifyType:this.identifyType,
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
            goToInfoDetail(val) {
                location.href = ctx+ "/orderReturn/refundOrderDetail?returnId=" + val;
            },
            initMethod(){
                let json = getAlumniInfos({
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
