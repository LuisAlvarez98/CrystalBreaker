package crystalbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Game Class
 * @author Luis Felipe Alvarez Sanchez A01194173
 * 4 Feb 2019
 */
public class Game implements Runnable{
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    
    private int x;
    private int direction;
    private ArrayList<Bar> bars;
    private KeyManager keyManager;
    
    /**
     * Game constructor
     * @param title
     * @param width
     * @param height 
     */
    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        bars = new ArrayList<Bar>();
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
     * inits the game with the display and player
     */
    public void init(){
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        for(int i = 0; i < 40; i++){
            for(int j = 0 ;j < 4; j++){
                bars.add(new Bar(10 + i*120 ,10 + j *60 ,100,100,this));
            }
        }
        display.getJframe().addKeyListener(keyManager);
    }
    /**
     * run method
     */
    @Override
    public void run() {
        init();
        int fps = 50;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(running){
            //Metodo statico
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;
            
            if(delta >= 1){
                tick();
                render();
                delta--; 
            }
        }
        stop();
    }
    /**
     * getKeyManager method
     * @return keyManager
     */
     public KeyManager getKeyManager() {
        return keyManager;
    }
    /**
     * tick method
     */
    private void tick(){
       keyManager.tick();
    }
    /**
     * render method
     */
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
        }else{
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background,0,0,width,height,null);
              for(int i = 0; i < 40; i++){
            for(int j = 0 ;j < 4; j++){
                     bars.get(i).render(g);
            }
        }
            bs.show();
            g.dispose();
        }
    }
    /**
     * start method
     */
    public synchronized void start(){
        if(!running){
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    /**
     * stop method
     */
    public synchronized void stop(){
        if(running){
            running = false;
            try{
                thread.join();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}
