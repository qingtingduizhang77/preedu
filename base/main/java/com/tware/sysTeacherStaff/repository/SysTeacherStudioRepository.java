package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherStudioRepository extends JpaRepository<SysTeacherStudio,Long> {
    List<SysTeacherStudio> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
