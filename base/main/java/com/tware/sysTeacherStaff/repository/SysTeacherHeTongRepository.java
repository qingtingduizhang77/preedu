package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherAchievement;
import com.tware.sysTeacherStaff.entity.SysTeacherHeTong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherHeTongRepository extends JpaRepository<SysTeacherHeTong,Long> {
    List<SysTeacherHeTong> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
