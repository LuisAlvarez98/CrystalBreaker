package crystalbreaker;

import java.awt.image.BufferedImage;

/**
 * Assets Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Assets {
    public static BufferedImage planetOne, planetTwo, background;
    /**
     * loads the assets
     */
    public static void init(){
        background = ImageLoader.loadImage("/images/bg.png");
        planetOne = ImageLoader.loadImage("/images/sprite1.png");
    }
    
}
