package com.luteng.lesson2.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John on 2015/12/29.
 */
public class Enjoiner {
    //下面static是工具类
    public static String getImageURL(String image) {
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        //TODO:检查网络，下载图片的类型
        String s = String.format(url,matcher.group(1),matcher.group(),"small",image);
        return s;
    }

    public static String getIconURL(long id, String icon) {
        String url = "http:///pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        //return String.format(url,id/1000)
        return String.format(url,id/10000,id,icon);
    }
}
