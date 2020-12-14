package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherFamilyTies is a Querydsl query type for SysTeacherFamilyTies
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherFamilyTies extends EntityPathBase<SysTeacherFamilyTies> {

    private static final long serialVersionUID = 1825525338L;

    public static final QSysTeacherFamilyTies sysTeacherFamilyTies = new QSysTeacherFamilyTies("sysTeacherFamilyTies");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final DateTimePath<java.util.Date> birthdate = createDateTime("birthdate", java.util.Date.class);

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

    public final StringPath name = createString("name");

    public final StringPath politicsStatus = createString("politicsStatus");

    public final StringPath relation = createString("relation");

    public final StringPath unit = createString("unit");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherFamilyTies(String variable) {
        super(SysTeacherFamilyTies.class, forVariable(variable));
    }

    public QSysTeacherFamilyTies(Path<? extends SysTeacherFamilyTies> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherFamilyTies(PathMetadata metadata) {
        super(SysTeacherFamilyTies.class, metadata);
    }

}

