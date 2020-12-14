package com.tware.config.filter.handler;

import com.tware.config.filter.provider.FilterCauseProvider;
import swallow.framework.web.BaseQueryBean;

import java.lang.reflect.Field;

public interface DataFilterQueryBeanHandler<T> {
    boolean support(FilterCauseProvider filterCauseProvider);
    BaseQueryBean inject(T t, BaseQueryBean baseQueryBean, Field targetField) throws IllegalAccessException;
}
