package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherAchievement is a Querydsl query type for SysTeacherAchievement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherAchievement extends EntityPathBase<SysTeacherAchievement> {

    private static final long serialVersionUID = -781783396L;

    public static final QSysTeacherAchievement sysTeacherAchievement = new QSysTeacherAchievement("sysTeacherAchievement");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final DateTimePath<java.util.Date> achievementDate = createDateTime("achievementDate", java.util.Date.class);

    public final StringPath achievementLevel = createString("achievementLevel");

    public final StringPath achievementName = createString("achievementName");

    public final StringPath achievementType = createString("achievementType");

    public final StringPath achievementUnit = createString("achievementUnit");

    public final StringPath certNum = createString("certNum");

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

    public QSysTeacherAchievement(String variable) {
        super(SysTeacherAchievement.class, forVariable(variable));
    }

    public QSysTeacherAchievement(Path<? extends SysTeacherAchievement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherAchievement(PathMetadata metadata) {
        super(SysTeacherAchievement.class, metadata);
    }

}

