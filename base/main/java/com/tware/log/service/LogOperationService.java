package com.tware.log.service;

import org.springframework.stereotype.Service;

import com.tware.log.entity.LogOperation;
import com.tware.log.repository.LogOperationRepository;

import swallow.framework.service.BaseService;

@Service
public class LogOperationService  extends BaseService<LogOperationRepository, LogOperation>{

}
