package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.example.practiceteaching.modules.resource.resource_management.entity.AllocateRecord;
import com.example.practiceteaching.modules.resource.resource_management.entity.ResourceAllocate;
import com.example.practiceteaching.modules.resource.resource_management.mapper.AllocateRecordMapper;
import com.example.practiceteaching.modules.resource.resource_management.mapper.ResourceAllocateMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.ResourceAllocateService;
import com.example.practiceteaching.modules.resource.resource_management.entity.Classroom;
import com.example.practiceteaching.modules.resource.resource_management.entity.Teacher;
import com.example.practiceteaching.modules.resource.resource_management.entity.Dormitory;
import com.example.practiceteaching.modules.resource.resource_management.mapper.ClassroomMapper;
import com.example.practiceteaching.modules.resource.resource_management.mapper.TeacherMapper;
import com.example.practiceteaching.modules.resource.resource_management.mapper.DormitoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ResourceAllocateServiceImpl implements ResourceAllocateService {

    private final ResourceAllocateMapper resourceAllocateMapper;
    private final AllocateRecordMapper allocateRecordMapper;
    private final ClassroomMapper classroomMapper;
    private final TeacherMapper teacherMapper;
    private final DormitoryMapper dormitoryMapper;

    @Override
    @Transactional
    public void allocate(ResourceAllocate allocate, String operator) {
        allocate.setCreateTime(new Date());
        resourceAllocateMapper.insert(allocate);

        updateResourceStatus(allocate, 1);

        String typeName = getAllocateTypeName(allocate.getType());
        String targetObject = typeName + "（ID：" + allocate.getResourceId() + "）";
        String content = "分配了资源，分配项目：" + (allocate.getProjectId() != null ? allocate.getProjectId() : "无");

        saveRecord(allocate.getId(), "分配", targetObject, content, operator);
    }

    @Override
    @Transactional
    public void updateAllocate(ResourceAllocate allocate, String operator) {
        resourceAllocateMapper.updateById(allocate);

        updateResourceStatus(allocate, 1);

        String typeName = getAllocateTypeName(allocate.getType());
        String targetObject = typeName + "（ID：" + allocate.getResourceId() + "）";
        String content = "修改了分配信息或延长了时间";

        saveRecord(allocate.getId(), "修改", targetObject, content, operator);
    }

    @Override
    @Transactional
    public void deleteAllocate(Long id, String operator) {
        ResourceAllocate allocate = resourceAllocateMapper.selectById(id);
        String targetObject = "未知资源";
        if (allocate != null) {
            updateResourceStatus(allocate, 0); // Revert status and endTime
            targetObject = getAllocateTypeName(allocate.getType()) + "（ID：" + allocate.getResourceId() + "）";
        }
        resourceAllocateMapper.deleteById(id);
        saveRecord(id, "回收", targetObject, "提前回收了资源", operator);
    }

    private void updateResourceStatus(ResourceAllocate allocate, int status) {
        LocalDateTime newEndTime = null;
        if (status == 1 && allocate.getEndTime() != null) {
            newEndTime = allocate.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        if (allocate.getType() != null) {
            if (allocate.getType() == 1) {
                Classroom c = classroomMapper.selectById(allocate.getResourceId());
                if (c != null) {
                    c.setStatus(status);
                    c.setEndTime(status == 1 ? newEndTime : null);
                    classroomMapper.updateById(c);
                }
            } else if (allocate.getType() == 2) {
                Teacher t = teacherMapper.selectById(allocate.getResourceId());
                if (t != null) {
                    t.setStatus(status);
                    t.setEndTime(status == 1 ? newEndTime : null);
                    teacherMapper.updateById(t);
                }
            } else if (allocate.getType() == 3) {
                Dormitory d = dormitoryMapper.selectById(allocate.getResourceId());
                if (d != null) {
                    d.setStatus(status);
                    d.setEndTime(status == 1 ? newEndTime : null);
                    dormitoryMapper.updateById(d);
                }
            }
        }
    }

    private String getAllocateTypeName(Integer type) {
        if (type == 1) return "教室";
        if (type == 2) return "师资";
        if (type == 3) return "寝室";
        return "未知资源";
    }

    private void saveRecord(Long allocateId, String type, String targetObject, String content, String operator) {
        AllocateRecord record = new AllocateRecord();
        record.setAllocateId(allocateId);
        record.setOperationType(type);
        record.setTargetObject(targetObject);
        record.setContent(content);
        record.setOperator(operator);
        record.setCreateTime(new Date());
        allocateRecordMapper.insert(record);
    }
}