package com.tianjian.property.bean.vo;


//微信参数接收页面
public class WechatVo {
    private String encryptedData;
    private String code;
    private String iv;
    private String phone;
    private String password;

    public WechatVo(String encryptedData, String code, String iv, String phone, String password) {
        this.encryptedData = encryptedData;
        this.code = code;
        this.iv = iv;
        this.phone = phone;
        this.password = password;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WechatVo{" +
                "encryptedData='" + encryptedData + '\'' +
                ", code='" + code + '\'' +
                ", iv='" + iv + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
