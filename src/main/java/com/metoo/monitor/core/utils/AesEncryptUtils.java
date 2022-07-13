package com.metoo.monitor.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.metoo.monitor.core.dto.LicenseDto;
import com.metoo.monitor.core.entity.License;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

@Component
public class AesEncryptUtils {

    //可配置到Constant中，并读取配置文件注入,16位,自定义
    private static final String KEY = "@NPzwDvPmCJvpYuE";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);/*提供加密的方式：DES*/
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }
    public String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }


    public static void main(String[] args) throws Exception {
        Map map=new HashMap<String,String>();
        map.put("expireTime","1964053510");
        map.put("systemSN","80B7A0DF-3027-48B1-B56A-BD7AF2C1B0BA");
        LicenseDto dto = new LicenseDto();
//        dto.setStartTime("123456");
//        dto.setEndTime("123456");
        dto.setSystemSN("Y3HaRlRtspphK9W8v/4VUr1l4728jYrkAURR5cGpUTWVwy+8fD5741NkqLnZKEXJ");
//        dto.setType(0);
        dto.setLicenseVersion("1.0");
        dto.setLicenseFireWall(0);
        dto.setLicenseRouter(0);
        dto.setLicenseHost(0);
        dto.setLicenseUe(0);
        String content = JSONObject.toJSONString(dto);
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content, KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt("iqiRfaK2eZiaxwhfNvJ4qZ1y38M9F5NveAkBmDk3+45TuYMduFcRMdLtcZf9rGVXjutqzw8TcU+4onhVGkEeTYwSW205KinxeqSIF8J17ldc9tSxVi2eCaAr8ovAeziidJV/h+kf52b8csa1k3mSBZSJAdZUYWdRtlg2wmJCNdPjlu42aa/4Ucg/2XjT1sPLgP79rvVyaQHt1UkVp9oJeA==", KEY);
        System.out.println("解密后：" + decrypt);

    }


//    public static void main(String[] args) {
//        // 比较时间戳
//        String endTimeStamp = "1647936175";
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        long currentTime = calendar.getTimeInMillis();
//        long timeStampSec = currentTime / 1000;// 13位时间戳（单位毫秒）转换为10位字符串（单位秒）
//        String timestamp = String.format("%010d", timeStampSec);// 当前时间
//        System.out.println(Long.valueOf(endTimeStamp).compareTo(Long.valueOf(timestamp))>0);
//    }


}
