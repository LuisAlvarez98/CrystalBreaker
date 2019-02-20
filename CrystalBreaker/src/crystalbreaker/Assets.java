package crystalbreaker;

import java.awt.image.BufferedImage;

/**
 * Assets Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Assets {
    public static BufferedImage planetOne, planetTwo, background, bar;
    /**
     * loads the assets
     */
    public static void init(){
        background = ImageLoader.loadImage("/images/bg.jpg");
        bar = ImageLoader.loadImage("/images/bar.png");
    }
    
}
