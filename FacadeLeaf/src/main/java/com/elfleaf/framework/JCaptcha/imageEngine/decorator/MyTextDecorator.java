package com.elfleaf.framework.JCaptcha.imageEngine.decorator;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.security.SecureRandom;
import java.text.AttributedString;
import java.util.Random;

import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.textpaster.ChangeableAttributedString;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;

/**
 * <pre>
 * 自己定义的验证码骚扰遮掩纹理类
 * @author blackcat
 * @version 1.0
 * Date:2014.8.14
 * </pre>
 */
public class MyTextDecorator implements IMyTextDecorator{

    private Random myRandom = new SecureRandom();

    private Integer numberOfLinesPerGlyph = new Integer(3);

    private ColorGenerator linesColorGenerator = null;

    private int alphaCompositeType = 3;

    /**width**/
    private Integer backgourdW = 1;

    /**height**/
    private Integer backgourdH = 1;


    public MyTextDecorator(Integer numberOfLinesPerGlyph, Color linesColor, Integer backgourdW,Integer backgourdH)
    {
        this.numberOfLinesPerGlyph = (numberOfLinesPerGlyph != null ? numberOfLinesPerGlyph : this.numberOfLinesPerGlyph);
        this.linesColorGenerator = new SingleColorGenerator(linesColor != null ? linesColor : Color.white);
        this.backgourdW = backgourdW > 0 ? backgourdW : this.backgourdW;
        this.backgourdH = backgourdH > 0 ? backgourdH : this.backgourdH;
    }



    public void decorateAttributedString(Graphics2D g2, AttributedString attributedWord, MyChangeableAttributedString newAttrString) {
        Color oldColor = g2.getColor();
        Composite oldComp = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(this.alphaCompositeType));

        /*产生随机字符*/
        Random random = new Random(System.currentTimeMillis());
        /*产生干扰噪点,i为噪点数量*/
        for(int i = 0; i < 100; i++)
        {
            /*根据第i个随机字，绘制第i个单字符,16777216是rbg三色连乘最大范围*/
            Color color = new Color(random.nextInt(16777216));
            g2.setColor(color);

            /*在长宽范围内随机画圆，圆半径为1，就相当于圆点了Σ( ° △ °|||)︴ */
            g2.drawOval(random.nextInt(backgourdW), random.nextInt(backgourdH), 1, 1);
        }
        
        
        
//        /*产生干扰弧线*/
//        g2.setStroke(new BasicStroke(5));
//        for(int i = 0; i < 5; i++)
//        {
//            /*根据第i个随机字，绘制第i个单字符,16777216是rbg三色连乘最大范围*/
//            Color color = new Color(random.nextInt(16777216));
//            g2.setColor(color);
//            
//            g2.drawArc(random.nextInt(backgourdW), random.nextInt(backgourdH), 
//                    random.nextInt(backgourdW), random.nextInt(backgourdH), 
//                    random.nextInt(30), random.nextInt(150));
//        }

        for (int j = 0; j < attributedWord.getIterator().getEndIndex(); j++) {
            g2.setColor(this.linesColorGenerator.getNextColor());
            Rectangle2D bounds = newAttrString.getBounds(j).getFrame();

            for (int i = 0; i < this.numberOfLinesPerGlyph.intValue(); i++)
            {
                //double circlex = bounds.getMinX() + bounds.getWidth() * 0.7D * this.myRandom.nextDouble();
                double circlex = bounds.getMinX() + 3 * 0.7D * this.myRandom.nextDouble();
                //double circley = bounds.getMinY() - bounds.getHeight() * 0.5D * this.myRandom.nextDouble();
                double circley = bounds.getMinY() - 3 * 0.5D * this.myRandom.nextDouble();
                
                double width = 5 + this.myRandom.nextInt(25);

                double length = 5 + this.myRandom.nextInt(25);

                double angle = 3.141592653589793D * this.myRandom.nextDouble();

                AffineTransform transformation = new AffineTransform(Math.cos(angle), -Math.sin(angle), Math.sin(angle), Math.cos(angle), circlex, circley);

                QuadCurve2D q = new QuadCurve2D.Double();

                q.setCurve(0.0D, 0.0D, length / 2.0D + 15.0D * this.myRandom.nextDouble() * (this.myRandom.nextBoolean() ? -1 : 1), width / 2.0D + 15.0D * this.myRandom.nextDouble() * (this.myRandom.nextBoolean() ? -1 : 1), length, width);

                g2.setStroke(new BasicStroke(1 + this.myRandom.nextInt(3)));
                g2.draw(transformation.createTransformedShape(q));
            }
        }
        g2.setComposite(oldComp);
        g2.setColor(oldColor);

    }

}
