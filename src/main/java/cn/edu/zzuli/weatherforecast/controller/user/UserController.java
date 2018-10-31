package cn.edu.zzuli.weatherforecast.controller.user;

import cn.edu.zzuli.weatherforecast.service.UserService;
import cn.edu.zzuli.weatherforecast.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: UserController
 * @date 2018/10/31 20:09
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/updatePassword" ,method = RequestMethod.GET)
    public Msg updatePassword(@RequestParam(value = "userId",required = true)Integer userId,
                              @RequestParam(value = "name",required = true)String  name,
                              @RequestParam(value = "value",required = true)Object  value){
        boolean result = userService.updateUser(userId,name,value);
        if(result){
            return Msg.success().setMsg("修改成功");
        }else{
            return Msg.success().setMsg("修改失败,请重试");
        }
    }
}
