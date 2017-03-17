<template>
    <div class="sidebar">
        <el-menu :default-active="onRoutes" class="el-menu-vertical-demo" theme="dark" unique-opened router>
            <el-menu-item index="main">
                <i class="el-icon-setting"></i>配置中心概述
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>通用数据</template>
                <el-menu-item index="env">环境管理</el-menu-item>
                <el-menu-item index="common">通用配置</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-date"></i>项目配置</template>
                <el-submenu index="kkk">
                    <template slot="title"><i class="el-icon-setting"></i>项目管理</template>
                    <el-menu-item index="basecharts">项目列表</el-menu-item>
                    <el-menu-item index="mixcharts">数据源</el-menu-item>
                    <el-menu-item index="mixcharts">应用状态</el-menu-item>
                </el-submenu>
                <!--<el-menu-item index="vueeditor">编辑器</el-menu-item>-->
                <!--<el-menu-item index="markdown">markdown</el-menu-item>-->
                <!--<el-menu-item index="upload">文件上传</el-menu-item>-->
            </el-submenu>
            <el-submenu index="4">
                <template slot="title"><i class="el-icon-star-on"></i>ACL</template>
                <el-menu-item index="basecharts">用户管理</el-menu-item>
                <el-menu-item index="mixcharts">角色管理</el-menu-item>
                <el-menu-item index="mixcharts">权限管理</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
                <template slot="title"><i class="el-icon-setting"></i>监控</template>
                <el-menu-item index="basecharts">系统监控</el-menu-item>
                <el-menu-item index="dataSource">数据源</el-menu-item>
                <el-menu-item index="mixcharts">应用状态</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>
<script>
    export default {
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        }
    }
</script>
<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        width: 250px;
        left: 0;
        top: 70px;
        bottom:0;
        background: #2E363F;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
