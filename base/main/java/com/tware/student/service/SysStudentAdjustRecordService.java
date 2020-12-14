package com.tware.student.service;

import com.tware.student.entity.SysStudentAdjustRecord;
import com.tware.student.repository.SysStudentAdjustRecordRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class SysStudentAdjustRecordService extends BaseService<SysStudentAdjustRecordRepository, SysStudentAdjustRecord> {


    public SysStudentAdjustRecord insertItem(SysStudentAdjustRecord item){
        return super.insertItem(item);
    }
}
