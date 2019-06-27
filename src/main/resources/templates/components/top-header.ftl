<script type="text/x-template" id="top-header">
<div id="top-header" v-cloak>
    <el-menu
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect($event)"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
        <el-menu-item index="1">处理中心</el-menu-item>
        <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item index="2-1">一级单位管理</el-menu-item>
            <el-menu-item index="2-2">二级单位管理</el-menu-item>
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