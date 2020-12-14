package com.tware.user.service;

import com.tware.role.entity.Role;
import com.tware.user.entity.QUserRoleRelation;
import com.tware.user.entity.UserRoleRelation;
import com.tware.user.repository.UserRoleRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class UserRoleRelationService extends BaseService<UserRoleRelationRepository, UserRoleRelation> {

    /** 通过用户Id获取用户所有的角色
     * @param userId
     * @return
     */
    public List<Role> getAllRoleByUserId(long userId) {
        return getRepsitory().getAllRoleByUserId(userId);
    }

    /** 通过用户Id获取用户所有的角色Id
     * @param userId
     * @return
     */
    public List<Long> getAllRoleIdsByUserId(long userId) {
        return getRepsitory().getAllRoleIdsByUserId(userId);
    }

    /** 删除用户和角色的关系
     * @return
     */
    @Transactional
    public long deleteEntityByUserIdAndRoleId(long userId,long roleId){
        QUserRoleRelation qUserRoleRelation = QUserRoleRelation.userRoleRelation;
        return getRepsitory()
                .deleteEntityByColumns(query -> {
                    return query.where(
                            qUserRoleRelation.userId.eq(userId)
                    .and(qUserRoleRelation.roleId.eq(roleId)));
                });
    }
}
