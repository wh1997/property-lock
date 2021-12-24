package com.tianjian.property.management.service;

import com.tianjian.property.utils.LockResult;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/16
 */
public interface SynService {
    LockResult lockMessage(Integer doorId);
}
