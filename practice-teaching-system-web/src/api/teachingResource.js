import request from './request'

// 分页查询
export function getResourcePage(params) {

    return request({
        url: '/teaching/resource/page',
        method: 'get',
        params
    })
}

// 删除
export function deleteResource(id) {

    return request({
        url: `/teaching/resource/delete/${id}`,
        method: 'delete'
    })
}