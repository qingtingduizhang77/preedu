package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherPastStudy is a Querydsl query type for SysTeacherPastStudy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherPastStudy extends EntityPathBase<SysTeacherPastStudy> {

    private static final long serialVersionUID = -1038038140L;

    public static final QSysTeacherPastStudy sysTeacherPastStudy = new QSysTeacherPastStudy("sysTeacherPastStudy");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath degree = createString("degree");

    public final StringPath degreeNumber = createString("degreeNumber");

    public final StringPath education = createString("education");

    public final StringPath educationNumber = createString("educationNumber");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath length = createString("length");

    public final StringPath major = createString("major");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath school = createString("school");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath studyMode = createString("studyMode");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherPastStudy(String variable) {
        super(SysTeacherPastStudy.class, forVariable(variable));
    }

    public QSysTeacherPastStudy(Path<? extends SysTeacherPastStudy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherPastStudy(PathMetadata metadata) {
        super(SysTeacherPastStudy.class, metadata);
    }

}

