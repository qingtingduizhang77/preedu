package com.tware.config.filter.where;

import com.tware.user.entity.QUser;
import com.querydsl.core.types.Predicate;

public class AreaIdWhereJoiner implements WhereJoiner {
    @Override
    public Predicate getPredicate(Object o) {
        return QUser.user.id.eq(1l);
    }
}
