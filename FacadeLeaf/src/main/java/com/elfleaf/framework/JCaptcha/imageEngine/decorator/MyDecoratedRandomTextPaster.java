package com.elfleaf.framework.JCaptcha.imageEngine.decorator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.textpaster.ChangeableAttributedString;

/**
 * 文字部分转图片
 */
public class MyDecoratedRandomTextPaster extends AbstractMyTextPaster{

    protected final int kerning = 20;
    private IMyTextDecorator[] decorators;

    /**
     * @param minAcceptedWordLength Max length of a word
     * @param maxAcceptedWordLength Min length of a word
     * @param colorGenerator        Color generatior for the text
     * @param decorators            An array of decorators
     */
    public MyDecoratedRandomTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength,
                                     ColorGenerator colorGenerator, IMyTextDecorator[] decorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
        this.decorators = decorators;

    }

    /**
     * @param minAcceptedWordLength Max length of a word
     * @param maxAcceptedWordLength Min length of a word
     * @param colorGenerator        Color generatior for the text
     * @param decorators            An array of decorators
     */
    public MyDecoratedRandomTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength,
                                     ColorGenerator colorGenerator, Boolean manageColorPerGlyph, IMyTextDecorator[] decorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
        this.decorators = decorators;

    }

    /**
     * Pastes the attributed string on the backround image and return the final image. Implementation must take into
     * account the fact that the text must be readable by human and non by programs
     *
     * @return the final image
     *
     * @throws com.octo.captcha.CaptchaException
     *          if any exception accurs during paste routine.
     */
    public BufferedImage pasteText(BufferedImage background, AttributedString attributedWord)
            throws CaptchaException {
        BufferedImage out = copyBackground(background);
        Graphics2D g2 = pasteBackgroundAndSetTextColor(out, background);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // this attribute doesn't do anything in JDK 1.4, but maybe it will in JDK 1.5
        // attributedString.addAttribute(TextAttribute.WIDTH, TextAttribute.WIDTH_EXTENDED);

        // convert string into a series of glyphs we can work with
        MyChangeableAttributedString newAttrString =  new MyChangeableAttributedString(g2,
                attributedWord, kerning);

        // space out the glyphs with a little kerning
        newAttrString.useMinimumSpacing(kerning);
        // shift string to a random spot in the output imge
        newAttrString.moveToRandomSpot(background);
        // now draw each glyph at the appropriate spot on th eimage.
        if (isManageColorPerGlyph()) {
            newAttrString.drawString(g2, getColorGenerator());
        } else {
            newAttrString.drawString(g2);
        }

        //and now decorate
        if (decorators != null) {
            for (int i = 0; i < decorators.length; i++) {
                decorators[i].decorateAttributedString(g2, attributedWord, newAttrString);

            }
        }
        g2.dispose();
        
        
//        int x = (background.getWidth()) / 20;
//        int y = (background.getHeight()) / 2;
//        BufferedImage out = copyBackground(background);
//        Graphics2D g2 = pasteBackgroundAndSetTextColor(out, background);
//        // pie.drawString(attributedWord.getIterator(), x, y);
//        // pie.dispose();

        
        
        
        
//        // convert string into a series of glyphs we can work with
//        MyChangeableAttributedString newAttrString = new MyChangeableAttributedString(g2, attributedWord, kerning);
//
//        // 字符间距
//        // space out the glyphs with a little kerning
//        newAttrString.useMinimumSpacing(kerning);
//        // newAttrString.useMonospacing(0);
//        // shift string to a random spot in the output imge
//        newAttrString.moveTo(x, y);//《----
//        
//        
//        
//        // now draw each glyph at the appropriate spot on the image.
//        if (isManageColorPerGlyph()) {
//            newAttrString.drawString(g2, getColorGenerator());
//        } else {
//            newAttrString.drawString(g2);
//        }
//
//        //调用绘制干扰纹理
//        // and now decorate
//        if (decorators != null) {
//            for (int i = 0; i < decorators.length; i++) {
//                decorators[i].decorateAttributedString(g2, attributedWord, newAttrString);
//
//            }
//        }
//
//        g2.dispose();
        
        
        
        
        return out;
    }
}
