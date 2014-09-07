package com.elfleaf.framework.JCaptcha.svc;

import com.elfleaf.framework.JCaptcha.imageEngine.MyImageCaptchaEngine;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * <pre>
 * 生成验证码图片引擎的单例
 * </pre>
 */
public class CaptchaService extends AbstractManageableImageCaptchaService implements ImageCaptchaService {

    private static CaptchaService instance = null;
   
    protected CaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
            int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }

    public static CaptchaService getInstance() {
        if (instance == null) {
            // use customized engine
            instance = new CaptchaService(
                    new FastHashMapCaptchaStore(),
                    new MyImageCaptchaEngine(),
                    180,
                    100000,
                    75000);
        }
        return instance;
    }
    
    /**
     * 检验输入验证码
     * @param id
     * @param challengeResponse
     * @return
     */
    public boolean hasCaptcha(String id, String challengeResponse) {  
        return store.getCaptcha(id).validateResponse(challengeResponse);  
    }
   
}
