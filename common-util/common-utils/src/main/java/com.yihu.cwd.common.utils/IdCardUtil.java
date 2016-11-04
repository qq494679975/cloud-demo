package com.yihu.cwd.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016.08.17.
 * 身份证工具类
 */
public class IdCardUtil {

    /**
     * 根据身份证的号码算出当前身份证持有者的性别
     * 1 女 2 男 3未知
     *
     * @return
     * @throws Exception
     */
    public static String getSexForIdcard(String CardCode) throws Exception{
        String sex = "未知";
            if (CardCode.length() == 18) {
                if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                    // modifid by lyr 2016-09-29
                    // sex =  Constant.level_sex_2;
                    sex ="女";
                    // modifid by lyr 2016-09-29
                } else {
                    // modifid by lyr 2016-09-29
                    // sex =  Constant.level_sex_1;
                    sex = "男";
                    // modifid by lyr 2016-09-29
                }
            } else if (CardCode.length() == 15) {
                String usex = CardCode.substring(14, 15);// 用户的性别
                if (Integer.parseInt(usex) % 2 == 0) {
                    // sex =  Constant.level_sex_2;
                    sex = "女";
                } else {
                    // sex =  Constant.level_sex_1;
                    sex = "男";
                }
            }
        return sex;
    }

//    /**
//     * 根据身份证的号码算出当前身份证持有者的年龄
//     *
//     * @param
//     * @throws Exception
//     */
//    public static int getAgeForIdcard(String card)
//            throws Exception {
//        int age = 0;
//        if (card.length() == 18) {
//            String year = card.substring(6).substring(0, 4);// 得到年份
//            String yue = card.substring(10).substring(0, 2);// 得到月份
//            // String day=CardCode.substring(12).substring(0,2);//得到日
//            Date date = new Date();// 得到当前的系统时间
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String fyear = format.format(date).substring(0, 4);// 当前年份
//            String fyue = format.format(date).substring(5, 7);// 月份
//            if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
//            } else {// 当前用户还没过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(year);
//            }
//        } else if (card.length() == 15) {
//            String uyear = "19" + card.substring(6, 8);// 年份
//            String uyue = card.substring(8, 10);// 月份
//            Date date = new Date();// 得到当前的系统时间
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String fyear = format.format(date).substring(0, 4);// 当前年份
//            String fyue = format.format(date).substring(5, 7);// 月份
//            // String fday=format.format(date).substring(8,10);
//            if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
//            } else {// 当前用户还没过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
//            }
//
//        }
//        return age;
//    }

    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @param
     * @throws Exception
     */
    public static int getAgeForIdcard(String idcard) {
        int age = 0;
        try{

        if (StringUtils.isEmpty(idcard)) {
            return age;
        }

        String birth = "";

        if (idcard.length() == 18) {
            birth = idcard.substring(6, 14);
        } else if (idcard.length() == 15) {
            birth = "19" + idcard.substring(6, 12);
        }

        int year = Integer.valueOf(birth.substring(0, 4));
        int month = Integer.valueOf(birth.substring(4, 6));
        int day = Integer.valueOf(birth.substring(6));
        Calendar cal = Calendar.getInstance();
        age = cal.get(Calendar.YEAR) - year;
        //周岁计算
        if (cal.get(Calendar.MONTH) > (month - 1) || (cal.get(Calendar.MONTH) == (month - 1) && cal.get(Calendar.DATE) > day)) {
            age--;
        }
        }catch (Exception e){
            return age;
        }
        return age;
    }

    /**
     * 身份证提取出身日期
     *
     * @param card
     * @return
     * @throws Exception
     */
    public static Date getBirthdayForIdcard(String card)
            throws Exception {
        Date b = null;
        if (card.length() == 18) {
            String year = card.substring(6).substring(0, 4);// 得到年份
            String yue = card.substring(10).substring(0, 2);// 得到月份
            String ri = card.substring(12).substring(0, 2);// 得到日
            // String day=CardCode.substring(12).substring(0,2);//得到日
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            b = format.parse(year + "-" + yue + "-" + ri);
        } else if (card.length() == 15) {
            String uyear = "19" + card.substring(6, 8);// 年份
            String uyue = card.substring(8, 10);// 月份
            String uri = card.substring(10, 12);// 得到日
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            b = format.parse(uyear + "-" + uyue + "-" + uri);
        }
        return b;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getSexForIdcard("350206199109092018"));
    }
}
