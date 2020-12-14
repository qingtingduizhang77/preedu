package com.tware.config.filter.where;

import com.querydsl.core.types.Predicate;

public interface WhereJoiner<T> {
    Predicate getPredicate(T t);
}
