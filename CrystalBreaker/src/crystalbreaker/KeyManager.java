/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyManager Class
 *
 * @author Luis Felipe Alvarez Sanchez A01194173 4 Feb 2019
 */
public class KeyManager implements KeyListener {

    //Flags to move the player
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean space;
    public boolean pause;
    public boolean enter;
    public boolean save;
    public boolean load;

    private boolean keys[]; // to store all the flags for every key

    /**
     * KeyManager Constructor
     */
    public KeyManager() {
        keys = new boolean[256];
    }
  /**
     * keyPressed method
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }
    /*
    Set Key Down method
    */
    public void setKeyDown(){
          keys[KeyEvent.VK_P] = false;
    }
    /**
     * KeyReleased method
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;

    }

    public boolean getPause() {
        return pause;
    }

    /**
     * setPause method
     *
     * @param pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void keyCheck(int key, boolean checker) {
        keys[key] = checker;
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_P];
        enter = keys[KeyEvent.VK_ENTER];
        save = keys[KeyEvent.VK_S];
        load = keys[KeyEvent.VK_L];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
