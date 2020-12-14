package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherHeTong is a Querydsl query type for SysTeacherHeTong
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherHeTong extends EntityPathBase<SysTeacherHeTong> {

    private static final long serialVersionUID = -1828318076L;

    public static final QSysTeacherHeTong sysTeacherHeTong = new QSysTeacherHeTong("sysTeacherHeTong");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final DateTimePath<java.util.Date> heTongEnd = createDateTime("heTongEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> heTongStart = createDateTime("heTongStart", java.util.Date.class);

    public final StringPath heTongUnit = createString("heTongUnit");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final DateTimePath<java.util.Date> shebaoEndDate = createDateTime("shebaoEndDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> shebaoStartDate = createDateTime("shebaoStartDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherHeTong(String variable) {
        super(SysTeacherHeTong.class, forVariable(variable));
    }

    public QSysTeacherHeTong(Path<? extends SysTeacherHeTong> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherHeTong(PathMetadata metadata) {
        super(SysTeacherHeTong.class, metadata);
    }

}

