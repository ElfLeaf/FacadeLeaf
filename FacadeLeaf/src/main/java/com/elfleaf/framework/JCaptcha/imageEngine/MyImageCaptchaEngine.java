package com.elfleaf.framework.JCaptcha.imageEngine;

import java.awt.Color;
import java.awt.Font;

import com.elfleaf.framework.JCaptcha.imageEngine.decorator.IMyTextDecorator;
import com.elfleaf.framework.JCaptcha.imageEngine.decorator.MyDecoratedRandomTextPaster;
import com.elfleaf.framework.JCaptcha.imageEngine.decorator.MyTextDecorator;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * <pre>
 * 自定义类生成图片的形式，文字颜色大小
 * @author blackcat
 * </pre>
 */
public class MyImageCaptchaEngine extends ListImageCaptchaEngine {
    
    protected void buildInitialFactories() {
        WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
                new int[] {80, 150},
                new int[] {30, 120},
                new int[] {0, 100});
        //TextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), cgen, true);

        //验证码宽度
        Integer backgourndWidth = 100;
        //验证码高度
        Integer backgroundHeight = 22;
        
        MyTextDecorator myTextDecorator = new MyTextDecorator(1, Color.RED, backgourndWidth, backgroundHeight);  
        // LineTextDecorator line_decorator2 = new LineTextDecorator(1,  
        // Color.blue);  
        IMyTextDecorator[] textdecorators = new IMyTextDecorator[1];  

        textdecorators[0] = myTextDecorator;  
        // textdecorators[1] = line_decorator2;  

        TextPaster textPaster = new MyDecoratedRandomTextPaster(  
                new Integer(4), new Integer(4), cgen, true,
                textdecorators);  

        BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(backgourndWidth, backgroundHeight);

        Font[] fontsList = new Font[] {
                new Font("Arial", 0, 18),
                new Font("Tahoma", 0, 18),
                new Font("Verdana", 0, 18),
        };

        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(18), new Integer(18), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }

}
