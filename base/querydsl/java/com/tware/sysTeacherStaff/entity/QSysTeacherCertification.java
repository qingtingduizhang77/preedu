package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherCertification is a Querydsl query type for SysTeacherCertification
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherCertification extends EntityPathBase<SysTeacherCertification> {

    private static final long serialVersionUID = -449344281L;

    public static final QSysTeacherCertification sysTeacherCertification = new QSysTeacherCertification("sysTeacherCertification");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certificationCertification = createString("certificationCertification");

    public final StringPath certificationType = createString("certificationType");

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath subject = createString("subject");

    public final StringPath teacherCertificationNumber = createString("teacherCertificationNumber");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherCertification(String variable) {
        super(SysTeacherCertification.class, forVariable(variable));
    }

    public QSysTeacherCertification(Path<? extends SysTeacherCertification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherCertification(PathMetadata metadata) {
        super(SysTeacherCertification.class, metadata);
    }

}

