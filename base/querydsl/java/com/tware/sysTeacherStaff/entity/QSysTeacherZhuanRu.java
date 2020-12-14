package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherZhuanRu is a Querydsl query type for SysTeacherZhuanRu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherZhuanRu extends EntityPathBase<SysTeacherZhuanRu> {

    private static final long serialVersionUID = -1932142396L;

    public static final QSysTeacherZhuanRu sysTeacherZhuanRu = new QSysTeacherZhuanRu("sysTeacherZhuanRu");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    public final StringPath certType = createString("certType");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath departId = createString("departId");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath job = createString("job");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath orgIdBefore = createString("orgIdBefore");

    public final StringPath orgIdNow = createString("orgIdNow");

    public final StringPath sex = createString("sex");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final DateTimePath<java.util.Date> zhuanRuDate = createDateTime("zhuanRuDate", java.util.Date.class);

    public QSysTeacherZhuanRu(String variable) {
        super(SysTeacherZhuanRu.class, forVariable(variable));
    }

    public QSysTeacherZhuanRu(Path<? extends SysTeacherZhuanRu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherZhuanRu(PathMetadata metadata) {
        super(SysTeacherZhuanRu.class, metadata);
    }

}

