<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<#include 'head.ftl' >
<head>
    <meta charset="UTF-8">
    <title>alumni-control</title>
</head>
<body>
<div id="RegisterSpecial" class="layout_container">
    <div><img src="/static/images/spec2.jpg" style="width: 100%;opacity: 0.8;"></div>
    <div class="registerSpec-include-control">
        <div class="layout_content">
        <div class="login-control">
            <div style="margin: 20px;"></div>

            <div class="md-example-child md-example-child-input-item-1
             md-example-child-picker md-example-child-picker-2 md-example-child-popup
             md-example-child-popup-0
             md-example-child-radio md-example-child-radio-1
             md-example-child-input-item-5
             md-example-child-date-picker md-example-child-date-picker-0">
                <md-field>
                    <md-input-item
                            ref="name"
                            title="真实姓名"
                            placeholder="真实姓名"
                            @blur="checkRealName"
                            v-model="realName"
                            clearable
                    >
                        <p v-if="isError"
                                class="error"
                                slot="error">
                            不支持当前银行
                        </p>
                    </md-input-item>
                    <md-field-item title="性别" solid>
                        <md-radio name="1" v-model="sex" label="男" inline />
                        <md-radio name="2" v-model="sex" label="女" inline />
                    </md-field-item>
                    <md-field-item
                            title="选择省市"
                            arrow="arrow-right"
                            :addon="pickerValue1"
                            @click="isPickerShow1 = true">
                    </md-field-item>
                    <md-field-item
                            title="所在学校"
                            arrow="arrow-right"
                            :addon="pickerValue2"
                            @click="isPickerShow2 = true">
                    </md-field-item>
                    <md-field-item
                            title="培养单位"
                            arrow="arrow-right"
                            :addon="pickerValue3"
                            @click="isPickerShow3 = true">
                    </md-field-item>
                    <md-field-item
                            title="年级"
                            arrow="arrow-right"
                            :addon="pickerValue4"
                            @click="isPickerShow4 = true">
                    </md-field-item>
                    <md-field-item
                            name="name"
                            title="入学时间"
                            arrow="arrow-right"
                            align="right"
                            :content="datePickerValue"
                            @click.native="isDatePickerShow = true">
                    </md-field-item>
                    <md-field-item
                            name="name"
                            title="毕业时间"
                            arrow="arrow-right"
                            align="right"
                            :content="datePickerValue"
                            @click.native="isDatePickerShow = true">
                    </md-field-item>
                    <md-field-item
                            title="学位"
                            arrow="arrow-right"
                            :addon="pickerValue5"
                            @click="isPickerShow5 = true">
                    </md-field-item>
                    <md-input-item
                            ref="id"
                            title="邮箱"
                            placeholder="邮箱"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="手机号"
                            placeholder="手机号"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="微信号"
                            placeholder="微信号"
                            clearable
                    ></md-input-item>
                    <md-field-item
                            title="核心标签"
                            arrow="arrow-right"
                            :addon="pickerValue8"
                            @click="showPopUp('bottom')">
                    </md-field-item>
                    <md-field-item
                            title="所属行业"
                            arrow="arrow-right"
                            :addon="pickerValue6"
                            @click="isPickerShow6 = true">
                    </md-field-item>
                    <md-input-item
                            ref="id"
                            title="专业"
                            placeholder="专业"
                            clearable
                    ></md-input-item>
                    <md-field-item
                            title="认证方式"
                            arrow="arrow-right"
                            :addon="pickerValue7"
                            @click="isPickerShow7 = true">
                    </md-field-item>
                    <md-input-item
                            ref="id"
                            title="学号"
                            placeholder="学号"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="身份证号"
                            placeholder="身份证号"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="账号"
                            placeholder="账号"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="密码"
                            placeholder="密码"
                            clearable
                    ></md-input-item>
                    <md-input-item
                            ref="id"
                            title="确认密码"
                            placeholder="确认密码"
                            clearable
                    ></md-input-item>
                    <md-field-item
                            title="添加工作经历"
                            arrow="arrow-right"
                            :addon="pickerValue10"
                            @click="showPopUpJobConfirm('bottom')">
                    </md-field-item>
                </md-field>
                <md-picker
                        ref="picker1"
                        v-model="isPickerShow1"
                        :data="pickerData1"
                        :cols="2"
                        is-cascade
                        large-radius
                        title="选择省市"
                        @confirm="onPickerConfirm(1)"
                ></md-picker>
                <md-picker
                        ref="picker2"
                        v-model="isPickerShow2"
                        :data="pickerData2"
                        :cols="2"
                        is-cascade
                        large-radius
                        title="选择学校"
                        @confirm="onPickerConfirm(2)"
                ></md-picker>
                <md-picker
                        ref="picker3"
                        v-model="isPickerShow3"
                        :data="pickerData3"
                        large-radius
                        @confirm="onPickerConfirm(3)"
                        title="培养单位"
                ></md-picker>
                <md-picker
                        ref="picker4"
                        v-model="isPickerShow4"
                        :data="pickerData4"
                        large-radius
                        @confirm="onPickerConfirm(4)"
                        title="年级"
                ></md-picker>
                <md-picker
                        ref="picker5"
                        v-model="isPickerShow5"
                        :data="pickerData5"
                        large-radius
                        @confirm="onPickerConfirm(5)"
                        title="学位"
                ></md-picker>
                <md-picker
                        ref="picker7"
                        v-model="isPickerShow7"
                        :data="pickerData7"
                        large-radius
                        @confirm="onPickerConfirm(7)"
                        title="认证方式"
                ></md-picker>
                <md-picker
                        ref="picker6"
                        v-model="isPickerShow6"
                        :data="pickerData6"
                        :cols="2"
                        is-cascade
                        large-radius
                        title="所属行业"
                        @confirm="onPickerConfirm(6)"
                ></md-picker>
                <md-popup
                        v-model="isPopupShow.bottom"
                        position="bottom">
                    <md-popup-title-bar
                            title="Popup Title"
                            describe="Popup Description"
                            ok-text="ok"
                            cancel-text="cancel"
                            large-radius
                            @confirm="hidePopUp('bottom')"
                            @cancel="hidePopUp('bottom')"
                    ></md-popup-title-bar>
                    <div class="md-example-popup md-example-popup-bottom md-example-child md-example-child-check md-example-child-check-3">
                        <md-check-group v-model="insurants">
                            <md-check-box  v-for="item in labelSet" name="item.name" >{{item.name}}</md-check-box>
                        </md-check-group>
                    </div>
                </md-popup>
                <md-date-picker
                        ref="datePicker"
                        v-model="isDatePickerShow"
                        type="custom"
                        title="选择入学时间"
                        large-radius
                        :custom-types="['yyyy', 'MM']"
                        :default-date="currentDate"
                        @confirm="onDatePickerConfirm"
                ></md-date-picker>
                <md-date-picker
                        ref="datePicker"
                        v-model="isDatePickerShow"
                        type="custom"
                        title="选择毕业时间"
                        large-radius
                        :custom-types="['yyyy', 'MM']"
                        :default-date="currentDate"
                        @confirm="onDatePickerConfirm"
                ></md-date-picker>
                <md-popup
                        v-model="isPopupShowJob.bottom"
                        position="bottom"
                >
                    <md-popup-title-bar
                            title="添加工作经历"
                            describe="Popup Description"
                            ok-text="ok"
                            cancel-text="cancel"
                            large-radius
                            @confirm="hidePopUpJobConfirm('bottom')"
                            @cancel="hidePopUpJobCancel('bottom')"
                    ></md-popup-title-bar>
                    <div class="md-example-child md-example-child-input-item-0">
                        <md-field>
                            <md-input-item
                                    ref="input0"
                                    title="公司名称"
                                    placeholder="请输入公司名称"
                                    v-model="companyName"
                                    is-amount
                                    :maxlength="5"
                            ></md-input-item>
                            <md-input-item
                                    ref="input1"
                                    title="职位名称"
                                    placeholder="请输入职位名称"
                                    v-model="positionName"
                                    is-amount
                                    :maxlength="5"
                            ></md-input-item>
                            <md-field-item
                                    name="name"
                                    title="工作开始时间"
                                    arrow="arrow-right"
                                    align="right"
                                    :content="beginTimePickerValue"
                                    @click.native="beginTimeShow = true">
                            </md-field-item>
                            <md-field-item
                                    name="name"
                                    title="工作结束时间"
                                    arrow="arrow-right"
                                    align="right"
                                    :content="endTimePickerValue"
                                    @click.native="endTimeShow = true">
                            </md-field-item>
                        </md-field>
                        <md-date-picker
                                ref="datePicker"
                                v-if="beginTimeShow"
                                v-model="beginTimeShowModel"
                                type="custom"
                                title="选择工作开始时间"
                                large-radius
                                :custom-types="['yyyy', 'MM']"
                                :default-date="currentDate"
                                @confirm="jobBeginTimeConfirm"
                        ></md-date-picker>
                        <md-date-picker
                                ref="datePicker"
                                v-if="endTimeShow"
                                v-model="endTimeShowModel"
                                type="custom"
                                title="选择工作结束时间"
                                large-radius
                                :custom-types="['yyyy', 'MM']"
                                :default-date="currentDate"
                                @confirm="jobEndTimeConfirm"
                        ></md-date-picker>
                    </div>
                </md-popup>
                <div class="md-example-child md-example-child-bill-0" v-if="jobExperienceVisible">
                    <md-bill
                            title="工作经历"
                            no="1"
                            water-mark="MAND-MOBILE">
                        <md-detail-item title="公司名称"
                                        v-model="companyNameVal">{{companyNameVal}}
                        </md-detail-item>
                        <md-detail-item title="职位名称" v-model="positionNameVal">
                            {{positionNameVal}}
                        </md-detail-item>
                        <md-detail-item title="工作开始时间" v-model="jobBeginTimeVal">
                            {{jobBeginTimeVal}}
                        </md-detail-item>
                        <md-detail-item title="工作结束时间" v-model="jobEndTimeVal">
                            {{jobEndTimeVal}}
                        </md-detail-item>
                    </md-bill>
                </div>
            </div>

            <#--<el-form :model="registerSpecForm" :inline="true" style="text-align: -webkit-center;" status-icon :rules="rules2" ref="registerSpecForm" label-width="100px" class="demo-ruleForm">-->
                <#--<el-form-item label="姓名" maxlength="16" prop="username" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.username" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="性别" prop="gender" style="width: 30%;">-->
                    <#--<el-select v-model="registerSpecForm.gender" size="large" style="width: 300px;">-->
                        <#--<el-option :label="item.value" v-for="(item,index) in genderTypes" :value="item.key"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="省" prop="province" style="width: 30%;">-->
                    <#--<el-select size="large" v-model="registerSpecForm.province" filterable clearable style="width: 300px;" placeholder="请选择所在省" @change="selectProvince($event)">-->
                        <#--<el-option :label="item.name" v-for="(item,index) in provinceList" :value="item.id"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="市" prop="city" style="width: 30%;">-->
                    <#--<el-select size="large" v-model="registerSpecForm.city" filterable clearable style="width: 300px;" placeholder="请选择所在市" @change="selectCity($event)">-->
                        <#--<el-option :label="item.name" v-for="(item,index) in citiesList" :value="item.id"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="所在学校" prop="collegeNo" style="width: 30%;" >-->
                    <#--<el-select size="large" v-model="registerSpecForm.collegeNo" filterable clearable style="width: 300px;" placeholder="请选择所在学校" @change="selectCollege($event)">-->
                        <#--<el-option :label="item.schoolName" v-for="(item,index) in collegeList" :value="item.schoolId"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="培养单位" prop="instituteNo" style="width: 30%;" >-->
                    <#--<el-select size="large" v-model="registerSpecForm.instituteNo" filterable clearable style="width: 300px;" placeholder="请选择培养单位" @change="selectInstitute($event)">-->
                        <#--<el-option :label="item.instituteName" v-for="(item,index) in instituteList" :value="item.instituteNo"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="年级" prop="grade" style="width: 30%;">-->
                    <#--<div class="block">-->
                        <#--<el-date-picker-->
                                <#--style="width: 300px;"-->
                                <#--v-model="registerSpecForm.grade"-->
                                <#--type="year"-->
                                <#--placeholder="选择入学年级"-->
                                <#-->-->
                        <#--</el-date-picker>-->
                    <#--</div>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="入学时间" prop="entranceTime" style="width: 30%;">-->
                    <#--<div class="block">-->
                        <#--<el-date-picker-->
                                <#--style="width: 300px;"-->
                                <#--v-model="registerSpecForm.entranceTime"-->
                                <#--type="date"-->
                                <#--value-format="yyyy-MM-dd"-->
                                <#--placeholder="选择日期"-->
                                <#--default-value="1999-10-01">-->
                        <#--</el-date-picker>-->
                    <#--</div>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="毕业时间" prop="graduationTime" style="width: 30%;">-->
                    <#--<div class="block">-->
                        <#--<el-date-picker-->
                                <#--style="width: 300px;"-->
                                <#--v-model="registerSpecForm.graduationTime"-->
                                <#--type="date"-->
                                <#--value-format="yyyy-MM-dd"-->
                                <#--placeholder="选择日期"-->
                                <#--default-value="1999-10-01">-->
                        <#--</el-date-picker>-->
                    <#--</div>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="学位" prop="degree" style="width: 30%;">-->
                    <#--<el-select v-model="registerSpecForm.degree" size="large" style="width: 300px;" @change="selectDegree($event)">-->
                        <#--<el-option :label="item.value" v-for="(item,index) in degreeList" :value="item.key"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="邮箱" prop="email" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.email" maxlength="20" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="手机号" prop="phoneNo" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.phoneNo" maxlength="20" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="微信号" prop="weixinNo" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.weixinNo" maxlength="20" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="核心标签" style="width: 30%;">-->
                    <#--<el-input id="coreLabel" @focus="onfocus($event)" v-model="coreLabel" maxlength="20" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="所属行业" style="width: 30%;">-->
                    <#--<el-cascader style="width: 300px;"-->
                            <#--:options="levelOneIndustryList"-->
                            <#--@change="handleItemChange($event)"-->
                            <#--:props="industryProps"-->
                            <#--v-model="domain"-->
                    <#--></el-cascader>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="专业" prop="profession" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.profession" maxlength="20" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="认证方式" style="width: 30%;">-->
                    <#--<el-select v-model="registerSpecForm.identifyType" size="large" style="width: 300px;" @change="selectIdentifyType($event)">-->
                        <#--<el-option :label="item.value" v-for="(item,index) in identifyTypes" :value="item.key"></el-option>-->
                    <#--</el-select>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="学号" v-if="identifyFlag == 2" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.studentNo" maxlength="30" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="身份证号" v-if="identifyFlag == 1" style="width: 30%;">-->
                    <#--<el-input v-model="registerSpecForm.idCardNo" style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="账号"  style="width: 100%;">-->
                    <#--<el-input  style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="密码"  style="width: 100%;">-->
                    <#--<el-input  style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="确认密码"  style="width: 100%;">-->
                    <#--<el-input  style="width: 300px;"></el-input>-->
                <#--</el-form-item>-->
                <#--<el-form-item label="工作经历" style="width: 30%;" :label-position="left">-->
                    <#--<section v-if="normalJobList.length!=0">-->
                        <#--<div v-for="(item,index) in normalJobList" style="position: relative;line-height: 4;">-->
                            <#--<el-input v-model="registerSpecForm.companyNameNor[index]" maxlength="20"  placeholder="公司名称"></el-input>-->
                            <#--<el-input v-model="registerSpecForm.positionNor[index]" maxlength="16" placeholder="职位名称"></el-input>-->
                            <#--<div class="block">-->
                                <#--<el-date-picker-->
                                        <#--style="width: 100%;"-->
                                        <#--v-model="registerSpecForm.beginTimeNor[index]"-->
                                        <#--type="date"-->
                                        <#--value-format="yyyy-MM-dd"-->
                                        <#--placeholder="请选择工作开始时间">-->
                                <#--</el-date-picker>-->
                            <#--</div>-->
                            <#--<div class="block">-->
                                <#--<el-date-picker-->
                                        <#--style="width: 100%;"-->
                                        <#--v-model="registerSpecForm.endTimeNor[index]"-->
                                        <#--type="date"-->
                                        <#--value-format="yyyy-MM-dd"-->
                                        <#--:picker-options="pickerOptions1"-->
                                        <#--placeholder="请选择工作结束时间">-->
                                <#--</el-date-picker>-->
                            <#--</div>-->
                            <#--<div style="position: absolute;margin-top: -134px;margin-left: 430px;">-->
                                <#--<img src="${ctx}/static/images/setting_input_del.png" @click="normalJobDelete(index)" />-->
                            <#--</div>-->
                            <#--<br/>-->
                        <#--</div>-->
                    <#--</section>-->
                    <#--<el-button type="info" @click="addNormalJob"><span>新增工作经历</span></el-button>-->
                <#--</el-form-item>-->
                <#--<br/>-->
                <#--<el-form-item label="社会兼职" style="width: 30%;" :label-position="left">-->
                    <#--<section v-if="partTimejobList.length!=0">-->
                    <#--<div v-for="(item,index) in partTimejobList" style="position: relative;line-height: 4;">-->
                        <#--<el-input v-model="registerSpecForm.companyNamePT[index]" maxlength="20"  placeholder="兼职公司名称"></el-input>-->
                        <#--<el-input v-model="registerSpecForm.positionPT[index]" maxlength="16" placeholder="兼职职位名称"></el-input>-->
                        <#--<div class="block">-->
                            <#--<el-date-picker-->
                                    <#--style="width: 100%;"-->
                                    <#--v-model="registerSpecForm.beginTimePT[index]"-->
                                    <#--type="date"-->
                                    <#--value-format="yyyy-MM-dd"-->
                                    <#--placeholder="请选择工作开始时间">-->
                            <#--</el-date-picker>-->
                        <#--</div>-->
                        <#--<div class="block">-->
                            <#--<el-date-picker-->
                                    <#--style="width: 100%;"-->
                                    <#--v-model="registerSpecForm.endTimePT[index]"-->
                                    <#--type="date"-->
                                    <#--value-format="yyyy-MM-dd"-->
                                    <#--:picker-options="pickerOptions2"-->
                                    <#--placeholder="请选择工作结束时间">-->
                            <#--</el-date-picker>-->
                        <#--</div>-->
                        <#--<div style="position: absolute;margin-top: -134px;margin-left: 430px;">-->
                            <#--<img src="${ctx}/static/images/setting_input_del.png" @click="partTimeJobDelete(index)" />-->
                        <#--</div>-->
                        <#--<br/>-->
                    <#--</div>-->
                    <#--</section>-->
                    <#--<el-button type="info" @click="addPartTimeJob"><span>新增社会兼职</span></el-button>-->
                <#--</el-form-item>-->
                <#--<div>-->
                    <#--<el-button type="success" @click="submitSpecForm('registerSpecForm')">提交</el-button>-->
                    <#--<el-button @click="resetForm('registerSpecForm')">重置</el-button>-->
                <#--</div>-->
            <#--</el-form>-->
            <el-dialog title="请选择核心标签" :visible.sync="dialogTableVisible">
                <div v-for="(item,index) in initialLabelList">
                        <span :id="item.labelId" class="Classification" @click="onLabelcheck(item)">
                            {{item.labelName}}</span>
                </div>
                <br/>
                <div style="position: absolute;margin-top: 342px;margin-left: 260px;">
                    <el-button type="success" @click="saveLabel">确认</el-button>
                    <el-button type="warning" @click="resetSelection">一键清空</el-button>
                </div>
            </el-dialog>

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
        components: {
        },
        data: function(){
            var validateUsername = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('姓名不能为空'));
                }else {
                    callback();
                }
            };
            var validateGender = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择性别'));
                }else {
                    callback();
                }
            };
            var validateProvince = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择所在省份'));
                }else {
                    callback();
                }
            };
            var validateCity = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择所在城市'));
                }else {
                    callback();
                }
            };
            var validateCollege = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择所在学校'));
                }else {
                    callback();
                }
            };
            var validateInstitute = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择培养单位'));
                }else {
                    callback();
                }
            };
            var validateGrade = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择入学年级'));
                }else {
                    callback();
                }
            };
            var validateEntranceTime = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择入学时间'));
                }else {
                    callback();
                }
            };
            var validateGraduationTime = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择毕业时间'));
                }else {
                    callback();
                }
            };
            var validateDegree = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请选择学位'));
                }else {
                    callback();
                }
            };
            var validateEmail = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请输入邮箱'));
                }else {
                    callback();
                }
            };
            var validatePhoneNo = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('手机号不能为空'));
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
            var validateWeixinNo = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请输入微信号'));
                }else {
                    callback();
                }
            };
            var validateProfession = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('请输入专业名称'));
                }else {
                    callback();
                }
            };

            return {
                realName: '',
                isError: false,

                currentDate: new Date(),
                isDatePickerShow: false,
                datePickerValue: '',

                beginTimeShow: false,
                endTimeShow: false,
                isDatePickerShow: false,
                beginTimePickerValue: '',
                endTimePickerValue: '',
                beginTimeShowModel: '',
                endTimeShowModel: '',
                datePickerValue: '',
                pickerValue10: '',
                isPopupShowJob: {},
                pickerValue8: '',
                companyName: '',
                companyNameVal: '',
                positionName: '',
                positionNameVal: '',
                jobBeginTimeVal: '',
                jobEndTimeVal: '',
                jobExperienceVisible: false,

                isPopupShow: {},
                pickerValue8: '',
                insurants: ['self'],
                labelSet:[
                    {
                        name:'label1',
                    }, {
                        name:'label12',
                    }, {
                        name:'label122',
                    },
                ],

                sex: '1',
                pickerValue3: '',
                pickerValue4: '',
                pickerValue5: '',
                pickerValue6: '',
                pickerValue7: '',
                isPickerShow3: false,
                isPickerShow4: false,
                isPickerShow5: false,
                isPickerShow6: false,
                isPickerShow7: false,
                pickerData3: [
                    [
                        {text: '长光所', value: 1},
                        {text: '高研院', value: 2}
                    ],
                ],
                pickerData4: [
                    [
                        {text: '2000', value: 1},
                        {text: '2001', value: 2}
                    ],
                ],
                pickerData5: [
                    [
                        {text: '学士', value: 1},
                        {text: '硕士', value: 2},
                        {text: '博士', value: 3},
                        {text: '其他', value: 4}
                    ],
                ],
                pickerData7: [
                    [
                        {text: '身份证', value: 1},
                        {text: '学号', value: 2}
                    ],
                ],
                pickerData1: [[{
                    "value": "340000",
                    "label": "安徽省",
                    "children": [{
                        "value": "341500",
                        "label": "六安市",
                    }, {
                        "value": "340500",
                        "label": "马鞍山市",
                    }, {
                        "value": "341800",
                        "label": "宣城市",
                    }]
                }]],
                pickerData2: [[{
                    "value": "340000",
                    "label": "安徽省",
                    "children": [{
                        "value": "341500",
                        "label": "中国科技大学",
                    }, {
                        "value": "340500",
                        "label": "合肥工业大学",
                    }, {
                        "value": "341800",
                        "label": "安徽大学",
                    }]
                }]],
                pickerData6: [[{
                    "value": "340000",
                    "label": "互联网",
                    "children": [{
                        "value": "341500",
                        "label": "软件",
                    }, {
                        "value": "340500",
                        "label": "网站开发",
                    }, {
                        "value": "341800",
                        "label": "算法",
                    }]
                }]],
                isPickerShow1: false,
                isPickerShow2: false,
                pickerValue1: '',
                pickerValue2: '',

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
                phoneNo: '',
                weixinNo: '',
                coreLabel: '',
                domain: '',
                profession: '',
                left:'',
                active:'',
                dialogTableVisible:false,
                color:'',
                labelList:[],
                initialLabelList:[],
                industryProps:{
                    value: 'industryNo',
                    label: 'industryName',
                    children: 'subIndustries'
                },
                tableRowData:'',
                jobsList:'',
                identifyFlag:'1',
                ucasInstituteList: [],
                instituteList:[],
                partTimejobList:[],
                normalJobList:[],
                collegeList:[],
                provinceList:[],
                citiesList:[],
                levelOneIndustryList:[],
                levelTwoIndustryList:[],
                pickerOptions1: {
                    shortcuts: [{
                        text: '至今',
                        onClick(picker) {
                            picker.$emit('pick', "9999-09-09");
                        }
                    }]
                },
                pickerOptions2: {
                    shortcuts: [{
                        text: '至今',
                        onClick(picker) {
                            picker.$emit('pick', "9999-09-09");
                        }
                    }]
                },
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
                registerSpecForm: {
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
                    phoneNo: '',
                    weixinNo: '',
                    profession: '',
                    jobs: '',
                    partTimejob: '',
                    studentNo: '',
                    idCardNo: '',
                    domain: '',
                    identifyType: 1,
                    companyNamePT: [],
                    positionPT: [],
                    beginTimePT: [],
                    endTimePT: [],
                    companyNameNor: [],
                    positionNor: [],
                    beginTimeNor: [],
                    endTimeNor: [],
                },
                rules2: {
                    username: [
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    gender: [
                        { validator: validateGender, trigger: 'blur' }
                    ],
                    province: [
                        { validator: validateProvince, trigger: 'blur' }
                    ],
                    city: [
                        { validator: validateCity, trigger: 'blur' }
                    ],
                    collegeNo: [
                        { validator: validateCollege, trigger: 'blur' }
                    ],
                    instituteNo: [
                        { validator: validateInstitute, trigger: 'blur' }
                    ],
                    grade: [
                        { validator: validateGrade, trigger: 'blur' }
                    ],
                    degree: [
                        { validator: validateDegree, trigger: 'blur' }
                    ],
                    entranceTime: [
                        { validator: validateEntranceTime, trigger: 'blur' }
                    ],
                    graduationTime: [
                        { validator: validateGraduationTime, trigger: 'blur' }
                    ],
                    email: [
                        { validator: validateEmail, trigger: 'blur' }
                    ],
                    phoneNo: [
                        { validator: validatePhoneNo, trigger: 'blur' }
                    ],
                    weixinNo: [
                        { validator: validateWeixinNo, trigger: 'blur' }
                    ],
                    profession: [
                        { validator: validateProfession, trigger: 'blur' }
                    ],
                },
            }
        },
        mounted: function () {
            this.initMethod();
            // 菜单选择
        },
        methods: {
            checkRealName() {
                if (this.realName && this.realName.substr(0, 4) !== '6222') {
                    this.isError = true
                } else {
                    this.isError = false
                }
            },
            showPopUpJobConfirm(type) {
                this.$set(this.isPopupShowJob, type, true);
            },
            hidePopUpJobConfirm(type) {
                this.$set(this.isPopupShowJob, type, false);
                console.log('this.companyName',this.companyName);
                this.companyNameVal = this.companyName;
                this.positionNameVal = this.positionName;
                this.jobBeginTimeVal = this.beginTimePickerValue;
                this.jobEndTimeVal = this.endTimePickerValue;
                this.jobExperienceVisible= true;
            },
            hidePopUpJobCancel(type) {
                this.$set(this.isPopupShowJob, type, false);
                this.jobExperienceVisible= false;
            },
            jobBeginTimeConfirm(columnsValue) {
                this.beginTimePickerValue = this.$refs.datePicker.getFormatDate('yyyy/MM');
            },
            jobEndTimeConfirm(columnsValue) {
                this.endTimePickerValue = this.$refs.datePicker.getFormatDate('yyyy/MM');
            },
            showPopUp(type) {
                this.$set(this.isPopupShow, type, true)
            },
            hidePopUp(type) {
                this.$set(this.isPopupShow, type, false)
            },
            onPickerConfirm(index) {
                console.log('index',index)
                const values = this.$refs[`picker`+index].getColumnValues()
                console.log('values',values)
                let res = ''
                values.forEach(value => {
                    value && (res += value.text || value.label)
            })
                this[`pickerValue`+index] = res
            },
            onDatePickerConfirm(columnsValue) {
                this.datePickerValue = this.$refs.datePicker.getFormatDate('yyyy/MM');
            },
            handleItemChange(val) {
                console.log('二级行业节点:', val[1]);
                this.registerSpecForm.domain = val[1];
            },
            saveLabel() {
                this.dialogTableVisible = false;
                document.getElementById("coreLabel").placeholder = '已选择' + this.labelList.length + '个标签';
            },
            resetSelection() {
                var len = this.labelList.length;
                for (var i = 0; i < len; i++) {
                    document.getElementById(this.labelList[i]).style.background = "#f7f7f7";
                }
                this.labelList.length = 0;
            },
            onfocus() {
                this.dialogTableVisible = true
            },
            onLabelcheck(label) {
                var index = label.labelId;
                if (document.getElementById(index).getAttribute("class") === "Classification") {
                    document.getElementById(index).setAttribute("class", "active");
                    this.randomColor();
                    document.getElementById(index).style.background = this.color;
                    this.labelList.push(index);
                } else {
                    document.getElementById(index).setAttribute("class", "Classification");
                    document.getElementById(index).style.background = "#f7f7f7";
                    var idx = this.labelList.indexOf(index);
                    if (idx > -1) {
                        this.labelList.splice(idx, 1);
                    }
                }
                console.log('labelList', this.labelList);
            },
            randomColor() {
                var colorList = [
                    "#2ae0c8", "#a2e1d4", "#acf6ef", "#cbf5fb", "#bdf3d4", "#e6e2c3", "#e3c887", "#fad8be", "#fbb8ac", "#fe6673"
                ];
                var index = Math.floor(Math.random() * 10);
                this.color = colorList[index];
            },
            addNormalJob() {
                this.normalJobList.push('');
                console.log(this.normalJobList);
            },
            addPartTimeJob() {
                this.partTimejobList.push('');
                console.log(this.partTimejobList);
            },
            normalJobDelete(index) {
                this.normalJobList.splice(index, 1);
                this.registerSpecForm.companyNameNor.splice(index, 1);
                this.registerSpecForm.positionNor.splice(index, 1);
                this.registerSpecForm.beginTimeNor.splice(index, 1);
                this.registerSpecForm.endTimeNor.splice(index, 1);
            },
            partTimeJobDelete(index) {
                this.partTimejobList.splice(index, 1);
                this.registerSpecForm.companyNamePT.splice(index, 1);
                this.registerSpecForm.positionPT.splice(index, 1);
                this.registerSpecForm.beginTimePT.splice(index, 1);
                this.registerSpecForm.endTimePT.splice(index, 1);
            },
            selectIdentifyType(val) {
                this.identifyFlag = val;
            },
            submitSpecForm(formName) {
                var reqData = this.registerSpecForm;
                console.log('reqData',reqData);

                this.$refs[formName].validate((valid) => {

                    // 工作经历校验
                    var jobNum = this.normalJobList.length;
                    for (var i=0;i<jobNum;i++){
                        if (!isNotNull(this.registerSpecForm.companyNameNor[i])){
                            this.$message({
                                message: '请输入公司名称',
                                type: 'error'
                            });
                            return;
                        }
                        if (!isNotNull(this.registerSpecForm.positionNor[i])){
                            this.$message({
                                message: '请输入职位名称',
                                type: 'error'
                            });
                            return;
                        }
                        if (!isNotNull(this.registerSpecForm.beginTimeNor[i])){
                            this.$message({
                                message: '请输入工作开始时间',
                                type: 'error'
                            });
                            return;
                        }
                    }

                    // 兼职经历校验
                    var jobPTNum = this.partTimejobList.length;
                    for (var i=0;i<jobPTNum;i++){
                        if (!isNotNull(this.registerSpecForm.companyNamePT[i])){
                            this.$message({
                                message: '请输入公司名称',
                                type: 'error'
                            });
                            return;
                        }
                        if (!isNotNull(this.registerSpecForm.positionPT[i])){
                            this.$message({
                                message: '请输入职位名称',
                                type: 'error'
                            });
                            return;
                        }
                        if (!isNotNull(this.registerSpecForm.beginTimePT[i])){
                            this.$message({
                                message: '请输入工作开始时间',
                                type: 'error'
                            });
                            return;
                        }
                    }

                    var num = this.labelList.length;
                    if(num == 0){
                        this.$message({
                            message: '请选择核心标签',
                            type: 'error'
                        });
                        return;
                    }
                    reqData.coreLabel = this.labelList.toString();

                    if(!isNotNull(this.domain)){
                        this.$message({
                            message: '请选择所在行业',
                            type: 'error'
                        });
                        return;
                    }
                    if(this.registerSpecForm.identifyType == 1){
                        if (!isNotNull(this.registerSpecForm.idCardNo)){
                            this.$message({
                                message: '请输入身份证号',
                                type: 'error'
                            });
                            return;
                        }
                    }else{
                    if (!isNotNull(this.registerSpecForm.studentNo)){
                        this.$message({
                            message: '请输入学号',
                            type: 'error'
                        });
                        return;
                    }
                    }
                    if(valid) {
                        console.log('提交成功!');
                    let json = registerSpecSubmit(reqData);
                    json.then((respData) => {
                        if(respData.data.code == 0){
                        this.$message({
                        message: '恭喜！您已注册成功！',
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
            // 修改
            save() {
                let reqData = {};
                reqData.identifyCollegeId = this.identifyCollegeId;
                reqData.identifyStatus = this.identifyStatus;
                reqData.instituteNo = this.instituteNo;
                reqData.degreeType = this.degreeNo;
                reqData.entranceTime = this.format(this.entranceTime, "yyyy-MM-dd");
                reqData.graduationTime = this.format(this.graduationTime, "yyyy-MM-dd");
                reqData.grade = this.format($('#detail_grade').val(), "yyyy-MM-dd");
                reqData.studentNo = $('#studentNo').val();
                reqData.professionName = this.professionName;
                reqData.studentNo = this.studentNo;
                console.log('reqData', reqData);
                let json = levelTwoIdentifyUpdate(reqData);
                json.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    setTimeout(() => {
                        location.reload();
                },
                    1000
                )
                    ;
                }
            else
                {
                    this.$message({
                        message: respData.data.message,
                        type: 'error'
                    });
                }
            })
                ;
            },
            selectCollege(val) {
                this.registerSpecForm.collegeNo = val;
            },
            selectProvince(val) {
                this.registerSpecForm.province = val;
                this.getCities(val);
            },
            selectCity(val) {
                this.registerSpecForm.city = val;
            },
            selectInstitute(val) {
                this.registerSpecForm.instituteNo = val;
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getCities(proId) {
                var that = this;
                let jsonProvinces = getCities(proId);
                jsonProvinces.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    if (respData.data.result != null) {
                        that.citiesList = respData.data.result;
                    }
                }
            })
                ;
            },
            getLevelTwoIndustry(parentId) {
                var that = this;
                let jsonLevelTwoIndustry = getLevelTwoIndustry(parentId);
                jsonLevelTwoIndustry.then((respData) => {
                    if(respData.data.code == 0){
                    if (respData.data.result != null) {
                        that.levelTwoIndustryList = respData.data.result;
                        var subData = respData.data.result;
                        for (var i=0;i<that.levelOneIndustryList.length;i++){
                            if (that.levelOneIndustryList[i].industryNo == parentId){
                                that.levelOneIndustryList[i].subIndustries = subData;
                            }
                        }
                    }
                }
                });
            },
            initMethod() {
                //获取学校集合
                var that = this;
                let jsonCollege = getColleges();
                jsonCollege.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    if (respData.data.result != null) {
                        that.collegeList = respData.data.result;
                    }
                }
            })
                ;
                //获取学院集合
                let jsonInstitute = getInstitute();
                jsonInstitute.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    if (respData.data.result != null) {
                        that.instituteList = respData.data.result;
                    }
                }
            })
                ;
                //获取省份集合
                let jsonProvinces = getProvinces();
                jsonProvinces.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    if (respData.data.result != null) {
                        that.provinceList = respData.data.result;
                    }
                }
            })
                ;
                //获取核心标签集合
                let jsonLabels = getLabels();
                jsonLabels.then((respData) => {
                    if(respData.data.code == 0
            )
                {
                    if (respData.data.result != null) {
                        that.initialLabelList = respData.data.result;
                    }
                }
            })
                ;

                //获取所有一级行业信息
                let jsonLevelOneIndustry = getLevelOneIndustry();
                jsonLevelOneIndustry.then((respData) => {
                    if(respData.data.code == 0)
                {if (respData.data.result != null) {
                        that.levelOneIndustryList = respData.data.result;
                        //初始化二级行业数据
                        for (var i=0;i<that.levelOneIndustryList.length;i++){
                            if (isNotNull(that.levelOneIndustryList[i].industryNo)){
                                this.getLevelTwoIndustry(that.levelOneIndustryList[i].industryNo);
                            }
                        }
                    }
                }
            });
            }
        }
});

</script>
