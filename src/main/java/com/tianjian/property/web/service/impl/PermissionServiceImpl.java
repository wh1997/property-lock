package com.tianjian.property.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.Module;
import com.tianjian.property.bean.Role;
import com.tianjian.property.bean.User;
import com.tianjian.property.dao.AuthDao;
import com.tianjian.property.dao.ModuleDao;
import com.tianjian.property.dao.RoleDao;
import com.tianjian.property.dao.UserDao;
import com.tianjian.property.management.service.impl.HttpService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@Service
public class PermissionServiceImpl extends HttpService implements PermissionService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private AuthDao authDao;
    @Override
    public PageResult<Map> selectStaff(Integer pageNum, Integer pageSize,User user) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map> staff = userDao.selectStaff(user);
        PageInfo<Map> staffPageInfo = new PageInfo<>(staff);
        List<Map> Doorlist = staffPageInfo.getList();
        int pages = staffPageInfo.getPages();
        //总共多少条
        long total = staffPageInfo.getTotal();
        PageResult<Map> PageResult = new PageResult<>(pageSize,pageNum,Doorlist,total,pages);
        return PageResult;
    }

    @Override
    @Transactional
    public LockResult addStaff(User user) {
        Integer userId = user.getUserId();
        Map map = userDao.selectByUserId(userId);
        int i=0;
        if (map!=null){
            i = userDao.updateByUserId(userId);
        }else{
            i = userDao.insertSelective(user);
        }
        if (i>0){
            return new LockResult(true,  "添加成功", ErrorEnum.SUCCESS.getCode(), "");
        }else{
            return new LockResult(false,ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }

    @Override
    public int deleteStaff(Integer userId) {
        return userDao.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public int addRole(Role role) {
        return roleDao.insertSelective(role);
    }

    @Override
    @Transactional
    public int updateRole(Role role) {
        return roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteRole(Integer id) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(1);
        return roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public PageResult<Role> selectRole(Role role,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleDao.selectRole(role);
        PageInfo<Role> staffPageInfo = new PageInfo<>(roles);
        List<Role> list = staffPageInfo.getList();
        int pages = staffPageInfo.getPages();
        //总共多少条
        long total = staffPageInfo.getTotal();
        PageResult<Role> PageResult = new PageResult<>(pageSize,pageNum,list,total,pages);
        return PageResult;
    }

    @Override
    @Transactional
    public int addModule(Module module) {
        return moduleDao.insertSelective(module);
    }

    @Override
    @Transactional
    public int deleteModule(Integer id) {
        return moduleDao.deleteModule(id);
    }

    @Override
    @Transactional
    public int updateModule(Module module) {
        return moduleDao.updateByPrimaryKeySelective(module);
    }

    @Override
    public PageResult<Module> selectModule(Module module,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Module> roles = moduleDao.selectModule(module);
        PageInfo<Module> staffPageInfo = new PageInfo<>(roles);
        List<Module> list = staffPageInfo.getList();
        int pages = staffPageInfo.getPages();
        //总共多少条
        long total = staffPageInfo.getTotal();
        PageResult<Module> PageResult = new PageResult<>(pageSize,pageNum,list,total,pages);
        return PageResult;

    }
    @Override
    public List<Module> selectLimitModule(List lists) {
        List<Module> roles = moduleDao.selectLimitModule(lists);
        return roles;

    }

    @Override
    public int  moduleAccredit(Integer appUID, Integer roleId, List<Integer> resourcesId,String type) {
        ArrayList<Auth> auths = new ArrayList<>();
        for (int i = 0; i <resourcesId.size() ; i++) {
            Auth auth = new Auth();
            auth.setPersonId(appUID);
            auth.setResourcesId(resourcesId.get(i));
            auth.setRoleId(roleId);
            auth.setStatus(0);
            auth.setType(type);
            auths.add(auth);
        }
        return authDao.insertAuthList(auths);
    }

    @Override
    public List<Module> selectModuleAccredit(Integer roleId) {
        //查看管理员用户的角色
        return authDao.selectModuleAccredit(roleId);
    }
}