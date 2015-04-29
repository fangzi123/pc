package com.micro.rent.common.comm.captcha.backgroundGenerator;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;

import java.security.SecureRandom;
import java.util.Random;

public abstract class CustomAbstractBackgroundGenerator implements
        BackgroundGenerator {

    Random myRandom;
    private int height;
    private int width;

    protected CustomAbstractBackgroundGenerator(Integer width, Integer height) {
        this.height = 100;
        this.width = 200;
        myRandom = new SecureRandom();
        this.width = width == null ? this.width : width.intValue();
        this.height = height == null ? this.height : height.intValue();
    }

    public int getImageHeight() {
        return height;
    }

    public int getImageWidth() {
        return width;
    }
}
