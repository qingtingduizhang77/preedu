package com.tware.config.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysExternalConfig is a Querydsl query type for SysExternalConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysExternalConfig extends EntityPathBase<SysExternalConfig> {

    private static final long serialVersionUID = 714452785L;

    public static final QSysExternalConfig sysExternalConfig = new QSysExternalConfig("sysExternalConfig");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath appId = createString("appId");

    public final StringPath appSecret = createString("appSecret");

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysExternalConfig(String variable) {
        super(SysExternalConfig.class, forVariable(variable));
    }

    public QSysExternalConfig(Path<? extends SysExternalConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysExternalConfig(PathMetadata metadata) {
        super(SysExternalConfig.class, metadata);
    }

}

