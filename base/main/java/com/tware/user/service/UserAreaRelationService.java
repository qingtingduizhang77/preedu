package com.tware.user.service;

import com.tware.user.entity.QUserAreaRelation;
import com.tware.user.entity.UserAreaRelation;
import com.tware.user.repository.UserAreaRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAreaRelationService extends BaseService<UserAreaRelationRepository, UserAreaRelation> {
    /** 根据用户Id获取用户的所有对应区域Id
     * @param userId
     * @return
     */
    public List<UserAreaRelation> getAllUserAreaRelationByUserId(long userId) {
        return getRepsitory()
                .findEntitysByColumns(query -> {
                    return query.where(QUserAreaRelation.userAreaRelation.userId.eq(userId));
                });
    }
    /** 通过用户Id获取用户所有的区域Id
     * @param userId
     * @return
     */
    public List<Long> getAllAreaIdsByUserId(long userId) {
        return getAllUserAreaRelationByUserId(userId).stream().map(item -> item.getAreaId()).collect(Collectors.toList());
    }
    /** 删除用户和角色的关系
     * @return
     */
    @Transactional
    public long deleteEntityByUserIdAndAreaId(long userId,long areaId){
        QUserAreaRelation qUserAreaRelation = QUserAreaRelation.userAreaRelation;
        return getRepsitory()
                .deleteEntityByColumns(query -> {
                    return query.where(
                            qUserAreaRelation.userId.eq(userId)
                                    .and(qUserAreaRelation.areaId.eq(areaId)));
                });
    }
}
