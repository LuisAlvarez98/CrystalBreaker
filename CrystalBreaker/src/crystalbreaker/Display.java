package crystalbreaker;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Display Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Display {
    private JFrame jframe;
    private Canvas canvas;
    private String title;
    private int width;
    private int height;
    /**
     * Display Constructor
     * @param title
     * @param width
     * @param height 
     */
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    /**
     *  getJframe method
     * @return jframe
     */
    public JFrame getJframe() {
        return jframe;
    }
    /**
     * setJframe method
     * @param jframe 
     */
    public void setJframe(JFrame jframe) {
        this.jframe = jframe;
    }
    /**
     * getCanvas method
     * @return canvas
     */
    public Canvas getCanvas(){
        return canvas;
    }
    /**
     * createDisplay method
     */
    public void createDisplay(){
        jframe = new JFrame(title);
        
        jframe.setSize(width, height);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        jframe.add(canvas);
        jframe.pack();
    }
    
}
