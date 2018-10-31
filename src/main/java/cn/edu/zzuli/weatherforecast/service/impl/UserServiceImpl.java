package cn.edu.zzuli.weatherforecast.service.impl;

import cn.edu.zzuli.weatherforecast.bean.User;
import cn.edu.zzuli.weatherforecast.mapper.UserMapper;
import cn.edu.zzuli.weatherforecast.service.UserService;
import cn.edu.zzuli.weatherforecast.utils.BaseUtil;
import com.alibaba.druid.sql.visitor.functions.Bin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: UserServiceImpl
 * @date 2018/10/31 13:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String,Object> login(String loginName, String passward, HttpSession session) {


        User user=null;

        //判断是否有这个用户
        boolean userByLoginName = getUserByLoginName(loginName,user);
        if(userByLoginName==false){
            return BaseUtil.getInfo("msg","用户名和密码错误！");
        }

        if(user.getUserStatus().equals(0)){
            return BaseUtil.getInfo("msg","该账号已经被禁用！");
        }

        //检查密码
        boolean b = this.checkPassward(user, passward);
        if(b==false){
            return BaseUtil.getInfo("msg","用户名和密码错误！");
        }

        // 用户登陆成功
        // 将用户信息传至前台
        session.setAttribute("user", user);
        //登录成功 返回一个空info
        return BaseUtil.getNullInfo();
    }

    @Override
    public void loginOut(HttpSession session, HttpServletRequest request) {
        // 获取ip
        System.out.println(BaseUtil.getIp(request));
        // 从session中移除已登录用户
        session.removeAttribute("user");
    }

    //注册新对像
    @Override
    public boolean newUserRegist(User user) {
        // 对密码进行盐值加密
        user.setPassword(BaseUtil.Md5Encode(user.getPassword()));
        // 进行插入操作
        boolean insertResult = userMapper.insertUser(user);
        // 返回结果
        return insertResult;
    }

    @Override
    public int  nameCheck(Integer flag,Integer userId, String name) {
        int i=0;
        if(flag.equals(0)){
            Map<String, Object> info = BaseUtil.getInfo("userName", name);
            i = this.checkName(userId, name, info);
        }else{
            Map<String, Object> info = BaseUtil.getInfo("email", name);
            i = this.checkName(userId, name, info);
        }
        return i;
    }

    @Override
    public boolean updateUser(Integer userId,String name,Object value) {
        Map<String, Object> info = BaseUtil.getInfo("userId", userId, name, value);
        boolean result = userMapper.updateUser(info);
        return result ;
    }


    private int checkName(Integer userId, String name,Map<String,Object> info) {
        //默认是0，没有重复的
        int a = 0;
        List<User> list = userMapper.selectUserByInfo(info);
        //如果有id的话，代表着是添加时候验证师傅有重复的，
        if (userId == null) {
            //添加的时候必须为空，不为空，返回1
            if (!list.isEmpty()) {
                a = 1;
            }
        } else {
            //这个时候的话，肯定只有一个名称为shiftname的班次，list的不为1的话，返回1
            if (list.size() == 1) {
                if (!list.get(0).getUserId().equals(userId)) {
                    return a = 1;
                }
            } else {
                if (list.size() == 0) {
                    return a = 0;
                }
                a = 1;
            }
        }
        return a;
    }



    /*检测数据库是否有loginName（email或者username）*/
    private boolean getUserByLoginName(String loginName,User user){

        Map<String, Object> info = BaseUtil.getInfo("email", loginName);
        List<User> users = userMapper.selectUserByInfo(info);
        if(users.isEmpty()){
            info = BaseUtil.getInfo("userName", loginName);
            users = userMapper.selectUserByInfo(info);
            if(users.isEmpty()) {
                return false;
            }
        }
        user = users.get(0);
        return true;
    }

    //检查密码是否正确
    private boolean checkPassward(User user,String passward){
        String s = BaseUtil.Md5Encode(passward);
        return  user.getPassword().equals(s)?true:false;
    }
}
