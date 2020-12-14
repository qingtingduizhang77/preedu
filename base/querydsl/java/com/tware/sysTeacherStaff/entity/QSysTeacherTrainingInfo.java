package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherTrainingInfo is a Querydsl query type for SysTeacherTrainingInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherTrainingInfo extends EntityPathBase<SysTeacherTrainingInfo> {

    private static final long serialVersionUID = -12684581L;

    public static final QSysTeacherTrainingInfo sysTeacherTrainingInfo = new QSysTeacherTrainingInfo("sysTeacherTrainingInfo");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath certNum = createString("certNum");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath trainingContent = createString("trainingContent");

    public final NumberPath<Integer> trainingHours = createNumber("trainingHours", Integer.class);

    public final StringPath trainingLevel = createString("trainingLevel");

    public final StringPath trainingOrganization = createString("trainingOrganization");

    public final StringPath trainingSite = createString("trainingSite");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysTeacherTrainingInfo(String variable) {
        super(SysTeacherTrainingInfo.class, forVariable(variable));
    }

    public QSysTeacherTrainingInfo(Path<? extends SysTeacherTrainingInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherTrainingInfo(PathMetadata metadata) {
        super(SysTeacherTrainingInfo.class, metadata);
    }

}

