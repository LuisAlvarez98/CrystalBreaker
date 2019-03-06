package crystalbreaker;

import java.awt.image.BufferedImage;

/**
 * Assets Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Assets {
    public static BufferedImage hank, background, bar, bar0, bar1, bar2, bullet, gameover;
    /**
     * loads the assets
     */
    public static void init(){
        hank = ImageLoader.loadImage("/images/hank.png");
        background = ImageLoader.loadImage("/images/bg.jpg");
        bar = ImageLoader.loadImage("/images/bar.png");
        bar2 = ImageLoader.loadImage("/images/bar_2.png");
        bar1 = ImageLoader.loadImage("/images/bar_1.png");
        bar0 = ImageLoader.loadImage("/images/bar_0.png");
        bullet = ImageLoader.loadImage("/images/bullet.png");
        gameover = ImageLoader.loadImage("/images/gameover.png");
    }
    
}
