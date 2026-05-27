<template>
  <div class="layout-container">
    <!-- 左侧菜单 -->
    <Sidebar />
    <!-- 右侧内容 -->
    <div class="main-container">
      <!-- 顶部导航 -->
      <Header />
      <!-- 页面内容 -->
      <div class="content-container">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import Sidebar from './components/Sidebar.vue'
import Header from './components/Header.vue'
</script>

<style scoped>
.layout-container {
  display: flex;
  height: 100vh;
}

/* 左侧区域 */
.main-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: #f5f7fa;
}

/* 内容区域 */
.content-container {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}
</style>