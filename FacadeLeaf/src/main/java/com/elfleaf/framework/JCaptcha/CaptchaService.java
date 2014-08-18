package com.elfleaf.framework.JCaptcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;


public class CaptchaService extends AbstractManageableImageCaptchaService implements ImageCaptchaService {

    private static ImageCaptchaService instance = null;
   
    protected CaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
            int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }

    public static ImageCaptchaService getInstance() {
        if (instance == null) {
            // use customized engine
            instance = new DefaultManageableImageCaptchaService(
                    new FastHashMapCaptchaStore(),
                    new MyImageCaptchaEngine(),
                    180,
                    100000,
                    75000);
        }
        return instance;
    }

   
}
