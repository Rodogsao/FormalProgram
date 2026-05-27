<template>
  <div style="padding: 20px">

    <h1>教学资源管理</h1>

    <!-- 搜索区域 -->
    <div style="margin: 20px 0">

      <el-input
          v-model="resourceName"
          placeholder="资源名称"
          style="width: 200px; margin-right: 10px"
          clearable
      />

      <el-input
          v-model="courseName"
          placeholder="课程名称"
          style="width: 200px; margin-right: 10px"
          clearable
      />

      <el-select
          v-model="resourceType"
          placeholder="资源类型"
          style="width: 150px; margin-right: 10px"
          clearable
      >
        <el-option label="pdf" value="pdf"/>
        <el-option label="docx" value="docx"/>
        <el-option label="ppt" value="ppt"/>
      </el-select>

      <el-button
          type="primary"
          @click="load"
      >
        搜索
      </el-button>

      <el-button
          type="success"
          style="margin-left: 10px"
          @click="openAdd"
      >
        新增资源
      </el-button>

    </div>

    <!-- 表格 -->
    <el-table
        :data="tableData"
        border
        style="width: 100%"
    >

      <el-table-column
          prop="id"
          label="ID"
          width="80"
      />

      <el-table-column
          prop="resourceName"
          label="资源名称"
      />

      <el-table-column
          prop="resourceType"
          label="资源类型"
          width="120"
      />

      <el-table-column
          prop="courseName"
          label="课程名称"
      />

      <el-table-column
          prop="uploadUser"
          label="上传人"
          width="120"
      />

      <el-table-column
          prop="uploadTime"
          label="上传时间"
          width="200"
      />

      <el-table-column
          label="操作"
          width="150"
      >

        <template #default="scope">

          <el-button
              type="primary"
              size="small"
          >
            编辑
          </el-button>

          <el-button
              type="danger"
              size="small"
          >
            删除
          </el-button>

        </template>

      </el-table-column>

    </el-table>

    <!-- 分页 -->
    <div style="margin-top: 20px">

      <el-pagination
          background
          layout="total, prev, pager, next"
          :total="total"
          :page-size="pageSize"
          :current-page="pageNum"
          @current-change="handleCurrentChange"
      />

    </div>

    <!-- 新增弹窗 -->
    <el-dialog
        v-model="dialogVisible"
        title="新增教学资源"
        width="500px"
    >

      <el-form
          :model="form"
          label-width="100px"
      >

        <el-form-item label="资源名称">

          <el-input v-model="form.resourceName"/>

        </el-form-item>

        <el-form-item label="资源类型">

          <el-select
              v-model="form.resourceType"
              style="width: 100%"
          >
            <el-option label="pdf" value="pdf"/>
            <el-option label="docx" value="docx"/>
            <el-option label="ppt" value="ppt"/>
          </el-select>

        </el-form-item>

        <el-form-item label="课程名称">

          <el-input v-model="form.courseName"/>

        </el-form-item>

        <el-form-item label="文件地址">

          <el-input v-model="form.fileUrl"/>

        </el-form-item>

        <el-form-item label="文件大小">

          <el-input v-model="form.fileSize"/>

        </el-form-item>

        <el-form-item label="上传用户">

          <el-input v-model="form.uploadUser"/>

        </el-form-item>

        <el-form-item label="资源描述">

          <el-input
              v-model="form.description"
              type="textarea"
          />

        </el-form-item>

      </el-form>

      <template #footer>

        <el-button @click="dialogVisible = false">
          取消
        </el-button>

        <el-button
            type="primary"
            @click="save"
        >
          保存
        </el-button>

      </template>

    </el-dialog>

  </div>
</template>

<script setup>

import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/api/request'

const tableData = ref([])

const total = ref(0)

const pageNum = ref(1)
const pageSize = ref(5)

const resourceName = ref('')
const courseName = ref('')
const resourceType = ref('')

const dialogVisible = ref(false)

const form = ref({

  resourceName: '',
  resourceType: '',
  courseName: '',
  fileUrl: '',
  fileSize: '',
  uploadUser: '',
  description: ''

})

const load = () => {

  request.get('/teaching/resource/page', {

    params: {

      pageNum: pageNum.value,
      pageSize: pageSize.value,

      resourceName: resourceName.value,
      courseName: courseName.value,
      resourceType: resourceType.value

    }

  }).then(res => {

    console.log(res)

    tableData.value = res.records
    total.value = res.total

  })
}

const handleCurrentChange = (currentPage) => {

  pageNum.value = currentPage

  load()
}

const openAdd = () => {

  form.value = {

    resourceName: '',
    resourceType: '',
    courseName: '',
    fileUrl: '',
    fileSize: '',
    uploadUser: '',
    description: ''

  }

  dialogVisible.value = true
}

const save = () => {

  request.post('/teaching/resource/save', form.value)
      .then(res => {

        ElMessage.success('新增成功')

        dialogVisible.value = false

        load()
      })
}

onMounted(() => {

  load()

})

</script>