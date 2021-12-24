package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.List;

public class LockAuthCode implements Serializable {
    private String aesKey	;		//可选	aes128密钥
    private List  authCodeList	;	// 	可选	授权码组
    private String authCode	;		//必填	授权码
    private int authCodeType	;		//必填	授权码类型	1-管理员，2-普通用户，3-临时用户

    public LockAuthCode(String aesKey, List authCodeList, String authCode, int authCodeType) {
        this.aesKey = aesKey;
        this.authCodeList = authCodeList;
        this.authCode = authCode;
        this.authCodeType = authCodeType;
    }

    public LockAuthCode() {
        super();
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public List getAuthCodeList() {
        return authCodeList;
    }

    public void setAuthCodeList(List authCodeList) {
        this.authCodeList = authCodeList;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public int getAuthCodeType() {
        return authCodeType;
    }

    public void setAuthCodeType(int authCodeType) {
        this.authCodeType = authCodeType;
    }

    @Override
    public String toString() {
        return "LockAuthCode{" +
                "aesKey='" + aesKey + '\'' +
                ", authCodeList=" + authCodeList +
                ", authCode='" + authCode + '\'' +
                ", authCodeType=" + authCodeType +
                '}';
    }
}
