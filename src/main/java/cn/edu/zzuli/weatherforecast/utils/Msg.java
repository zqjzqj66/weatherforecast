package cn.edu.zzuli.weatherforecast.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: Msg
 * @date 2018/10/31 12:33
 */
public class Msg {

    private int code;

    private String msg;

    private Map<String,Object> data;

    private Msg(){
    }

    public static Msg success(){
        Msg result =new Msg();
        result.setCode(100);
        return result;
    }

    public static Msg fail(){
        Msg reslut =new Msg();
        reslut.setCode(200);
        return reslut;
    }


    public Msg setCode(int code) {
        this.code = code;
        return this;
    }

    public Msg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Msg add(String key ,Object value) {
        this.data = new HashMap<>();
        this.data.put(key,value);
        return this;
    }
}
