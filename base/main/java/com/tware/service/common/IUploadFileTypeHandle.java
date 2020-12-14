package com.tware.service.common;

public interface IUploadFileTypeHandle<T> {
    boolean support(T t);
    String getFileStroeDirectory();
}
