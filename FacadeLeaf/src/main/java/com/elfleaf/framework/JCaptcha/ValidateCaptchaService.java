package com.elfleaf.framework.JCaptcha;

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
    /**
     * 验证验证码
     * 
     * @param challengeResponse
     *            输入的验证码字母
     * @return
     */
    public boolean validateCaptchaChallenge(final HttpServletRequest request, String challengeResponse) {
        //TODO 完整验证码检测正确性的代码
        try {
            // 获取产生验证码的id,用session的id来产生验证码
            String captchaID = request.getSession().getId();
            // 获取输入的验证码
            // String challengeResponse =
            // request.getParameter(captchaParamterName);
            return CaptchaService.getInstance().validateResponseForID(captchaID, challengeResponse);
        } catch (CaptchaServiceException e) {
            System.out.println(e);
            return false;
        }
    }

}
