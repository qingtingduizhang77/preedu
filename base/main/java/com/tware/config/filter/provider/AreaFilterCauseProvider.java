package com.tware.config.filter.provider;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.tware.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.tware.user.entity.UserAreaRelation;
import com.tware.user.service.UserManagerService;

/**
 * 区域过滤条件提供器
 */
public class AreaFilterCauseProvider implements FilterCauseProvider<List<Long>> {
    @Autowired
    private UserManagerService userManagerService;
    @Override
    public List<Long> getFilterCause() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipals().getPrimaryPrincipal();
        List<UserAreaRelation> userAreaReleationList = userManagerService.getAllUserAreaRelationByUserId((Long)user.getId());
        if(null==userAreaReleationList) {
            return Collections.emptyList();
        }
        return userAreaReleationList.stream().map(item -> item.getAreaId()).collect(Collectors.toList());
    }
}
