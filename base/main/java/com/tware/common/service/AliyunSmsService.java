package com.tware.common.service;

import com.tware.common.utils.AliyunSmsBuilder;
import com.tware.common.utils.AliyunSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliyunSmsService {

    @Autowired
    private AliyunSmsUtil aliyunSmsUtil;

    public void sendMsg(AliyunSmsBuilder aliyunSmsBuilder) throws Exception {
        aliyunSmsUtil.sendMsg(aliyunSmsBuilder);
    }
}
