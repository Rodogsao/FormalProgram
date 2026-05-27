import { createRouter, createWebHistory } from 'vue-router'

import teachingRoutes from './modules/teaching'

const routes = [

    // 首页重定向(目前定向到教学系统)
    {
        path: '/',
        redirect: '/teaching/resource'
    },

    // 模块路由
    ...teachingRoutes
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
