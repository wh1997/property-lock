package com.tianjian.property.web.service;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.LockAuthorization;
import com.tianjian.property.bean.LockUser;
import com.tianjian.property.bean.User;
import com.tianjian.property.bean.vo.AuthorizationVo;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.error.BusinessException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
public interface  AuthorizationService {
    PageResult<User> selectUser(Integer pageNum, Integer pageSize, List<Integer> propertyList,String name,String phone);

    int addRight(LockAuthorization lockAuthorization);

    PageResult<Door> selectDoorByProperty(List<Integer> propertyList,Door door, Integer pageNum, Integer pageSize);

    PageResult<AuthorizationVo> selectRight(Integer userId, Integer pageNum, Integer pageSize) throws Exception;

    PageResult<Map<String, Object>> ordinarySelectRight(Integer userId,Integer pageNum, Integer pageSize) throws Exception;

    LockResult deleteRight(Integer aId, Integer doorId);

    int addLockuser(LockUser lockUser);

    int deleteLockuser(Integer userId,Integer doorId,Integer appUID);

    int updateLockuser(LockUser lockUser);

    List<Map> selectLockuser(LockUser lockUser);

    PageResult selectDoorToUser(Integer doorId, Integer pageNum, Integer pageSize);
}
