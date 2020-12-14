package com.tware.config.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysConfig is a Querydsl query type for SysConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysConfig extends EntityPathBase<SysConfig> {

    private static final long serialVersionUID = -88922234L;

    public static final QSysConfig sysConfig = new QSysConfig("sysConfig");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath configKey = createString("configKey");

    public final StringPath configValue = createString("configValue");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysConfig(String variable) {
        super(SysConfig.class, forVariable(variable));
    }

    public QSysConfig(Path<? extends SysConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysConfig(PathMetadata metadata) {
        super(SysConfig.class, metadata);
    }

}

