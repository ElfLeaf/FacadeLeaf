package com.elfleaf.framework.JCaptcha;

import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class MyImageCaptchaEngine extends ListImageCaptchaEngine {
    protected void buildInitialFactories() {
        WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
                new int[] {80, 150},
                new int[] {30, 120},
                new int[] {0, 100});
        //TextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), cgen, true);

        Integer backgourndWidth = 100;
        Integer backgroundHeight = 50;
        
        MyTextDecorator myTextDecorator = new MyTextDecorator(1, Color.RED, backgourndWidth, backgroundHeight);  
        // LineTextDecorator line_decorator2 = new LineTextDecorator(1,  
        // Color.blue);  
        TextDecorator[] textdecorators = new TextDecorator[1];  

        textdecorators[0] = myTextDecorator;  
        // textdecorators[1] = line_decorator2;  

        TextPaster textPaster = new DecoratedRandomTextPaster(  
                new Integer(4), new Integer(4), cgen, true,
                textdecorators);  




        BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(backgourndWidth, backgroundHeight);

        Font[] fontsList = new Font[] {
                new Font("Arial", 0, 10),
                new Font("Tahoma", 0, 10),
                new Font("Verdana", 0, 10),
        };

        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20), new Integer(35), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }

}
