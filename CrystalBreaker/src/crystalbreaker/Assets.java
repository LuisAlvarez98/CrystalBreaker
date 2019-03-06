package crystalbreaker;

import java.awt.image.BufferedImage;

/**
 * Assets Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Assets {
    public static BufferedImage hank, background, bar, bullet, gameover;
    /**
     * loads the assets
     */
    public static void init(){
        hank = ImageLoader.loadImage("/images/hank.png");
        background = ImageLoader.loadImage("/images/bg.jpg");
        bar = ImageLoader.loadImage("/images/bar.png");
        bullet = ImageLoader.loadImage("/images/bullet.png");
        gameover = ImageLoader.loadImage("/images/gameover.png");
    }
    
}
