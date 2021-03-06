package com.tware.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAreaRelation is a Querydsl query type for UserAreaRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAreaRelation extends EntityPathBase<UserAreaRelation> {

    private static final long serialVersionUID = 1242573350L;

    public static final QUserAreaRelation userAreaRelation = new QUserAreaRelation("userAreaRelation");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

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

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QUserAreaRelation(String variable) {
        super(UserAreaRelation.class, forVariable(variable));
    }

    public QUserAreaRelation(Path<? extends UserAreaRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAreaRelation(PathMetadata metadata) {
        super(UserAreaRelation.class, metadata);
    }

}

