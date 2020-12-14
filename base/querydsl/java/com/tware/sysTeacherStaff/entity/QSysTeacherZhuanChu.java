package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherZhuanChu is a Querydsl query type for SysTeacherZhuanChu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherZhuanChu extends EntityPathBase<SysTeacherZhuanChu> {

    private static final long serialVersionUID = 233113167L;

    public static final QSysTeacherZhuanChu sysTeacherZhuanChu = new QSysTeacherZhuanChu("sysTeacherZhuanChu");

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

    public final StringPath sex = createString("sex");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final DateTimePath<java.util.Date> zhuanChuDate = createDateTime("zhuanChuDate", java.util.Date.class);

    public QSysTeacherZhuanChu(String variable) {
        super(SysTeacherZhuanChu.class, forVariable(variable));
    }

    public QSysTeacherZhuanChu(Path<? extends SysTeacherZhuanChu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherZhuanChu(PathMetadata metadata) {
        super(SysTeacherZhuanChu.class, metadata);
    }

}

