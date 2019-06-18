<!DOCTYPE html>
<html lang="en">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="alumniInfoList" class="layout_container">
    <div class="baofu-shop-nav--control">
    <right-header></right-header>
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
                        fixed
                        width="150"
                        label="姓名">
                    <template slot-scope="scope">
                        <span class="order-num" @click="goToInfoDetail(scope.row.identifyCollegeId)">{{ scope.row.studentName}}</span>
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
                        width="100"
                        prop="degreeNo">
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
                </el-table-column>
                <el-table-column
                        align="center"
                        width="150"
                        label="专业"
                        prop="professionName">
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
                    <template slot-scope="scope">
                        <div class="order-status-val">
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
                    currentPage:this.currentPage,
                    pageSize:this.pageSize,
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
                let json = getRefundOrders({
                    collegeNo: "4111014430",
                    managerId: "4000001",
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
                    collegeNo: "4111014430",
                    managerId: "4000001",
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
                }
            });
            }


        }
    })

</script>
