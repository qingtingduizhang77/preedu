package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherWorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherWorkExperienceRepository extends JpaRepository<SysTeacherWorkExperience,Long> {
    List<SysTeacherWorkExperience> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
