package com.tianjian.property.management.service;

import com.tianjian.property.bean.Fingerprint;
import com.tianjian.property.utils.LockResult;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/7
 */
public interface FingerprintService {
    LockResult addFingerprint(Fingerprint fingerprint);

    LockResult deleteFingerprint(Integer doorId, Integer lockKeyId);

    List<Map> selectFingerprint(Integer doorId, Integer appUID);
}
