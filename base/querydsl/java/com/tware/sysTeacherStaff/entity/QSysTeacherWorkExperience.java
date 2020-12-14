package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherWorkExperience is a Querydsl query type for SysTeacherWorkExperience
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherWorkExperience extends EntityPathBase<SysTeacherWorkExperience> {

    private static final long serialVersionUID = -336074546L;

    public static final QSysTeacherWorkExperience sysTeacherWorkExperience = new QSysTeacherWorkExperience("sysTeacherWorkExperience");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath job = createString("job");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final NumberPath<Integer> teachLimit = createNumber("teachLimit", Integer.class);

    public final StringPath unit = createString("unit");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherWorkExperience(String variable) {
        super(SysTeacherWorkExperience.class, forVariable(variable));
    }

    public QSysTeacherWorkExperience(Path<? extends SysTeacherWorkExperience> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherWorkExperience(PathMetadata metadata) {
        super(SysTeacherWorkExperience.class, metadata);
    }

}

