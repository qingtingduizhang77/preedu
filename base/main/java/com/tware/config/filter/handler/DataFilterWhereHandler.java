package com.tware.config.filter.handler;

import com.tware.config.filter.provider.FilterCauseProvider;
import com.tware.config.filter.where.WhereJoiner;
import com.querydsl.jpa.impl.JPAQuery;

public interface DataFilterWhereHandler<T> {
    boolean support(FilterCauseProvider filterCauseProvider);
    JPAQuery<?> filter(T t, JPAQuery<?> jpaQuery, WhereJoiner whereJoiner);
}
