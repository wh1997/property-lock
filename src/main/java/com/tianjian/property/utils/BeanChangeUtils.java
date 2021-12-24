package com.tianjian.property.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date on 2020\5\9 0009  9:24
 * @description 实体类工具类
 */
public class BeanChangeUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanChangeUtils.class);

    public static <E> E mapToBean(Map source, Class<E> newClass) throws Exception {
        if (source == null){
            return null;
        }
        E newInstance  = newClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(newInstance.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                Object param = source.get(property.getName());
                if(param != null){
                    // 对于要进行
                    if(Date.class == property.getPropertyType()){
                        if(param instanceof Timestamp){
                            setter.invoke(newInstance, (Date)param);
                        }else if(param instanceof String){
                            setter.invoke(newInstance, DateUtils.stringToDate((String) param));
                        }
                    }else{
                        setter.invoke(newInstance, getMatchValue(param, property.getPropertyType()));
                    }
                }
            }
        }
        return newInstance;
    }

    /**
     * 把参数转换成指定类型
     * @param param
     * @param propertyType
     * @return
     */
    private static Object getMatchValue(Object param, Class<?> propertyType) {
        if(Integer.class ==  propertyType){
            return Integer.parseInt(String.valueOf(param));
        } else if(Long.class ==  propertyType){
            return Long.parseLong(String.valueOf(param));
        } else if(Double.class ==  propertyType){
            return Double.parseDouble(String.valueOf(param));
        }  else if(Short.class ==  propertyType){
            return Short.parseShort(String.valueOf(param));
        } else if(Float.class ==  propertyType){
            return Float.parseFloat(String.valueOf(param));
        } else if(Boolean.class ==  propertyType){
            return Boolean.parseBoolean((String) param);
        } else {
            return param;
        }
    }

    public static <E> List<E> ListMapToBean(List<Map> source, Class<E> target) throws Exception {
        try {
            // 建立目标新数组
            List<E> results = new ArrayList<>();
            // 循环拷贝数据到新数组
            for (Map map : source){
                // 新建数组的子元素
                E result = (E) mapToBean(map, target.newInstance().getClass());
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            logger.error("Map数组转bean异常，异常为：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean类型转换
     * @param source 数据来源
     * @param newClass 新数据类型
     * @param <E>
     * @return
     */
    public static <E> E beanChange(Object source, Class<E> newClass){
        // 数据不能为空
        if(source == null){
            return null;
        }
        // 要转换的数据类型不能为空
        if (newClass == null) {
            return null;
        }
        try {
            // 创建新的对象实例
            E newInstance = newClass.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            // 返回新对象
            return newInstance;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("bean类型转换异常，异常为" + e.getMessage());
            return null;
        }
    }

    /**
     * bean数组转换 <>list循环模式</>
     * @param source 数据源
     * @param target 新数组元素类型
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> listBeanChange(List<T> source, Class<V> target){
        try {
            // 建立目标新数组
            List<V> results = new ArrayList<>();
            // 循环拷贝数据到新数组
            for (T t : source){
                // 新建数组的子元素
                V result = (V) beanChange(t, target.newInstance().getClass());
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            logger.error("bean数组转换异常，异常为：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean数组转换 <>stream模式</>
     * @param source 数据源
     * @param target 新数组元素类型
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> listBeanChangeStream(List<T> source, Class<V> target){
        List<V> results = source.stream().map(item -> {
            try {
                return (V) beanChange(item, target.newInstance().getClass());
            } catch (Exception e){
                logger.error("bean数组转换异常，异常为：" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return null;

    }
    public static  <P, D> PageInfo<D> pageInfo2PageInfoDTO(PageInfo<P> pageInfoPO, Class<D> dClass) {
        Page<D> page = new Page<>(pageInfoPO.getPageNum(), pageInfoPO.getPageSize());
        page.setTotal(pageInfoPO.getTotal());
        for (P p : pageInfoPO.getList()) {
            D d = null;
            try {
                d = dClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(p, d);
            page.add(d);
        }
        return new PageInfo<>(page);
    }

    public static  <P, D> PageResult<D> page2PageResult(Page<P> page, Class<D> dClass) {
        PageResult<D> pageResult = new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal());
        List<D> row = new ArrayList<>();
        for (P p : page) {
            D d = null;
            try {
                d = dClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(p, d);
            row.add(d);
        }
        pageResult.setRows(row);
        return pageResult;
    }

    public static boolean beanIsEmpty(Object source) throws Exception {
        if(source == null){
            return true;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method getter = source.getClass().getMethod(property.getReadMethod().getName());
            if("getClass".equals(getter.getName())){
                continue;
            }
            Object param = getter.invoke(source);
            if(null != param){
                return false;
            }
        }
        return true;
    }
}
