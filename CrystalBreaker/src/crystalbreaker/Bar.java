package crystalbreaker;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *  Player class
 * @author Luis Felipe Alvarez Sanchez A01194173
 *  4 Feb 2019
 */
public class Bar extends Item{
    private int width;
    private int height;
    private Game game;
    
    /**
     * Player constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     */
    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
    }
    /**
     * getHeight method
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * getWidth method
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * setHeight method
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * setWidth method
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * tick method
     * The overall movement of the player
     */
    @Override
    public void tick() {   
        
    }
     //Collisions
    /**
     * getPerimetro method
     *
     * @return rectangle
     */
    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    /**
     * Renders the player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.bar,getX(), getY(), getWidth(), getHeight(), null);
    }
}
