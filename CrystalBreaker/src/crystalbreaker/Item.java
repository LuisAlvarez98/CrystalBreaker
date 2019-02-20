package crystalbreaker;

import java.awt.Graphics;

/**
 * Item Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public abstract class Item {
    protected int x;
    protected int y;
    /**
     * Item constructor
     * @param x
     * @param y 
     */
    public Item(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * abstract method tick
     */
    public abstract void tick();
    /**
     * abstract method render
     * @param g 
     */
    public abstract void render(Graphics g);
    /**
     * getX method
     * @return 
     */
    public int getX() {
        return x;
    }
    /**
     * getY merthod
     * @return 
     */
    public int getY() {
        return y;
    }
    /**
     * setX method
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * setY method
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
