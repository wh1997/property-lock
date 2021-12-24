package com.tianjian.property.web.controller;

import com.tianjian.property.bean.Address;
import com.tianjian.property.bean.Property;
import com.tianjian.property.bean.vo.AddressVo;
import com.tianjian.property.management.service.PropertyService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.AddressService;
import com.tianjian.property.web.service.SelectRoleService;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/26
 */
@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private SelectRoleService selectRoleService;
    /**
     * 获取级联地址
     * @return
     */
    @PostMapping(value = "/relationlist")
    public LockResult relationList(@RequestHeader String token, @RequestBody Map map) throws JoseException {
        try{
            Integer appUID = TokenUtil.getAppUID(token);
            Integer status = (Integer) map.get("status");
            List<Integer> addressIds = selectRoleService.selecPropertyId(appUID,status);
            if (null==addressIds){
                return new LockResult(false,  ErrorEnum.RIGHT.getErrorMsg(), ErrorEnum.RIGHT.getCode(), "");
            }
            // 提取入参
            List<Address> address = addressService.getListByIds(addressIds);
            List<AddressVo> addressVOS = BeanChangeUtils.listBeanChange(address, AddressVo.class);
            // 提取省市区
            List<AddressVo> province = new ArrayList<>(); // 省
            List<AddressVo> city = new ArrayList<>(); // 市
            List<AddressVo> area = new ArrayList<>(); // 区
            for(int i = 0; i < addressVOS.size(); i++){
                switch (addressVOS.get(i).getLevel()){
                    case 1:
                        province.add(addressVOS.get(i));
                        break;
                    case 2:
                        city.add(addressVOS.get(i));
                        break;
                    case 3:
                        area.add(addressVOS.get(i));
                        break;
                    default:
                        log.warn("ID为：" + addressVOS.get(i).getId() + "的地址级别错误");
                        break;
                }
            }
            // 查询所有有效的小区
            List<Property> propertyS = propertyService.getListBySelect();
            Map<String, Object> resultMap = new HashMap<>();
            // 封装返回数据
            resultMap.put("province", province);
            resultMap.put("city", city);
            resultMap.put("area", area);
            resultMap.put("property", propertyS);
            return new LockResult(true,  ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(), resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(true,  ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
}
