import { createRouter, createWebHistory } from 'vue-router'

import Layout from '@/layout/index.vue'

const routes = [

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [
            {
                path: 'dashboard',
                component: () =>
                    import('@/views/dashboard/index.vue')
            },
            {
                path: 'teaching/resource',
                component: () =>
                    import('@/views/teaching/teaching-resource/index.vue')
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
