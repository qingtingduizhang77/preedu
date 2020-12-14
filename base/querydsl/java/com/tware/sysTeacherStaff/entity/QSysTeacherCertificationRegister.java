package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherCertificationRegister is a Querydsl query type for SysTeacherCertificationRegister
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherCertificationRegister extends EntityPathBase<SysTeacherCertificationRegister> {

    private static final long serialVersionUID = -815642454L;

    public static final QSysTeacherCertificationRegister sysTeacherCertificationRegister = new QSysTeacherCertificationRegister("sysTeacherCertificationRegister");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

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

    public final DateTimePath<java.util.Date> registerDate = createDateTime("registerDate", java.util.Date.class);

    public final StringPath registerNumber = createString("registerNumber");

    public final StringPath registerUnit = createString("registerUnit");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherCertificationRegister(String variable) {
        super(SysTeacherCertificationRegister.class, forVariable(variable));
    }

    public QSysTeacherCertificationRegister(Path<? extends SysTeacherCertificationRegister> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherCertificationRegister(PathMetadata metadata) {
        super(SysTeacherCertificationRegister.class, metadata);
    }

}

