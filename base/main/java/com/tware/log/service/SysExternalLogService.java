package com.tware.log.service;

import com.tware.log.entity.SysExternalLog;
import com.tware.log.repository.SysExternalLogRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class SysExternalLogService extends BaseService<SysExternalLogRepository, SysExternalLog> {
}
