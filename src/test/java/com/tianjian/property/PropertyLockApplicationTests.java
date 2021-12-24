package com.tianjian.property;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.Property;
import com.tianjian.property.bean.Role;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.bean.vo.RoomVo;
import com.tianjian.property.management.service.*;
import com.tianjian.property.management.service.impl.HttpService;
import com.tianjian.property.utils.LockConstants;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.web.service.UserLoginService;
import com.tianjian.property.web.service.ManageService;
import com.tianjian.property.web.service.MonitorService;
import com.tianjian.property.web.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Controller
class PropertyLockApplicationTests extends HttpService{
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private NetworkCardService networkCardService;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private com.tianjian.property.management.service.UserService userService;
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserLoginService loginService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private SynService synService;
    @Autowired
    private FingerprintService fingerprintService;
    @Autowired
    private RoomDoorService roomDoorService;
    @Test
    void redisceshi()  {
       redisTemplate.opsForValue().set("嘻嘻呵呵","hahahaha");
    }
    @Test
    void test()  {
        Map map = networkCardService.openLock("867157046608501", "123", "100001");
        String status = (String) map.get("status");
        System.out.println(status);
    }
    @Test
    void test4()  {
        Map map = networkCardService.selectEquipmentStatus("867157046608501");
        System.out.println(map.toString());
    }
    @Test
    void test1()  {
        List list = networkCardService.selectEquipment(103);
        System.out.println(list);
    }
    @Test
    void test2()  {
        String apartment = gatewayService.getApartment();
        System.out.println(apartment);
    }
    @Test
    void test5()  {
        userService.selectUserByRole(100069);
    }
    @Test
    void test6()  {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        List<Property> properties = userService.selectPropertyByRole(integers);
        System.out.println(properties);
    }
    @Test
    void test8()  {
        Object o = manageService.BluetoothdDetails("0tyxLhB8vJ8=");
        System.out.println(o);
    }
    //网关详情测试
    @Test
    void test3()  {
        gatewayService.selectGateway("Zkg8OGRQ4Yk=");
    }
    //登录测试
    @Test
    void test9() throws Exception {
        //LockResult login = loginService.login("15873306605");
       // System.out.println(login.getErrorMessage());
    }
    @Test
    void test10() throws Exception {
        Role role = new Role();
        role.setName("鲁班");
        PageResult<Role> result = permissionService.selectRole(role, 1, 1);
        System.out.println(result.getRows().get(0).getName());
    }
    @Test
    void test12() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("branchId","33947904");
        map.put("buildingId","10000020");
        Object o = staffPostResult("", map);
    }
    @Test
    void test13() throws Exception {
        String userToken = TokenUtil.createToken(100069);
        System.out.println(userToken);
        redisTemplate.opsForValue().set(LockConstants.USER_TOKEN+"eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.ntZYkR3nEcADipbTN2gOAhl1dfyEiGg-9xcpdC6LvRv1_CgLDWuvVQ.84wv-riM-2UZP7z-DyXl2Q.MfJR3YpvgXtGoNM0KmJKqQ.fgnPHaGz-Clq8WhHs3MiOA","",1, TimeUnit.DAYS);
    }
    @Test
    void test11() throws Exception {
        ArrayList<Integer> integers1 = new ArrayList<>();
        integers1.add(0);
        integers1.add(1);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(100008);
        PageResult<DoorVo> doorVoPageResult = monitorService.selectPublicDoor(new Door(),integers1, integers, 1, 5);
        System.out.println(doorVoPageResult.getRows());
    }
    @Test
    void test15() throws Exception {
        LockResult result = synService.lockMessage(24);
        System.out.println(result);
    }
}