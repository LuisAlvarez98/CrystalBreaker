package crystalbreaker;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *  Player class
 * @author Luis Felipe Alvarez Sanchez A01194173
 *  4 Feb 2019
 */
public class Player extends Item{
    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed;
    private int movement;
    
    private boolean collided;    
    private int counter;
    
    public boolean dir[] ={false,false,false,false};
    /**
     * Player constructor
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 1;
        this.collided = false;
        this.counter = 0;
        this.movement = 1;
    }
    /**
     * setSpeed method
     * @param speed 
     */
      public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * getSpeed method
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * getMovement method
     * @return movement
     */
    public int getMovement() {
        return movement;
    }
    /**
     * setMovement method
     * @param movement 
     */
    public void setMovement(int movement) {
        this.movement = movement;
    }

    /**
     * setCollided method
     * @param collided 
     */
    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    /**
     * getDirection method
     * @return direction 
     */
    public int getDirection() {
        return direction;
    }
    /**
     * setDirection method
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
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
       
            //Left Movement
            if(game.getKeyManager().left){
                setSpeed(getSpeed()-1);
            }
            //Right movement
            if(game.getKeyManager().right){
                   setSpeed(getSpeed()+1); 
            }
           
         //Colissions
      if(getX() + 60 >= game.getWidth()){
          setX(game.getWidth() - 60);
          setMovement(3);
          setCollided(true);
      }
      else if(getX() <= -30){
           setX(-30);
           setMovement(4);
           setCollided(true);
      }
      if(getY() + 100 >= game.getHeight()){
          setY(game.getHeight() - 100);
           setMovement(1);
           setCollided(true);
      }
      else if(getY() <= -30){
          setY(-30);
          setMovement(2);
          setCollided(true);
      }
    }
    /**
     * Renders the player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.hank,getX(), getY(), getWidth(), getHeight(), null);
    }
}
