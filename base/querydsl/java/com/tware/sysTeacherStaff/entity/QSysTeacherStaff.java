package com.tware.sysTeacherStaff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysTeacherStaff is a Querydsl query type for SysTeacherStaff
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysTeacherStaff extends EntityPathBase<SysTeacherStaff> {

    private static final long serialVersionUID = 782923789L;

    public static final QSysTeacherStaff sysTeacherStaff = new QSysTeacherStaff("sysTeacherStaff");

    public final com.tware.common.entity.QBaseEntity _super = new com.tware.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath backboneTeacherDate = createString("backboneTeacherDate");

    public final StringPath backboneTeacherLevel = createString("backboneTeacherLevel");

    public final StringPath banHao = createString("banHao");

    public final StringPath bankHolder = createString("bankHolder");

    public final StringPath bankName = createString("bankName");

    public final StringPath bankNum = createString("bankNum");

    public final StringPath bankSite = createString("bankSite");

    public final DateTimePath<java.util.Date> birthay = createDateTime("birthay", java.util.Date.class);

    public final StringPath birthplace = createString("birthplace");

    public final StringPath certNum = createString("certNum");

    public final StringPath certType = createString("certType");

    public final StringPath contactWay = createString("contactWay");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath departId = createString("departId");

    public final StringPath email = createString("email");

    public final StringPath fullTimeDegree = createString("fullTimeDegree");

    public final StringPath fullTimeEducation = createString("fullTimeEducation");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> jinTieYear = createNumber("jinTieYear", Integer.class);

    public final StringPath job = createString("job");

    public final DateTimePath<java.util.Date> jobAssessTime = createDateTime("jobAssessTime", java.util.Date.class);

    public final StringPath jobLevel = createString("jobLevel");

    public final StringPath jobName = createString("jobName");

    public final StringPath jobStartTime = createString("jobStartTime");

    public final StringPath juZhuZheng = createString("juZhuZheng");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final DateTimePath<java.util.Date> longhuaWorkTime = createDateTime("longhuaWorkTime", java.util.Date.class);

    public final StringPath maritalStatus = createString("maritalStatus");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath nation = createString("nation");

    public final StringPath nativePlace = createString("nativePlace");

    public final StringPath nianji = createString("nianji");

    public final StringPath normalSchoolStudent = createString("normalSchoolStudent");

    public final DateTimePath<java.util.Date> nowWorkTime = createDateTime("nowWorkTime", java.util.Date.class);

    public final StringPath orgId = createString("orgId");

    public final StringPath partTimeDegree = createString("partTimeDegree");

    public final StringPath partTimeEducation = createString("partTimeEducation");

    public final DateTimePath<java.util.Date> partyTime = createDateTime("partyTime", java.util.Date.class);

    public final StringPath persionType = createString("persionType");

    public final StringPath placeInfo = createString("placeInfo");

    public final StringPath placeOfDomicile = createString("placeOfDomicile");

    public final StringPath politicsStatus = createString("politicsStatus");

    public final StringPath sex = createString("sex");

    public final StringPath sfTuiXiu = createString("sfTuiXiu");

    public final StringPath sheBaoNum = createString("sheBaoNum");

    public final DateTimePath<java.util.Date> shenzhenWorkTime = createDateTime("shenzhenWorkTime", java.util.Date.class);

    public final StringPath speciality = createString("speciality");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.util.Date> teacherCertDate = createDateTime("teacherCertDate", java.util.Date.class);

    public final StringPath teacherCertName = createString("teacherCertName");

    public final StringPath teacherCertNum = createString("teacherCertNum");

    public final StringPath teacherCertUnit = createString("teacherCertUnit");

    public final StringPath teachStage = createString("teachStage");

    public final StringPath teachSubject = createString("teachSubject");

    public final NumberPath<Integer> totalEducationTime = createNumber("totalEducationTime", Integer.class);

    public final DateTimePath<java.util.Date> totalWorkTime = createDateTime("totalWorkTime", java.util.Date.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath yuanZhangCertNum = createString("yuanZhangCertNum");

    public QSysTeacherStaff(String variable) {
        super(SysTeacherStaff.class, forVariable(variable));
    }

    public QSysTeacherStaff(Path<? extends SysTeacherStaff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysTeacherStaff(PathMetadata metadata) {
        super(SysTeacherStaff.class, metadata);
    }

}

