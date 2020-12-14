package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherPastStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherPastStudyRepository extends JpaRepository<SysTeacherPastStudy,Long> {
    List<SysTeacherPastStudy> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
