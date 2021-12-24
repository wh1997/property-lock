package com.tianjian.property.web.service;

import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.Module;
import com.tianjian.property.bean.Role;
import com.tianjian.property.bean.User;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
public interface PermissionService {
    PageResult<Map> selectStaff(Integer pageNum, Integer pageSize, User user);

    LockResult addStaff(User user);

    int deleteStaff(Integer userId);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(Integer id);

    PageResult<Role> selectRole(Role role,Integer pageNum, Integer pageSize);

    int addModule(Module module);

    int deleteModule(Integer id);

    int updateModule(Module module);

    PageResult<Module> selectModule(Module module,Integer pageNum, Integer pageSize);

    List<Module> selectLimitModule(List lists);

    int moduleAccredit(Integer appUID, Integer roleId, List<Integer> resourcesId,String type);

    List<Module> selectModuleAccredit(Integer roleId);


}
