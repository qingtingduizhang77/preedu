package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherJob is a Querydsl query type for SysTeacherJob
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherJob extends EntityPathBase<SysTeacherJob> {

    private static final long serialVersionUID = -1862879190L;

    public static final QSysTeacherJob sysTeacherJob = new QSysTeacherJob("sysTeacherJob");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath department = createString("department");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath jobNumber = createString("jobNumber");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath mainOrDputy = createString("mainOrDputy");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath unit = createString("unit");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherJob(String variable) {
        super(SysTeacherJob.class, forVariable(variable));
    }

    public QSysTeacherJob(Path<? extends SysTeacherJob> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherJob(PathMetadata metadata) {
        super(SysTeacherJob.class, metadata);
    }

}

