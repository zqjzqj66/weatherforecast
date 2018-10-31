package cn.edu.zzuli.weatherforecast.utils;

import cn.edu.zzuli.weatherforecast.exception.KeyValueException;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: BaseUtil
 * @date 2018/10/30 18:28
 */
public class BaseUtil {

    /**
     * Description: 用于获取IP地址
     *
     * @Title: getIp
     *
     * @param request
     * @return String
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Description: 传入要初始化的map, 之后以键值对的方式成对的传入信息
     *
     * @Title: initInfo
     *
     * @param info
     * @param strs
     */
    public static void initInfo(Map<String, Object> info, Object... strs) {
        // 如果不符合键值对规则
        if (strs.length % 2 != 0) {
            return;
        }
        info.clear();
        for (int i = 0; i < strs.length; i++) {
            // 跳过值为空的键值对
            if (strs[i + 1] == null) {
                i++;
                continue;
            }
            // 将成对的键值对放入map
            info.put(strs[i++].toString(), strs[i]);
        }
    }

    /*返回一个空的hashmap*/
    public static Map<String , Object> getNullInfo(){
        return new HashMap<>();
    }

    /*把可以和value传过啦,直接的封装在一个info里面*/
    public static Map<String ,Object> getInfo(Object... strs){
        Map<String,Object> info =new HashMap<>();
        if (strs.length % 2 != 0) {
            throw new KeyValueException("key和value不对应");
        }
        info.clear();
        for (int i = 0; i < strs.length; i++) {
            // 跳过值为空的键值对
            if (strs[i + 1] == null) {
                i++;
                continue;
            }
            // 将成对的键值对放入map
            info.put(strs[i++].toString(), strs[i]);
        }

        return info;
    }

    /**
     *
     * Description: 用于对密码进行MD5盐值加密
     *
     * @Title: Md5Encode
     *
     * @param password
     * @return String
     */
    public static String Md5Encode(String password) {
        String md5Password = "";
        try {
            // 获取MD5加密器
            //注意了，这里的md5加密是java自己的
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加密次数
            for (int i = 0; i < 10; i++) {
                // 加密
                md5.update(password.getBytes());
                // 获取加密后的值
                byte[] result = md5.digest();
                StringBuffer buffer = new StringBuffer();
                // 进行盐值加密
                for (byte b : result) {
                    // 利用与运算
                    int number = b & 0xff;
                    String str = Integer.toHexString(number);
                    if (str.length() == 1) {
                        buffer.append("0");
                    } else {
                        buffer.append(str);
                    }
                }
                // 获取加密后的值
                md5Password = password = buffer.toString();
                // 处理下次要加密的密码 因为要加密十次
                password = password.substring(0, password.length() - 1);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Password;
    }

    /**
     * Description: 设置拦截器要拦截的请求
     *
     * @Title: isInterCeptor
     *
     * @param path
     *            路径
     * @return {@link Boolean} 是否要拦截
     */
    public static boolean isInterCeptor(String path) {
        //我也是有点忘了，这里的isInterceptor是一个特殊的情况的
        //只要请求里面有login的话，就是不拦截的
        //然后的 含有。do和。page的都是拦截的
        //我国际这个是学长另外突然设置的，其实也不碍事的
        // 不拦截登录请求
        if (path.contains("login")) {
            return false;
        }
        // 拦截尾缀.do .page
        return (path.contains(".do") || path.contains(".page"));
    }

}
