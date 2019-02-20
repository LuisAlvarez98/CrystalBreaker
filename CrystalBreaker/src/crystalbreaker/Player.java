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
            //Up movement
            if(game.getKeyManager().up){
                 game.getKeyManager().keyCheck(KeyEvent.VK_UP, false);
                 setMovement(1);
               if(!dir[0]){
                     setSpeed(getSpeed()-1);
                      
                      if(getSpeed() == 0){
                        setSpeed(1);
                        dir[0] = true;
                       }
                }else{
                   setSpeed(getSpeed()+1); 
                }
                 //sets the other directions to false
                 dir[1] = false;
                 dir[2] = false;
                 dir[3] = false;
            }
            //Down movement
            if(game.getKeyManager().down){
                game.getKeyManager().keyCheck(KeyEvent.VK_DOWN, false);
                setMovement(2);
               if(!dir[1]){
                     setSpeed(getSpeed()-1);
                      
                      if(getSpeed() == 0){
                        setSpeed(1);
                        dir[1] = true;
                       }
                }else{
                   setSpeed(getSpeed()+1); 
                }
                //sets the other directions to false
                 dir[0] = false;
                 dir[2] = false;
                 dir[3] = false;
            }
            //Left Movement
            if(game.getKeyManager().left){
                game.getKeyManager().keyCheck(KeyEvent.VK_LEFT, false);
                setMovement(3);
                if(!dir[2]){
                     setSpeed(getSpeed()-1);
                      
                      if(getSpeed() == 0){
                        setSpeed(1);
                        dir[2] = true;
                       }
                }else{
                   setSpeed(getSpeed()+1); 
                }
                //sets the other directions to false
                 dir[0] = false;
                 dir[1] = false;
                 dir[3] = false;
            }
            //Right movement
            if(game.getKeyManager().right){
                game.getKeyManager().keyCheck(KeyEvent.VK_RIGHT, false);
                setMovement(4);
                if(!dir[3]){
                     setSpeed(getSpeed()-1);
                      
                      if(getSpeed() == 0){
                        setSpeed(1);
                        dir[3] = true;
                       }
                }else{
                   setSpeed(getSpeed()+1); 
                }
                //sets the other directions to false
                 dir[0] = false;
                 dir[1] = false;
                 dir[2] = false;   

            }
           
         setSpeed(getSpeed());
         //Movement decider
         switch(getMovement()){
             case 1:
                  setY(getY() - getSpeed());
                 break;
             case 2:
                 setY(getY() + getSpeed());
                 break;
             case 3:
                  setX(getX() - getSpeed());
                 break;
             case 4:
                 setX(getX() + getSpeed());
                 break;
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
        counter++;
        
        //Checks if player has collided
        if(collided){
            g.drawImage(Assets.planetTwo,getX(), getY(), getWidth(), getHeight(), null);
        }else{
            g.drawImage(Assets.planetOne,getX(), getY(), getWidth(), getHeight(), null);
        }
        if(counter > 260 && collided){
            setCollided(false);
            counter=0;
        }
    }
}
