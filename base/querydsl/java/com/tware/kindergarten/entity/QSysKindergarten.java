package com.tware.kindergarten.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysKindergarten is a Querydsl query type for SysKindergarten
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysKindergarten extends EntityPathBase<SysKindergarten> {

    private static final long serialVersionUID = -2091403386L;

    public static final QSysKindergarten sysKindergarten = new QSysKindergarten("sysKindergarten");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Double> area = createNumber("area", Double.class);

    public final StringPath areaCode = createString("areaCode");

    public final StringPath asConclusion = createString("asConclusion");

    public final StringPath byLevel = createString("byLevel");

    public final StringPath byType = createString("byType");

    public final StringPath certCode = createString("certCode");

    public final NumberPath<Double> charges = createNumber("charges", Double.class);

    public final NumberPath<Integer> classNum = createNumber("classNum", Integer.class);

    public final StringPath code = createString("code");

    public final NumberPath<Double> conArea = createNumber("conArea", Double.class);

    public final DateTimePath<java.util.Date> contractEndTime = createDateTime("contractEndTime", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath legalPerson = createString("legalPerson");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath organizer = createString("organizer");

    public final StringPath orgId = createString("orgId");

    public final NumberPath<Double> outdoorArea = createNumber("outdoorArea", Double.class);

    public final StringPath parentOrgId = createString("parentOrgId");

    public final NumberPath<Integer> personNum = createNumber("personNum", Integer.class);

    public final StringPath principal = createString("principal");

    public final StringPath principalCertNo = createString("principalCertNo");

    public final StringPath registerAddress = createString("registerAddress");

    public final NumberPath<Double> rent = createNumber("rent", Double.class);

    public final StringPath rewards = createString("rewards");

    public final StringPath schoolNature = createString("schoolNature");

    public final StringPath schoolProperty = createString("schoolProperty");

    public final DateTimePath<java.util.Date> setupTime = createDateTime("setupTime", java.util.Date.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath streetCode = createString("streetCode");

    public final NumberPath<Double> subsidyIncome = createNumber("subsidyIncome", Double.class);

    public final StringPath telephone = createString("telephone");

    public final NumberPath<Double> totalIncome = createNumber("totalIncome", Double.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysKindergarten(String variable) {
        super(SysKindergarten.class, forVariable(variable));
    }

    public QSysKindergarten(Path<? extends SysKindergarten> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysKindergarten(PathMetadata metadata) {
        super(SysKindergarten.class, metadata);
    }

}

