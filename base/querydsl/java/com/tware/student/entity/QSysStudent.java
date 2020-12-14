package com.tware.student.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysStudent is a Querydsl query type for SysStudent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysStudent extends EntityPathBase<SysStudent> {

    private static final long serialVersionUID = 1994929564L;

    public static final QSysStudent sysStudent = new QSysStudent("sysStudent");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath accountHolder = createString("accountHolder");

    public final StringPath bankName = createString("bankName");

    public final DateTimePath<java.util.Date> birthdayTime = createDateTime("birthdayTime", java.util.Date.class);

    public final StringPath birthplace = createString("birthplace");

    public final StringPath cardNo = createString("cardNo");

    public final StringPath census = createString("census");

    public final StringPath certNo = createString("certNo");

    public final StringPath certType = createString("certType");

    public final StringPath clazz = createString("clazz");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath currentAddress = createString("currentAddress");

    public final DateTimePath<java.util.Date> enrollmentTime = createDateTime("enrollmentTime", java.util.Date.class);

    public final StringPath grade = createString("grade");

    public final StringPath guardian = createString("guardian");

    public final StringPath guardian2 = createString("guardian2");

    public final StringPath guardianCensus = createString("guardianCensus");

    public final StringPath guardianCensus2 = createString("guardianCensus2");

    public final StringPath guardianCertNo = createString("guardianCertNo");

    public final StringPath guardianCertNo2 = createString("guardianCertNo2");

    public final StringPath guardianCertType = createString("guardianCertType");

    public final StringPath guardianCertType2 = createString("guardianCertType2");

    public final StringPath guardianName = createString("guardianName");

    public final StringPath guardianName2 = createString("guardianName2");

    public final StringPath guardianPhone = createString("guardianPhone");

    public final StringPath guardianPhone2 = createString("guardianPhone2");

    public final StringPath guardianWorkUnit = createString("guardianWorkUnit");

    public final StringPath guardianWorkUnit2 = createString("guardianWorkUnit2");

    public final StringPath hometown = createString("hometown");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath isDisability = createString("isDisability");

    public final StringPath isMartyrsChildren = createString("isMartyrsChildren");

    public final StringPath isOneParentFamily = createString("isOneParentFamily");

    public final StringPath isOrphan = createString("isOrphan");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath nation = createString("nation");

    public final StringPath oldOrgId = createString("oldOrgId");

    public final StringPath orgId = createString("orgId");

    public final StringPath residents = createString("residents");

    public final StringPath sex = createString("sex");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath subBranch = createString("subBranch");

    public final StringPath telephone = createString("telephone");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSysStudent(String variable) {
        super(SysStudent.class, forVariable(variable));
    }

    public QSysStudent(Path<? extends SysStudent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysStudent(PathMetadata metadata) {
        super(SysStudent.class, metadata);
    }

}

