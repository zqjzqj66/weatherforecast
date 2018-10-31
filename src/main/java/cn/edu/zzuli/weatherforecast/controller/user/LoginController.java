package cn.edu.zzuli.weatherforecast.controller.user;

import cn.edu.zzuli.weatherforecast.bean.User;
import cn.edu.zzuli.weatherforecast.service.UserService;
import cn.edu.zzuli.weatherforecast.utils.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: LoginController
 * @date 2018/10/31 12:27
 */
@Api("用户登录相关操作——周启江")
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @ApiOperation("用户登录操作")
    @ResponseBody
    @RequestMapping(value = "/login/{userId}/{userPwd}", method = RequestMethod.POST)
    public Msg logins(@PathVariable(value = "userId", required = true) String userId,
                      @PathVariable(value = "userPwd", required = true) String userPwd, HttpSession session) {
        System.out.println(userId + "," + userPwd); // 仅用于测试
        // 登录操作
        Map<String, Object> error = userService.login(userId, userPwd, session);
        // 当返回值大小为0时，登陆成功，进入主页面
        if (error.isEmpty()) {
            return Msg.success().add("user", session.getAttribute("user"));
        }
        // 返回错误信息
        return Msg.fail().add("error", error);
    }

    /**
     * Description: 登出操作控制
     *
     * @param request 请求
     * @param session 会话
     * @return Msg 返回值信息
     * @Title: loginOut
     */
    @ApiOperation("用户登出操作")
    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public Msg loginOut(HttpServletRequest request, HttpSession session) {
        // 清除信息
        userService.loginOut(session, request);
        return Msg.success().setMsg("退出成功");
    }

    /**
     * Description: 新用户注册
     *
     * @param user 用户对象
     * @return Msg
     * 返回值类型
     * @Title: userRegistered
     */
    @ApiOperation("新用户注册")
    @ResponseBody
    @RequestMapping(value = "/userRegistered", method = RequestMethod.POST)
    public Msg userRegistered(User user) {
        //进行数据校验
        if (user==null) {
            return Msg.fail().setMsg("操作失败，请重试");
        } else {
            //用户名称查重
            boolean messageResult = userService.newUserRegist(user);
            //返回结果
            return messageResult ? Msg.success().setMsg("注册成功") : Msg.fail().setMsg("注册失败，请重新注册");
        }
    }

    /**
     * Description: 新用户注册时的查重(管理员和超级管理员)
     *
     * @return Msg
     * 返回值类型
     * @Title: userRechecking
     * 用户查重
     */
    @ApiOperation("新用户注册时的查重(管理员和超级管理员)")
    @ResponseBody
    @RequestMapping(value = "/userRechecking", method = RequestMethod.GET)
    public Msg nameCheck(@RequestParam(name = "flag", required = true) Integer flag,
                         @RequestParam(name = "userId", required = false) Integer userId,
                         @RequestParam(name = "name", required = true) String name) {

        //查询数据，接收数据
        int messageResult = userService.nameCheck(flag, userId,name);
        //返回查询结果
        return Msg.success().add("messageResult", messageResult);
    }

}
