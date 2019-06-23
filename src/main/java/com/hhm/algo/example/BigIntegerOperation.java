package com.hhm.algo.example;

import java.util.Arrays;

/**
 * create by huanghaimin
 * 大整数加、减。
 *
 * 大整数相加：
 * （1） 创建两个整数数组，数组长度是较大整数的位数加1。并发两个待加的数倒序加入各自的数组。
 * （2） 创建结果数组，数组长度是较大整数的位数加1。数组的每个初始值皆为0；
 * （3） 三个数组对应的相同下标的数据相加，若相加的结果s大于1等于10，则将结果数组当前下标的值为s-10，下一个下标的值为。
 * 若不大于0，数组当前下标的值为s。
 * （4） 遍历结果数组，将其倒序输出
 *
 */
public class BigIntegerOperation {


    /**
     * 大整数相加
     * @param str1
     * @param str2
     * @return
     */
    public static String add(String str1, String str2){
        int length = 0;
        if(str1.length() >= str2.length()){
            length = str1.length() + 1;
        }else {
            length = str2.length() + 1;
        }
        int[] arr1 = new int[length];
        int[] arr2 = new int[length];
        int[] result = new int[length];

        // 待相加的数倒序加入数组。
        for(int i = 0; i < length; i++){
            if (i < str1.length()) {
                arr1[i] = str1.charAt(str1.length() -1 -i) - '0';
            }
            if (i < str2.length()) {
                arr2[i] = str2.charAt(str2.length() -1 -i) - '0';
            }
        }

        for (int i = 0; i < length-1; i++){
            int temp = result[i];
            temp += arr1[i];
            temp += arr2[i];
            if(temp >= 10) {
                result[i] = temp - 10;
                result[i + 1] = 1;
            }else {
                result[i] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 是否是最高位
        boolean firstStr = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if(!firstStr){
                if(result[i] == 0){
                    continue;
                }
                firstStr = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String str1 = "9900000000000000000000000000000000";
        String str2 = "20000000000000000000000000000000000";

        String res = add(str1,str2);
        System.out.println(res);
    }
}
