package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherExamine is a Querydsl query type for SysTeacherExamine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherExamine extends EntityPathBase<SysTeacherExamine> {

    private static final long serialVersionUID = 1345066318L;

    public static final QSysTeacherExamine sysTeacherExamine = new QSysTeacherExamine("sysTeacherExamine");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath examineResult = createString("examineResult");

    public final StringPath examineUnit = createString("examineUnit");

    public final StringPath examineYear = createString("examineYear");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherExamine(String variable) {
        super(SysTeacherExamine.class, forVariable(variable));
    }

    public QSysTeacherExamine(Path<? extends SysTeacherExamine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherExamine(PathMetadata metadata) {
        super(SysTeacherExamine.class, metadata);
    }

}

