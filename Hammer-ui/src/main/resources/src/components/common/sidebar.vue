<template>
    <div class="sidebar">
        <el-menu :default-active="onRoutes"
                 class="el-menu-vertical-demo"
                 theme="dark"
                 unique-opened
                 router>
            <el-menu-item index="main">
                <i class="fa fa-book"></i> 配置中心概述
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title"><i class="fa fa-imdb"></i> 通用数据</template>
                <el-menu-item index="env"><i class="fa fa-envira"></i> 环境管理</el-menu-item>
                <el-submenu index="c">
                    <template slot="title"><i class="fa fa-cogs"></i> 通用配置</template>
                    <el-menu-item index="const"> <i class="fa fa-leaf"></i> 通用Bean</el-menu-item>
                    <el-submenu index="x">
                        <template slot="title"><i class="fa fa-code"></i> 码表管理</template>
                        <el-menu-item index="group"><i class="fa fa-group"></i> 码本分组</el-menu-item>
                        <el-menu-item index="codebook"><i class="fa fa-list"></i> 码本数据</el-menu-item>
                    </el-submenu>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="projects">
                <i class="fa fa-eye"></i> 项目配置
            </el-menu-item>
            <el-submenu index="4">
                <template slot="title"><i class="fa fa-lock"></i> 权限控制</template>
                <el-menu-item index="users"><i class="fa fa-user"></i> 用户管理</el-menu-item>
                <el-menu-item index="roles"><i class="fa fa-user-circle"></i> 角色管理</el-menu-item>
                <el-menu-item index="permissions"><i class="fa fa-unlock"></i> 权限管理</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
                <template slot="title"><i class="fa fa-tachometer" aria-hidden="true"></i> 监控面板</template>
                <el-menu-item index="system"><i class="fa fa-server"></i> 系统监控</el-menu-item>
                <el-menu-item index="dataSource"><i class="fa fa-database"></i> Druid监控</el-menu-item>
                <el-menu-item index="application"><i class="fa fa-server"></i> 应用状态</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>
<script>
export default {
    computed: {
        onRoutes() {
            return this.$route.path.replace('/', '');
        }
    }
}
</script>
<style scoped>
.sidebar {
    display: block;
    position: absolute;
    width: 250px;
    left: 0;
    top: 70px;
    bottom: 0;
    background: #2E363F;
}

.sidebar>ul {
    height: 100%;
}
</style>
