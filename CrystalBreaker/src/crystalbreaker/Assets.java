package crystalbreaker;

import java.awt.image.BufferedImage;

/**
 * Assets Class
 * @author Luis Felipe Alvarez Sanchez and Genaro
 * 4 Feb 2019
 */
public class Assets {
    public static SoundClip intro, explosion;
    public static BufferedImage hank, background, bar, bar0, bar1, bar2, bullet, bullet_destroyed, gameover, howto, won, flask;
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
        bullet_destroyed = ImageLoader.loadImage("/images/bullet_explosion.png");
        gameover = ImageLoader.loadImage("/images/gameover.png");
        howto = ImageLoader.loadImage("/images/howto.png");
        won = ImageLoader.loadImage("/images/won.png");
        intro = new SoundClip("/sounds/intro.wav");
        explosion = new SoundClip("/sounds/explosion.wav");
        flask = ImageLoader.loadImage("/images/flask.png");
    }
    
}
