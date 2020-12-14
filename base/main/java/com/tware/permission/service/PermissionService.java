package com.tware.permission.service;

import com.tware.permission.entity.Permission;
import com.tware.permission.repository.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
@Service
public class PermissionService extends BaseService<PermissionRepository, Permission> {
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);
}
