package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherAchievementRepository extends JpaRepository<SysTeacherAchievement,Long> {
    //@Query("select t from SysTeacherAchievement t where t.sysTeacherStaffId = ?1")
    List<SysTeacherAchievement> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
