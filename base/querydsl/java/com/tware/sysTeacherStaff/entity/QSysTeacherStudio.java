package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherStudio is a Querydsl query type for SysTeacherStudio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherStudio extends EntityPathBase<SysTeacherStudio> {

    private static final long serialVersionUID = -1498572215L;

    public static final QSysTeacherStudio sysTeacherStudio = new QSysTeacherStudio("sysTeacherStudio");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath job = createString("job");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath level = createString("level");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath remark = createString("remark");

    public final StringPath studioName = createString("studioName");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherStudio(String variable) {
        super(SysTeacherStudio.class, forVariable(variable));
    }

    public QSysTeacherStudio(Path<? extends SysTeacherStudio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherStudio(PathMetadata metadata) {
        super(SysTeacherStudio.class, metadata);
    }

}

