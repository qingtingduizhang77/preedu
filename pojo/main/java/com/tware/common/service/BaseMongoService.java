package com.tware.common.service;

import com.tware.common.repository.BaseMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMongoService<T extends BaseMongoRepository> {
    @Autowired
    protected T repository;
    public T getRepository() {
        return repository;
    }
}
