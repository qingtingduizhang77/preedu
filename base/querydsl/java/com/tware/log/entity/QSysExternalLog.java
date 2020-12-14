package com.tware.log.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysExternalLog is a Querydsl query type for SysExternalLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysExternalLog extends EntityPathBase<SysExternalLog> {

    private static final long serialVersionUID = 786524579L;

    public static final QSysExternalLog sysExternalLog = new QSysExternalLog("sysExternalLog");

    public final com.tware.common.entity.QOnlyIdEntity _super = new com.tware.common.entity.QOnlyIdEntity(this);

    public final StringPath apiType = createString("apiType");

    public final StringPath appId = createString("appId");

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath message = createString("message");

    public final StringPath reqDesc = createString("reqDesc");

    public final StringPath resDesc = createString("resDesc");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysExternalLog(String variable) {
        super(SysExternalLog.class, forVariable(variable));
    }

    public QSysExternalLog(Path<? extends SysExternalLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysExternalLog(PathMetadata metadata) {
        super(SysExternalLog.class, metadata);
    }

}

