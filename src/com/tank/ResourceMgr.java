package com.tank;

import test.ImageTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage tankl,tankr,tanku,tankd;
    public static BufferedImage bulletl,bulletr,bulletu,bulletd;
    public static BufferedImage[] explodes = new BufferedImage[16];
    public static Audio expoldeAudio = new Audio("audio/explode.wav");
    static {
        try{

            tanku = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            tankl = ImageUtil.rotateImage(tanku, -90);
            tankr = ImageUtil.rotateImage(tanku, 90);
            tankd = ImageUtil.rotateImage(tanku, 180);

            bulletu = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletl = ImageUtil.rotateImage(bulletu, -90);
            bulletr = ImageUtil.rotateImage(bulletu, 90);
            bulletd = ImageUtil.rotateImage(bulletu, 180);

            for(int i=1;i<=16;i++) {
                explodes[i-1] = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/e" +i+".gif"));
            }
        }
        catch (IOException e) {

        }
    }
}
