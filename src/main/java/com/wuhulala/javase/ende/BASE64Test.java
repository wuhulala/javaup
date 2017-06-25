package com.wuhulala.javase.ende;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: BASE64编码器（修改3个8位01二进表示的分割为4个6位01二进制，并且在在下面的字符表中查找对应index对应的值）
 *   2<<6 = 64
 *   BASE64的编码字符有64个，分别为： {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
 *  1的字符编码为
 *
 */
public class BASE64Test {

    private final static Logger logger = LoggerFactory.getLogger(BASE64Test.class);

    private final static char[] dic = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '+', '/'};

    public static void main(String[] args) {
        String data = "123!";
        testChar2Bit(data);

        //编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encodeResult = encoder.encode(data.getBytes());

        System.out.println(encodeResult);

        //解码
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodeBytes = decoder.decodeBuffer(encodeResult);
            System.out.println(new String(decodeBytes));
        } catch (IOException e) {
            System.out.println("解码失败 【" + data +"】");
        }

    }

    /**
     * 模拟BASE64编码过程，大致意思有的 可能在处理临界条件有问题^_^
     * @param str
     */
    public static void testChar2Bit(String str){

        StringBuilder sb = new StringBuilder();

        for (int i = 0, length = str.length(); i < length; i++) {
            String resultBitString = wrapLeaderZero(Integer.toBinaryString(str.charAt(i)));
            sb.append(resultBitString);
            System.out.println(str.charAt(i)+":"+ resultBitString);
        }
        String allBitString = sb.toString();
        int length = allBitString.length();
        int sixBitLength = length / 6;
        String[] sixBitResult = new String[length];
        for (int i = 0; i < sixBitLength; i++) {
            sixBitResult[i] = allBitString.substring(i * 6 , (i+1) * 6);
            System.out.println(i + "：" +sixBitResult[i]);
        }

        //按图索骥
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sixBitLength; i++) {
            int index = Integer.parseInt(sixBitResult[i], 2);
            result.append(dic[index]);
        }

        //如果还有余位，补0
        if(length % 6 != 0){
            int tmp = length - sixBitLength * 6;
            String remainStr = allBitString.substring(sixBitLength * 6 , length);
            int offset = 6 - tmp;
            for (int i = 0; i < offset; i++) {
                remainStr += "0";
            }
            result.append(dic[Integer.parseInt(remainStr, 2)]);
        }

        //如果生成字符串不是4的倍数 补”=“
        if((sixBitLength + 1) % 4 != 0){
            for (int i = 0; i < 4 - (sixBitLength + 1) % 4 ; i++) {
                result.append("=");
            }
        }
        System.out.println(result.toString());
    }

    /**
     * 如果不够8位 补前导0
     * @return
     */
    public static String wrapLeaderZero(String origin){
        if(origin == null || origin.length() > 8){
            logger.warn("目标字符串不应该为空并且长度应该小于等于8");
            return origin;
        }
        int offset = 8 - origin.length();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < offset; i++) {
            sb.append(0);
        }
        sb.append(origin);
        return sb.toString();
    }
}
