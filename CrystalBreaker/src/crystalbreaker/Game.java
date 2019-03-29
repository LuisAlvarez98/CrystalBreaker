package crystalbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Game Class Crystal Braker Game
 *
 * @author Luis Felipe Alvarez Sanchez and Genaro
 */
public class Game implements Runnable {

    private BufferStrategy bs; // BufferStrategy var
    private Graphics g; // for the graphics
    private Display display; // for the display of the game
    String title; // the title of the game
    private int width; // the width of the game
    private int height; //the height of the game
    private Thread thread; //the thread of the game
    private boolean running; //boolean saying if it is running
    private boolean paused; // paused boolean

    private ArrayList<Bar> bars; //blocks array list
    private Player player; //player instance
    private Bullet bullet; //bullet instance
    private KeyManager keyManager; //key manager

    private boolean gameOver; //gameover boolean
    private int score; //score of the game
    private boolean gameStart; //gamestart boolean

    private boolean won; // did you win?
    private int enemies; // number of enemies

    private ArrayList<PowerUp> powerups;

    /**
     * Game Constructor
     *
     * @param title
     * @param width
     * @param height
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        paused = false;
        keyManager = new KeyManager();
        bars = new ArrayList<Bar>();
        powerups = new ArrayList<PowerUp>();
        this.score = 0;
        this.gameStart = false;
        this.won = false;
        this.enemies = 0;
    }

    /**
     * setGameStart method
     *
     * @param gameStart
     */
    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    /**
     * isPaused method
     *
     * @return paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * setPaused method
     *
     * @param paused
     */
    public void setPaused() {
        this.paused = !paused;
    }
    
    /**
     * isWon
     *
     * @return won
     */
    public boolean isWon() {
        return won;
    }

    /**
     * setWon method
     *
     * @param won
     */
    public void setWon(boolean won) {
        this.won = won;
    }

    /**
     * isGameStart method
     *
     * @return gameStart
     */
    public boolean isGameStart() {
        return gameStart;
    }

    /**
     * setGameOver method
     *
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * isGameOver method
     *
     * @return gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * IncreaseScore method increases score by 10
     */
    public void increaseScore() {
        this.score += 10;
    }

