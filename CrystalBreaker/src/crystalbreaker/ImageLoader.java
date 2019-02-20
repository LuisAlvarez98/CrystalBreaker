package crystalbreaker;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * ImageLoader Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class ImageLoader {
    /**
     * Image loader
     * @param path
     * @return bufferedImage
     */
  public static BufferedImage loadImage(String path) {
    BufferedImage bi = null;
    try {
        bi = ImageIO.read(ImageLoader.class.getResource(path));
    } catch (IOException ioe) {
        System.out.println("Error loading image " + path + ioe.toString());
        System.exit(1);
    }
        return bi;
    }
    
}
