<template>

  <div class="sidebar-container">

    <!-- 系统标题 -->
    <div class="logo">

      实践教学管理系统

    </div>

    <!-- 菜单 -->
    <el-menu
        router
        default-active="/dashboard"
        class="menu"
    >

      <!-- 首页 -->
      <el-menu-item index="/dashboard">

        <span>系统首页</span>

      </el-menu-item>

      <!-- 教学资源 -->
      <el-menu-item index="/teaching/resource">

        <span>教学资源管理</span>

      </el-menu-item>

      <!-- 项目管理 -->
      <el-menu-item index="/project">

        <span>项目计划管理</span>

      </el-menu-item>

      <!-- 资源管理 -->
      <el-menu-item index="/resource">

        <span>资源分配管理</span>

      </el-menu-item>

      <!-- 里程碑 -->
      <el-menu-item index="/milestone">

        <span>项目里程碑管理</span>

      </el-menu-item>

    </el-menu>

  </div>

</template>

<style scoped>

.sidebar-container {

  width: 220px;

  height: 100vh;

  background-color: #001529;

}

/* 系统标题 */
.logo {

  height: 60px;

  line-height: 60px;

  text-align: center;

  color: white;

  font-size: 18px;

  font-weight: bold;

  border-bottom: 1px solid #1f1f1f;

}

/* 菜单 */
.menu {

  border-right: none;

}

</style>