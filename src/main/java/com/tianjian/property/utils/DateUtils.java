package com.tianjian.property.utils;

import com.tianjian.property.utils.error.BusinessException;
import com.tianjian.property.utils.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date on 2020\9\18 0018  15:32
 * @description 日期工具类
 */
@Slf4j
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date stringToDate(String date) throws ParseException, BusinessException {
        return stringToDate(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将字符串转换为指定日期格式
     * @param date
     * @param formatType
     * @return
     * @throws ParseException
     * @throws BusinessException
     */
    public static Date stringToDate(String date, String formatType) throws ParseException, BusinessException {
        if(null == date || date.length() == 0){
            return null;
        }
        if(isDateString(date)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
            return simpleDateFormat.parse(date);
        }
        throw new BusinessException(ErrorEnum.OPERATION_ERROR, "日期格式不正确");
    }

    /**
     * date转String
     * @param date
     * @return
     * @throws ParseException
     * @throws BusinessException
     */
    public static String dateToString(Date date) throws ParseException, BusinessException {
        return dateToString(date, DEFAULT_DATE_FORMAT);
    }
    /**
     * date转String
     * @param date
     * @param formatType
     * @return
     * @throws ParseException
     * @throws BusinessException
     */
    public static String dateToString(Date date, String formatType) throws ParseException, BusinessException {
        if(null == date){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        return simpleDateFormat.format(date);
    }
    /**
     * 检测是否是默认日期格式
     * @param date
     * @return
     */
    public static boolean isDateString(String date) {
        return isDateString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 检测是否是指定日期格式
     * @param date
     * @param formatType
     * @return
     */
    public static boolean isDateString(String date, String formatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        try {
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("日期格式不正确");
            return false;
        }
    }
    //设置时间格式，将该时间格式的时间转换为时间戳
    public static String dateToStamp(Long timestamp) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date date = new Date(timestamp);
        return simpleDateFormat.format(date);
    }
}
