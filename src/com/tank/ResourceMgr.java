package com.tank;

import test.ImageTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage tankl,tankr,tanku,tankd;
    public static BufferedImage bulletl,bulletr,bulletu,bulletd;

    static {
        try{
            tankl = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankr = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tanku = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankd = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

            bulletl = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletr = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletu = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletd = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

        }
        catch (IOException e) {

        }
    }
}
