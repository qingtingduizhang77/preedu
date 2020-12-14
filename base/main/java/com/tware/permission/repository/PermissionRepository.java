package com.tware.permission.repository;

import com.tware.common.repository.BaseRepository;
import com.tware.permission.entity.Permission;
import com.tware.permission.entity.QPermission;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PermissionRepository extends BaseRepository<Permission> {
    private static final Logger log = LoggerFactory.getLogger(PermissionRepository.class);

   
}
