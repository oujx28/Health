package com.luckystar.health.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


/**
 * PBE安全编码组件
 * 终端使用userID作为口令， DEFAULTSALT作为盐
 * 终端加密后的密文是十六进制的字符串，接口需要先转换成 字节数组，才能进行解密
 * Created by ZhuLin on 2016/2/20.
 */
public class PBECoder {
    /**
     * 默认的加密密码
     */
    public String defaultPassword = "YCSZluckSn@%$!_*&";
    /**
     * 采用的加密算法
     */
    private String algorithm = "PBEWITHMD5andDES";

    /**
     * 默认盐，可配置，需要与客户端保持一致 盐的位数必须为8字字节
     */
    private String defaultSalt = "szyc2016";

    /**
     * 密钥摘要次数，可配置，需要与客户端保持一致
     */
    private int iterationsNum = 50;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getDefaultSalt() {
        return defaultSalt;
    }

    public void setDefaultSalt(String defaultSalt) {
        this.defaultSalt = defaultSalt;
    }

    public int getIterationsNum() {
        return iterationsNum;
    }

    public void setIterationsNum(int iterationsNum) {
        this.iterationsNum = iterationsNum;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    /**
     * 转换密钥<br>
     *
     * @param password 口令，这里暂定为用户名
     * @return
     * @throws Exception
     */
    private Key toKey(String password) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);

        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        return secretKey;
    }

    /**
     * 加密
     *
     * @param data     数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception {

        Key key = toKey(password);

        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 50);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        return cipher.doFinal(data);

    }

    /**
     * 解密
     *
     * @param data     数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception {

        Key key = toKey(password);

        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 50);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        return cipher.doFinal(data);

    }

    /**
     * 默认的解密方法
     *
     * @param str
     * @return
     * @throws Exception
     */
    public String decrypt(String str, String password) throws Exception {
        byte[] data = decodeHex(str);
        byte[] salt = defaultSalt.getBytes();
        byte[] decryptByteArr = decrypt(data, password, salt);
        return new String(decryptByteArr);
    }

    public String decrypt(String str) throws Exception {
        return decrypt(str, defaultPassword);
    }

    /**
     * 二行制转十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            stmp = java.lang.Integer.toHexString(b[n] & 0XFF);

            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }

        return hs.toString().toUpperCase(); // 转成大写
    }

    public static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static byte[] decodeHex(char[] data) {

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    public static byte[] decodeHex(String input) {
        return decodeHex(input.toCharArray());
    }

    public static void main(String[] args) {

        PBECoder pbecoder = new PBECoder();
        try {
//     String inputStr = "v=1.0&phone=13951950788&isregister=0&name=好吗&breedid=1";
            String inputStr = "name=zhulin&headicon=1111111&age=50&sex=0&deviceid=88888888&st=1452027262&v=1";
            URLDecoder dec = new URLDecoder();
            String inputstr1 = URLEncoder.encode(inputStr, "UTF-8");
            System.err.println("原文: " + inputstr1);
            byte[] input = inputstr1.getBytes();

            String pwd = pbecoder.defaultPassword;
            System.err.println("密码: " + pbecoder.defaultPassword);

            byte[] salt = pbecoder.getDefaultSalt().getBytes();

            byte[] data = pbecoder.encrypt(input, pwd, salt);
            String hexStr = PBECoder.byte2hex(data);
            System.err.println("加密后: " + hexStr);

            long lStart = System.currentTimeMillis();
            byte[] output = pbecoder.decrypt(data, pwd, salt);
            // 使用默认的解密方法
            String outputString = pbecoder.decrypt(hexStr, pwd);
            //
//     A3FF7AD934FFC43D78FBDC12C17156C51EBC188AF091A35CEF0E54980569FD1F9CE2556C3ACBF29946CAED07D4A2DDC05583530577AC6CA1
            String outputStr = new String(output);
            String output1 = dec.decode(outputStr);
            String output2 = dec.decode(outputString);
            System.err.println("解密后（解密方法一）: " + output1);
            System.err.println("解密后（解密方法二）: " + output2);

            long lUseTime = System.currentTimeMillis() - lStart;

            System.out.println("解密耗时：" + lUseTime + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
