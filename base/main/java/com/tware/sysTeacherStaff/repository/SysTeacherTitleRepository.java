package com.tware.sysTeacherStaff.repository;

import com.tware.sysTeacherStaff.entity.SysTeacherTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/9/9 10:43
 */
public interface SysTeacherTitleRepository extends JpaRepository<SysTeacherTitle,Long> {
    List<SysTeacherTitle> findByCertNum(String certNum);

    void deleteAllByCertNum(String certNum);
}
