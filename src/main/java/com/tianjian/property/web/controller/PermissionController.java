package com.tianjian.property.web.controller;

import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.Module;
import com.tianjian.property.bean.Role;
import com.tianjian.property.bean.User;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.PermissionService;
import com.tianjian.property.web.service.SelectRoleService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:  管理员权限
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SelectRoleService selectRoleService;
    /**
    * @Description: 查询所有用户
    * @Param:  
    * @return:  
    * @Date: 2021/11/13 
    */
    @PostMapping("/select/all")
    public LockResult selectStaff(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            User user = BeanChangeUtils.mapToBean(map, User.class);
            PageResult<Map> pageResult =permissionService.selectStaff(pageNum,pageSize,user);
            if (pageResult.getRows()!=null){
                return new LockResult(true,  ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(), pageResult);
            }else {
                return new LockResult(true,  "查询成功没有数据", ErrorEnum.SUCCESS.getCode(), pageResult);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 添加管理员
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/add/all")
    public LockResult addStaff(@RequestHeader String token ,@RequestBody Map map) {
        try {
            User user = BeanChangeUtils.mapToBean(map, User.class);
            return permissionService.addStaff(user);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 删除管理员(将管理员改成普通用户)
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/delete/all")
    public LockResult deleteStaff(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer userId = (Integer) map.get("userId");
            int i= permissionService.deleteStaff(userId);
            if (i>0){
                return new LockResult(true,  "删除成功", ErrorEnum.SUCCESS.getCode(), "");
            }else {
                return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 添加角色
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/add/role")
    public LockResult addRole(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Role role = BeanChangeUtils.mapToBean(map, Role.class);
            int i= permissionService.addRole(role);
            if (i>0){
                return new LockResult(true,  "添加成功", ErrorEnum.SUCCESS.getCode(), "");
            }else {
                return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 修改角色
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/update/role")
    public LockResult updateRole(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Role role = BeanChangeUtils.mapToBean(map, Role.class);
            int i= permissionService.updateRole(role);
            if (i>0){
                return new LockResult(true,  "修改成功", ErrorEnum.SUCCESS.getCode(), "");
            }else {
                return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 删除角色
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/delete/role")
    public LockResult deleteRole(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer id = (Integer) map.get("id");
            int i= permissionService.deleteRole(id);
            if (i>0){
                return new LockResult(true,  "删除成功", ErrorEnum.SUCCESS.getCode(), "");
            }else {
                return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  角色列表
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/select/role")
    public LockResult selectRole(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            Role role = BeanChangeUtils.mapToBean(map, Role.class);
            PageResult<Role> result= permissionService.selectRole(role,pageNum,pageSize);
            return new LockResult(true,  "查询成功", ErrorEnum.SUCCESS.getCode(), result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  添加模块权限
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/add/module")
    public LockResult addModule(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Module module = BeanChangeUtils.mapToBean(map, Module.class);
            module.setCreateBy(appUID);
            int i =permissionService.addModule(module);
            if (i>0){
                return new LockResult(true,  "添加成功", ErrorEnum.SUCCESS.getCode(), "");
            }else{
                return new LockResult(false,  "添加失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  删除模块权限
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/delete/module")
    public LockResult deleteModule(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer id = (Integer) map.get("id");
            int i =permissionService.deleteModule(id);
            if (i>0){
                return new LockResult(true,  "删除成功", ErrorEnum.SUCCESS.getCode(), "");
            }else{
                return new LockResult(false,  "删除失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  修改模块权限
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/update/module")
    public LockResult updateModule(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Module module = BeanChangeUtils.mapToBean(map, Module.class);
            module.setUpdateBy(appUID);
            int i =permissionService.updateModule(module);
            if (i>0){
                return new LockResult(true,  "修改成功", ErrorEnum.SUCCESS.getCode(), "");
            }else{
                return new LockResult(false,  "修改失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  查询模块权限
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/select/module")
    public LockResult selectModule(@RequestHeader String token ,@RequestBody(required = false) Map map) {
        try {
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            Module module = BeanChangeUtils.mapToBean(map, Module.class);
            PageResult<Module> result=permissionService.selectModule(module,pageNum,pageSize);
            if (result!=null){
                return new LockResult(true,  "查询成功", ErrorEnum.SUCCESS.getCode(), result);
            }else{
                return new LockResult(true,  "查询成功没有数据", ErrorEnum.SUCCESS.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  查询模块权限
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/select/limit/module")
    public LockResult selectLimitModule(@RequestHeader String token ,@RequestBody(required = false) Map map) {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List<Integer> lists = selectRoleService.selectRoleId(appUID);
            if (lists.size()==0){
                return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
            List<Module> result=permissionService.selectLimitModule(lists);
            if (result!=null){
                return new LockResult(true,  "查询成功", ErrorEnum.SUCCESS.getCode(), result);
            }else{
                return new LockResult(true,  "查询成功没有数据", ErrorEnum.SUCCESS.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description:  模块授权
    * @Param:
    * @return:
    * @Date: 2021/11/13
    */
    @PostMapping("/module/accredit")
    public LockResult moduleAccredit(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Integer roleId = (Integer) map.get("roleId");
            List<Integer> resourcesId = (List<Integer>) map.get("resourcesId");
            String type = (String) map.get("type");
            int i =permissionService.moduleAccredit(appUID,roleId,resourcesId,type);
            if (i>0){
                return new LockResult(true,  "成功", ErrorEnum.SUCCESS.getCode(), "");
            }else{
                return new LockResult(false,  ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
     * @Description:  查看管理员模块授权
     * @Param:
     * @return:
     * @Date: 2021/11/13
     */
    @PostMapping("/select/module/accredit")
    public LockResult selectModuleAccredit(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer roleId = (Integer) map.get("roleId");
            List<Module> result =permissionService.selectModuleAccredit(roleId);
            if (result !=null){
                return new LockResult(true,  "查询成功", ErrorEnum.SUCCESS.getCode(), result);
            }else{
                return new LockResult(true,   "查询成功,没有权限", ErrorEnum.SUCCESS.getCode(), "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,   ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }

}
