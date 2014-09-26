package com.elfleaf.subjects.userCenter.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elfleaf.consistent.CRequest;
import com.elfleaf.framework.consistent.CJSONObject;
import com.elfleaf.subjects.userCenter.cpt.LoginCpt;

/**
 * 用户登录类
 */
@RequestMapping("/userCenter")
@RestController
public class LoginCtrl {

    @Autowired
    private LoginCpt loginCpt;
    
    
    /**
     * 登录页请求方法
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginPage")
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception { 
        /*
         * @STEP 1 获得用户请求跳转到登录页面前的url地址
         * @STEP 2 将用户登录前的url地址放入到session中
         * @STEP 3 返回用户登录页面
         */
        
        //@STEP 1 获得用户请求跳转到登录页面前的url地址
        if (request.getHeader("Referer") != null) {
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                String lastURL = savedRequest.getRequestUrl();
                //@STEP 2 将用户登录前的url地址放入到session中
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                session.setAttribute(CRequest.LAST_REQUEST_URL, lastURL);
            }
        }
        
        
        //@STEP 3 返回用户登录页面
        ModelAndView mav = new ModelAndView("userCenter/loginPage");
        return mav;
        //return "userCenter/loginPage";
    }
    
    /**
     * 执行登录验证请求方法
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doLogin", method=RequestMethod.POST)
    //@ResponseStatus(HttpStatus.OK)
    public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
        
        /*
         * @STEP 1检测验证码是否输入正确
         * @STEP 2获得用户的登录名，登录密码
         * @STEP 3验证登录
         * @STEP 4从SESSION中取出用户登录之前请求的lastUrl
         * @STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
         */
        
        // TODO @STEP 1检测验证码是否输入正确
        String challengeResponse = (String) request.getParameter("challengeResponse");
        boolean isCorrectVerifyCode = loginCpt.validateCaptcha(request, challengeResponse);
        if (!isCorrectVerifyCode) {
            //TODO 返回验证码错误
        }
        
        //@STEP 2获得用户的登录名，登录密码
        String loginName = (String) request.getParameter("loginName");
        String password = (String) request.getParameter("password");
        
        //@STEP 3验证登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        try {  
            //登录，即身份验证  
            subject.login(token);  
        } catch (AuthenticationException e) {  
            throw new Exception("用户名或密码错误");
        }  
        
        //@STEP 4从SESSION中取出用户登录之前请求的lastUrl
        Session session = subject.getSession();
        String lastURL = (String) session.getAttribute(CRequest.LAST_REQUEST_URL);
        
        //@STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
        ModelAndView mav = new ModelAndView();
        if (lastURL != null) {
            //若存在lastUrl那么重定向到lastUrl
            //需要配置shiro的rememberMe 否则session丢失
            mav.setViewName("redirect:" + lastURL);
            return mav;
            //return "redirect:" + lastURL;
        } else {
            //若不存在则返回首页
            mav.setViewName("redirect:index");
            return mav;
            
            //return "redirect:index";
            //return "testSession";
        }
    }
    
    
    /**
     * 执行登录验证请求方法
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doLogin2", method=RequestMethod.POST)
    public JSONObject doLogin2(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
        JSONObject rtnJson = new JSONObject();
        /*
         * @STEP 1检测验证码是否输入正确
         * @STEP 2获得用户的登录名，登录密码
         * @STEP 3验证登录
         * @STEP 4从SESSION中取出用户登录之前请求的lastUrl
         * @STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
         */
        
        // TODO @STEP 1检测验证码是否输入正确
        String challengeResponse = (String) request.getParameter("challengeResponse");
        boolean isCorrectVerifyCode = loginCpt.validateCaptcha(request, challengeResponse);
        if (!isCorrectVerifyCode) {
            //TODO 返回验证码错误
            rtnJson.put(CJSONObject.STATUS_KEY, CJSONObject.STATUS_FAILED);
            rtnJson.put(CJSONObject.MESSAGE_KEY, "验证码错误");
            return rtnJson;
        }
        
        //@STEP 2获得用户的登录名，登录密码
        String loginName = (String) request.getParameter("loginName");
        String password = (String) request.getParameter("password");
        
        //@STEP 3验证登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        try {  
            //登录，即身份验证  
            subject.login(token);
        } catch (AuthenticationException e) {  
            rtnJson.put(CJSONObject.STATUS_KEY, CJSONObject.STATUS_FAILED);
            rtnJson.put(CJSONObject.MESSAGE_KEY, "用户名或密码错误");
            return rtnJson;
        }  
        
        //@STEP 4从SESSION中取出用户登录之前请求的lastUrl
        Session session = subject.getSession();
        String lastURL = (String) session.getAttribute(CRequest.LAST_REQUEST_URL);
        
        rtnJson.put(CJSONObject.STATUS_KEY, CJSONObject.STATUS_SUCCESS);
        rtnJson.put(CJSONObject.MESSAGE_KEY, "登录成功");
        
        
        
        //@STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
        if (lastURL != null) {
            //若存在lastUrl那么重定向到lastUrl
            //需要配置shiro的rememberMe 否则session丢失
            rtnJson.put("lastURL", lastURL);
            return rtnJson;
        } else {
            //若不存在则返回首页
            rtnJson.put("lastURL", "/");
            return rtnJson;
            //return "testSession";
        }
    }
    
}
