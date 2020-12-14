package com.tware.service.common;

import org.springframework.stereotype.Component;

@Component
public class recordTypeHandle implements IUploadFileTypeHandle<Integer> {
    @Override
    public boolean support(Integer integer) {
        return integer == 3;
    }

    @Override
    public String getFileStroeDirectory() {
        return "record/";
    }
}
