package com.elfleaf.framework.JCaptcha.imageEngine.decorator;

import java.awt.Graphics2D;
import java.text.AttributedString;

public interface IMyTextDecorator {
    void decorateAttributedString(Graphics2D g2, AttributedString attributedWord, MyChangeableAttributedString newAttrString);
}
