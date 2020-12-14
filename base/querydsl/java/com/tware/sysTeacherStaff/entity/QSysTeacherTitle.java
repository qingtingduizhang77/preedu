package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherTitle is a Querydsl query type for SysTeacherTitle
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherTitle extends EntityPathBase<SysTeacherTitle> {

    private static final long serialVersionUID = 783538053L;

    public static final QSysTeacherTitle sysTeacherTitle = new QSysTeacherTitle("sysTeacherTitle");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certifyingAuthority = createString("certifyingAuthority");

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath level = createString("level");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath titleName = createString("titleName");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherTitle(String variable) {
        super(SysTeacherTitle.class, forVariable(variable));
    }

    public QSysTeacherTitle(Path<? extends SysTeacherTitle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherTitle(PathMetadata metadata) {
        super(SysTeacherTitle.class, metadata);
    }

}

