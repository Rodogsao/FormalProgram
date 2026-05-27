import { createRouter, createWebHistory } from 'vue-router'

import TeachingResource from '@/views/teachingResource/index.vue'

const routes = [
    {
        path: '/',
        redirect: '/teaching/resource'
    },
    {
        path: '/teaching/resource',
        component: TeachingResource
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
