package com.tware.student.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysStudentAdjustRecord is a Querydsl query type for SysStudentAdjustRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysStudentAdjustRecord extends EntityPathBase<SysStudentAdjustRecord> {

    private static final long serialVersionUID = 241068156L;

    public static final QSysStudentAdjustRecord sysStudentAdjustRecord = new QSysStudentAdjustRecord("sysStudentAdjustRecord");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath adjustCause = createString("adjustCause");

    public final DateTimePath<java.util.Date> adjustTime = createDateTime("adjustTime", java.util.Date.class);

    public final StringPath adjustType = createString("adjustType");

    public final DateTimePath<java.util.Date> birthdayTime = createDateTime("birthdayTime", java.util.Date.class);

    public final StringPath certNo = createString("certNo");

    public final StringPath certType = createString("certType");

    public final StringPath clazz = createString("clazz");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath grade = createString("grade");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath kindergartenName = createString("kindergartenName");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath newClazz = createString("newClazz");

    public final StringPath newGrade = createString("newGrade");

    public final StringPath oldKindergartenName = createString("oldKindergartenName");

    public final StringPath oldOrgId = createString("oldOrgId");

    public final StringPath orgId = createString("orgId");

    public final StringPath sex = createString("sex");

    public final NumberPath<Long> studentId = createNumber("studentId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysStudentAdjustRecord(String variable) {
        super(SysStudentAdjustRecord.class, forVariable(variable));
    }

    public QSysStudentAdjustRecord(Path<? extends SysStudentAdjustRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysStudentAdjustRecord(PathMetadata metadata) {
        super(SysStudentAdjustRecord.class, metadata);
    }

}

