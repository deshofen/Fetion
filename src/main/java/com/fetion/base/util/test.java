package com.fetion.base.util;

public class test {

    //测试用例1
//    public static void main(String[] args) {
//        //16位
//        String key = "MIGfMA0GCSqGSIb3";
//
//        //字符串
//        String str = "2239329914@qq.com:卞宇轩";
//        try {
//            //加密
//            String encrypt = AesUtil.encrypt(str, key);
//            //解密
//            String decrypt = AesUtil.decrypt(encrypt, key);
//
//            System.out.println("加密前：" + str);
//            System.out.println("加密后：" + encrypt);
//            System.out.println("解密后：" + decrypt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //测试用例2
//    public static void main(String[] args) {
//        //16位
//        String key = "MIGfMA0GCSqGSIb3";
//
//        //复杂对象
//        ImsUserVo userVo = new ImsUserVo();
//        userVo.setUserName("123456");
//        userVo.setPassword("111111");
//        try {
//            //加密
//            String encrypt = AesUtil.encrypt(userVo.toString(), key);
//            //解密
//            String decrypt = AesUtil.decrypt(encrypt, key);
//            System.out.println("加密前：" + userVo.toString());
//            System.out.println("加密后：" + encrypt);
//            System.out.println("解密后：" + decrypt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //测试用例3
//    public static void main(String[] args) {
//        //复杂对象
//        ImsUserVo userVo = new ImsUserVo();
//        userVo.setUserName("123456");
//        userVo.setPassword("111111");
//        try {
//            System.out.println("私钥：" + RsaUtil.getPrivateKey());
//            System.out.println("公钥：" + RsaUtil.getPublicKey());
//
//            //公钥加密
//            byte[] ciphertext = RsaUtil.encryptByPublicKey(userVo.toString().getBytes(), RsaUtil.getPublicKey());
//            //私钥解密
//            byte[] plaintext = RsaUtil.decryptByPrivateKey(ciphertext, RsaUtil.getPrivateKey());
//
//            System.out.println("公钥加密前：" + userVo.toString());
//            System.out.println("公钥加密后：" + Base64.encodeBase64String(ciphertext));
//            System.out.println("私钥解密后：" + new String(plaintext));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}