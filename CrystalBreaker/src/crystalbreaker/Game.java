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
import static java.lang.System.console;
import java.util.ArrayList;

/**
 * Game Class
 *
 * @author Luis Felipe Alvarez Sanchez A01194173 4 Feb 2019
 */
public class Game implements Runnable {

    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private boolean paused;

    private ArrayList<Bar> bars;
    private Player player;
    private Bullet bullet;
    private KeyManager keyManager;

    private boolean gameOver;
    private int score;
    private boolean gameStart;
    
    private boolean won;
    private int enemies;

    /**
     * Game constructor
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
        this.score = 0;
        this.gameStart = false;
        this.won = false;
        this.enemies = 0;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
    
    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void increaseScore() {
        this.score += 10;
    }

    public void setScore(int score) {
        this.score = score;
    }

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
        player = new Player(getWidth() / 2 - 150, getHeight() - 200, 1, 200, 200, this, 3);
        bullet = new Bullet(player.getX() + 70, player.getY() - 80, 64, 64, this);
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
            //Metodo estatico
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
        if (!isGameStart()) {
            //1 to start game
            if (getKeyManager().space) {
                setGameStart(true);
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
                    // Or we could just do this: 
                    // ex.printStackTrace();
                }
                System.out.println("Load");
            }
        }
        //Save
        if (getKeyManager().save && !isGameOver() && isGameStart()) {
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
                    /**
                     * NEEDS TO SAVE SCORE LIVES POSITION AND BLOCKS POSITION
                     */
                    // saves score
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
                    // Or we could just do this:
                    // ex.printStackTrace();
                }
            }
        }

        //Game
        if (!isGameOver() && isGameStart() && !isWon()) {
            //System.out.println(paused);
            //Pierde la bola
            if (bullet.isDead()) {
                //Setea la bola en la posicion inicial
                player.decreasePlayerLive();
                if (player.getLives() <= 0) {
                    setGameOver(true);
                }
                bullet = new Bullet(player.getX() + 70, player.getY() - 80, 64, 64, this);
                //setDeath false
                bullet.setDead(false);
            }
            //Intersecta con el jugador
            if (bullet.intersectaJugador(player)) {
                bullet.changeBulletByPlayerDirection();
                bullet.setHit(false);
            }
            //Intersecta la ball con las barritas
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 4; j++) {
                    if (bullet.intersectaBarra(bars.get(i))) {
                        //Kill the bar
                        this.enemies--;
                        if(this.enemies <= 0){
                            setWon(true);
                        }
                        if (!bullet.isHit()) {
                            increaseScore();
                            bullet.setHit(true);
                            bars.get(i).setHealth(bars.get(i).getHealth() - 1);
                            if (bars.get(i).getHealth() <= 0) {
                                bars.get(i).setDead(true);
                                bars.remove(i);
                            }
                            System.out.println("Bar" + i + " " + bars.get(i).getHealth());
                            /*if(bars.get(i).getHealth() <= 0){
                                    bars.remove(i);
                                } else {
                                    bars.get(i).setHealth(bars.get(i).getHealth() - 1);
                                }*/
                            bullet.changeDirection();
                        }
                    }
                }
            }
            if (!isPaused()) {
                player.tick();
                bullet.tick();
            }
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
     * render method
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
          

            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!bars.get(i).isDead()) {
                        bars.get(i).render(g);
                    }
                }
            }
            if (!isGameStart()) {
                g.drawImage(Assets.howto, 0, 0, getWidth(), getHeight(), null);
            }
            if (isWon()) {
                bars.remove(g);
                 g.drawImage(Assets.won, 0, 0, getWidth(), getHeight(), null);
            }
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
