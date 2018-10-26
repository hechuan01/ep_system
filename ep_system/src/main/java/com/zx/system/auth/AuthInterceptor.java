package com.zx.system.auth;

import com.zx.common.annotation.NotAuthPassport;
import com.zx.common.controller.BaseController;
import com.zx.common.utils.CookieUtil;
import com.zx.system.model.UserLogin;
import com.zx.system.service.UserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by Tony on 2017/3/8.
 * 权限控制拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
//
//            //没有声明需要权限,或者声明不验证权限
//            if (authPassport == null || authPassport.validate() == false)
//                return true;
//            else {
//                //在这里实现自己的权限验证逻辑
////                if (true)//如果验证成功返回true(这里直接写false来模拟验证失败的处理)
////                    return true;
////                else {//如果验证失败
////                    //返回到登录界面
////                    response.sendRedirect("register.html");
////                    return false;
////                }
//
//
//            }
//        } else
//            return true;
//    }

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
        /*
     注意：每次重新启动浏览器会重新启动一个sessionId和Cookie，之前设置的session会因为sessionId的变化而取不到,所以会出现用户明明已经登录，但是重开浏览器又需要登录.
     流程：
     1、用户选择记住密码：取出cookie中的用户名和密码查询,如果此用户已不存在，则清除cookie中的值，如果存在则判断用户是否重新登录,如果未重新登录则将cookie中用户信息设置到session中,如果用户重新登录了则判断登录用户是否与cookie中用户一致，一致则将cookie中用户信息设置到session中,不一致则将当前登录用户的信息设置到session中。
     将用户信息放入session中是为了(通过cookie中的用户名密码可以得到用户信息):
     1、重开浏览器的时候如果已经登录的用户可以直接进入
     2、防止用户直接将执行方法的连接拷贝进地址栏，而方法中又需要在session中取用户信息的错误
     2、用户未选记住密码：判断session中是否存在用户信息，如果存在，则true，如果不存在则返回登录页面
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userLogin") == null) {
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                NotAuthPassport notAuthPassport = ((HandlerMethod) handler).getMethodAnnotation(NotAuthPassport.class);
                //声明不验证权限
                if (notAuthPassport != null)
                    return true;
                else {
                    String path = request.getContextPath();
//                    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

                    Cookie cokLoginName = CookieUtil.getCookieByName(request, "loginid");
                    Cookie cokLoginPwd = CookieUtil.getCookieByName(request, "psd");
                    //如果前面的人登录勾选了记住密码,cookie中存在上一个人的信息
                    if (cokLoginName != null && cokLoginPwd != null && cokLoginName.getValue() != "" && cokLoginPwd.getValue() != "") {
                        String loginid = cokLoginName.getValue();
                        String psd = cokLoginPwd.getValue();

                        // 检查到客户端保存了用户的密码，进行该账户的验证
                        UserLogin userLogin = userService.login(loginid, psd);

                        //如果此人不存在
                        if (userLogin.getID() == null) {
                            CookieUtil.addCookie(response, "loginid", null, 0); // 清除Cookie
                            CookieUtil.addCookie(response, "psd", null, 0); // 清除Cookie
                            try {
//                                response.sendRedirect("/home/login.html");
                                response.getWriter().write("<script>top.location.href='" + request.getContextPath() + "/home/login.html'</script>");
                                return false;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            request.getSession().setAttribute("errorInfo", "请登录！");
                        }
                        //如果存在此人
                        else {
                            UserLogin user = (UserLogin) request.getSession().getAttribute("userLogin");
                            if (user == null) {//如果未登录而直接拷贝地址栏进入页面
                                request.getSession().setAttribute("userLogin", userLogin);
                            } else {//用户登录后
                                if (user.getLoginId().equals(userLogin.getLoginId())) {//如果当前登录人与cookie中信息一致
                                    request.getSession().setAttribute("userLogin", userLogin);
                                } else {//如果当前登录人与cookie中信息不一致
                                    request.getSession().setAttribute("userLogin", user);
                                }
                            }
                        }
                    } else {
                        //如果cookie中没有内容，即未勾选记住密码，或者是退出后清除了cookie
                        UserLogin u = (UserLogin) request.getSession().getAttribute("userLogin");
                        if (u == null) {//如果未登录
//                            response.sendRedirect("/home/login.html");
                            response.getWriter().write("<script>top.location.href='" + request.getContextPath() + "/home/login.html'</script>");
                            return false;
                        } else {
                            //如果已经登录

                        }
                    }
                }
                /******退出的时候记得清除cookie中的内容,如果用户已经登录********/
            }
        }
        if (BaseController.loginInfo == null) {
            BaseController.loginInfo = (UserLogin) request.getSession().getAttribute("userLogin");
        }
        if (BaseController.allModules == null || BaseController.allModules.size() <= 0) {
            BaseController.allModules = (List<String>) request.getSession().getAttribute("allModules");
        }
        //验证菜单是否有权限

        if (BaseController.allModules.contains(request.getServletPath())) {
            if (BaseController.loginInfo.isAdmin()) {
                return true;
            } else if (!com.zx.common.controller.BaseController.loginInfo.getModules().stream().anyMatch(l -> l.getMurl().equals(request.getServletPath()))) {
                //没有权限
                response.getWriter().write("<script>top.location.href='" + request.getContextPath() + "/home/login.html'</script>");
                return false;
            }
        }
        return true;
    }
}