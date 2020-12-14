package com.tware.sysTeacherStaff.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.tware.common.repository.BaseRepository;
import com.tware.config.entity.QSpResults;
import com.tware.org.entity.QSysOrg;
import com.tware.sysTeacherStaff.entity.QSysTeacherStaff;
import com.tware.sysTeacherStaff.entity.SysTeacherStaff;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Guoyz
 * createTime   2020/9/7 15:35
 */
@Service
public class SysTeacherStaffRepository extends BaseRepository<SysTeacherStaff> {

    public QueryResults<SysTeacherStaff> getApplyResults( String orgId, String name, String certNo, String year,
                                                    int page, int pageSize) {
        QSysTeacherStaff sysTeacherStaff = QSysTeacherStaff.sysTeacherStaff;
        QSpResults qSpResults = QSpResults.spResults;
        QSysOrg qSysOrg = QSysOrg.sysOrg;
        QueryResults<SysTeacherStaff> result = null;
        JPAQuery<Tuple> jpaQuery = getQuery();
        if (StringUtils.isNotEmpty(orgId)) {
            jpaQuery.where(sysTeacherStaff.orgId.eq(orgId));
        }
        if (StringUtils.isNotEmpty(name)) {
            jpaQuery.where(sysTeacherStaff.name.like("%"+name+"%"));
        }
        if (StringUtils.isNotEmpty(certNo)) {
            jpaQuery.where(sysTeacherStaff.certNum.like("%"+certNo+"%"));
        }
        if (page > 0 && pageSize > 0) {
            jpaQuery.limit(pageSize).offset((page-1) * pageSize);
        }
        result = jpaQuery
                .select(Projections.bean(SysTeacherStaff.class,sysTeacherStaff.certNum,sysTeacherStaff.name,sysTeacherStaff.orgId,sysTeacherStaff.lastmodi
                        ,qSysOrg.name.as("kindergarten"),qSpResults.year,qSpResults.spStatus,qSpResults.bizType, qSpResults.status.as("actStatus"),qSpResults.remark))
                .leftJoin(qSpResults)
                .on(qSpResults.certNo.eq(sysTeacherStaff.certNum).and(qSpResults.year.eq(year).or(qSpResults.year.isNull())))
                .where(sysTeacherStaff.status.in(0))
                .groupBy(sysTeacherStaff.id)
                .orderBy(sysTeacherStaff.created.desc())
                .fetchResults();
        return result;

    }

}
