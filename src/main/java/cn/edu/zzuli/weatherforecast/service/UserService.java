package cn.edu.zzuli.weatherforecast.service;

import cn.edu.zzuli.weatherforecast.bean.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: UserService
 * @date 2018/10/31 13:19
 */
@Service
public interface UserService {
    Map<String,Object> login(String userId, String userPwd, HttpSession session);

    void loginOut(HttpSession session, HttpServletRequest request);

    boolean newUserRegist(User user);

    int nameCheck(Integer flag,Integer userId, String name);

    boolean updateUser(Integer userId,String name,Object value);
}
