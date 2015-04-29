package com.micro.rent.common.comm.captcha.backgroundGenerator;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomGradientBackGroundGenerator extends
        CustomAbstractBackgroundGenerator {

    protected ColorGenerator firstColor;
    protected ColorGenerator secondColor;

    public CustomGradientBackGroundGenerator(Integer width, Integer height,
                                             Color firstColor, Color secondColor) {
        super(width, height);
        this.firstColor = null;
        this.secondColor = null;
        if (firstColor == null || secondColor == null) {
            throw new CaptchaException("Color is null");
        } else {
            this.firstColor = new SingleColorGenerator(firstColor);
            this.secondColor = new SingleColorGenerator(secondColor);
            return;
        }
    }

    public CustomGradientBackGroundGenerator(Integer width, Integer height,
                                             ColorGenerator firstColorGenerator,
                                             ColorGenerator secondColorGenerator) {
        super(width, height);
        firstColor = null;
        secondColor = null;
        if (firstColorGenerator == null || secondColorGenerator == null) {
            throw new CaptchaException("ColorGenerator is null");
        } else {
            firstColor = firstColorGenerator;
            secondColor = secondColorGenerator;
            return;
        }
    }

    public BufferedImage getBackground() {
        BufferedImage bi = new BufferedImage(getImageWidth(), getImageHeight(),
                1);
        Graphics2D pie = (Graphics2D) bi.getGraphics();
        pie.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0.0F, 0.0F,
                firstColor.getNextColor(), 0.0F, getImageHeight(),
                secondColor.getNextColor());
        pie.setPaint(gp);
        pie.fillRect(0, 0, getImageWidth(), getImageHeight());
        pie.dispose();
        return bi;
    }
}
