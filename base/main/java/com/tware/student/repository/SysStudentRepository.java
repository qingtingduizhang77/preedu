package com.tware.student.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.tware.common.repository.BaseRepository;
import com.tware.config.entity.QSpResults;
import com.tware.kindergarten.entity.QSysKindergarten;
import com.tware.org.entity.QSysOrg;
import com.tware.student.entity.QSysStudent;
import com.tware.student.entity.QSysStudentAdjustRecord;
import com.tware.student.entity.SysStudent;
import com.tware.student.entity.vo.SysStudentVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SysStudentRepository extends BaseRepository<SysStudent> {

    public QueryResults<SysStudentVo> getTransferList(int userType,String orgId,String oldOrgId, String name, String certType, String certNo,
                                                      String grade,String clazz, String adjustCause, Date adjustTime,int page, int pageSize) {
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        QSysStudentAdjustRecord qSysStudentAdjustRecord = QSysStudentAdjustRecord.sysStudentAdjustRecord;
        QueryResults<SysStudentVo> result = null;
        JPAQuery<Tuple> jpaQuery = getQuery();
        if (userType != 1) {
            jpaQuery.where(qSysStudent.oldOrgId.eq(orgId));
        }
        if (StringUtils.isNotEmpty(oldOrgId)) {
            jpaQuery.where(qSysStudentAdjustRecord.oldOrgId.eq(oldOrgId));
        }
        if (StringUtils.isNotEmpty(name)) {
            jpaQuery.where(qSysStudentAdjustRecord.name.like("%"+name+"%"));
        }
        if (StringUtils.isNotEmpty(certType)) {
            jpaQuery.where(qSysStudentAdjustRecord.certType.eq(certType));
        }
        if (StringUtils.isNotEmpty(certNo)) {
            jpaQuery.where(qSysStudentAdjustRecord.certNo.like("%"+certNo+"%"));
        }
        if (StringUtils.isNotEmpty(grade)) {
            jpaQuery.where(qSysStudentAdjustRecord.grade.eq(grade));
        }
        if (StringUtils.isNotEmpty(clazz)) {
            jpaQuery.where(qSysStudentAdjustRecord.clazz.eq(clazz));
        }
        if (StringUtils.isNotEmpty(adjustCause)) {
            jpaQuery.where(qSysStudentAdjustRecord.adjustCause.like("%"+adjustCause+"%"));
        }
        if (adjustTime != null) {
            jpaQuery.where(qSysStudentAdjustRecord.adjustTime.eq(adjustTime));
        }
        if (page > 0 && pageSize > 0) {
            jpaQuery.limit(pageSize).offset((page-1) * pageSize);
        }
        result = jpaQuery
                .select(Projections.bean(SysStudentVo.class,qSysStudentAdjustRecord.adjustCause.max().as("adjustCause"),
                        qSysStudentAdjustRecord.adjustTime.max().as("adjustTime"),
                        qSysStudent.oldOrgId,qSysStudentAdjustRecord.oldKindergartenName,qSysStudent.name,
                        qSysStudent.sex,qSysStudent.certType,qSysStudent.certNo,qSysStudent.grade,
                        qSysStudent.clazz,qSysStudentAdjustRecord.created.max().as("opTime")))
                .innerJoin(qSysStudentAdjustRecord)
                .on(qSysStudentAdjustRecord.studentId.eq(qSysStudent.id))
                .where(qSysStudent.status.in(1))
                .where(qSysStudentAdjustRecord.adjustType.eq("2"))
                .groupBy(qSysStudent.id)
                .orderBy(qSysStudentAdjustRecord.created.desc())
                .fetchResults();
        return result;
    }

    public QueryResults<SysStudent> getApplyResults(int userType,String orgId, String name, String certNo,String year,
                                                    int page, int pageSize) {
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        QSpResults qSpResults = QSpResults.spResults;
        QSysOrg qSysOrg = QSysOrg.sysOrg;
        QueryResults<SysStudent> result = null;
        JPAQuery<Tuple> jpaQuery = getQuery();
        if (userType != 1 || StringUtils.isNotEmpty(orgId)) {
            jpaQuery.where(qSysStudent.orgId.eq(orgId));
        }
        if (StringUtils.isNotEmpty(name)) {
            jpaQuery.where(qSysStudent.name.like("%"+name+"%"));
        }
        if (StringUtils.isNotEmpty(certNo)) {
            jpaQuery.where(qSysStudent.certNo.like("%"+certNo+"%"));
        }
        if (page > 0 && pageSize > 0) {
            jpaQuery.limit(pageSize).offset((page-1) * pageSize);
        }
        result = jpaQuery
                .select(Projections.bean(SysStudent.class,qSysStudent.certNo,qSysStudent.name,qSysStudent.orgId,
                        qSysOrg.name.as("kindergartenName"),qSpResults.year,qSpResults.spStatus,qSpResults.bizType,
                        qSpResults.status.as("actStatus"),qSpResults.remark))
                .leftJoin(qSpResults)
                .on(qSpResults.certNo.eq(qSysStudent.certNo).and(qSpResults.year.eq(year).or(qSpResults.year.isNull())))
                .where(qSysStudent.status.in(0))
                .groupBy(qSysStudent.id)
                .orderBy(qSysStudent.created.desc())
                .fetchResults();
        return result;
    }
}