    /**
     * setScore method
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * getScore method
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * getHeight method
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getWidth method
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * initBlocks method inits the blocks position and size on the game
     */
    public void initBlocks() {
        bars = new ArrayList<Bar>();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 4; j++) {
                bars.add(new Bar(10 + i * 120, 10 + j * 60, 100, 100, this));
            }
        }
        this.enemies = bars.size();
    }

    /**
     * inits the game with the display and player
     */
    public void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        Assets.intro.setLooping(true);
        Assets.intro.play();
        player = new Player(getWidth() / 2 - 150, getHeight() - 200, 1, 200, 200, this, 3);
        bullet = new Bullet(player.getX() + 70, player.getY() - 80, 64, 64, this);
        //inits blocks
        initBlocks();
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
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * getKeyManager method
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * tick method
     */
    private void tick() {
        keyManager.tick();
        //Gamestart
        System.out.println(isPaused());
        if (!isGameStart()) {
            //if space is clicked it starts the game
            if (getKeyManager().space) {
                setGameStart(true);
                //or you can load the game by clickin L
            } else if (getKeyManager().load) {
                //setGameStart(true);
                //load game
                // The name of the file to open.
                String fileName = "gamesave.txt";

                // This will reference one line at a time
                String line = null;

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader
                            = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader
                            = new BufferedReader(fileReader);
                    //reads everything from the file
                    setScore(Integer.parseInt(bufferedReader.readLine()));
                    player.setLives(Integer.parseInt(bufferedReader.readLine()));
                    player.setX(Integer.parseInt(bufferedReader.readLine()));
                    player.setY(Integer.parseInt(bufferedReader.readLine()));
                    bullet.setX(Integer.parseInt(bufferedReader.readLine()));
                    bullet.setY(Integer.parseInt(bufferedReader.readLine()));
                    bullet.setSpeedX(Integer.parseInt(bufferedReader.readLine()));
                    bullet.setSpeedY(Integer.parseInt(bufferedReader.readLine()));
                    for (int i = 0; i < 40; i++) {
                        for (int j = 0; j < 4; j++) {
                            Bar bar = bars.get(i);
                            bar.setX(Integer.parseInt(bufferedReader.readLine()));
                            bar.setY(Integer.parseInt(bufferedReader.readLine()));
                            bar.setHealth(Integer.parseInt(bufferedReader.readLine()));
                            int dead = Integer.parseInt(bufferedReader.readLine());
                            //if dead is true
                            bar.setDead(dead == 1);
                        }
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file '"
                            + fileName + "'");
                } catch (IOException ex) {
                    System.out.println(
                            "Error reading file '"
                            + fileName + "'");
                }
            }
        }
        
        
        //When you click S you save the current instance of the game
        if (getKeyManager().save && !isGameOver() && isGameStart()) {
            //counter used to avoid multiple clicks
            int count = 0;
            if (count == 0) {
                count++;
                // The name of the file to open.
                String fileName = "gamesave.txt";

                try {
                    // Assume default encoding.
                    FileWriter fileWriter
                            = new FileWriter(fileName);

                    // Always wrap FileWriter in BufferedWriter.
                    BufferedWriter bufferedWriter
                            = new BufferedWriter(fileWriter);

                    // saves everything on the textfile
                    bufferedWriter.write(Integer.toString(getScore()) + '\n');
                    bufferedWriter.write(Integer.toString(player.getLives()) + '\n');
                    bufferedWriter.write(Integer.toString(player.getX()) + '\n');
                    bufferedWriter.write(Integer.toString(player.getY()) + '\n');
                    bufferedWriter.write(Integer.toString(bullet.getX()) + '\n');
                    bufferedWriter.write(Integer.toString(bullet.getY()) + '\n');
                    bufferedWriter.write(Integer.toString(bullet.getSpeedX()) + '\n');
                    bufferedWriter.write(Integer.toString(bullet.getSpeedY()) + '\n');

                    for (int i = 0; i < 40; i++) {
                        for (int j = 0; j < 4; j++) {
                            Bar bar = bars.get(i);
                            bufferedWriter.write(Integer.toString(bar.getX()) + '\n');
                            bufferedWriter.write(Integer.toString(bar.getY()) + '\n');
                            bufferedWriter.write(Integer.toString(bar.getHealth()) + '\n');
                            int dead = (bar.isDead() ? 1 : 0);
                            bufferedWriter.write(Integer.toString(dead) + '\n');
                        }
                    }
                    // Always close files.
                    bufferedWriter.close();
                } catch (IOException ex) {
                    System.out.println(
                            "Error writing to file '"
                            + fileName + "'");
                }
            }
        }

        //Game instance itself
        if (!isGameOver() && isGameStart() && !isWon()) {
             if (getKeyManager().pause) {
                getKeyManager().setKeyDown();
                paused = !paused;
            }
            if (!paused) {
            //Bullet is dead
            if (bullet.isDead()) {
                //sets the bullet on the initial position and decreases a life
                player.decreasePlayerLive();
                if (player.getLives() <= 0) {
                    //if lives <= 0 then bye bye
                    setGameOver(true);
                }
                bullet = new Bullet(player.getX() + 70, player.getY() - 80, 64, 64, this);
                //setDeath false
                bullet.setDead(false);
            }
            //Intersection between bullet and player
            if (bullet.intersectaJugador(player)) {
                bullet.changeBulletByPlayerDirection();
                bullet.setHit(false);
            }
            //Intersect between bullet and blocks
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 4; j++) {
                    if (bullet.intersectaBarra(bars.get(i))) {
                        //Kill the bar
                     
                        if (!bullet.isHit()) {
                            increaseScore();
                            bullet.setHit(true);
                            bars.get(i).setHealth(bars.get(i).getHealth() - 1);
                            Assets.explosion.play();
                            if (bars.get(i).getHealth() <= 0) {
                                //Random powerups when block destroyed
                                int randNum = (int) (Math.random() * 10 + 1);
                                if (randNum % 2 == 0) {
                                    PowerUp powerup = new PowerUp(bars.get(i).getX(), bars.get(i).getY(), 50, 50, this);
                                    powerups.add(powerup);
                                }
                                bars.get(i).setDead(true);
                                bars.remove(i);
                            }
                            //System.out.println("Bar" + i + " " + bars.get(i).getHealth());
                            bullet.changeDirection();
                        }
                           this.enemies--;
                        if (this.enemies <= 0 || bars.size() <= 0) {
                            setWon(true);
                        }
                    }
                }
            }
            //if is not paused game runs normally
                player.tick();
                bullet.tick();
                for (int i = 0; i < powerups.size(); i++) {
                    powerups.get(i).tick();
                    if (powerups.get(i).intersecta(player)) {
                        increaseScore();
                        powerups.remove(i);
                    }else if(powerups.get(i).isDead()){
                        powerups.remove(i);
                    }
                }
            }
            //if game is over and you hit enter then it resets the game
        } else if (getKeyManager().enter) {
            //init everything
            setGameOver(false);
            //Resets lives and score
            player.setLives(3);
            setScore(0);
            //RESETS PLAYER, BULLET AND BLOCKS
            player = new Player(getWidth() / 2 - 150, getHeight() - 200, 1, 200, 200, this, 3);
            bullet = new Bullet(player.getX() + 70, player.getY() - 60, 64, 64, this);
            initBlocks();
        }

    }

    /**
     * render method where all the magic happens
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);

            g.setColor(Color.WHITE);
            g.drawString("Score: " + getScore(), 10, getHeight() - 10);
            g.setColor(Color.WHITE);
            g.drawString("Lives: " + player.getLives(), getWidth() - 80, getHeight() - 10);
            player.render(g);
            bullet.render(g);
            //renders the blocks
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!bars.get(i).isDead()) {
                        bars.get(i).render(g);
                    }
                }
            }
            for (int i = 0; i < powerups.size(); i++) {
                powerups.get(i).render(g);
            }
            //if the game hasnt started it displays the howto
            if (!isGameStart()) {
                g.drawImage(Assets.howto, 0, 0, getWidth(), getHeight(), null);
            }
            //if player won it displays the won image
            if (isWon()) {
                g.drawImage(Assets.won, 0, 0, getWidth(), getHeight(), null);
            }
            //if player loses all his lives then gameover
            if (isGameOver()) {
                bars.remove(g);
                g.drawImage(Assets.gameover, 0, 0, getWidth(), getHeight(), null);
            }
            bs.show();
            g.dispose();
        }
    }

    /**
     * start method
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stop method
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
