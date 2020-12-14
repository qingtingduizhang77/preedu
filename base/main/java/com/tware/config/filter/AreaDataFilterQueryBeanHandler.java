package com.tware.config.filter;

import com.tware.config.filter.handler.DataFilterQueryBeanHandler;
import com.tware.config.filter.provider.AreaFilterCauseProvider;
import com.tware.config.filter.provider.FilterCauseProvider;
import swallow.framework.web.BaseQueryBean;

import java.lang.reflect.Field;
import java.util.List;

public class AreaDataFilterQueryBeanHandler implements DataFilterQueryBeanHandler<AreaFilterCauseProvider> {
    @Override
    public boolean support(FilterCauseProvider filterCauseProvider) {
        return filterCauseProvider instanceof AreaFilterCauseProvider;
    }

    @Override
    public BaseQueryBean inject(AreaFilterCauseProvider areaFilterCauseProvider, BaseQueryBean baseQueryBean, Field targetField) throws IllegalAccessException {
        // 当前用户所属的区域
        List<Long> areaIdsOfUser =  areaFilterCauseProvider.getFilterCause();
        targetField.set(baseQueryBean,areaIdsOfUser);
        return baseQueryBean;
    }
}
