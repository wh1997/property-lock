package com.tianjian.property.web.service.impl;

import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.User;
import com.tianjian.property.bean.UserRole;
import com.tianjian.property.dao.AuthDao;
import com.tianjian.property.dao.UserDao;
import com.tianjian.property.dao.UserRoleDao;
import com.tianjian.property.management.service.impl.HttpService;
import com.tianjian.property.utils.LockConstants;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/12
 */
@Service
public class UserLoginServiceImpl extends HttpService implements UserLoginService {
    @Value("${baiwei.webLoginURL}")
    private  String webLoginURL;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private AuthDao authDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public LockResult login(String phone,String Password) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Identity",phone);
        map.put("Password",Password);
        Map fromJson = (Map) postResult(webLoginURL+"?scenario=Mobile", map);
        Map<String,Object> identity = (Map<String, Object>) fromJson.get("Identity");
        if (identity==null){
            return new LockResult(false,"登录失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
        Integer userId = (Integer) identity.get("UserId");
        String name = (String) identity.get("FullName");
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setUserId(userId);
        List<User> select = userDao.select(user);
        if (select!=null){
            String userToken = TokenUtil.createToken(userId);
            redisTemplate.opsForValue().set(LockConstants.WEB_USER_TOKEN+userToken,fromJson,1, TimeUnit.DAYS);
            fromJson.put("token",userToken);
            return new LockResult(true,"登录成功", ErrorEnum.SUCCESS.getCode(),fromJson);
        }else {
            return new LockResult(true,"登录失败,请先注册", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }

    @Override
    @Transactional
    public int addRole(Integer appUID, List<Integer> roleId, Integer userId) {
        ArrayList<UserRole> list = new ArrayList<>();
        for (int i = 0; i <roleId.size() ; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId.get(i));
            userRole.setAddPerson(appUID);
            userRole.setStatus(0);
            //查询该角色是否添加
            UserRole tUserRole1 = userRoleDao.selectOne(userRole);
            if (tUserRole1!=null){
                return -1;
            }
            list.add(userRole);
        }
        return userRoleDao.batchInsert(list);
    }

    @Override
    public int deleteRole(List<Integer> urId) {
        return userRoleDao.updateStatus(urId);
    }

    @Override
    public List<Map> selectRole(Integer userId) {
        return userRoleDao.selectRole(userId);
    }

    /*@Override
    @Transactional
    public int addRight(Integer appUID, Integer roleId, List<Integer> resourcesId) {
        ArrayList<Auth> auths = new ArrayList<>();
        for (int i = 0; i <resourcesId.size() ; i++) {
            Auth auth = new Auth();
            auth.setPersonId(appUID);
            auth.setResourcesId(resourcesId.get(i));
            auth.setRoleId(roleId);
            auth.setStatus(0);
            auth.setType("module");
        }
        return authDao.insertList(auths);
    }*/

    @Override
    public int deleteRight(List<Integer> aId,String type) {
        return authDao.deleteRight(aId,type);
    }

    @Override
    public List<Map> propertyRight(List<Integer> roleId) {
        return authDao.propertyRight(roleId);
    }
}
