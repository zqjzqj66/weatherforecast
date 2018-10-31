package cn.edu.zzuli.weatherforecast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: HelloTest
 * @date 2018/10/30 15:22
 */
@RequestMapping(value="test")
@Controller
public class HelloTest {

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String test(){

        return  "hello world";
    }
}
