<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include 'head.ftl'/>
</head>
<body>
<div id="Home" v-cloak>
    <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
        <el-menu-item index="1">处理中心</el-menu-item>
        <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
        </el-submenu>
        <el-menu-item index="3">订单管理</a></el-menu-item>
    </el-menu>
    <div id="form_demo">
        <el-form :label-position="left" label-width="80px" :model="formLabelAlign">
            <el-form-item label="名称">
                <el-input v-model="formLabelAlign.name"></el-input>
            </el-form-item>
            <el-form-item label="活动区域">
                <el-input v-model="formLabelAlign.region"></el-input>
            </el-form-item>
            <el-form-item label="活动形式">
                <el-input v-model="formLabelAlign.type"></el-input>
            </el-form-item>
        </el-form>
    </div>
</div>

</body>
</html>
<script>
    const vm = new Vue({
        el: '#Home',
        data: {
            formLabelAlign: '',
        },
        methods: {
            dateLoad() {
                var result = navigateHome();
                result.then((res) = > {
                    this.formLabelAlign = res.data.result;
            })
            },
        },
        mounted() {
            this.dateLoad();
        }
    })
</script>