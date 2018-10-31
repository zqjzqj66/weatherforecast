package cn.edu.zzuli.weatherforecast.Interceptor;

import cn.edu.zzuli.weatherforecast.bean.User;
import cn.edu.zzuli.weatherforecast.utils.BaseUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: VerifyLoginInterceptor
 * @date 2018/10/30 18:01
 */
public class VerifyLoginInterceptor implements HandlerInterceptor {

    //注意了，这里的handlerInterceptor是所有拦截器都要继承的 它里面有
    //三个default的空方法的，注意了，这里的使用，所以的话
    //最好每次直接接的吧这个三个方法给直接的拷贝过来
    //这个是在拦截之前所做的准备 这个怕热handle是经常使用的
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        // 若为不要拦截 放行
        if (!BaseUtil.isInterCeptor(path)) {
            // 标记不要拦截
            request.setAttribute("interceptor", false);
            return true;
        }
        // 标记应要拦截
        request.setAttribute("interceptor", true);
        // 登录 拦截
        if (userIsLogin(request)) {
            return true;
        }
        //重定向到主页面
        response.sendRedirect("/goldengrain");
        return false;
    }


    /**
     * Description: 判断用户是否登录
     *
     * @Title: userIsLogin
     *
     * @param request
     *            请求
     * @return {@link Boolean} 若未登录返回false
     */
    public boolean userIsLogin(HttpServletRequest request) {
        //登录验证的这个逻辑是首先从httpserveltrequest
        //里面获取getsessionhttpsession的这个session
        //然后的判断session是否为空的 如果是空的话
        //直接的false 拦截
        HttpSession session = request.getSession(false);
        // 这个是用来校验是否强制访问需要登录的页面, 正常情况下不会出现没有session的情况
        if (session == null) {
            return false;
        }
        //不为空的话，就是从session里面取出user的这个，返回true是放行的
        // 取出存取的用户
        User user = (User) session.getAttribute("user");
        // 检验是否为空
        if (user == null) {
            return false;
        }
        return true;
    }

    //这个是在拦截之后所做的准备，一般不用的
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    //这个是所有都做完了，所做的准备，一般不用的
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
