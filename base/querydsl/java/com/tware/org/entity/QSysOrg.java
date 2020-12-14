package com.tware.org.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysOrg is a Querydsl query type for SysOrg
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysOrg extends EntityPathBase<SysOrg> {

    private static final long serialVersionUID = -1420106066L;

    public static final QSysOrg sysOrg = new QSysOrg("sysOrg");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath code = createString("code");

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

    public final StringPath orgId = createString("orgId");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysOrg(String variable) {
        super(SysOrg.class, forVariable(variable));
    }

    public QSysOrg(Path<? extends SysOrg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysOrg(PathMetadata metadata) {
        super(SysOrg.class, metadata);
    }

}

