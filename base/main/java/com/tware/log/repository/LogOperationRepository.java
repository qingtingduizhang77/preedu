package com.tware.log.repository;

import org.springframework.stereotype.Service;

import com.tware.log.entity.LogOperation;

import swallow.framework.jpaquery.repository.SwallowRepository;

@Service
public class LogOperationRepository extends SwallowRepository<LogOperation>{

}
