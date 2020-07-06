package com.tencent.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

public class MyUtils {
    //获取IP地址
    public static String getIpAddr(HttpServletRequest request){
        //用来获取客户端真实的ip地址
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null||ipAddress.length() == 0||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null||ipAddress.length() == 0||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null||ipAddress.length() == 0||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个ip为客户端真实ip，多个ip按照","分割
        if(ipAddress != null && ipAddress.length() > 15){
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
    //获得Md5加密
    public static String strToMd5(String str){
        String md5Str = null;
        if(str != null && str.length() != 0){
            try {
                //MessageDigest类为应用程序提供信息摘要算法的功能，如MD5算法
                //该对象通过update()来处理数据，任何时候都可以调用reset方法重置摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte[] b = md.digest();//所有的数据被更新了之后，需要重新调用digest方法来重新计算hash值
                int i;
                StringBuffer buf = new StringBuffer("");
                for(int offset = 0; offset < b.length; offset++){
                    i = b[offset];
                    if(i < 0){
                         i += 256;
                   }
                    if(i < 16){
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                md5Str = buf.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return md5Str;
    }
    //根据email来获取gravatar头像
    public static String getGravatar(String email){
        String emailMd5 = strToMd5(email);
        //设置图片大小为32px
        String avatar = "http://cn/gravatar.com/avatar/"+emailMd5+"?s=128&d=identicon&r=PG";
        return avatar;
    }


}
