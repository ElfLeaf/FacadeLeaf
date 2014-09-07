package com.elfleaf.framework.JCaptcha.svc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * </pre>
 * 检测验证码是否正确的服务类
 * </pre>
 */
@Service
public class ValidateCaptchaService {
//extends DefaultManageableImageCaptchaService{
    /**
     * 验证验证码是否正确
     * @param request http请求
     * @param challengeResponse 输入的验证码字母
     * @return
     */
    public boolean validateCaptchaChallenge(final HttpServletRequest request, String challengeResponse) {
        //验证码检测正确性
        try {
            // 获取产生验证码的id,用session的id来产生验证码
            String captchaID = request.getSession().getId();
            // 获取输入的验证码
            return CaptchaService.getInstance().hasCaptcha(captchaID, challengeResponse);
        } catch (CaptchaServiceException e) {
            System.out.println(e);
            return false;
        }
    }

}
