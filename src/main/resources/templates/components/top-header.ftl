<script type="text/x-template" id="top-header">
<div id="top-header" v-cloak>
    <el-menu
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect($event)"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
        <el-submenu index="2">
            <template slot="title">校友信息管理</template>
            <el-menu-item index="2-1">一级单位管理</el-menu-item>
            <el-menu-item index="2-2">二级单位管理</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">校友圈动态</template>
            <el-menu-item index="3-1">发现</el-menu-item>
            <el-menu-item index="3-2">发表动态</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title">个人中心</template>
            <el-menu-item index="4-1">我的动态</el-menu-item>
            <el-menu-item index="4-2">编辑资料</el-menu-item>
            <el-menu-item index="4-3">修改密码</el-menu-item>
        </el-submenu>

        <el-button type="info" @click="logout" style="list-style: none;margin: 10px;padding-left: 20px;">退出</el-button>
    </el-menu>
</div>
</script>
<script>
    Vue.component('top-header', {
        template: '#top-header',
        props: ['active','child-active'],
        mounted: function () {

        },
        methods: {
            handleSelect(val) {
                if (val == '2-1'){
                    location.href = ctx+ "/audit/alumni-manage-one-level";
                }else if (val == '2-2'){
                    location.href = ctx+ "/audit/alumni-manage";
                }
            },
            logout(){
                localStorage.clear();
                location.href = ctx+ "/base/loginPage";
            }
        }
    });
</script>