package com.niit.web.blog.util;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author yujie_zhao
 * @ClassName StringUtil
 * @Description TODO
 * @Date 2019/11/17
 * @Version 1.0
 **/
public class StringUtil {
    /**
     * 提取字符串中的数字字符为一个字符串数组
     *
     * @param sourceStr
     * @return
     */
    public static String[] getDigital(String sourceStr) {
        String[] result = new String[sourceStr.length()];
        //这个2是指连续数字的最少个数
        Pattern p = Pattern.compile("\\d{2,}");
        Matcher m = p.matcher(sourceStr);
        int i = 0;
        while (m.find()) {
            result[i] = m.group();
            i++;
        }
        return result;
    }
    public static String getcode (){
        StringBuilder sb = new StringBuilder("");
        Random random = new Random();
        for(int i =0; i<6;i++){
            if (i%2==0) {
                int num = random.nextInt(10);
                sb.append(num);
            }else{
                final int temp = random.nextInt(26) + 97;
                String s = String.valueOf((char) temp);
                sb.append(s);
            }


        }
        return  sb.toString();
    }
    public static String getNum (){
        StringBuilder stringBuilder = new StringBuilder("");
        Random random = new Random();
        for(int i =0; i<4;i++){
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return  stringBuilder.toString();
    }


    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\d{2,}");//这个2是指连续数字的最少个数
        String u = "收录了 27093 篇文章，35936 人关注";
        Matcher m = p.matcher(u);
        int i = 0;
        while (m.find()) {
            System.out.println(m.group());
            i++;
        }
        System.out.println(getNum());
        System.out.println(getcode());
    }

    public static String getRandomCode() {
        return null;
    }
}
