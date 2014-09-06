package com.elfleaf.framework.JCaptcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <pre>
 * 请求生成验证码图片的servlet
 * @author blackcat
 * </pre>
 */
@SuppressWarnings("serial")
public class ImageCaptchaServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(ImageCaptchaServlet.class);
    
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }


    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //TODO 存储请求的jsessionId
        //CaptchaService.getInstance().
        
        
        //生成验证码
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String captchaId = httpServletRequest.getSession().getId();
            BufferedImage challenge = CaptchaService.getInstance().getImageChallengeForID(captchaId,httpServletRequest.getLocale());
            
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);

            jpegEncoder.encode(challenge);

        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            logger.error(e);
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        //设置response，输出图片客户端不缓存
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }
    
    
}