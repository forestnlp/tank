package com.tank;

import test.ImageTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static BufferedImage tankl,tankr,tanku,tankd;

    static {
        try{
             tankl = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
             tankr = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
             tanku = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
             tankd = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

        }
        catch (IOException e) {

        }
    }
}
