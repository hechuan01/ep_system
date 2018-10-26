package com.zx.system.controller;

import com.zx.common.annotation.NotAuthPassport;
import com.zx.common.controller.BaseController;
import com.zx.common.utils.*;
import com.zx.system.model.UserLogin;
import com.zx.system.service.UserService;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/16
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * Method description
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("loginInfo", loginInfo);
        return "home/index";
    }

    @RequestMapping("/center")
    public String center(Model model) {

        return "home/center";
    }
    @RequestMapping("/getIpdnscount")
    @ResponseBody
    public Object getIpdnscount() {

        ///获取IP指纹和ISP数据增长
        Map<String, String> mapInterface = ReadPropertiesUtil.ReadProperits("ipinterface.properties");
        String url = String.valueOf(mapInterface.get("ipdnscount"));
        /**
         * 1、请求接口
         */
        String json = null;
        try {
            json = HttpUtil.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 2、解析返回结果
         */
        NutMap nutMap = Json.fromJson(NutMap.class, Lang.inr(json));
        return nutMap;
    }

    @NotAuthPassport
    @RequestMapping("/login")
    public String login() {
        return "home/login";
    }

    @NotAuthPassport
    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse, HttpSession session) {
        ReturnModel rm = new ReturnModel();
        try {
            session.setAttribute("userLogin", null);
            CookieUtil.addCookie(httpResponse, "loginid", null, 0); // 清除Cookie
            CookieUtil.addCookie(httpResponse, "psd", null, 0); // 清除Cookie
            rm.setState(true);
            rm.setMessage("成功推出系统");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("系统异常，请重新尝试");
        }
        return rm;
    }

    @NotAuthPassport
    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object loginSubmit(HttpServletRequest httpRequest, HttpServletResponse httpResponse, HttpSession httpSession)
            throws IOException {
        ReturnModel rm = new ReturnModel();
        String loginid = httpRequest.getParameter("loginid");
        String psd = httpRequest.getParameter("psd");
        String captcha = httpRequest.getParameter("captcha");
        String sessionCaptcha = httpSession.getAttribute("RANDOMVALIDATECODEKEY") != null ?
                httpSession.getAttribute("RANDOMVALIDATECODEKEY").toString() : "";
        if (!captcha.toUpperCase().equals(sessionCaptcha) || sessionCaptcha.isEmpty()) {
            rm.setState(false);
            rm.setMessage("验证码错误！");
        } else {
            if (loginid != null && loginid.trim() != "" && psd != null && psd.trim() != "") {
                //加密
                psd = EncryptUtil.getMD5Encryption(psd);
                UserLogin userLogin = userService.login(loginid, psd);
                if (userLogin.getID() == null) {
                    rm.setState(false);
                    rm.setMessage("用户名或密码错误，请重新登录！");
                } else {
                    int loginMaxAge = 24 * 60 * 60;   //定义账户密码的生命周期,单位为秒
                    CookieUtil.addCookie(httpResponse, "loginid", loginid, loginMaxAge); //将姓名加入到cookie中
                    CookieUtil.addCookie(httpResponse, "psd", psd, loginMaxAge);   //将密码加入到cookie中
                    //将userLogin放到session中
                    httpSession.getId();
                    httpSession.setAttribute("userLogin", userLogin);
                    //获取所有菜单
//                    List<SysModule> modules = getModuleList();
//                    List<String> allModules = new ArrayList<>();
//                    allModules = allModules.stream().distinct().collect(Collectors.toList());
//                    if (modules.size() > 0) {
//                        List<String> finalAllModules = allModules;
//                        modules.forEach(l -> {
//                                    if (!l.getMurl().isEmpty()) {
//                                        finalAllModules.add(l.getMurl());
//                                    }
//                                }
//                        );
//                        httpSession.setAttribute("allModules", allModules);
//                    }

                    //isLogin(httpSession);
                    rm.setState(true);
                    rm.setMessage("登录成功，即将跳转至首页！");
                }
            } else {
                rm.setState(false);
                rm.setMessage("系统异常，请稍后重试。");
            }
        }
        return rm;
    }

}


